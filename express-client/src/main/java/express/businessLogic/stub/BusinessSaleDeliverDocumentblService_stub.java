//package express.businessLogic.stub;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleDeliverDocumentblService;
//import express.vo.DeliverDocVO;
//
//public class BusinessSaleDeliverDocumentblService_stub implements BusinessSaleDeliverDocumentblService {
//	private String arriveDate;
//	private String orderID;
//	private String deliverManID;//快递员工号
//	
//	
//	public DeliverDocVO deliverDocVO=new DeliverDocVO();
//	
//	//consturctor
//	public BusinessSaleDeliverDocumentblService_stub(String arriveDate,String orderID,String deliverManID){
//		this.arriveDate=arriveDate;	
//		this.orderID=orderID;
//		this.deliverManID=deliverManID;
//		
//	}
//	public boolean addDeliverDoc(DeliverDocVO vo){
//		return true;
//		
//	}
//	
//	public DeliverDocVO getDeliverDoc(String OrderID){
//		return deliverDocVO;
//	}
//	
//	public boolean isOrderIDavailable(String id){
//		return true;
//	}
//	
//	public void endDeliverDoc(){
//		
//	}
//	
//}
