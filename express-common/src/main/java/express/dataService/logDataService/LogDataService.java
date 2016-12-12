package express.dataService.logDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.LogPO;

public interface LogDataService extends Remote{
	
	public ArrayList<LogPO> getSystemLog() throws RemoteException;
	
	public boolean createSystemLog(LogPO po) throws RemoteException;
}
