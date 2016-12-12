package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.vehicleAndDriverDataService.DriverDataService;
import express.po.DriverInfoPO;
import express.po.VehicleInfoPO;

public class DriverDataService_stub implements DriverDataService {

	@Override
	public boolean createDriverInfo(DriverInfoPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<DriverInfoPO> getSpecifiedDriverList(String orgID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DriverInfoPO> getAllDriver() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverInfoPO getDriverInfo(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDriverInfo(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeDriverInfo(DriverInfoPO po, String id)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDriverIDAvailable(String driverID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeAllDriverInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	
}

