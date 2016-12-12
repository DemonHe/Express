package express.businessLogic.infoManageBL;//

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.businesslogicService.managerBLService.OrgManageBLService;
import express.dataService.organizationDataService.OrganizationDataService;
import express.po.OrgProperty;
import express.po.OrganizationPO;
import express.rmi.RMIClient;
import express.vo.OrganizationVO;

public class OrgForManager implements OrgManageBLService,OrgInfoManageService{
	
	OrganizationDataService org;
	
	public OrgForManager(){
		org=RMIClient.getOrgObject();
	}

	public ArrayList<OrganizationVO> getAllOrgInfo() {
		
		ArrayList<OrganizationPO> list=new ArrayList<OrganizationPO>();
		ArrayList<OrganizationVO> transList=new ArrayList<OrganizationVO>();
		
		try {
			
			list=org.getAllOrgInfo();
			
			if(list!=null){
				for(OrganizationPO po:list){
					OrganizationVO vo=transPOToVO(po);
					transList.add(vo);
				}
			}
	//		if(list.size()>0)
				return transList;
//			else
//				return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public ArrayList<OrganizationPO> getAllOrgInfoPO() {

		try {
			return org.getAllOrgInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> getAllOrgName() {
		try {
			return org.getAllOrgName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getAllOrgNameByProperty(OrgProperty property) {
		try {
			return org.getAllOrgNameByProperty(property);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getOrgIDByProperty(OrgProperty property){
		try {
			return org.getAllOrgIDByProperty(property);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public OrganizationVO getOrgInfo(String orgID) {
		try {
			OrganizationPO po=org.getOrgInfo(orgID);
			if(po!=null){
				OrganizationVO vo=transPOToVO(po);
				return vo;
			}
			else
				return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getOrgID(String name) {
		try {
			return org.getOrgID(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean addOrgInfo(OrganizationVO vo) {
		OrganizationPO po=transVOToPO(vo);
		try {
			return org.createOrgInfo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeOrgInfo(String orgID) {
		try {
			return org.deleteOrgInfo(orgID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeOrgInfo(OrganizationVO vo, String orgID) {
		OrganizationPO po=transVOToPO(vo);
		try {
			return org.changeOrgInfo(po, orgID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean isOrgNameAvailable(String name) {
		try {
			
			ArrayList<String> nameList=org.getAllOrgName();
			if(nameList!=null){
				for(String s:nameList){
					if(s.equals(name))
						return true;
				}
			}
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean isOrgIDAvailable(String id) {
		try {
			
			ArrayList<OrganizationPO> list=org.getAllOrgInfo();
			
			if(list!=null){
				for(OrganizationPO po:list){
					if(po.getOrgID().equals(id))
						return true;
				}
			}
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void recordOrgInfo() {
		try {
			org.writeAllOrgInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endManage() {
		SysLog log=new SysLog();
		log.addSysLog("机构信息管理");
		
		try {
			org.writeAllOrgInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private OrganizationVO transPOToVO(OrganizationPO po){

		OrganizationVO vo=new OrganizationVO();
		
		vo.setAddress(po.getAddress());
		vo.setCity(po.getCity());
		vo.setName(po.getName());
		vo.setOrgID(po.getOrgID());
		vo.setOrgProperty(po.getOrgProperty());
		
		return vo;
	}
	
	private OrganizationPO transVOToPO(OrganizationVO vo){

		OrganizationPO po=new OrganizationPO();
		
		po.setAddress(vo.getAddress());
		po.setCity(vo.getCity());
		po.setName(vo.getName());
		po.setOrgID(vo.getOrgID());
		po.setOrgProperty(vo.getOrgProperty());
		
		return po;
	}
	
}
