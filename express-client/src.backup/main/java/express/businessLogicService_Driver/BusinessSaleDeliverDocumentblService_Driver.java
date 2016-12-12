//package express.businessLogicService_Driver;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleDeliverDocumentblService;
//import express.vo.DeliverDocVO;
//
//public class BusinessSaleDeliverDocumentblService_Driver {
//	public void drive(BusinessSaleDeliverDocumentblService deliverDocservice){
//		if (deliverDocservice.isOrderIDavailable("10001")){
//			deliverDocservice.addDeliverDoc(new DeliverDocVO());
//			System.out.println("DeliverDoc added");
//		}
//		
//		DeliverDocVO deliverDocVO=new DeliverDocVO();
//		deliverDocVO=deliverDocservice.getDeliverDoc("1001");
//		System.out.println("DeliverDoc got");
//		
//		deliverDocservice.endDeliverDoc();
//		
//	}
//}
