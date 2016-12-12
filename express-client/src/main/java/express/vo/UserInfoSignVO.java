package express.vo;

import express.po.UserRole;

public class UserInfoSignVO {

	private String name;
	private String id;
	private UserRole role;
	
	public UserInfoSignVO(String name,String id,UserRole role){
		this.name=name;
		this.id=id;
		this.role=role;
	}
	
	public String getName(){
		return name;
	}
	
	public String getID(){
		return id;
	}
	
	public UserRole getPosition(){
		return role;
	}
}
