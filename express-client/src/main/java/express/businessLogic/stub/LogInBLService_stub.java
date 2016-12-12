package express.businessLogic.stub;

import express.businesslogicService.signBLService.LogInBLService;
import express.vo.SignInVO;
import express.vo.UserInfoSignVO;

public class LogInBLService_stub implements LogInBLService{
	public SignInVO isIdentified(String id,String password){
		if(id.equals("1001001")&&password.equals("123456")){
			System.out.println("id identified");
		}
		else{
			System.out.println("id is not identified");
		}
		return null;
		
	}
	public boolean isSigned(String id){
		if(id.equals("1001001")){
			return true;
		}
		else{
			return false;
		}
	}
	public UserInfoSignVO getUserInfo(String id){
		if(id.equals("1001001")){
			System.out.println("id :1001001 information has been get");
		}
		else{
			System.out.println("cannot find infomation");
		}
		return null;
	}
	public boolean LogOut(String id){
		if(id.equals("1001001")){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean changeSigned(String id){
		if(id.equals("1001001")){
			return true;
		}
		else{
			return false;
		}
	}
	public SignInVO signIn(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean SignOut(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
