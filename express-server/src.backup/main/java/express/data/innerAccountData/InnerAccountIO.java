package express.data.innerAccountData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.innerAccountDataService.InnerAccountDataService;
import express.po.InnerAccountPO;


public class InnerAccountIO extends UnicastRemoteObject implements InnerAccountDataService{

	String filename="SerializableData/InnerAccount.ser";
	ArrayList<InnerAccountPO> innerAccountList;
	
	public InnerAccountIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		innerAccountList=new ArrayList<InnerAccountPO>();
		try {
			innerAccountList=(ArrayList<InnerAccountPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createInnerAccount(InnerAccountPO innerAccount) throws RemoteException{
		innerAccountList.add(innerAccount);
		return true;
	}

	@Override
	public ArrayList<InnerAccountPO> getAllInnerAccount() throws RemoteException{
		if(innerAccountList.size()>0)
			return innerAccountList;
		else
			return null;
	}

	@Override
	public InnerAccountPO getInnerAccount(int index) throws RemoteException{
		if(index>=0&&index<innerAccountList.size())
			return innerAccountList.get(index);
		else
			return null;
	}

	@Override
	public ArrayList<String> getInnerAccountName() throws RemoteException {
		if(innerAccountList.size()==0)
			return null;
		ArrayList<String> nameList=new ArrayList<String>();
		for(InnerAccountPO po:innerAccountList)
			nameList.add(po.getInnerAccountID());
		return nameList;
	}

	@Override
	public boolean writeAllInnerAccount() throws RemoteException{
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename,innerAccountList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

}
