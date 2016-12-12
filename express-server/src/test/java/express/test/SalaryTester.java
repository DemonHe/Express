package express.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import express.data.IOHelper.IOHelper;
import express.data.strategyData.SalaryIO;
import express.po.SalaryPO;
import express.po.Strategy;
import express.po.UserRole;

public class SalaryTester {

	@Test
	public void test() {
		try {
			SalaryIO salary=new SalaryIO();
			ArrayList<SalaryPO> list=new ArrayList<SalaryPO>();
			SalaryPO s1=new SalaryPO(UserRole.Admin,Strategy.FIXED,3000);
			SalaryPO s2=new SalaryPO(UserRole.BusinessSale,Strategy.FIXED,2500);
			SalaryPO s3=new SalaryPO(UserRole.DeliverMan,Strategy.PERCENT,10);
			SalaryPO s4=new SalaryPO(UserRole.Financial,Strategy.FIXED,4000);
			SalaryPO s6=new SalaryPO(UserRole.Manager,Strategy.FIXED,5000);
			SalaryPO s7=new SalaryPO(UserRole.TransCenterRepo,Strategy.FIXED,3000);
			SalaryPO s8=new SalaryPO(UserRole.TransCenterSale,Strategy.FIXED,3000);
			SalaryPO s9=new SalaryPO(UserRole.Driver,Strategy.COUNT_BY_TIME,100);
			
			list.add(s1);
			list.add(s2);
			list.add(s3);
			list.add(s4);
			list.add(s6);
			list.add(s7);
			list.add(s8);
			list.add(s9);
			IOHelper io = new IOHelper();
			io.writeToDisk("SerializableData/Salary.ser", list);
			//salary.writeAllSalaryStrategy();
			
			//assertEquals(UserRole.Admin,salary.getAllSalaryStrategy().get(0).getPosition());

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
