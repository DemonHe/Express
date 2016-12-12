package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.InDocPO;
import express.po.OutDocPO;

public interface InDocDataService extends Remote{
	public boolean createInDoc(InDocPO po) throws RemoteException ;
	public InDocPO getInDocPO(String orderID) throws RemoteException;
	public ArrayList<InDocPO> getInDocPOlist() throws RemoteException;
	public boolean writeAllInDoc() throws RemoteException;
	public boolean changeInDoc(InDocPO po) throws RemoteException;
}
