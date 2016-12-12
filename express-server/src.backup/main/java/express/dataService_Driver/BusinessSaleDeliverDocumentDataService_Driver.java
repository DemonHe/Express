//package express.dataService_Driver;
//
//import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
//import express.po.DeliverDocPO;
//
//public class BusinessSaleDeliverDocumentDataService_Driver {
//	public void drive(BusinessSaleDeliverDocumentDataService deliverDocservice){
//		boolean create=deliverDocservice.createDeliverDoc(new DeliverDocPO(null, null, null));
//		if (create){
//			System.out.println("DeliverDoc created");
//		}
//		
//		DeliverDocPO deliverDocPO=new DeliverDocPO(null, null, null);
//		deliverDocPO=deliverDocservice.getDeliverDoc("10001");
//		System.out.println("DeliverDoc got");
//		
//
//	}
//}
