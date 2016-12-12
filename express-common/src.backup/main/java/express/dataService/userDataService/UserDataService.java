package express.dataService.userDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.UserInfoPO;

public interface UserDataService extends Remote{

	public boolean createuser(UserInfoPO po) throws RemoteException;
	
	public UserInfoPO getUserInfo(String id) throws RemoteException;
	
	public ArrayList<UserInfoPO> getUnregistered() throws RemoteException;
	
	public ArrayList<UserInfoPO> getAllUserInfo() throws RemoteException; 
	
	public boolean deleteUser(String id) throws RemoteException;
	
	public boolean changeUserInfo(UserInfoPO po,String id) throws RemoteException;
	
	public boolean register(String id) throws RemoteException;
	
	public String getUserCity(String id) throws RemoteException;
	
	public boolean writeAllUserInfo() throws RemoteException;
	
}
