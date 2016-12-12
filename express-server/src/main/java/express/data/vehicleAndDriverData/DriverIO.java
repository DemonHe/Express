package express.data.vehicleAndDriverData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.vehicleAndDriverDataService.DriverDataService;
import express.po.DriverInfoPO;

public class DriverIO extends UnicastRemoteObject implements DriverDataService{

	String filename="SerializableData/Driver.ser";
	ArrayList<DriverInfoPO> driverInfoList;
	
	public DriverIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		driverInfoList=new ArrayList<DriverInfoPO>();
		try {
			driverInfoList=(ArrayList<DriverInfoPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createDriverInfo(DriverInfoPO po) throws RemoteException{
		driverInfoList.add(po);
		return true;
	}

	@Override
	public ArrayList<DriverInfoPO> getSpecifiedDriverList(String orgID)
			throws RemoteException{
		ArrayList<DriverInfoPO> selectList=new ArrayList<DriverInfoPO>();
		if(driverInfoList.size()>0){
			
			for(DriverInfoPO driver:driverInfoList){
				if(driver.getbusinesshallNumber().equals(orgID)){
					selectList.add(driver);
				}
			}
		}
		if(selectList.size()>0)
			return selectList;
		else
			return null;
	}
	
	@Override
	public ArrayList<DriverInfoPO> getAllDriver() throws RemoteException{
		if(driverInfoList.size()>0)
			return driverInfoList;
		else
			return null;
	}

	@Override
	public DriverInfoPO getDriverInfo(String id) throws RemoteException{
		if(driverInfoList.size()>0){
			for(DriverInfoPO driver:driverInfoList){
				if(driver.getdriverNumber().equals(id)){
					return driver;
				}
			}
		}
		return null;
	}

	@Override
	public boolean deleteDriverInfo(String id) throws RemoteException{
		if(driverInfoList.size()>0){
			for(DriverInfoPO driver:driverInfoList){
				if(driver.getdriverNumber().equals(id)){
					driverInfoList.remove(driver);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean changeDriverInfo(DriverInfoPO po,String id) throws RemoteException{
		if(driverInfoList.size()>0){
			int index=0;
			for(DriverInfoPO driver:driverInfoList){
				if(driver.getdriverNumber().equals(id)){
					driverInfoList.set(index,po);
					return true;
				}
				index++;
			}
		}
		return false;
	}
	
	public boolean isDriverIDAvailable(String driverID) throws RemoteException{
		if(driverInfoList.size()>0){
			for(DriverInfoPO driver:driverInfoList){
				if(driver.getdriverNumber().equals(driverID)){
					
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean writeAllDriverInfo() throws RemoteException{
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, driverInfoList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

}
