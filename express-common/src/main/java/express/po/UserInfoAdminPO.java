package express.po;

import java.io.Serializable;

public class UserInfoAdminPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5302126120989367642L;
	private String name;
	private String id;
	private UserRole position;
	private String password;
	
	public UserInfoAdminPO(String name,String id,UserRole position,String key){
		this.name = name;
		this.id = id;
		this.position = position;
		this.password = key;
	}
	
	public UserInfoAdminPO(){
		this.name = null;
		this.id = null;
		this.position = null;
		this.password = null;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean setName(String name){
		this.name = name;
		return false;
	}
	
	public String getID(){
		return id;
	}
	
	public boolean setID(String id){
		this.id = id;
		return false;
	}
	
	public UserRole getPosition(){
		return position;
	}
	
	public boolean setPosition(UserRole pos){
		this.position = pos;
		return false;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean setPassword(String key){
		this.password  = key;
		return false;
	}
}
