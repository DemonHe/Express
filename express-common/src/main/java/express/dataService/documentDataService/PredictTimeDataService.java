package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import express.po.HistoryTimePO;
import express.po.PredictTimePO;

public interface PredictTimeDataService extends Remote{
	public boolean addToHistory(HistoryTimePO po) throws RemoteException;
	public PredictTimePO getPredictlist(String startCity,String endCity) throws RemoteException;
	
	public boolean writeAllPredictTime() throws RemoteException;
}
