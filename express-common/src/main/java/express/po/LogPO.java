package express.po;

import java.io.Serializable;

public class LogPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -572106608480900184L;
	private String operation;
	private String time;
	
	public LogPO(String operation,String time){
		this.operation = operation;
		this.time = time;
	}
	
	public String getOperation(){
		return operation;
	}
	
	public boolean setOperation(String operation){
		this.operation = operation;
		return false;
	}
	
	public String getTime(){
		return time;
	}
	
	public boolean setTime(String time){
		this.time = time;
		return false;
	}
}
