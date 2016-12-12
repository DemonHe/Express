package express.businessLogicService_Driver;

import java.util.ArrayList;

import express.businesslogicService.managerBLService.SysLogBLService;
import express.vo.LogVO;

public class SyslogBLService_Driver {

	public void drive(SysLogBLService syslogservice){
		ArrayList<LogVO> log = syslogservice.getSystemLog();
		System.out.println("Get succeed.");
		//syslogservice.addSystemLog("2015年10月26日10点36分","人员机构管理");
		System.out.println("Add succeed.");
	}
}
