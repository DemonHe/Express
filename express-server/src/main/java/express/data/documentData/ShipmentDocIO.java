package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.BusinessSaleShipmentDocumentDataService;
import express.po.ArrivalDocPO;
import express.po.DeliverDocPO;
import express.po.ShipmentDocPO;

public class ShipmentDocIO extends UnicastRemoteObject implements BusinessSaleShipmentDocumentDataService{
	ArrayList<ShipmentDocPO> shipmentdoclist;
	String filepath="SerializableData/ARShipmentDoc.ser";
	
	
	
	public ShipmentDocIO() throws RemoteException {
		super();
		shipmentdoclist=new ArrayList<ShipmentDocPO>();
		try{
			IOHelper io=new IOHelper();
			shipmentdoclist=(ArrayList<ShipmentDocPO>) io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean createShipmentDoc(ShipmentDocPO po){
		System.out.println("writing...shipmentdoc.....");
		shipmentdoclist.add(po);
		//writeAllShipmentDoc();//决定是否在此时写入磁盘
		return true;	
		
	}
	public ShipmentDocPO getShipmentDoc(String OrderID){
		int len=shipmentdoclist.size();
		for(int i=0;i<len;i++){
			if(shipmentdoclist.get(i).getOrderID().equals(OrderID)){
				return shipmentdoclist.get(i);
			}
		}
		//not find
		return new ShipmentDocPO(null, null, null, null, null, null, null, null, 0);
	}
	
	
	@Override
	public boolean writeAllShipmentDoc() throws RemoteException {
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, shipmentdoclist);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	@Override
	public ArrayList<ShipmentDocPO> getShipmentDoclist() throws RemoteException{
		return shipmentdoclist;
	}
}
