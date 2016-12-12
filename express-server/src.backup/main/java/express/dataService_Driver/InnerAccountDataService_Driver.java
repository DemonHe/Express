package express.dataService_Driver;

import java.rmi.RemoteException;

import express.dataService.innerAccountDataService.InnerAccountDataService;
import express.po.InnerAccountPO;

public class InnerAccountDataService_Driver {
	
	public void drive(InnerAccountDataService innerAccountDataService){
		try {
			innerAccountDataService.getInnerAccount(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
