package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.TransCenterTransferDocDataService;
import express.po.DeliverDocPO;
import express.po.TransferDocPO;
import javafx.scene.control.Tab;

public class TransferDocIO extends UnicastRemoteObject implements TransCenterTransferDocDataService{
	ArrayList<TransferDocPO> transferdoclist;
	String filepath="SerializableData/ARTransferDoc.ser";
	
	public TransferDocIO() throws RemoteException {
		super();
		transferdoclist=new ArrayList<TransferDocPO>();
		try{
			IOHelper io=new IOHelper();
			transferdoclist=(ArrayList<TransferDocPO>) io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createTransferDoc(TransferDocPO po) throws RemoteException {
		System.out.println("writing...deliverdoc......");
		transferdoclist.add(po);
		//writeAllDeliverDoc();//决定是否在此时写入磁盘
		return true;
	}

	@Override
	public TransferDocPO getTransferDoc(String transID) throws RemoteException {
		for(TransferDocPO po:transferdoclist){
			if(po.getTransDocID().equals(transID)){
				return po;
			}
		}
		return null;
	}

	@Override
	public boolean writeAllTransferDoc() throws RemoteException {
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, transferdoclist);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public ArrayList<TransferDocPO> getTransferDoclist() throws RemoteException {
		return transferdoclist;
	}
	
	public boolean changeTransferDoc(TransferDocPO po) throws RemoteException{
		String transid=po.getTransDocID();
		int len=transferdoclist.size();
		for(int i=0;i<len;i++){
			if(transferdoclist.get(i).getTransDocID().equals(transid)){
				transferdoclist.set(i, po);
				writeAllTransferDoc();
				return true;
			}
		}
		return false;
	}
	

}
