package express.businessLogic.infoManageBL;//

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
import express.dataService.vehicleAndDriverDataService.DriverDataService;
import express.po.DriverInfoPO;
import express.rmi.RMIClient;
import express.vo.DriverInfoVO;

public class Driver implements DriverBusinessSaleblService{
	
	DriverDataService driver;
	
	public Driver(){
		driver=RMIClient.getDriverObject();
	}

	public boolean addDriverInfo(DriverInfoVO vo){
		
		DriverInfoPO po=transVOToPO(vo);
		
		try {
			return driver.createDriverInfo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeDriverInfo(String DriverID) {
		try {
			return driver.deleteDriverInfo(DriverID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<DriverInfoVO> getDriverInfoList(String orgID) {
		try {
			ArrayList<DriverInfoPO> list=driver.getSpecifiedDriverList(orgID);
			ArrayList<DriverInfoVO> transList=new ArrayList<DriverInfoVO>();
			if(list!=null){
				for(DriverInfoPO po:list){
					DriverInfoVO vo=transPOToVO(po);
					
					transList.add(vo);
				}
			}
			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public ArrayList<DriverInfoVO> getDriverInfoList() {
		ArrayList<DriverInfoPO> list;
		try {
			list = driver.getAllDriver();
			ArrayList<DriverInfoVO> transList=new ArrayList<DriverInfoVO>();
			if(list!=null){
				for(DriverInfoPO po:list){
					DriverInfoVO vo=transPOToVO(po);
					
					transList.add(vo);
				}
			}
			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public DriverInfoVO getDriverInfo(String DriverID) {
		try {
			
			DriverInfoPO po=driver.getDriverInfo(DriverID);
			
			if(po!=null){
				DriverInfoVO vo=transPOToVO(po);
				return vo;
			}
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean changeDriverInfo(DriverInfoVO vo,String DriverID) {
		
		DriverInfoPO po=transVOToPO(vo);
		
		try {
			return driver.changeDriverInfo(po, DriverID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isDriverIDAvailable(String driverID) {
		try{
			return driver.isDriverIDAvailable(driverID);
		}catch(RemoteException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean isIdentityIdAvailable(String id) {
		if(id.length()!=18)
			return false;
		for(int i=0;i<17;i++){
			char ch=id.charAt(i);
			if((ch>'9'||ch<'0'))
				return false;
		}
		char last=id.charAt(17);
		if((last>'9'||last<'0')&&(!(last=='X'||last=='x')))
			return false;
		return true;
	}

	public void endManage() {
		
		SysLog log=new SysLog();
		log.addSysLog("管理司机信息");
		
		try {
			driver.writeAllDriverInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DriverInfoPO transVOToPO(DriverInfoVO vo){
		
		DriverInfoPO po=new DriverInfoPO();
		
		po.setbusinesshallNumber(vo.getbusinesshallNumber());
		po.setcellPhone(vo.getcellPhone());
		po.setdate(vo.getdate());
		po.setdeadline(vo.getdeadline());
		po.setdriverNumber(vo.getdriverNumber());
		po.setID(vo.getID());
		po.setname(vo.getname());
		po.setsex(vo.getsex());
		
		return po;
	}
	
	private DriverInfoVO transPOToVO(DriverInfoPO po){
		
		DriverInfoVO vo=new DriverInfoVO();
		
		vo.setbusinesshallNumber(po.getbusinesshallNumber());
		vo.setcellPhone(po.getcellPhone());
		vo.setdate(po.getdate());
		vo.setdeadline(po.getdeadline());
		vo.setdriverNumber(po.getdriverNumber());
		vo.setID(po.getID());
		vo.setname(po.getname());
		vo.setsex(po.getsex());
		
		return vo;
	}

}
