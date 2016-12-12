package express.dataService.userDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.UserInfoAdminPO;
import express.po.UserRole;

public interface AdminUserDataService extends Remote{
	
	public boolean createUser(UserInfoAdminPO po) throws RemoteException;
	
	public UserInfoAdminPO getUserAdmin(String id) throws RemoteException;
	
	public ArrayList<UserInfoAdminPO> getAllUserAdmin() throws RemoteException;
	
	public boolean deleteUser(String id) throws RemoteException;
	
	public boolean changeUserPassword(String id,String password) throws RemoteException;
	
	public boolean changeUserInfo(String name,UserRole role, String id) throws RemoteException;
	
	public boolean writeAllUserAdmin() throws RemoteException;
}
