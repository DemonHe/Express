package express.businessLogicService_Driver;

import express.businesslogicService.adminBLService.AdminBLService;
import express.po.UserRole;
import express.vo.UserInfoAdminVO;

public class AdminBLService_Driver {
	public void drive(AdminBLService adminblservice){
		//if(adminblservice.isExisted("1001100")){
			//adminblservice.addUserID(new UserInfoAdminVO("卢海龙","1001100",UserRole.DeliverMan,"123456"));
		//}
		//UserInfoAdminVO userinfo = adminblservice.getUserInfo("1001100");
		System.out.println("Get succeed.");
		//adminblservice.changeUserPassword("1001100");
		//adminblservice.changeUserPosition("1001100");
		adminblservice.endManage();
		System.out.println("Closing...");
		System.out.println("Close succeed.");
	}
}
