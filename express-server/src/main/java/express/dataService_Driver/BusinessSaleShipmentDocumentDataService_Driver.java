//package express.dataService_Driver;
//
//import express.dataService.documentDataService.BusinessSaleShipmentDocumentDataService;
//import express.po.ShipmentDocPO;
//
//public class BusinessSaleShipmentDocumentDataService_Driver {
//	public void drive(BusinessSaleShipmentDocumentDataService shipmentDocservice){
//		boolean create=shipmentDocservice.createShipmentDoc(new ShipmentDocPO(null, null, null, null, null, null, null, null, 0));
//		if (create){
//			System.out.println("ShipmentDoc created");
//		}
//		
//		ShipmentDocPO shipmentDocPO=new ShipmentDocPO(null, null, null, null, null, null, null, null, 0);
//		shipmentDocPO=shipmentDocservice.getShipmentDoc("10001");
//		System.out.println("ShipmentDoc got");
//		
//
//	}
//}
