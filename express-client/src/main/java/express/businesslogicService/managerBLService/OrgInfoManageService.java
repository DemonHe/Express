package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.po.OrgProperty;

public interface OrgInfoManageService {
	
	public ArrayList<String> getAllOrgName();
	
	public ArrayList<String> getAllOrgNameByProperty(OrgProperty property);
	
	public ArrayList<String> getOrgIDByProperty(OrgProperty property);
	
	public String getOrgID(String name);
}
