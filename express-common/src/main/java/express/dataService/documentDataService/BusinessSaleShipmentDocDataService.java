package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.ShipmentDocBusinessHallPO;


public interface BusinessSaleShipmentDocDataService extends Remote {
		public boolean createShipmentDoc(ShipmentDocBusinessHallPO po) throws RemoteException;
		public ShipmentDocBusinessHallPO getShipmentDoc(String OrderID) throws RemoteException;
		public boolean writeAllShipmentDoc() throws RemoteException;
		public ArrayList<ShipmentDocBusinessHallPO> getShipmentDoclist() throws RemoteException;
		public boolean changeBusinessHallShipmentDoc(ShipmentDocBusinessHallPO po) throws RemoteException;
}
