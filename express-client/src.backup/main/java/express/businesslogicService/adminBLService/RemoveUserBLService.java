package express.businesslogicService.adminBLService;

import express.po.UserRole;

public interface RemoveUserBLService {
	
	public boolean removeUser(String id);
	
	public boolean changeUserInfo(String name,UserRole role,String id);
}
