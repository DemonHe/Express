//package express.dataService_Driver;
//
//import express.dataService.documentDataService.BusinessSaleReceiveDocumentDataService;
//import express.po.ReceiveDocPO;
//
//public class BusinessSaleReceiveDocumentDataService_Driver {
//	public void drive(BusinessSaleReceiveDocumentDataService receiveDocservice){
//		boolean create=receiveDocservice.createReceiveDoc(new ReceiveDocPO("", 0.5, "", null));
//		if (create){
//			System.out.println("ReceiveDoc created");
//		}
//		
//		ReceiveDocPO receiveDocPO=new ReceiveDocPO("", 0.5, "", null);
//		receiveDocPO=receiveDocservice.getReceiveDoc("10001");
//		System.out.println("ReceiveDoc got");
//		
//
//	}
//}
