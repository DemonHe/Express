package express.data.documentData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.po.GoodTransStatusPO;
import express.po.ReceiveInfoPO;

public class GoodTransStatusIO extends UnicastRemoteObject implements GoodsStatusDataService{
	ArrayList<GoodTransStatusPO> goodtranslist;
	String filepath="SerializableData/ARGoodtransstatus.ser";
	public GoodTransStatusIO() throws RemoteException{
		super();
		goodtranslist=new ArrayList<GoodTransStatusPO>();
		try{
			IOHelper io=new IOHelper();
			goodtranslist=(ArrayList<GoodTransStatusPO>)io.readFromDisk(filepath);	
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public GoodTransStatusPO search(String id) throws RemoteException{
		System.out.println("entered");
		int len=goodtranslist.size();
		for(int i=0;i<len;i++){
			if(goodtranslist.get(i).getOrderID().equals(id)){
				System.out.println(i);
				return goodtranslist.get(i);
				
			}
		}
		GoodTransStatusPO notfind=new GoodTransStatusPO();
		notfind.setOrderID("-1");
		return notfind;
	}
	
	
	public boolean changeGoodtransstatus(GoodTransStatusPO po) throws RemoteException{
		String id=po.getOrderID();	
		int len=goodtranslist.size();
		for(int i=0;i<len;i++){
			if(goodtranslist.get(i).getOrderID().equals(id)){
				goodtranslist.remove(i);
				break;
			}
		}
		goodtranslist.add(po);
		return true;
		
	}//可以改也可以直接添加
	
	
	public boolean writeAllGoodTransStatus() throws RemoteException{
		try{
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, goodtranslist);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	
}
