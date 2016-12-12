package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import express.po.GoodTransStatusPO;

public interface GoodsStatusDataService extends Remote{
	public GoodTransStatusPO search(String id) throws RemoteException;
	public boolean changeGoodtransstatus(GoodTransStatusPO po) throws RemoteException;
	public boolean writeAllGoodTransStatus() throws RemoteException;
}
