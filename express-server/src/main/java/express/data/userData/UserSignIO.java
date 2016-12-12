package express.data.userData;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.dataService.userDataService.SignUserDataService;
import express.po.UserPO;

public class UserSignIO extends UnicastRemoteObject implements SignUserDataService{

	ArrayList<UserPO> registerList;
	
	public UserSignIO() throws RemoteException{
		super();
		registerList=new ArrayList<UserPO>();
	}

	@Override
	public boolean logInRegister(UserPO user) throws RemoteException {
		registerList.add(user);
		return true;
	}

	@Override
	public boolean logOutRegister(String id) throws RemoteException {
		if(registerList.size()>0)
			for(UserPO user:registerList)
				if(user.getID().equals(id)){
					registerList.remove(user);
					return true;
				}
		return false;
	}

	@Override
	public boolean checkLogIn(String id) throws RemoteException {
		if(registerList.size()>0)
			for(UserPO user:registerList)
				if(user.getID().equals(id))
					return true;
		return false;
	}
}
