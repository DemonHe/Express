package express.data.stub;

import express.po.UserPO;
import express.po.UserRole;

public class LogInUserDataService_stub{

	String id;
	String passWord;
	String name;
	UserRole role;
	
	public LogInUserDataService_stub(String id,String name){
		this.id=id;
		this.name=name;
		
	}
	
	public UserPO getUser(String id,String password){
		if(id.equals("1001001")&&password.equals("123456"))
			return new UserPO(id,role);
		else{
			System.out.println("Not found");
			return null;
		}
	}
}
