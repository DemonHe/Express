package express.dataService.vehicleAndDriverDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.VehicleInfoPO;

public interface VehicleDataService extends Remote{

	public boolean createVehicleInfo(VehicleInfoPO po) throws RemoteException;

	public ArrayList<VehicleInfoPO> getVehicleInfoList(String orgID) throws RemoteException;
	
	public ArrayList<VehicleInfoPO> getVehicleInfoList() throws RemoteException;

	public VehicleInfoPO getVehicleInfo(String id) throws RemoteException;

	public boolean deleteVehicleInfo(String id) throws RemoteException;

	public boolean changeVehicleInfo(VehicleInfoPO po,String id) throws RemoteException;
	
	public boolean isVehicleIDAvailable(String id) throws RemoteException;
	
	public boolean isVehicleLicenseAvailable(String license) throws RemoteException;
	
	public boolean writeAllVehicleInfo() throws RemoteException;
}