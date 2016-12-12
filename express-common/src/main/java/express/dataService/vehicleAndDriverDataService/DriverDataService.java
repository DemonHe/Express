package express.dataService.vehicleAndDriverDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.DriverInfoPO;

public interface DriverDataService extends Remote{
	
	public boolean createDriverInfo(DriverInfoPO po) throws RemoteException;

	public ArrayList<DriverInfoPO> getSpecifiedDriverList(String orgID)
			throws RemoteException;
	
	public ArrayList<DriverInfoPO> getAllDriver() throws RemoteException;

	public DriverInfoPO getDriverInfo(String id) throws RemoteException;

	public boolean deleteDriverInfo(String id) throws RemoteException;

	public boolean changeDriverInfo(DriverInfoPO po,String id) throws RemoteException;
	
	public boolean isDriverIDAvailable(String driverID) throws RemoteException;
	
	public boolean writeAllDriverInfo() throws RemoteException;

}
