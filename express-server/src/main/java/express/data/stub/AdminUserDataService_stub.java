package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.userDataService.AdminUserDataService;
import express.po.UserInfoAdminPO;
import express.po.UserInfoPO;
import express.po.UserRole;

public class AdminUserDataService_stub implements AdminUserDataService{
	
	public boolean createuser(UserInfoPO po){
		System.out.println("Add succeed.");
		return true;
	}
	
	public boolean checkExisted(String id){
		if(id.equals("1001001"))
			return false;
		else
			return true;
	}
	
	public UserInfoAdminPO getUserInfo(String id){
		return new UserInfoAdminPO("卢海龙",id,UserRole.DeliverMan,"123456");
	}
	
	public boolean deleteUser(String id){
		System.out.println("Detele succeed.");
		return true;
	}
	
	public boolean changeUserPosition(String id){
		System.out.println("Change succeed.");
		return true;
	}
	
	public boolean changeUserPassword(String id){
		System.out.println("Change succeed.");
		return true;
	}

	public boolean createuser(UserInfoAdminPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createUser(UserInfoAdminPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserInfoAdminPO getUserAdmin(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserInfoAdminPO> getAllUserAdmin() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeUserPassword(String id, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeAllUserAdmin() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeUserInfo(String name, UserRole role, String id)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}
