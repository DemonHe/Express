//package express.businessLogicService_Driver;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleArrivalDocumentblService;
//import express.vo.ArrivalDocVO;
//
//public class BusinessSaleArrivalDocumentblService_Driver {
//	public void drive(BusinessSaleArrivalDocumentblService arrivalDocservice){
//		
//		ArrivalDocVO arrivalDocVO1=new ArrivalDocVO();
//		ArrivalDocVO arrivalDocVO2=new ArrivalDocVO();
//		
//		boolean add=arrivalDocservice.addArrivalDoc(arrivalDocVO1);
//		if (add){
//			System.out.println("ArrivalDoc added");
//		}
//		
//		arrivalDocVO2=arrivalDocservice.getArrivalDoc("100001");
//		System.out.println("ArrivalDoc got");
//		
//		arrivalDocservice.endArrivalDoc();
//		System.out.println("ending inputing");
//	}
//}
