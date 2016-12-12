package express.businesslogicService.signBLService;

import express.vo.SignInVO;
import express.vo.UserInfoSignVO;

public interface LogInBLService {
	
	public SignInVO signIn(String id,String password);
	
	public UserInfoSignVO getUserInfo(String id);
	
	public boolean SignOut(String id);
}
