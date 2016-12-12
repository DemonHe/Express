package express.po;

import java.io.Serializable;

public class UserPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7651874347332546011L;
	private String id;
	private UserRole role;
	
	public UserPO(String i,UserRole r){
		id=i;
		role=r;
	}
	
	public String getID(){
		return id;
	}
	
	public UserRole getName(){
		return role;
	}

}