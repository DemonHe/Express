package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.ReceiveInfoDataService;
import express.po.DeliverDocPO;
import express.po.ReceiveInfoPO;

public class ReceiveInfoIO extends UnicastRemoteObject implements ReceiveInfoDataService{

	ArrayList<ReceiveInfoPO> receiveinfolist;
	String filepath="SerializableData/ARReceiveInfo.ser";
	public ReceiveInfoIO() throws RemoteException {
		super();
		receiveinfolist=new ArrayList<ReceiveInfoPO>();
		try{
			IOHelper io=new IOHelper();
			receiveinfolist=(ArrayList<ReceiveInfoPO>)io.readFromDisk(filepath);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean createReceiveInfo(ReceiveInfoPO po) {
		System.out.println("writing...receiveInfo......");
		receiveinfolist.add(po);
		//writeAllDeliverDoc();//决定是否在此时写入磁盘
		return true;
	}
	
	@Override
	public ReceiveInfoPO getReceiveInfo(String orderID) {
		int len=receiveinfolist.size();
		for(int i=0;i<len;i++){
			if(receiveinfolist.get(i).getOrderID().equals(orderID)){
				return receiveinfolist.get(i);
			}
		}

		return null;
	}
	
	@Override
	public boolean writeAllReceiveInfo() throws RemoteException {
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, receiveinfolist);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	@Override
	public ArrayList<ReceiveInfoPO> getReceiveInfolist() throws RemoteException {
		return receiveinfolist;
	}
	
	

}
