package express.data.documentData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.documentDataService.PredictTimeDataService;
import express.po.HistoryTimePO;
import express.po.PredictTimePO;

public class PredictTimeIO extends UnicastRemoteObject implements PredictTimeDataService{
	ArrayList<PredictTimePO> predictTimelist;
	String filepath="SerializableData/ARPredictTime.ser";
	
	public PredictTimeIO() throws RemoteException {
		super();
		predictTimelist=new ArrayList<PredictTimePO>();
		try{
			IOHelper io=new IOHelper();
			predictTimelist=(ArrayList<PredictTimePO>)io.readFromDisk(filepath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public boolean addToHistory(HistoryTimePO po) throws RemoteException{
		int len=predictTimelist.size();
		String setout=po.getStartCity();
		String arrival=po.getEndCity();
		for(int i=0;i<len;i++){
			PredictTimePO ptpo=predictTimelist.get(i);
			if(ptpo.getSetOutCity().equals(setout)&&ptpo.getArrivalCity().equals(arrival)){
				ptpo.addHistoryTime(po);
				return true;
			}

		}
		return false;
	}
	
	public PredictTimePO getPredictlist(String startCity,String endCity) throws RemoteException{
		String setout=startCity;
		String arrival=endCity;
		int len=predictTimelist.size();
		System.out.println(len);
		for(int i=0;i<len;i++){
			PredictTimePO ptpo=predictTimelist.get(i);
			if(ptpo.getSetOutCity().equals(setout)&&ptpo.getArrivalCity().equals(arrival)){
				return ptpo;
			}
		}
		System.out.println("FAlse?");
		
		return null;
	}
	
	public boolean writeAllPredictTime() throws RemoteException{
		try {
			IOHelper io=new IOHelper();
			io.writeToDisk(filepath, predictTimelist);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
		
	}
	
//	public static void main(String[] args){
//		PredictTimePO po=new PredictTimePO();
//		po.setArrivalCity("北京");
//		po.setSetOutCity("南京");
//		HistoryTimePO hp=new HistoryTimePO("南京", "北京", "2015-12-09", "2015-12-11");
//		po.addHistoryTime(hp);
//		
//		
//		try {
//			PredictTimeIO io=new PredictTimeIO();
//			io.predictTimelist.add(po);
//			io.writeAllPredictTime();
//			
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	
	
	
}
