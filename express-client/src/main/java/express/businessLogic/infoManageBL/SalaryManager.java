package express.businessLogic.infoManageBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businesslogicService.managerBLService.SalaryManagerBLService;
import express.dataService.strategyDataService.SalaryDataService;
import express.po.SalaryPO;
import express.rmi.RMIClient;
import express.vo.SalaryStrategyVO;

public class SalaryManager implements SalaryManagerBLService {

	SalaryDataService salary;
	
	public SalaryManager() {
		salary=RMIClient.getSalaryStrategy();
	}
	
	public boolean changeSalaryStrategy(SalaryStrategyVO vo) {
		SalaryPO po=new SalaryPO(vo.getPosition(),vo.getStrategy(),vo.getValue());
		try {
			return salary.changeSalaryStrategy(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<SalaryStrategyVO> getSalaryStrategyList() {
		
		ArrayList<SalaryPO> list;
		try {
			list = salary.getAllSalaryStrategy();
			
			if(list==null)
				return null;
			
			ArrayList<SalaryStrategyVO> transList=new ArrayList<SalaryStrategyVO>();
			for(SalaryPO po:list){
				SalaryStrategyVO vo=new SalaryStrategyVO(po.getPosition(),
						po.getStrategy(),po.getValue());
				
				transList.add(vo);
			}
			
			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public ArrayList<SalaryPO> getSalaryStrategyListPO() {
		
		try {
			return salary.getAllSalaryStrategy();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean recordSalaryStrategy() {
		
		try {
			return salary.writeAllSalaryStrategy();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
