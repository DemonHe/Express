package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.GoodTransStatusPO;
import express.po.OrderPO;

public interface DeliverCreateOrderDataService extends Remote{
	public boolean createOrder(OrderPO po) throws RemoteException;
	public OrderPO getOrder(String orderid) throws RemoteException;
	public String getNextOrderID() throws RemoteException;
	public ArrayList<OrderPO> getOrderlist() throws RemoteException;
	public boolean changeOrder(OrderPO po) throws RemoteException;
	public boolean writeAllOrder() throws RemoteException;
	
}
