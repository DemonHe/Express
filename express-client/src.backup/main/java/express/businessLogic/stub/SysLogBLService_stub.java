package express.businessLogic.stub;

import java.util.ArrayList;

import express.businesslogicService.managerBLService.SysLogBLService;
import express.vo.LogVO;

public class SysLogBLService_stub implements SysLogBLService{
	ArrayList<LogVO> log;
	
	public SysLogBLService_stub(ArrayList<LogVO> log){
		this.log = log;
	}
	
	public ArrayList<LogVO> getSystemLog (){
		return log;
	}
	
	public boolean addSystemLog (String time, String operation){
		log.add(new LogVO(time,operation));
		return false;
	}

	public void addSysLog(String operation) {
		// TODO Auto-generated method stub
		
	}
}
