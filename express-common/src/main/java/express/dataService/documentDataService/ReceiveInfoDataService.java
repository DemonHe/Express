package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.DeliverDocPO;
import express.po.ReceiveInfoPO;

public interface ReceiveInfoDataService extends Remote {
	public boolean createReceiveInfo(ReceiveInfoPO po) throws RemoteException;
	public ReceiveInfoPO getReceiveInfo(String orderID) throws RemoteException;
	public boolean writeAllReceiveInfo() throws RemoteException;
	public ArrayList<ReceiveInfoPO> getReceiveInfolist() throws RemoteException;
	
}
