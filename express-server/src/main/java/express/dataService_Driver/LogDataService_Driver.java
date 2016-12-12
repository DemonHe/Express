package express.dataService_Driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.logDataService.LogDataService;
import express.po.LogPO;

public class LogDataService_Driver {
	
	public void drive(LogDataService logdataservice){
		try {
			ArrayList<LogPO> log = logdataservice.getSystemLog();
			System.out.println("Get succeed.");
			logdataservice.createSystemLog(new LogPO("2015年10月25日15点23分","审批单据"));
			System.out.println("Add succeed.");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
