//package express.businessLogic.stub;
//
//import java.util.ArrayList;
//
//import express.businesslogicService.businessSaleBLService.BusinessSaleShipmentDocumentblService;
//import express.vo.ArrivalDocVO;
//import express.vo.ShipmentDocVO;
//
//public class BusinessSaleShipmentDocumentblService_stub implements BusinessSaleShipmentDocumentblService {
//	private String date;
//	private String transcenterNumber;
//	private String arrival;
//	private String vin;
//	private String checkMan;
//	private String transMan;
//	private String shipmentID;
//	private ArrayList<String> orderID;
//	private double money;
//	
//	public ShipmentDocVO shipmentDocVO=new ShipmentDocVO();
//	
//	public BusinessSaleShipmentDocumentblService_stub(String d,String transnumber,String ar,String v,String che,
//			String transman,String shipmentID,ArrayList<String> orderID,double m){
//		this.date=d;
//		this.transcenterNumber=transnumber;
//		this.arrival=ar;
//		this.vin=v;
//		this.checkMan=che;
//		this.transMan=transman;
//		this.shipmentID=shipmentID;
//		this.money=m;
//	}
//	public boolean addShipmentDoc(ShipmentDocVO vo){
//		System.out.println("We have added an ShipmentDocVO");
//		return true;
//	}
//	public ShipmentDocVO getShipmentDoc(String ShipmentID){
//		return shipmentDocVO;
//	}
//	
//	public boolean isOrderIDavailable(String id){
//		if (id=="Lu Hailong"){
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	public boolean isBusinessshallavailable(String Businessshallid){
//		if (Businessshallid=="Lu Hailong"){
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	
//	public boolean isTransCarIDavailable(String TransCarIDid){
//		if (TransCarIDid=="Lu Hailong"){
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//		
//	
//	public void endShipmentDoc(){
//		System.out.println("We have ended inputing");
//		
//	}
//	
//}
