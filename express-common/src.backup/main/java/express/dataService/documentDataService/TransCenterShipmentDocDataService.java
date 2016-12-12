package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.ShipmentDocTransCenterPO;

public interface TransCenterShipmentDocDataService extends Remote{
	public boolean createShipmentDoc(ShipmentDocTransCenterPO po) throws RemoteException;
	public ShipmentDocTransCenterPO getShipmentDoc(String OrderID) throws RemoteException;
	public boolean writeAllShipmentDoc() throws RemoteException;
	public ArrayList<ShipmentDocTransCenterPO> getShipmentDoclist() throws RemoteException;
	public boolean changeTransCenterShipmentDoc(ShipmentDocTransCenterPO po) throws RemoteException;
}
