//package express.test;
//
//import static org.junit.Assert.*;
//
//import java.rmi.RemoteException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import org.junit.Test;
//
//import express.data.documentData.PaymentDocIO;
//import express.po.PaymentDocPO;
//import express.po.PaymentItem;
//
//public class PaymentDocTester {
//
//	@Test
//	public void test() {
//		try {
//			PaymentDocIO pay = new PaymentDocIO();
//			
//			PaymentDocPO po = new PaymentDocPO();
//			
//			Date d=new Date();
//			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dateFactory=format.format(d);
//			String currDate=dateFactory.substring(0, 7);
//			String date=dateFactory.substring(0, 10);
//			String cd=dateFactory.substring(5, 7);
//			if(cd.charAt(0)=='0')
//				cd=cd.substring(1, 2);
//			
//			po.setID(dateFactory);
//			
//			ArrayList<PaymentItem> list = new ArrayList<PaymentItem>();
//			
//			PaymentItem p1 = new PaymentItem("张三", date, "S快递公司", "人员工资", cd + "月份工资", 3000);
//			
//			for(int i=0;i<12;i++){
//				list.add(p1);
//			}
//			
//			po.setPaymentList(list);
//			po.setSum(3000*12);
//			
//			pay.createPaymentDoc(po);
//			pay.writeAllPaymentDoc();
//			
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
