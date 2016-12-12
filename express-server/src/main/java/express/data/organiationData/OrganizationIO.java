package express.data.organiationData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.organizationDataService.OrganizationDataService;
import express.po.OrgProperty;
import express.po.OrganizationPO;

public class OrganizationIO extends UnicastRemoteObject implements OrganizationDataService{

	String filename="SerializableData/Organization.ser";
	ArrayList<OrganizationPO> orgInfoList;
	
	public OrganizationIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		orgInfoList=new ArrayList<OrganizationPO>();
		try {
			orgInfoList=(ArrayList<OrganizationPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<OrganizationPO> getAllOrgInfo() throws RemoteException {
		if(orgInfoList.size()>0)
			return orgInfoList;
		else
			return null;
	}

	@Override
	public OrganizationPO getOrgInfo(String orgID) throws RemoteException {
		if(orgInfoList.size()>0){
			for(OrganizationPO org:orgInfoList){
				if(org.getOrgID().equals(orgID)){
					return org;
				}
			}
		}
		return null;
	}

	@Override
	public boolean deleteOrgInfo(String orgID) throws RemoteException {
		if(orgInfoList.size()>0){
			for(OrganizationPO org:orgInfoList){
				if(org.getOrgID().equals(orgID)){
					orgInfoList.remove(org);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean changeOrgInfo(OrganizationPO po, String orgID) throws RemoteException {
		if(orgInfoList.size()>0){
			int index=0;
			for(OrganizationPO org:orgInfoList){
				if(org.getOrgID().equals(orgID)){
					orgInfoList.set(index, org);
					return true;
				}
				index++;
			}
		}
		return false;
	}

	@Override
	public boolean writeAllOrgInfo() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename,orgInfoList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

	@Override
	public ArrayList<String> getAllOrgName() throws RemoteException {
		ArrayList<String> nameList=new ArrayList<String>();
		if(orgInfoList.size()>0){
			for(OrganizationPO org:orgInfoList){
				nameList.add(org.getName());
			}
		}
		if(nameList.size()>0)
			return nameList;
		else
			return null;
	}

	@Override
	public ArrayList<String> getAllOrgNameByProperty(OrgProperty property)
			throws RemoteException {
		ArrayList<String> nameList=new ArrayList<String>();
		if(orgInfoList.size()>0){
			for(OrganizationPO org:orgInfoList){
				if(org.getOrgProperty().equals(property)){
					nameList.add(org.getName());
				}
			}
		}
		if(nameList.size()>0)
			return nameList;
		else
			return null;
	}
	
	public ArrayList<String> getAllOrgIDByProperty(OrgProperty property)
			throws RemoteException {
		ArrayList<String> nameList=new ArrayList<String>();
		if(orgInfoList.size()>0){
			for(OrganizationPO org:orgInfoList){
				if(org.getOrgProperty().equals(property)){
					nameList.add(org.getOrgID());
				}
			}
		}
		if(nameList.size()>0)
			return nameList;
		else
			return null;
	}

	@Override
	public String getOrgID(String name) throws RemoteException {
		if(orgInfoList.size()>0){
			for(OrganizationPO org:orgInfoList){
				if(org.getName().equals(name)){
					return org.getOrgID();
				}
			}
		}
		return null;
	}

	@Override
	public boolean createOrgInfo(OrganizationPO po) throws RemoteException {
		orgInfoList.add(po);
		return true;
	}

}
