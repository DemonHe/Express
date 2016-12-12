package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.vehicleAndDriverDataService.VehicleDataService;
import express.po.VehicleInfoPO;

public class VehicleDataService_stub implements VehicleDataService{

	@Override
	public boolean createVehicleInfo(VehicleInfoPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<VehicleInfoPO> getVehicleInfoList(String orgID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VehicleInfoPO> getVehicleInfoList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleInfoPO getVehicleInfo(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteVehicleInfo(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeVehicleInfo(VehicleInfoPO po, String id)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVehicleIDAvailable(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVehicleLicenseAvailable(String license)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeAllVehicleInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
