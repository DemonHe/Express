//package express.test;
//
//import static org.junit.Assert.*;
//
//import java.rmi.RemoteException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.Test;
//
//import express.data.statisticsData.ProfitFormIO;
//import express.po.ProfitFormPO;
//
//public class ProfitFormTester {
//
//	@Test
//	public void test() {
//		try {
//			ProfitFormIO profit=new ProfitFormIO();
//			
//			Date date=new Date();
//			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//			String time=format.format(date);
//			ProfitFormPO profitForm=new ProfitFormPO(time,3000,2000,1000);
//			//profit.createProfitForm(profitForm);
//			//profit.writeAllProfitForm();
//			System.out.println(profit.getProfitForm("2015-12-06").getProfit());
//			
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//
//}
