package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.OutDocDataService;
import express.po.OutDocPO;

public class OutDocIO extends UnicastRemoteObject implements OutDocDataService{

	public ArrayList<OutDocPO> outDoclist;
	public String filepath="SerializableData/AROutDoc.ser";
	
	
	public OutDocIO() throws RemoteException {
		super();
		outDoclist=new ArrayList<OutDocPO>();
		try{
			IOHelper io=new IOHelper();
			outDoclist=(ArrayList<OutDocPO>) io.readFromDisk(filepath);		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}


	@Override
	public boolean crateOutDoc(OutDocPO po) throws RemoteException {
		System.out.println("writing...outdoc......");
		outDoclist.add(po);
		return true;
	}


	@Override
	public OutDocPO getOutDocPO(String deliveryNumber) throws RemoteException {
		for(OutDocPO po:outDoclist){
			if(po.getOrderID().equals(deliveryNumber)){
				return po;
			}
		}
		return null;
	}


	@Override
	public ArrayList<OutDocPO> getOutDocPOlist() throws RemoteException {
		return outDoclist;	
	}


	@Override
	public boolean writeAllOutDoc() throws RemoteException {
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, outDoclist);
			return true;
		}catch(Exception e){
			return false;
		}
	}


	@Override
	public boolean changeOutDoc(OutDocPO po) throws RemoteException {
		String orderid=po.getOrderID();
		int len=outDoclist.size();
		for(int i=0;i<len;i++){
			if(outDoclist.get(i).getOrderID().equals(orderid)){
				outDoclist.set(i, po);
				writeAllOutDoc();
				return true;
			}
		}
		return false;

	}

}
