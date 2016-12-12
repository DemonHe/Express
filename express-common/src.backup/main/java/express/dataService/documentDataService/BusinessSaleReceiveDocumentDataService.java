package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.DeliverDocPO;
import express.po.ReceiveDocPO;

public interface BusinessSaleReceiveDocumentDataService extends Remote{
	
	public boolean createReceiveDoc(ReceiveDocPO po) throws RemoteException;
	
	public ReceiveDocPO getReceiveDoc(String date,String delivermanID) throws RemoteException;
	
	public boolean writeAllReceiveDoc() throws RemoteException;
	
	public ArrayList<ReceiveDocPO> getReceiveDoclist() throws RemoteException;
	
	public boolean changeReceiveDoc(ReceiveDocPO po) throws RemoteException;
}
