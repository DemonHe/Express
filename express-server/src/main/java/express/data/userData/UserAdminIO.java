package express.data.userData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.userDataService.AdminUserDataService;
import express.po.UserInfoAdminPO;
import express.po.UserRole;

public class UserAdminIO extends UnicastRemoteObject implements AdminUserDataService{

	String filename="SerializableData/UserAdminInfo.ser";
	ArrayList<UserInfoAdminPO> userAdminList;
	
	public UserAdminIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		userAdminList=new ArrayList<UserInfoAdminPO>();
		try {
			userAdminList=(ArrayList<UserInfoAdminPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createUser(UserInfoAdminPO po) throws RemoteException {
		userAdminList.add(po);
		return true;
	}

	@Override
	public UserInfoAdminPO getUserAdmin(String id) throws RemoteException {
		if(userAdminList.size()>0)
			for(UserInfoAdminPO userAdmin:userAdminList)
				if(userAdmin.getID().equals(id))
					return userAdmin;
		return null;
	}

	@Override
	public ArrayList<UserInfoAdminPO> getAllUserAdmin() throws RemoteException {
		if(userAdminList.size()>0)
			return userAdminList;
		else
			return null;
	}

	@Override
	public boolean deleteUser(String id) throws RemoteException {
		if(userAdminList.size()>0)
			for(UserInfoAdminPO userAdmin:userAdminList)
				if(userAdmin.getID().equals(id)){
					userAdminList.remove(userAdmin);
					return true;
				}
		return false;
	}

	@Override
	public boolean changeUserPassword(String id, String password)
			throws RemoteException {
		if(userAdminList.size()>0)
			for(UserInfoAdminPO userAdmin:userAdminList)
				if(userAdmin.getID().equals(id)){
					userAdmin.setPassword(password);
					return true;
				}
		return false;
	}

	@Override
	public boolean changeUserInfo(String name,UserRole role, String id)
			throws RemoteException {
		if(userAdminList.size()>0){
			for(UserInfoAdminPO userAdmin:userAdminList){
				if(userAdmin.getID().equals(id)){
					userAdmin.setName(name);
					userAdmin.setPosition(role);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean writeAllUserAdmin() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, userAdminList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

}
