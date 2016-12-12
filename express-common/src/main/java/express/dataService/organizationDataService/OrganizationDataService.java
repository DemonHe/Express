package express.dataService.organizationDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.OrgProperty;
import express.po.OrganizationPO;

public interface OrganizationDataService extends Remote{
	
	public ArrayList<OrganizationPO> getAllOrgInfo() throws RemoteException;
	
	public ArrayList<String> getAllOrgName() throws RemoteException;
	
	public ArrayList<String> getAllOrgNameByProperty(OrgProperty property)
			throws RemoteException;
	
	public ArrayList<String> getAllOrgIDByProperty(OrgProperty property)
			throws RemoteException;

	public OrganizationPO getOrgInfo(String orgID) throws RemoteException;
	
	public String getOrgID(String name) throws RemoteException;
	
	public boolean createOrgInfo(OrganizationPO po) throws RemoteException;

	public boolean deleteOrgInfo(String orgID) throws RemoteException;

	public boolean changeOrgInfo(OrganizationPO po,String orgID) throws RemoteException;
	
	public boolean writeAllOrgInfo() throws RemoteException;
}
