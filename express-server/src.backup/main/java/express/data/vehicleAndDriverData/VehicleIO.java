package express.data.vehicleAndDriverData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.vehicleAndDriverDataService.VehicleDataService;
import express.po.DriverInfoPO;
import express.po.VehicleInfoPO;

public class VehicleIO extends UnicastRemoteObject implements VehicleDataService{

	String filename="SerializableData/Vehicle.ser";
	ArrayList<VehicleInfoPO> vehicleInfoList;
	
	public VehicleIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		vehicleInfoList=new ArrayList<VehicleInfoPO>();
		try {
			vehicleInfoList=(ArrayList<VehicleInfoPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createVehicleInfo(VehicleInfoPO po) throws RemoteException {
		vehicleInfoList.add(po);
		return true;
	}

	@Override
	public ArrayList<VehicleInfoPO> getVehicleInfoList(String orgID) throws RemoteException {
		ArrayList<VehicleInfoPO> list=new ArrayList<VehicleInfoPO>();
		if(vehicleInfoList.size()>0){
			for(VehicleInfoPO vehicle:vehicleInfoList){
				if(vehicle.getOrgID().equals(orgID)){
					list.add(vehicle);
				}
			}
		}
		if(list.size()>0)
			return list;
		else
			return null;
	}

	@Override
	public VehicleInfoPO getVehicleInfo(String id) throws RemoteException {
		if(vehicleInfoList.size()>0){
			for(VehicleInfoPO vehicle:vehicleInfoList){
				if(vehicle.getMark().equals(id)){
					return vehicle;
				}
			}
		}
		return null;
	}

	@Override
	public boolean deleteVehicleInfo(String id) throws RemoteException {
		if(vehicleInfoList.size()>0){
			for(VehicleInfoPO vehicle:vehicleInfoList){
				if(vehicle.getMark().equals(id)){
					vehicleInfoList.remove(vehicle);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean writeAllVehicleInfo() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, vehicleInfoList);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean changeVehicleInfo(VehicleInfoPO po, String id)
			throws RemoteException {
		if(vehicleInfoList.size()>0){
			int index=0;
			for(VehicleInfoPO vehicle:vehicleInfoList){
				if(vehicle.getMark().equals(id)){
					vehicleInfoList.set(index, po);
					return true;
				}
				index++;
			}
		}
		return false;
	}

	@Override
	public boolean isVehicleIDAvailable(String id) throws RemoteException {
		if(vehicleInfoList.size()>0){
			for(VehicleInfoPO vehicle:vehicleInfoList){
				if(vehicle.getMark().equals(id)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<VehicleInfoPO> getVehicleInfoList() throws RemoteException {
		if(vehicleInfoList.size()>0)
			return vehicleInfoList;
		else
			return null;
	}

	@Override
	public boolean isVehicleLicenseAvailable(String license)
			throws RemoteException {
		if(vehicleInfoList.size()>0){
			for(VehicleInfoPO vehicle:vehicleInfoList){
				if(vehicle.getLicense().equals(license)){
					return true;
				}
			}
		}
		return false;
	}

}
