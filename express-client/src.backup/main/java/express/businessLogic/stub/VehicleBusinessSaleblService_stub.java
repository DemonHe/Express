//package express.businessLogic.stub;
//
//import java.util.ArrayList;
//
//import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
//import express.vo.VehicleInfoListVO;
//import express.vo.VehicleInfoVO;
//
//public class VehicleBusinessSaleblService_stub implements VehicleBusinessSaleblService {
//	private String mark;
//	private String license;
//	
//	public VehicleInfoListVO vehicleInfoListVO=new VehicleInfoListVO();   
//	public VehicleInfoVO vehicleInfoVO=new VehicleInfoVO();
//	
//	public VehicleBusinessSaleblService_stub(String mark,String license){
//		this.mark=mark;
//		this.license=license;
//	}
//	
//	public boolean addVehicleInfo(VehicleInfoVO vo){
//		System.out.println("We have added a VehicleInfoVO");
//		return true;
//	}
//	public boolean removeVehicleInfo(String CarID){
//		System.out.println("We have removed a VehicleInfoVO");
//		return true;
//	}
//	public VehicleInfoListVO getVehicleInfoList(){
//		System.out.println("We have got a VehicleInfoListVO");
//		return vehicleInfoListVO ;
//		
//	}
//	public VehicleInfoVO getVehicleInfo(String CarID){
//		System.out.println("We have got a VehicleInfoVO");		
//		return vehicleInfoVO;
//	}
//	public boolean changeVehicleInfo(String CarID){
//		if (CarID=="AAAA"){
//			return false;
//		}
//		else {
//			return true;
//		}
//		
//	} 
//	public boolean isDocFullfilled(){
//		System.out.println("We are checking that if Doc is Fullfilled");
//		return true;
//	}
//	public void endManage(){
//		
//	}
//	
//}
//
