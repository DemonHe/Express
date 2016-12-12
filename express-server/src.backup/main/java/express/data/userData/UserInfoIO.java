package express.data.userData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.userDataService.UserDataService;
import express.po.UserInfoPO;

public class UserInfoIO extends UnicastRemoteObject implements UserDataService{

	String filename="SerializableData/UserInfo.ser";
	ArrayList<UserInfoPO> userList;
	
	public UserInfoIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		userList=new ArrayList<UserInfoPO>();
		try {
			userList=(ArrayList<UserInfoPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createuser(UserInfoPO po) throws RemoteException {
		userList.add(po);
		return true;
	}

	@Override
	public UserInfoPO getUserInfo(String id) throws RemoteException {
		if(userList.size()>0)
			for(UserInfoPO user:userList)
				if(user.getID().equals(id))
					return user;
		return null;
	}
	

	@Override
	public ArrayList<UserInfoPO> getUnregistered() throws RemoteException {
		
		ArrayList<UserInfoPO> selectList=new ArrayList<UserInfoPO>();
		
		if(userList.size()>0)
			for(UserInfoPO user:userList)
				if(!user.checkAdmin())
					selectList.add(user);
		
		if(selectList.size()>0)
			return selectList;
		else
			return null;
	}

	@Override
	public ArrayList<UserInfoPO> getAllUserInfo() throws RemoteException {
		if(userList.size()>0)
			return userList;
		else
			return null;
	}

	@Override
	public boolean deleteUser(String id) throws RemoteException {
		if(userList.size()>0)
			for(UserInfoPO user:userList)
				if(user.getID().equals(id)){
					userList.remove(user);
					return true;
				}
		return false;
	}

	@Override
	public boolean changeUserInfo(UserInfoPO po,String id) throws RemoteException {
		if(userList.size()>0)
			for(UserInfoPO user:userList)
				if(user.getID().equals(id)){
					user.setCity(po.getCity());
					user.setDate(po.getDate());
					user.setGender(po.getGender());
					user.setName(po.getName());
					user.setPhoneNum(po.getPhoneNum());
					user.setPosition(po.getPosition());
					return true;
				}
		return false;
	}
	

	@Override
	public boolean register(String id) throws RemoteException {
		if(userList.size()>0)
			for(UserInfoPO user:userList)
				if(user.getID().equals(id)){
					user.setAdmin(true);
					return true;
				}
		return false;
	}

	@Override
	public String getUserCity(String id) throws RemoteException {
		if(userList.size()>0)
			for(UserInfoPO user:userList)
				if(user.getID().equals(id))
					return user.getCity();
		return null;
	}

	@Override
	public boolean writeAllUserInfo() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, userList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
		
	}

}
