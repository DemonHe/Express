package express.dataService.strategyDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import express.po.SalaryPO;

public interface SalaryDataService extends Remote{
	
	public boolean changeSalaryStrategy(SalaryPO po) throws RemoteException;
	
	public ArrayList<SalaryPO> getAllSalaryStrategy() throws RemoteException;
	
	public boolean writeAllSalaryStrategy() throws RemoteException;
}
