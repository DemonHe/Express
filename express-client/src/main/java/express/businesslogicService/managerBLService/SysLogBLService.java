package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.vo.LogVO;

public interface SysLogBLService{
	
	public ArrayList<LogVO> getSystemLog();
	
	public void addSysLog(String operation);
}
