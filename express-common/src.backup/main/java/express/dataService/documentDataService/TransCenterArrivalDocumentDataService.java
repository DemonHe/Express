package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.ArrivalDocTransCenterPO;

public interface TransCenterArrivalDocumentDataService extends Remote{
	public boolean createArrivalDoc(ArrivalDocTransCenterPO po) throws RemoteException;
	public ArrivalDocTransCenterPO getArrivalDoc(String OrderID) throws RemoteException;
	public boolean writeAllArrivalDoc() throws RemoteException;
	public ArrayList<ArrivalDocTransCenterPO> getArrivalDoclist() throws RemoteException;
	public boolean changeArrialDoc(ArrivalDocTransCenterPO po) throws RemoteException;
}
