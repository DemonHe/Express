//package express.businessLogic.stub;
//
//import java.util.ArrayList;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleReceiveDocumentblService;
//import express.vo.ReceiveDocVO;
//import express.vo.ShipmentDocVO;
//
//public class BusinessSaleReceiveDocumentblService_stub implements BusinessSaleReceiveDocumentblService {
//	private String receiveDate;
//	private double receivePrice;
//	private String deliverManID;
//	private ArrayList<String> allOrderIDs;
//	
//	public ReceiveDocVO receiveDocVO=new ReceiveDocVO();
//	
//	
//	public BusinessSaleReceiveDocumentblService_stub(String receiveDate,double receivePrice,String deliverManID,ArrayList<String> allOrderIDs){
//		this.receiveDate=receiveDate;
//		this.receivePrice=receivePrice;
//		this.deliverManID=deliverManID;
//		this.allOrderIDs=allOrderIDs;
//	}
//	public boolean addReceiveDoc(ReceiveDocVO vo){
//		System.out.println("We have added an ReceiveDocVO");
//		return true;
//	}
//	public ReceiveDocVO getReceiveDoc(String date){
//		return receiveDocVO;
//	}
//	
//	public boolean isDateAvailable(String date){
//		if (date=="2015-10-01"){
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	public void endReceiveDoc(){
//		System.out.println("We have ended inputing");
//		
//	}
//	
//	
//}
