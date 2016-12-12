package express.businessLogic.repoBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.dataService.repoDataService.RepoDataService;
import express.po.Area;
import express.po.RepoInfoPO;
import express.po.RepoPosition;
import express.rmi.RMIClient;
import express.vo.RepoPositionVO;

public class AdjustRepo {

	RepoDataService repo;

	public AdjustRepo() {
		repo = RMIClient.getRepoInfoObject();
	}

	public boolean checkRepoBlockUsed(String orgID, RepoPosition position) {

		try {
			RepoInfoPO r = repo.getRepo(orgID);

			ArrayList<RepoPosition> list = r.getRepoPosition();
			if (list == null)
				return false;
			if (list.size() == 0)
				return false;

			for (RepoPosition p : list) {
				if (p.getblock().equals(position.getblock())&&
						p.getrow() == position.getrow()&&
						p.getshelf() == position.getshelf()&&
						p.getposition() == position.getposition()) {
					return true;
				}
			}
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean setRepoBlock(String orgID, RepoPosition position) {
		try {
			position.setIsUsed(true);
			return repo.addBlock(orgID, position);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean freeRepoBlock(String orgID, RepoPosition position) {
		try {
			position.setIsUsed(true);
			return repo.deleteBlock(orgID, position);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean alarm(String orgID, RepoPosition position) {
		try {
			RepoInfoPO r = repo.getRepo(orgID);
			if (repo == null)
				return false;

			int curr = 0;
			int max = 0;
			if (position.getblock().equals(Area.AIR)) {
				curr = r.getAirSum();
				max = r.getAirSize();
			} else if (position.getblock().equals(Area.TRAIN)) {
				curr = r.getTrainSum();
				max = r.getTrainSize();
			} else if (position.getblock().equals(Area.TRAIN)) {
				curr = r.getTruckSum();
				max = r.getTruckSize();
			}

			int alarm = (int) Math.ceil(max * 0.9);
			// 仓库的每一个位都是整数，向上取整

			if (curr < alarm)
				return false;
			// 未达警戒线
			else
				return true;
			// 大于等于警戒线
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean adjustRepo(String orgID, RepoPosition oldPosition,
			RepoPosition newPosition) {

		try {
			oldPosition.setIsUsed(true);
			newPosition.setIsUsed(true);
			repo.deleteBlock(orgID, oldPosition);
			return repo.addBlock(orgID, newPosition);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public String[] getRow(String orgID, Area area) {
		String[] row = new String[1];
		row[0] = "没有空排";
		int num = 0;

		try {
			RepoInfoPO po = repo.getRepo(orgID);
			if (po != null) {
				switch (area) {
				case AIR:
					num = po.getAirShelfSize();
					break;
				case TRAIN:
					num = po.getTrainShelfSize();
					break;
				case CAR:
					num = po.getTruckShelfSize();
					break;
				default:
					break;
				}
				if (num > 0) {
					row = new String[num];
					for (int i = 0; i < num; i++) {
						int r = i + 1;
						row[i] = "第" + r + "排";
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public String[] getAllRow(String orgID, Area area) {
		String[] row = new String[1];
		row[0] = "没有空排";
		int num = 0;

		try {
			RepoInfoPO po = repo.getRepo(orgID);
			if (po != null) {
				switch (area) {
				case AIR:
					num = po.getAirShelfSize();
					break;
				case TRAIN:
					num = po.getTrainShelfSize();
					break;
				case CAR:
					num = po.getTruckShelfSize();
					break;
				default:
					num = po.getFlexibleShelfSize();
					break;
				}
				if (num > 0) {
					row = new String[num];
					for (int i = 0; i < num; i++) {
						int r = i + 1;
						row[i] = "第" + r + "排";
					}
				}
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public String[] getPosition(String orgID, Area area, int row) {
		String[] shelf = new String[12];

		for (int i = 0; i < shelf.length; i++) {
			int s = i / 4 + 1;
			int p = i % 4 + 1;
			shelf[i] = s + "+" + p;
		}

		try {
			RepoInfoPO po = repo.getRepo(orgID);

			if (po != null) {
				ArrayList<RepoPosition> rpList = po.getRepoPosition();
				if (rpList != null) {
					for (RepoPosition rp : rpList) {
						if (rp.getblock().equals(area) && rp.getrow() == row) {
							int s = rp.getshelf();
							int p = rp.getposition();
							int position = (s - 1) * 4 + p - 1;
							shelf[position] = "#" + s + "+" + p;
						}
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shelf;
	}

	public void recordRepo() {
		try {
			repo.writeAllRepo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endRepoManage() {
		SysLog log = new SysLog();
		log.addSysLog("仓库调整");

		try {
			repo.writeAllRepo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkIn(String orgID, String orderID) {
		try {
			RepoInfoPO r = repo.getRepo(orgID);

			if (r == null)
				return true;
			ArrayList<RepoPosition> list = r.getRepoPosition();

			if (list == null)
				return true;
			else {
				for (RepoPosition rp : list) {
					if (rp.getOrderID().equals(orderID))
						return false;
				}
			}
			return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}

	public String[] getAllInDoc(String orgID) {
		String[] idList = new String[1];
		idList[0] = "仓库没有快递入库";

		RepoInfoPO r;

		try {
			r = repo.getRepo(orgID);
			if (r == null)
				return idList;

			ArrayList<RepoPosition> list = r.getRepoPosition();

			if (list != null && list.size() > 0) {
				idList = new String[list.size()];
				int index = 0;
				for (RepoPosition rp : list) {
					idList[index] = rp.getOrderID();
					index++;
				}
			}
			return idList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return idList;
		}
	}

	public RepoPosition getPosition(String orgID, String orderID) {
		try {
			RepoInfoPO r = repo.getRepo(orgID);
			if (r == null)
				return null;
			ArrayList<RepoPosition> list = r.getRepoPosition();
			if (list == null)
				return null;
			for (RepoPosition rp : list) {
				if (rp.getOrderID().equals(orderID))
					return rp;
			}
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
