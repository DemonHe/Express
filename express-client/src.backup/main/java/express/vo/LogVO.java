package express.vo;

import express.po.LogPO;

public class LogVO {
	private String operation;
	private String time;
	
	public LogVO(String time, String operation){
		this.time = time;
		this.operation = operation;
	}
	
	public LogVO(LogPO log){
		this.time=log.getTime();
		this.operation=log.getOperation();
	}
	
	public String getTime(){
		return time;
	}
	public String getOperation(){
		return operation;
	}
}
