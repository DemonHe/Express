package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.TransCenterShipmentDocDataService;
import express.po.ArrivalDocTransCenterPO;
import express.po.DeliverDocPO;
import express.po.ShipmentDocBusinessHallPO;
import express.po.ShipmentDocTransCenterPO;

public class ShipmentDocTransCenterIO extends UnicastRemoteObject implements TransCenterShipmentDocDataService{
	ArrayList<ShipmentDocTransCenterPO> shipmentdoclist;
	String filepath="SerializableData/ARShipmentDocTRANS.ser";
	
	
	
	public ShipmentDocTransCenterIO() throws RemoteException {
		super();
		shipmentdoclist=new ArrayList<ShipmentDocTransCenterPO>();
		try{
			IOHelper io=new IOHelper();
			shipmentdoclist=(ArrayList<ShipmentDocTransCenterPO>) io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean createShipmentDoc(ShipmentDocTransCenterPO po) throws RemoteException{
		System.out.println("writing...shipmentdoc.....");
		shipmentdoclist.add(po);
		//writeAllShipmentDoc();//决定是否在此时写入磁�?
		return true;	
		
	}
	public ShipmentDocTransCenterPO getShipmentDoc(String ShipmenID) throws RemoteException{  //根据订单号找装车单？//根据shipmentID
		int len=shipmentdoclist.size();
		for(int i=0;i<len;i++){
			if(shipmentdoclist.get(i).getShipmentID().equals(ShipmenID)){
				return shipmentdoclist.get(i);
			}

		}
		//not find
		return null;
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
	public ArrayList<ShipmentDocTransCenterPO> getShipmentDoclist() throws RemoteException{
		return shipmentdoclist;
	}
	
	public boolean changeTransCenterShipmentDoc(ShipmentDocTransCenterPO po) throws RemoteException{
		String shpimentid=po.getShipmentID();
		int len=shipmentdoclist.size();
		for(int i=0;i<len;i++){
			if(shipmentdoclist.get(i).getShipmentID().equals(shpimentid)){
				shipmentdoclist.set(i, po);
				writeAllShipmentDoc();
				return true;
			}
		}
		return false;
	}
	
	
}
