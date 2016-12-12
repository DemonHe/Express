package express.test;

import static org.junit.Assert.*;

import org.junit.Test;

import express.businessLogic.infoManageBL.Driver;
import express.rmi.RMIClient;
import express.vo.DriverInfoVO;

public class DriverTester {

	@Test
	public void test() {
		try {
			RMIClient.init();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		Driver d = new Driver();
		DriverInfoVO vo = new DriverInfoVO("001","001","1","1","1","1",false,1);
		d.addDriverInfo(vo);
		DriverInfoVO vo1 = d.getDriverInfo("001");
		vo1.setbusinesshallNumber("002");
		d.changeDriverInfo(vo1, "001");
		assertEquals("002",d.getDriverInfo("001").getbusinesshallNumber());
	}

}
