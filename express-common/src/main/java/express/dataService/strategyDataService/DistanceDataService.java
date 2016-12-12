package express.dataService.strategyDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import express.po.CityDistancePO;

public interface DistanceDataService extends Remote{
	
	public boolean addDistanceStrategy(CityDistancePO po) throws RemoteException;
	
	public boolean deleteDistanceStrategy(String city) throws RemoteException;
	
	//public boolean changeDistanceStrategy(CityDistancePO po,String id) throws RemoteException;
	
	public ArrayList<CityDistancePO> getAllDistanceStrategy() throws RemoteException;
	
	public boolean writeAllDistanceStrategy() throws RemoteException;

	boolean changeDistanceStrategy(CityDistancePO po) throws RemoteException;
	
	public boolean addDistanceStrategy(CityDistancePO po,int insert) throws RemoteException;
}
