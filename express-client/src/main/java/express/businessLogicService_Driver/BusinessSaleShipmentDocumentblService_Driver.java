//package express.businessLogicService_Driver;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleShipmentDocumentblService;
//import express.vo.ShipmentDocVO;
//
//public class BusinessSaleShipmentDocumentblService_Driver {
//	public void drive(BusinessSaleShipmentDocumentblService shipmentDocservice){
//		if (shipmentDocservice.isOrderIDavailable("10001")&&shipmentDocservice.isBusinessshallavailable("10001")
//				&&shipmentDocservice.isTransCarIDavailable("10001")){
//			boolean add=shipmentDocservice.addShipmentDoc(new ShipmentDocVO());
//			if (add){
//				System.out.println("ShipmentDoc added");
//				
//			}
//		}
//		
//		shipmentDocservice.endShipmentDoc();
//		System.out.println("end inputing");	
//	}
//}
