package express.dataService.innerAccountDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.InnerAccountPO;

public interface InnerAccountDataService extends Remote{
	
	public boolean createInnerAccount(InnerAccountPO innerAccount) throws RemoteException;
	
	public ArrayList<InnerAccountPO> getAllInnerAccount() throws RemoteException;
	
	public ArrayList<String> getInnerAccountName() throws RemoteException;
	
	public InnerAccountPO getInnerAccount(int index) throws RemoteException;
	
	public boolean writeAllInnerAccount() throws RemoteException;
}
