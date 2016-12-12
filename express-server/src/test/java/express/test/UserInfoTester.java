package express.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import express.data.userData.UserInfoIO;
import express.po.UserInfoAdminPO;
import express.po.UserInfoPO;
import express.po.UserRole;

public class UserInfoTester {

	@Test
	public void test() {
		try {
			UserInfoIO user = new UserInfoIO();
			
			UserInfoPO us1 = new UserInfoPO("张三",true,"admin","18896963726",UserRole.Admin,"010000","2015-10-6",true);
			UserInfoPO us2 = new UserInfoPO("李四",true,"1001001","18896963736",UserRole.BusinessSale,"025001","2015-10-6",true);
			UserInfoPO us3 = new UserInfoPO("王五",true,"1001002","18896963738",UserRole.DeliverMan,"025001","2015-10-6",true);
			UserInfoPO us4 = new UserInfoPO("Peter",true,"1001003","18896963706",UserRole.Financial,"010000","2015-10-6",true);
			UserInfoPO us5 = new UserInfoPO("Jack",true,"1001004","18896963826",UserRole.Financial_highest,"010000","2015-10-6",true);
			UserInfoPO us6 = new UserInfoPO("Sandy",false,"1001005","18896963836",UserRole.Manager,"010000","2015-10-6",true);
			UserInfoPO us7 = new UserInfoPO("Adam",true,"1001006","18896963806",UserRole.TransCenterRepo,"0250","2015-10-6",true);
			UserInfoPO us8 = new UserInfoPO("卢海龙",true,"1001007","18896963938",UserRole.TransCenterSale,"0250","2015-10-6",true);
			
			user.createuser(us1);
			user.createuser(us2);
			user.createuser(us3);
			user.createuser(us4);
			user.createuser(us5);
			user.createuser(us6);
			user.createuser(us7);
			user.createuser(us8);
			user.writeAllUserInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
