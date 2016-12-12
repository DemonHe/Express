package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.BusinessSaleArrivalDocumentDataService;
import express.po.ArrivalDocBusinessHallPO;
import express.po.ArrivalDocTransCenterPO;

public class ArrivalDocBusinessHallIO extends UnicastRemoteObject implements BusinessSaleArrivalDocumentDataService{

	
	ArrayList<ArrivalDocBusinessHallPO> arrivaldoclist;
	String filepath="SerializableData/ARBusinessHallArrivalDoc.ser";
	
	public ArrivalDocBusinessHallIO() throws RemoteException {
		super();
		arrivaldoclist=new ArrayList<ArrivalDocBusinessHallPO>();
		try{
			IOHelper io=new IOHelper();
			arrivaldoclist=(ArrayList<ArrivalDocBusinessHallPO>)io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createArrivalDoc(ArrivalDocBusinessHallPO po) throws RemoteException {
		System.out.println("writing...arrivaldoc......");
		arrivaldoclist.add(po);
		//writeAllDeliverDoc();//决定是否在此时写入磁盘
		return true;
	}

	@Override
	public ArrivalDocBusinessHallPO getArrivalDoc(String OrderID) throws RemoteException {
		int len=arrivaldoclist.size();
		for(int i=0;i<len;i++){
			if(arrivaldoclist.get(i).getOrderID().equals(OrderID)){
				return arrivaldoclist.get(i);
			}
		}
		
		
		return null;
	}

	@Override
	public boolean writeAllArrivalDoc() throws RemoteException {
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, arrivaldoclist);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	@Override
	public ArrayList<ArrivalDocBusinessHallPO> getArrivalDoclist() throws RemoteException {
		return arrivaldoclist;
	}

	
	public boolean changeBusinessHallArrivalDoc(ArrivalDocBusinessHallPO po) throws RemoteException{
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
