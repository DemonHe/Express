package express.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import express.data.userData.UserAdminIO;
import express.po.UserInfoAdminPO;
import express.po.UserRole;

public class SignInTester {

	@Test
	public void test() {
		UserAdminIO user;
		try {
			user = new UserAdminIO();
			UserInfoAdminPO  u1=new UserInfoAdminPO ("张三","admin",UserRole.Admin,"admin");
			UserInfoAdminPO  u2=new UserInfoAdminPO ("李四","1001001",UserRole.BusinessSale,"123456");
			UserInfoAdminPO  u3=new UserInfoAdminPO ("王五","1001002",UserRole.DeliverMan,"123456");
			UserInfoAdminPO  u4=new UserInfoAdminPO ("Peter","1001003",UserRole.Financial,"123456");
			UserInfoAdminPO  u5=new UserInfoAdminPO ("Jack","1001004",UserRole.Financial_highest,"123456");
			UserInfoAdminPO  u6=new UserInfoAdminPO ("Sandy","1001005",UserRole.Manager,"123456");
			UserInfoAdminPO  u7=new UserInfoAdminPO ("Adam","1001006",UserRole.TransCenterRepo,"123456");
			UserInfoAdminPO  u8=new UserInfoAdminPO ("卢海�?","1001007",UserRole.TransCenterSale,"123456");
			user.createUser(u1);
			user.createUser(u2);
			user.createUser(u3);
			user.createUser(u4);
			user.createUser(u5);
			user.createUser(u6);
			user.createUser(u7);
			user.createUser(u8);
			user.writeAllUserAdmin();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
