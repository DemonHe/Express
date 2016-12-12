package express.businessLogic.repoBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.documentBL.InDoc;
import express.businessLogic.documentBL.OutDoc;
import express.businesslogicService.transcenterRepoBLService.InDocblService;
import express.businesslogicService.transcenterRepoBLService.OutDocblService;
import express.dataService.repoDataService.RepoDataService;
import express.po.InDocPO;
import express.po.OutDocPO;
import express.po.RepoInfoPO;
import express.po.RepoPosition;
import express.rmi.RMIClient;
import express.vo.RepoCacheVO;
import express.vo.RepoPositionVO;

public class ViewRepo {

	RepoDataService repo;

	public ViewRepo() {
		repo = RMIClient.getRepoInfoObject();
	}

	public RepoCacheVO getRepoInfo(String orgID, String startDate,
			String endDate) {

		try {
			RepoCacheVO cache = new RepoCacheVO();
			RepoInfoPO po;

			int countImport = countInDoc(orgID, startDate, endDate);
			int countExport = countOutDoc(orgID, startDate, endDate);

			cache.setImportSum(countImport);
			cache.setExportSum(countExport);

			po = repo.getRepo(orgID);
			if (repo == null)
				cache.setSum(0);
			else
				cache.setSum(po.getRepoSum());

			return cache;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<RepoPositionVO> getRepoPositionList(String orgID) {
		try {
			RepoInfoPO po = repo.getRepo(orgID);

			if (po == null)
				return null;
			ArrayList<RepoPosition> positionList = po.getRepoPosition();

			if (positionList == null)
				return null;
			if (positionList.size() == 0)
				return null;

			ArrayList<RepoPositionVO> transList = new ArrayList<RepoPositionVO>();

			for (RepoPosition r : positionList) {
				RepoPositionVO vo = new RepoPositionVO(r.getOrderID(),
						r.getblock(), r.getrow(), r.getshelf(),
						r.getposition(), r.getIsUsed());

				transList.add(vo);
			}

			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public boolean checkBeginAndEndDate(String beginDate, String endDate) {

		if (beginDate.compareTo(endDate) > 0)
			return false;
		else
			return true;

	}

	private int countInDoc(String orgID, String startDate, String endDate) {
		int count = 0;

		InDocblService inDoc = new InDoc();

		ArrayList<InDocPO> list = inDoc.getAllInDocPO(orgID);
		if (startDate == null) {
			if (endDate == null) {
				if (list != null)
					count = list.size();
			} else {
				if (list != null) {
					for (InDocPO po : list) {
						String date = po.getdate();
						if (date.compareTo(endDate) <= 0)
							count++;
					}
				}
			}
		} else {
			if (endDate == null) {
				if (list != null) {
					for (InDocPO po : list) {
						String date = po.getdate();
						if (date.compareTo(startDate) >= 0)
							count++;
					}
				}
			} else {
				if (list != null) {
					for (InDocPO po : list) {
						String date = po.getdate();
						if (date.compareTo(startDate) >= 0
								&& date.compareTo(endDate) <= 0)
							count++;
					}
				}
			}
		}

		return count;
	}

	private int countOutDoc(String orgID, String startDate, String endDate) {
		int count = 0;

		OutDocblService outDoc = new OutDoc();

		ArrayList<OutDocPO> list = outDoc.getAllOutDocPO(orgID);
		if (startDate == null) {
			if (endDate == null) {
				if (list != null)
					count = list.size();
			} else {
				if (list != null) {
					for (OutDocPO po : list) {
						String date = po.getdate();
						if (date.compareTo(endDate) <= 0)
							count++;
					}
				}
			}
		} else {
			if (endDate == null) {
				if (list != null) {
					for (OutDocPO po : list) {
						String date = po.getdate();
						if (date.compareTo(startDate) >= 0)
							count++;
					}
				}
			} else {
				if (list != null) {
					for (OutDocPO po : list) {
						String date = po.getdate();
						if (date.compareTo(startDate) >= 0
								&& date.compareTo(endDate) <= 0)
							count++;
					}
				}
			}
		}

		return count;
	}

}
