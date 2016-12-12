//package express.test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import express.businessLogic.statisticBL.ProfitStatistic;
//import express.rmi.ClientException;
//import express.rmi.RMIClient;
//import express.vo.ProfitFormVO;
//
//public class ProfitFormTester {
//
//	@Test
//	public void test() {
//		try {
//	        RMIClient.init();
//	    } catch (ClientException e) {
//	        e.printStackTrace(); 
//	    }
//		
//		ProfitStatistic profit=new ProfitStatistic();
//		ProfitFormVO profitForm=profit.getProfitForm("2015-12-06");
//		ProfitFormVO profitForm1=new ProfitFormVO("2015-12-07",5000,3000,2000);
//		profit.exportExcel(profitForm1);
//	}
//
//}
