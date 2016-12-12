//package express.businessLogic.stub;
//
//import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
//import express.vo.DriverInfoListVO;
//import express.vo.DriverInfoVO;
//
//public class DriverBusinessSaleblService_stub implements DriverBusinessSaleblService {
//	private String arriveDate;
//	private String orderID;
//	private String deliverManID;//快递员工号
//	
//	public DriverInfoListVO driverInfoListVO=new DriverInfoListVO();   
//	public DriverInfoVO driverInfoVO=new DriverInfoVO();
//	
//	
//	//consturctor
//	public  DriverBusinessSaleblService_stub (String arriveDate,String orderID,String deliverManID){
//		this.arriveDate=arriveDate;	
//		this.orderID=orderID;
//		this.deliverManID=deliverManID;
//		
//	}
//	
//	public boolean addDriverInfo(DriverInfoVO vo){
//		System.out.println("We have added a DriverInfoVO");
//		return true;
//	}
//	public boolean removeDriverInfo(String DriverID){
//		System.out.println("We have removed a DriverInfoVO");
//		return true;
//	}
//	public DriverInfoListVO getDriverInfoList(){
//		System.out.println("We have got a DriverInfoListVO");
//		return driverInfoListVO ;
//		
//	}
//	public DriverInfoVO getDriverInfo(String DriverID){
//		System.out.println("We have got a DriverInfoVO");		
//		return driverInfoVO;
//	}
//	public boolean changeDriverInfo(String DriverID){
//		if (DriverID=="AAAA"){
//			return false;
//		}
//		else {
//			return true;
//		}
//		
//	} 
//	public boolean isIdentityIdAvailable(String id){
//		if (id=="Lu Hailong") {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	public boolean isDocFullfilled(){
//		System.out.println("We are checking that if Doc is Fullfilled");
//		return true;
//	}
//	public void endManage(){
//		
//	}
//	
//	
//}
