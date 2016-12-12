//package express.businessLogic.stub;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleArrivalDocumentblService;
//import express.po.GoodsArrivalStatus;
//import express.vo.ArrivalDocVO;
//
//public class BusinessSaleArrivalDocumentblService_stub implements BusinessSaleArrivalDocumentblService {
//	private String arriveDate;
//	private String transCenterID;//中转中心ID
//	private String transferDocID;//中转单ID
//	private String departure;//出发地
//	private GoodsArrivalStatus arrivalStatus;//枚举类  货物到达状态（损坏、完整、丢失）
//	
//	public ArrivalDocVO arrivalDocVO=new ArrivalDocVO();
//	
//	//constructor
//	public BusinessSaleArrivalDocumentblService_stub(String arriveDate,String transCenterID,
//						String transferDocID,String departure,
//						GoodsArrivalStatus arrivalStatus){
//		
//		this.arriveDate=arriveDate;
//		this.transCenterID=transCenterID;
//		this.transferDocID=transferDocID;
//		this.departure=departure;
//		this.arrivalStatus=arrivalStatus;	
//	}
//	public boolean addArrivalDoc(ArrivalDocVO vo){
//		System.out.println("We have added an ArrivalDocVO");
//		return true;
//	}
//	public ArrivalDocVO getArrivalDoc(String OrderID){
//		return arrivalDocVO;
//	}
//	public void endArrivalDoc(){
//		System.out.println("We have ended inputing");
//		
//	}
//}
