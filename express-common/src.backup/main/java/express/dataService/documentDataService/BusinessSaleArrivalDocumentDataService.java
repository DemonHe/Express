package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.ArrivalDocBusinessHallPO;

public interface BusinessSaleArrivalDocumentDataService extends Remote{
	public boolean createArrivalDoc(ArrivalDocBusinessHallPO po) throws RemoteException;
	public ArrivalDocBusinessHallPO getArrivalDoc(String OrderID) throws RemoteException;
	public boolean writeAllArrivalDoc() throws RemoteException;
	public ArrayList<ArrivalDocBusinessHallPO> getArrivalDoclist() throws RemoteException;
	public boolean changeBusinessHallArrivalDoc(ArrivalDocBusinessHallPO po) throws RemoteException;
	
	
	
	
}
