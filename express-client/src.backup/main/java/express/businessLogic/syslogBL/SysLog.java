package express.businessLogic.syslogBL;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import express.businesslogicService.managerBLService.SysLogBLService;
import express.dataService.logDataService.LogDataService;
import express.po.LogPO;
import express.rmi.RMIClient;
import express.vo.LogVO;

public class SysLog implements SysLogBLService{

	LogDataService log;
	
	public SysLog(){
		log=RMIClient.getLogObject();
	}
	
	public ArrayList<LogVO> getSystemLog() {
		try {
			ArrayList<LogPO> logListPO=log.getSystemLog();
			if(logListPO==null)
				return null;
			ArrayList<LogVO> logList=new ArrayList<LogVO>();
			for(LogPO log:logListPO)
				logList.add(new LogVO(log));
			return logList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void addSysLog(String operation){
		//System.currentTimeMillis();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		try {
			log.createSystemLog(new LogPO(operation,time));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
