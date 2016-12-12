package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.vo.UserInfoVO;

public interface UserRegisterService {
	
	public boolean register(String id);
	
	public ArrayList<UserInfoVO> getUnregistered();
}
