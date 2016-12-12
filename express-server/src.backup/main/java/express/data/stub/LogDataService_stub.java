package express.data.stub;

import java.util.ArrayList;

import express.dataService.logDataService.LogDataService;
import express.po.LogPO;

public class LogDataService_stub implements LogDataService{
	ArrayList<LogPO> logpo;
	
	public LogDataService_stub(ArrayList<LogPO> log){
		logpo = log;
	}
	
	public ArrayList<LogPO> getSystemLog(){
		return logpo;
	}
	
	public boolean createSystemLog(LogPO po){
		logpo.add(po);
		return true;
	}
}
