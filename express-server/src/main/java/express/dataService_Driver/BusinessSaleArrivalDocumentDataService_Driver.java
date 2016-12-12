//package express.dataService_Driver;
//
//import express.dataService.documentDataService.BusinessSaleArrivalDocumentDataService;
//import express.po.ArrivalDocPO;
//
//public class BusinessSaleArrivalDocumentDataService_Driver {
//	public void drive(BusinessSaleArrivalDocumentDataService arrivalDocservice){
//		boolean create=arrivalDocservice.createArrivalDoc(new ArrivalDocPO(null, null, null, null, null));
//		if (create){
//			System.out.println("ArrivalDoc created");
//		}
//		
//		ArrivalDocPO arrivalDocPO=new ArrivalDocPO(null, null, null, null, null);
//		arrivalDocPO=arrivalDocservice.getArrivalDoc("10001");
//		System.out.println("ArrivalDoc got");
//		
//		
//	}
//}
