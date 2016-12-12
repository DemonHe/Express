package express.businessLogic.infoManageBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
import express.dataService.vehicleAndDriverDataService.VehicleDataService;
import express.po.VehicleInfoPO;
import express.rmi.RMIClient;
import express.vo.VehicleInfoListVO;
import express.vo.VehicleInfoVO;

public class Vehicle implements VehicleBusinessSaleblService{

	VehicleDataService vehicle;
	
	public Vehicle(){
		vehicle=RMIClient.getVehicleObject();
	}
	
	public boolean addVehicleInfo(VehicleInfoVO vo) {
		VehicleInfoPO po=transVOToPO(vo);
		try {
			return vehicle.createVehicleInfo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeVehicleInfo(String CarID) {
		try {
			return vehicle.deleteVehicleInfo(CarID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public VehicleInfoVO getVehicleInfo(String CarID) {
		try {
			
			VehicleInfoPO po=vehicle.getVehicleInfo(CarID);
			if(po==null)
				return null;
			else{
				VehicleInfoVO vo=transPOToVO(po);
				return vo;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean changeVehicleInfo(VehicleInfoVO vo,String CarID) {
		VehicleInfoPO po=transVOToPO(vo);
		try {
			return vehicle.changeVehicleInfo(po, CarID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void endManage() {
		SysLog log=new SysLog();
		log.addSysLog("车辆信息管理");
		
		try {
			vehicle.writeAllVehicleInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isCarIDAvailable(String carID) {
		try {
			return vehicle.isVehicleIDAvailable(carID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean isCarLicenseAvailable(String license) {
		try {
			return vehicle.isVehicleLicenseAvailable(license);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<VehicleInfoVO> getVehicleInfoList() {
		try {
			ArrayList<VehicleInfoPO> list=vehicle.getVehicleInfoList();
			ArrayList<VehicleInfoVO> transList=new ArrayList<VehicleInfoVO>();
			if(list!=null){
				for(VehicleInfoPO po:list){
					VehicleInfoVO vo=transPOToVO(po);
					transList.add(vo);
				}
			}
			if(transList.size()>0)
				return transList;
			else
				return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public ArrayList<VehicleInfoPO> getVehicleInfoListPO() {
		
		try {
			return vehicle.getVehicleInfoList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<VehicleInfoVO> getVehicleInfoList(String orgID) {
		try {
			ArrayList<VehicleInfoPO> list=vehicle.getVehicleInfoList(orgID);
			ArrayList<VehicleInfoVO> transList=new ArrayList<VehicleInfoVO>();
			if(list!=null){
				for(VehicleInfoPO po:list){
					VehicleInfoVO vo=transPOToVO(po);
					transList.add(vo);
				}
			}
			if(transList.size()>0)
				return transList;
			else
				return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void recordVehicleInfo() {
		try {
			vehicle.writeAllVehicleInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private VehicleInfoVO transPOToVO(VehicleInfoPO po){
		VehicleInfoVO vo=new VehicleInfoVO();
		
		vo.setIsUsing(po.checkIsUsing());
		vo.setLicense(po.getLicense());
		vo.setMark(po.getMark());
		vo.setOrgID(po.getOrgID());
		vo.setUseYear(po.getUseYear());
		
		return vo;
	}
	
	private VehicleInfoPO transVOToPO(VehicleInfoVO vo){
		VehicleInfoPO po=new VehicleInfoPO();
		
		po.setIsUsing(vo.getIsUsing());
		po.setLicense(vo.getLicense());
		po.setMark(vo.getMark());
		po.setOrgID(vo.getOrgID());
		po.setUseYear(vo.getUseYear());
		
		return po;
	}

}
