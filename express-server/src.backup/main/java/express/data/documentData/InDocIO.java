package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.InDocDataService;
import express.po.InDocPO;
import express.po.OutDocPO;

public class InDocIO extends UnicastRemoteObject implements InDocDataService{
	public ArrayList<InDocPO> indoclist;
	public String filepath="SerializableData/ARInDoc.ser";
	
	
	public InDocIO() throws RemoteException {
		super();
		indoclist=new ArrayList<InDocPO>();
		try{
			IOHelper io=new IOHelper();
			indoclist=(ArrayList<InDocPO>) io.readFromDisk(filepath);		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}	

	}

	@Override
	public boolean createInDoc(InDocPO po) throws RemoteException {
		System.out.println("writing...indoc......");
		indoclist.add(po);
		return true;
	}

	@Override
	public InDocPO getInDocPO(String orderID) throws RemoteException {
		for(InDocPO po:indoclist){
			if(po.getdeliveryNumber().equals(orderID)){
				return po;
			}
		}
		return null;
	}

	@Override
	public ArrayList<InDocPO> getInDocPOlist() throws RemoteException {
			return indoclist;
	}

	@Override
	public boolean writeAllInDoc() throws RemoteException {
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, indoclist);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean changeInDoc(InDocPO po) throws RemoteException {
		String orderid=po.getdeliveryNumber();
		int len=indoclist.size();
		for(int i=0;i<len;i++){
			if(indoclist.get(i).getdeliveryNumber().equals(orderid)){
				indoclist.set(i, po);
				writeAllInDoc();
				return true;
			}
		}
		return false;	
	}

	
}
