package express.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.data.vehicleAndDriverData.DriverIO;
import express.po.DriverInfoPO;

public class Test {

	@org.junit.Test
	public void test() {
		try {
			DriverIO driver=new DriverIO();
			ArrayList<DriverInfoPO> list=driver.getAllDriver();
			assertEquals(null,list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
