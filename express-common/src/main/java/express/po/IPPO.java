package express.po;

import java.io.Serializable;

public class IPPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2578942435121656061L;


	public IPPO(String ip,String name){
		this.ipaddress=ip;
		this.name=name;
	}
	
	private String ipaddress;
	private String name;
	
	
	public String getIP(){
		return ipaddress;
	}
	
	public String getName() {
		return name;
	}
}
