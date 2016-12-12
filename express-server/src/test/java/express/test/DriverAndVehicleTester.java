package express.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import express.data.vehicleAndDriverData.DriverIO;
import express.data.vehicleAndDriverData.VehicleIO;
import express.po.DriverInfoPO;
import express.po.VehicleInfoPO;

public class DriverAndVehicleTester {

	@Test
	public void test() {
		/*try {
			DriverIO driver=new DriverIO();
			VehicleIO vehicle=new VehicleIO();
			assertEquals(null,driver.getDriverInfoList());
			assertEquals(null,vehicle.getVehicleInfoList());
			DriverInfoPO d1=new DriverInfoPO("10010", "1","张三","2015-12-2", "1001001", "12345678901", false, 0);
			driver.createDriverInfo(d1);
			driver.writeAllDriverInfo();
			assertEquals(d1,driver.getDriverInfoList().get(0));
			VehicleInfoPO v1=new VehicleInfoPO("100", "苏A1111",false);
			vehicle.createVehicleInfo(v1);
			vehicle.writeAllVehicleInfo();
			assertEquals(v1,vehicle.getVehicleInfoList().get(0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
