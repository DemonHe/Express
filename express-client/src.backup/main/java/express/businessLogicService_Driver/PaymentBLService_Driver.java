//package express.businessLogicService_Driver;
//
//import express.businesslogicService.financialBLService.PaymentBLService;
//import express.vo.DateAvailableVO;
//
//public class PaymentBLService_Driver {
//
//	public void drive(PaymentBLService paymentBLService){
//		paymentBLService.addPaymentDoc(null);
//		if(paymentBLService.checkDateAvailable("2015-10-26")==DateAvailableVO.Found)
//			System.out.println(DateAvailableVO.Found);
//		if(paymentBLService.checkDateAvailable("2015-10-25")!=DateAvailableVO.Found)
//			System.out.println(DateAvailableVO.NotFound);
//		paymentBLService.getPaymentDoc("1");
//		paymentBLService.endPaymentDoc();
//	}
//}
