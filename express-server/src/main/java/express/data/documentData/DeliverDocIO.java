package express.data.documentData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
import express.po.DeliverDocPO;
import express.po.TransferDocPO;

public class DeliverDocIO extends UnicastRemoteObject implements BusinessSaleDeliverDocumentDataService{
	ArrayList<DeliverDocPO> deliverdoclist;
	String filepath="SerializableData/ARDeliverDoc.ser";
	
	public  DeliverDocIO() throws RemoteException{
		super();
		deliverdoclist=new ArrayList<DeliverDocPO>();
		try{
			IOHelper io=new IOHelper();
			deliverdoclist=(ArrayList<DeliverDocPO>) io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	public boolean createDeliverDoc(DeliverDocPO po) throws RemoteException{
		System.out.println("writing...deliverdoc......");
		deliverdoclist.add(po);
		//writeAllDeliverDoc();//决定是否在此时写入磁盘
		return true;
	}
	
	public DeliverDocPO getDeliverDoc(String OrderID) throws RemoteException{
		
		int len=deliverdoclist.size();
		for(int i=0;i<len;i++){
			if(deliverdoclist.get(i).getOrderID().equals(OrderID)){
				return deliverdoclist.get(i);
			}
		}
		
		DeliverDocPO notfind=new DeliverDocPO(null,"-1",null);
		return notfind;
		
	}
	
	public boolean writeAllDeliverDoc() throws RemoteException{
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, deliverdoclist);
			return true;
		}catch(Exception e){
			return false;
		}

	}

	public ArrayList<DeliverDocPO> getDeliverDoclist() throws RemoteException{
		return deliverdoclist;
	}
	
	public boolean changeDeliverDoc(DeliverDocPO po) throws RemoteException{
		String orderid=po.getOrderID();
		int len=deliverdoclist.size();
		for(int i=0;i<len;i++){
			if(deliverdoclist.get(i).getOrderID().equals(orderid)){
				deliverdoclist.set(i, po);
				writeAllDeliverDoc();
				return true;
			}
		}
		return false;
	}
	

}
