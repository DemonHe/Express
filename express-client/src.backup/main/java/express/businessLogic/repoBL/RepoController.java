package express.businessLogic.repoBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.businesslogicService.transcenterRepoBLService.InitRepoBLService;
import express.businesslogicService.transcenterRepoBLService.InventoryRepoBLService;
import express.businesslogicService.transcenterRepoBLService.ScanRepoBLService;
import express.dataService.repoDataService.RepoDataService;
import express.po.Area;
import express.po.RepoInfoPO;
import express.po.RepoPosition;
import express.rmi.RMIClient;
import express.vo.InDocVO;
import express.vo.RepoCacheVO;
import express.vo.RepoInfoVO;
import express.vo.RepoPositionVO;

public class RepoController implements AdjustRepoBLService,
		InventoryRepoBLService, ScanRepoBLService, InitRepoBLService {

	ViewRepo view;
	InventoryRepo inventry;
	AdjustRepo adjust;
	RepoDataService repo;

	public RepoController() {
		view = new ViewRepo();
		inventry = new InventoryRepo();
		adjust = new AdjustRepo();
		repo = RMIClient.getRepoInfoObject();
	}

	public RepoCacheVO getRepoInfo(String orgID, String startDate,
			String endDate) {
		return view.getRepoInfo(orgID, startDate, endDate);
	}

	public ArrayList<RepoPositionVO> getRepoPositionList(String orgID) {
		return view.getRepoPositionList(orgID);
	}

	public boolean checkBeginAndEndDate(String beginDate, String endDate) {
		return view.checkBeginAndEndDate(beginDate, endDate);
	}

	public ArrayList<InDocVO> inventoryRepo(String orgID) {
		return inventry.inventoryRepo(orgID);
	}

	public boolean exportExcel(String orgID,String path) {
		return inventry.exportExcel(orgID,path);
	}

	public void endRepoInventory() {
		inventry.endRepoInventory();

	}

	public boolean setRepoBlock(String orgID, RepoPosition position) {
		return adjust.setRepoBlock(orgID, position);
	}
	
	public boolean freeRepoBlock(String orgID, RepoPosition position){
		return adjust.freeRepoBlock(orgID, position);
	}

	public void endRepoManage() {
		adjust.endRepoManage();
	}

	@Override
	public boolean checkRepoBlockUsed(String orgID, RepoPosition position) {
		return adjust.checkRepoBlockUsed(orgID, position);
	}

	@Override
	public boolean alarm(String orgID, RepoPosition position) {
		return adjust.alarm(orgID, position);
	}

	@Override
	public boolean adjustRepo(String orgID, RepoPosition oldPosition,
			RepoPosition newPosition) {
		return adjust.adjustRepo(orgID, oldPosition, newPosition);
	}

	@Override
	public String[] getRow(String orgID, Area area) {
		return adjust.getRow(orgID, area);
	}
	
	@Override
	public String[] getAllRow(String orgID, Area area){
		return adjust.getAllRow(orgID, area);
	}

	@Override
	public String[] getPosition(String orgID, Area area, int row) {
		return adjust.getPosition(orgID, area, row);
	}

	@Override
	public void recordRepo() {
		adjust.recordRepo();

	}

	@Override
	public boolean initRepo(RepoInfoVO vo) {
		RepoInfoPO po = new RepoInfoPO(vo.getCity(), vo.getAirShelfSize(),
				vo.getTrainShelfSize(), vo.getTruckShelfSize(),
				vo.getFlexibleShelfSize());

		try {
			return repo.addRepo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean changeRepoInfo(String orgID, RepoInfoVO vo) {
		RepoInfoPO po = new RepoInfoPO(vo.getCity(), vo.getAirShelfSize(),
				vo.getTrainShelfSize(), vo.getTruckShelfSize(),
				vo.getFlexibleShelfSize());

		try {
			repo.deleteRepo(orgID);
			return repo.addRepo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteRepo(String orgID) {
		try {
			return repo.deleteRepo(orgID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<RepoInfoVO> getAllRepo() {
		try {
			ArrayList<RepoInfoPO> list = repo.getAllRepo();
			ArrayList<RepoInfoVO> transList = new ArrayList<RepoInfoVO>();

			if (list != null) {
				for (RepoInfoPO po : list) {
					RepoInfoVO vo = new RepoInfoVO(po.getCity(),
							po.getAirShelfSize(), po.getTrainShelfSize(),
							po.getTruckShelfSize(), po.getFlexibleShelfSize());
					transList.add(vo);
				}
			}
			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isNumValid(String num) {
		if (num == null)
			return false;

		char c = num.charAt(0);
		if (!((c <= '9' && c >= '0') || c == '+'))
			return false;

		for (int i = 1; i < num.length(); i++) {
			char ch = num.charAt(i);
			if (ch > '9' || ch < '0')
				return false;
		}
		return true;
	}

	@Override
	public boolean checkReset(String orgID) {
		try {
			RepoInfoPO po = repo.getRepo(orgID);
			if (po == null)
				return false;
			else
				return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkIn(String orgID, String orderID) {
		return adjust.checkIn(orgID, orderID);
	}

	@Override
	public String[] getAllInDoc(String orgID) {
		return adjust.getAllInDoc(orgID);
	}

	@Override
	public RepoPosition getPosition(String orgID, String orderID) {
		return adjust.getPosition(orgID, orderID);
	}

}
