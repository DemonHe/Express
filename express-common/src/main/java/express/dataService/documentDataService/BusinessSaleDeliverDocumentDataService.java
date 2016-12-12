package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.DeliverDocPO;

public interface BusinessSaleDeliverDocumentDataService extends Remote{
	public boolean createDeliverDoc(DeliverDocPO po) throws RemoteException;
	public DeliverDocPO getDeliverDoc(String OrderID) throws RemoteException;
	public boolean writeAllDeliverDoc() throws RemoteException;
	public ArrayList<DeliverDocPO> getDeliverDoclist() throws RemoteException;
	public boolean changeDeliverDoc(DeliverDocPO po) throws RemoteException;
}
