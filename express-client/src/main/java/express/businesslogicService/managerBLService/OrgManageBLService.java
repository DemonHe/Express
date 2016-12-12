package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.po.OrganizationPO;
import express.vo.OrganizationVO;

public interface OrgManageBLService {
	
	public ArrayList<OrganizationVO> getAllOrgInfo();
	
	public ArrayList<OrganizationPO> getAllOrgInfoPO();

	public OrganizationVO getOrgInfo(String orgID);
	
	public boolean addOrgInfo(OrganizationVO vo);

	public boolean removeOrgInfo(String orgID);

	public boolean changeOrgInfo(OrganizationVO vo,String orgID);
	
	public boolean isOrgNameAvailable(String name);
	
	public boolean isOrgIDAvailable(String id);
	
	public void recordOrgInfo();
	
	public void endManage();
}
