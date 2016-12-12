package express.data.strategyData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import express.data.IOHelper.IOHelper;
import express.dataService.strategyDataService.DistanceDataService;
import express.po.CityDistancePO;

public class DistanceIO extends UnicastRemoteObject implements DistanceDataService{

	String filename="SerializableData/Distance.ser";
	ArrayList<CityDistancePO> distanceStartegyList;
	
	public DistanceIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		distanceStartegyList=new ArrayList<CityDistancePO>();
		try {
			distanceStartegyList=(ArrayList<CityDistancePO>)io.readFromDisk(filename);
		} catch (Exception e) {
			
		} 
	}

	@Override
	public boolean addDistanceStrategy(CityDistancePO po) throws RemoteException {
		distanceStartegyList.add(po);
		return true;
	}

	
	@Override
	public boolean addDistanceStrategy(CityDistancePO po,int insert) throws RemoteException {  //insert in certain place
		distanceStartegyList.add(insert ,po);
		return true;
	}
	
	
	@Override
	public boolean deleteDistanceStrategy(String city) throws RemoteException {
		
		ArrayList<CityDistancePO> anewlist=new ArrayList<CityDistancePO>();
		
		if(distanceStartegyList.size()>0){
			for(CityDistancePO distance:distanceStartegyList){
				if(distance.getCity1().equals(city)||distance.getCity2().equals(city)){
					//distanceStartegyList.remove(distance);
				}
				else {
					anewlist.add(distance);
				}
			}
			distanceStartegyList=anewlist;
			
			return true;
		}
		return false;
	}

	@Override
	public boolean changeDistanceStrategy(CityDistancePO po) throws RemoteException {  //change twice
		if(distanceStartegyList.size()>0){
			String city1=po.getCity1();
			String city2=po.getCity2();
			
			int len=distanceStartegyList.size();
			int index=0;
			
			
			for(CityDistancePO distance:distanceStartegyList){
				if(distance.getCity1().equals(city1)&&distance.getCity2().equals(city2)){
					System.out.println("enter1");
					
					distanceStartegyList.set(index, po);
					return true;
				}
				index++;
			}
			
			CityDistancePO poreverse=new CityDistancePO(city2, city1, po.getDistance());
			
			
			index=0;
			for(CityDistancePO distance:distanceStartegyList){
				if(distance.getCity1().equals(city2)&&distance.getCity2().equals(city1)){
					System.out.println("enter2");
					distanceStartegyList.set(index, poreverse);
					return true;
				}
				index++;
			}
		}
		return false;
	}

	@Override
	public ArrayList<CityDistancePO> getAllDistanceStrategy() throws RemoteException {
		if(distanceStartegyList.size()>0){
			return distanceStartegyList;
		}
		else{
			System.out.println("no size?");
			return null;
		}
	}

	@Override
	public boolean writeAllDistanceStrategy() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, distanceStartegyList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

	
//	public static void main(String[] args){
//		CityDistancePO po1=new CityDistancePO("北京", "上海", 1064.7);
//		CityDistancePO po2=new CityDistancePO("北京", "广州", 1888.8);
//		CityDistancePO po3=new CityDistancePO("北京", "南京", 900);
//		CityDistancePO po4=new CityDistancePO("北京", "北京", 30);
//		
//		CityDistancePO po5=new CityDistancePO("上海", "北京", 1064.7);
//		CityDistancePO po6=new CityDistancePO("上海", "广州", 1213);
//		CityDistancePO po7=new CityDistancePO("上海", "南京", 266);
//		CityDistancePO po8=new CityDistancePO("上海", "上海", 30);
//		
//		CityDistancePO po9=new CityDistancePO("广州", "北京", 1888.8);
//		CityDistancePO po10=new CityDistancePO("广州", "上海", 1213);
//		CityDistancePO po11=new CityDistancePO("广州", "南京", 1132);
//		CityDistancePO po12=new CityDistancePO("广州", "广州", 30);
//		
//		CityDistancePO po13=new CityDistancePO("南京", "北京", 900);
//		CityDistancePO po14=new CityDistancePO("南京", "上海", 266);
//		CityDistancePO po15=new CityDistancePO("南京", "广州", 1132);
//		CityDistancePO po16=new CityDistancePO("南京", "南京", 30);
//		
//		
//		try {
//			DistanceIO dio=new DistanceIO();
//			dio.addDistanceStrategy(po1);
//			dio.addDistanceStrategy(po2);
//			dio.addDistanceStrategy(po3);
//			dio.addDistanceStrategy(po4);
//			dio.addDistanceStrategy(po5);
//			dio.addDistanceStrategy(po6);
//			dio.addDistanceStrategy(po7);
//			dio.addDistanceStrategy(po8);
//			dio.addDistanceStrategy(po9);
//			dio.addDistanceStrategy(po10);
//			dio.addDistanceStrategy(po11);
//			dio.addDistanceStrategy(po12);
//			dio.addDistanceStrategy(po13);
//			dio.addDistanceStrategy(po14);
//			dio.addDistanceStrategy(po15);
//			dio.addDistanceStrategy(po16);
//			dio.writeAllDistanceStrategy();
//			
//			
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	


}
