//package express.businessLogicService_Driver;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleReceiveDocumentblService;
//import express.vo.ReceiveDocVO;
//
//public class BusinessSaleReceiveDocumentblService_Driver {
//	public void drive(BusinessSaleReceiveDocumentblService receiveDocservice){
//		if (receiveDocservice.isDateAvailable("2015-10-01")){
//			receiveDocservice.addReceiveDoc(new ReceiveDocVO());
//			System.out.println("ReceiveDoc added");
//		}
//		
//		ReceiveDocVO receiveDocVO=new ReceiveDocVO();
//		receiveDocVO=receiveDocservice.getReceiveDoc("2015-10-01");
//		System.out.println("ReceiveDoc got");
//		
//		receiveDocservice.endReceiveDoc();
//		System.out.println("end inputing");
//		
//	}
//}
