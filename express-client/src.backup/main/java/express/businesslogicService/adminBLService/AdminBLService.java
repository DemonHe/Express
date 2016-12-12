package express.businesslogicService.adminBLService;

import java.util.ArrayList;

import express.vo.UserInfoAdminVO;
import express.vo.UserInfoVO;

public interface AdminBLService {
	
	public boolean checkUserID(String id);
	
	public boolean addUser(UserInfoAdminVO vo);
	
	public UserInfoAdminVO getUser(String id);
	
	public ArrayList<UserInfoVO> getUnregistered();
	
	public ArrayList<UserInfoAdminVO> getAllUser();
	
	public boolean changeUserPassword(String id,String password);
	
	public void endManage();
}
