package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.po.UserInfoPO;
import express.vo.SalaryStrategyVO;
import express.vo.UserInfoVO;

public interface StaffManageBLService {

	public boolean addUserFromManager(UserInfoVO vo);
	
	public boolean removeUser(String id);
	
	public UserInfoVO getUser(String id);
	
	public ArrayList<UserInfoVO> getAllUser();
	
	public ArrayList<UserInfoPO> getAllUserPO();
	
	public boolean changeUser(UserInfoVO vo,String id);

	public boolean isUserIDAvailable(String id);
	
	public boolean isCellPhoneAvailable(String phoneNum);
	
	public boolean isJoininDateAvailable(String Date);
	
	public void recordStaffInfo();
	
	public void endManage();
}
