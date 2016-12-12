package express.dataService.strategyDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.PricePO;

public interface PriceDataService extends Remote{

	public boolean setPriceStrategy(PricePO po) throws RemoteException;
	
	public boolean writePriceStrategy() throws RemoteException;

	public ArrayList<PricePO> getPriceStrategyList() throws RemoteException;
}
