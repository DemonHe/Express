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
import express.dataService.documentDataService.TransCenterArrivalDocumentDataService;
import express.po.ArrivalDocTransCenterPO;
import express.po.DeliverDocPO;

public class ArrivalDocTransCenterIO extends UnicastRemoteObject implements TransCenterArrivalDocumentDataService{

	ArrayList<ArrivalDocTransCenterPO> arrivaldoclist;
	String filepath="SerializableData/ARTransCenterArrivalDoc.ser";
	
	
	public  ArrivalDocTransCenterIO() throws RemoteException{
		super();
		arrivaldoclist=new ArrayList<ArrivalDocTransCenterPO>();
		try{
			IOHelper io=new IOHelper();
			arrivaldoclist=(ArrayList<ArrivalDocTransCenterPO>)io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean createArrivalDoc(ArrivalDocTransCenterPO po) throws RemoteException{
		System.out.println("writing...arrivaldoc......");
		arrivaldoclist.add(po);
		//writeAllDeliverDoc();//决定是否在此时写入磁�?
		return true;
		
	}

	@Override
	public ArrivalDocTransCenterPO getArrivalDoc(String OrderID) throws RemoteException{
		int len=arrivaldoclist.size();
		for(int i=0;i<len;i++){
			if(arrivaldoclist.get(i).getOrderID().equals(OrderID)){
				return arrivaldoclist.get(i);
			}
		}
		
		
		return null;

	}
	
	public boolean writeAllArrivalDoc() throws RemoteException{
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, arrivaldoclist);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public ArrayList<ArrivalDocTransCenterPO> getArrivalDoclist() throws RemoteException{
		return arrivaldoclist;
	}
	
	
	public boolean changeArrialDoc(ArrivalDocTransCenterPO po) throws RemoteException{
		String orderid=po.getOrderID();
		int len=arrivaldoclist.size();
		for(int i=0;i<len;i++){
			if(arrivaldoclist.get(i).getOrderID().equals(orderid)){
				arrivaldoclist.set(i, po);
				 writeAllArrivalDoc();
				return true;
			}
		}
		return false;
	}
	
		
}
