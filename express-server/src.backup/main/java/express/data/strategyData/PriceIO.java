package express.data.strategyData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import express.data.IOHelper.IOHelper;
import express.dataService.strategyDataService.PriceDataService;
import express.po.DeliveryType;
import express.po.PricePO;

public class PriceIO extends UnicastRemoteObject implements PriceDataService{
	//0--Slow
	//1--Standard
	//2--fast
	
	String filename="SerializableData/Price.ser";
	ArrayList<PricePO> priceStartegylist;
	
	public PriceIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		priceStartegylist=new ArrayList<PricePO>();
		try {
			priceStartegylist=(ArrayList<PricePO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}

	@Override
	public boolean setPriceStrategy(PricePO po) throws RemoteException {
			int len=priceStartegylist.size();
			for(int i=0;i<len;i++){
				if(po.getDeliveryType().equals(priceStartegylist.get(i).getDeliveryType())){
					
					priceStartegylist.get(i).setPrice(po.getPrice());
					return true;
				}
			}
			return false;
	}

	
	public ArrayList<PricePO> getPriceStrategyList() throws RemoteException {
		if(priceStartegylist!=null)
			return priceStartegylist;
		else
			return null;
	}

	@Override
	public boolean writePriceStrategy() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, priceStartegylist);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}
}
