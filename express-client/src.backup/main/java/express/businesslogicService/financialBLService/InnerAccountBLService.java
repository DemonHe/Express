package express.businesslogicService.financialBLService;

import java.util.ArrayList;

import express.vo.BankAccountVO;
import express.vo.InnerAccountVO;
import express.vo.OrganizationVO;
import express.vo.RepoInfoVO;
import express.vo.UserInfoVO;
import express.vo.VehicleInfoVO;

public interface InnerAccountBLService {

	public boolean initUserInfo(UserInfoVO user);
	
	public boolean initOrganizationInfo(OrganizationVO organization);
	
	public boolean initRepoInfo(RepoInfoVO vo);
	
	public boolean initVehicleInfo(VehicleInfoVO car);
	
	public boolean initBankAccountInfo(BankAccountVO bankAccount);
	
	public boolean changeUserInfo(UserInfoVO user,String id);
	
	public boolean changeOrganizationInfo(OrganizationVO organization,String orgID);
	
	public boolean changeRepoInfo(RepoInfoVO repo,String orgID);
	
	public boolean changeVehicleInfo(VehicleInfoVO car,String carID);
	
	public boolean changeBankAccountInfo(BankAccountVO bankAccount,String name);
	
	public boolean deleteUserInfo(String id);
	
	public boolean deleteOrganizationInfo(String orgID);
	
	public boolean deleteRepoInfo(String orgID);
	
	public boolean deleteVehicleInfo(String carID);
	
	public boolean deleteBankAccountInfo(String name);
	
	public ArrayList<UserInfoVO> getUser();
	
	public ArrayList<OrganizationVO> getOrg();
	
	public ArrayList<RepoInfoVO> getRepo();
	
	public ArrayList<VehicleInfoVO> getVehicle();
	
	public ArrayList<BankAccountVO> getBankAccount();
	
	public ArrayList<String> getInnerAccountNameList();
	
	public InnerAccountVO getPrevious(int index);
	//人员信息�?�?
	public boolean isUserIDAvailable(String id);
	
	public boolean isCellPhoneAvailable(String phoneNum);
	
	public boolean isJoininDateAvailable(String Date);
	//机构信息�?�?
	public boolean isOrgNameAvailable(String name);
	
	public boolean isOrgIDAvailable(String id);
	//仓库信息�?�?
	public boolean isNumValid(String num);
	
	public boolean checkReset(String orgID);
	//车辆信息�?�?
	public boolean isCarIDAvailable(String carID);
	
	public boolean isCarLicenseAvailable(String license);
	//银行账户信息�?�?
	public boolean checkDuplication(String name);
	
	public boolean isMoneyValid(String money);
	//
	public void recordStaffInfo();
	
	public void recordOrgInfo();
	
	public void recordVehicleInfo();
	
	public void recordBankAccount();
	
	public void endInitial();
}
