package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.BusinessSaleShipmentDocDataService;
import express.po.ShipmentDocBusinessHallPO;


public class ShipmentDocBusinessHallIO extends UnicastRemoteObject implements BusinessSaleShipmentDocDataService{
	ArrayList<ShipmentDocBusinessHallPO> shipmentdoclist;
	String filepath="SerializableData/ARShipmentDocBUSHALL.ser";
	
	
	public ShipmentDocBusinessHallIO() throws RemoteException {
		super();
		shipmentdoclist=new ArrayList<ShipmentDocBusinessHallPO>();
		try{
			IOHelper io=new IOHelper();
			shipmentdoclist=(ArrayList<ShipmentDocBusinessHallPO>) io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createShipmentDoc(ShipmentDocBusinessHallPO po) throws RemoteException {
		System.out.println("writing...shipmentdoc.....");
		shipmentdoclist.add(po);
		//writeAllShipmentDoc();//决定是否在此时写入磁盘
		return true;	

	}

	@Override
	public ShipmentDocBusinessHallPO getShipmentDoc(String shipmentID) throws RemoteException {
		int len=shipmentdoclist.size();
		for(int i=0;i<len;i++){
			if(shipmentdoclist.get(i).getShipmentID().equals(shipmentID)){
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
	public ArrayList<ShipmentDocBusinessHallPO> getShipmentDoclist() throws RemoteException {
		return shipmentdoclist;
	}
	
	public boolean changeBusinessHallShipmentDoc(ShipmentDocBusinessHallPO po) throws RemoteException{
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
