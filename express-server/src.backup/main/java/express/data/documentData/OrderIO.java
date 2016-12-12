package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.DeliverCreateOrderDataService;
import express.po.DeliverDocPO;
import express.po.GoodTransStatusPO;
import express.po.OrderPO;

public class OrderIO extends UnicastRemoteObject implements DeliverCreateOrderDataService{
	private long orderID=0L;//�?要序列化
	ArrayList<OrderPO> orderlist;
	String orderfilepath="SerializableData/AROrder.ser";
	String nextIDfilepath="SerializableData/NextID.ser";
	
	
	public OrderIO() throws RemoteException {
		super();
		orderlist=new ArrayList<OrderPO>();
		IOHelper io=new IOHelper();
		IOHelper io2=new IOHelper();
		try{
			orderlist=(ArrayList<OrderPO>) io.readFromDisk(orderfilepath);
			orderID=Long.valueOf(String.valueOf(io2.readFromDisk(nextIDfilepath))).longValue();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public boolean createOrder(OrderPO po) throws RemoteException{
		System.out.println("writing...order......");
		orderlist.add(po);
		//writeAllDeliverDoc();//决定是否在此时写入磁�?
		orderID++;
		return true;
	}
	
	
	
	public OrderPO getOrder(String orderid) throws RemoteException{
		int len=orderlist.size();
		for(int i=0;i<len;i++){
			System.out.println("in?");
			
			
			if(orderlist.get(i).getOrderID().equals(orderid)){
				return orderlist.get(i);
			}
		}
		System.out.println("not found");
		return null;
	}
	
	public String getNextOrderID() throws RemoteException{
		String nextID="";
		nextID+=orderID;
		int len=10-nextID.length();
		for(int i=0;i<len;i++){
			nextID="0"+nextID;
		}
		return nextID;	
	}
	
	public boolean writeAllOrder() throws RemoteException{
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(orderfilepath, orderlist);
			IOHelper io2=new IOHelper();
			io2.writeToDisk(nextIDfilepath, orderID);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public ArrayList<OrderPO> getOrderlist() throws RemoteException{
		return orderlist;
	}
	
	public boolean changeOrder(OrderPO po) throws RemoteException{
		String orderid=po.getOrderID();
		int len=orderlist.size();
		for(int i=0;i<len;i++){
			if(orderlist.get(i).getOrderID().equals(orderid)){
				orderlist.set(i, po);
				writeAllOrder();
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
}
