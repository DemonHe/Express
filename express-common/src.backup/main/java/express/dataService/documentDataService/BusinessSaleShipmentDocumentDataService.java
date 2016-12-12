package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.ShipmentDocPO;

public interface BusinessSaleShipmentDocumentDataService extends Remote{
	
	public boolean createShipmentDoc(ShipmentDocPO po) throws RemoteException;
	
	public ShipmentDocPO getShipmentDoc(String OrderID) throws RemoteException;
	
	public boolean writeAllShipmentDoc() throws RemoteException;
	
	public ArrayList<ShipmentDocPO> getShipmentDoclist() throws RemoteException;
}
