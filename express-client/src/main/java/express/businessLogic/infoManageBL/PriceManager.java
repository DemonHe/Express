package express.businessLogic.infoManageBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.strategyDataService.PriceDataService;
import express.po.DeliveryType;
import express.po.PricePO;
import express.vo.PriceVO;
import express.rmi.RMIClient;

public class PriceManager{
	public double getPricePerKMPerKilo(DeliveryType type) {
		PriceDataService rmiObj=RMIClient.getPriceStrategy();
		try {
			ArrayList<PricePO> arr=rmiObj.getPriceStrategyList();
			int len=arr.size();
			for(int i=0;i<len;i++){
				if(type.equals(arr.get(i).getDeliveryType())){
					return arr.get(i).getPrice();
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public boolean setPricePerKMPerKilo(PriceVO vo){
		PricePO po=new PricePO(vo.getPrice(), vo.getDeliveryType());
		PriceDataService rmiObj=RMIClient.getPriceStrategy();
		try{
			rmiObj.setPriceStrategy(po);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	}
	
}