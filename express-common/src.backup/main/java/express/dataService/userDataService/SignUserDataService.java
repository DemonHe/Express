package express.dataService.userDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import express.po.UserPO;

public interface SignUserDataService extends Remote{

	public boolean logInRegister(UserPO user) throws RemoteException;
	
	public boolean logOutRegister(String id) throws RemoteException;
	
	public boolean checkLogIn(String id) throws RemoteException;
}
