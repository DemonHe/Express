package express.businessLogicService_Driver;

import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.vo.SalaryStrategyVO;
import express.vo.UserInfoVO;

public class StaffManageBLService_Driver {
	public void drive(StaffManageBLService managerservice){
		if(managerservice.isCellPhoneAvailable("18362929967")&&managerservice.isJoininDateAvailable("2015年9月1日")&&managerservice.isUserIDAvailable("1001100"))
			//managerservice.addUserFromManager(new UserInfoVO("卢海龙",true,"1001100","18362929967",UserRole.DeliverMan,"2015年9月1日"));
		//managerservice.setSalaryStrategy(new SalaryStrategyVO());
		managerservice.endManage();
		System.out.println("Closing...");
		System.out.println("Close succeed.");
	}
}
