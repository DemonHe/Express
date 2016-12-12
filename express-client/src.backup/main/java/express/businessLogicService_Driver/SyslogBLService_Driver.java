package express.businessLogicService_Driver;

import java.util.ArrayList;

import express.businesslogicService.managerBLService.SysLogBLService;
import express.vo.LogVO;

public class SyslogBLService_Driver {

	public void drive(SysLogBLService syslogservice){
		ArrayList<LogVO> log = syslogservice.getSystemLog();
		System.out.println("Get succeed.");
		//syslogservice.addSystemLog("2015å¹?10æœ?26æ—?10ç‚?36åˆ?","äººå‘˜æœºæ„ç®¡ç†");
		System.out.println("Add succeed.");
	}
}
