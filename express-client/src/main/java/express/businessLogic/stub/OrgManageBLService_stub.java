//package express.businessLogic.stub;
//
//import express.businesslogicService.managerBLService.OrgManageBLService;
//import express.vo.CityDistanceVO;
//import express.vo.LogVO;
//import express.vo.OrganizationInfoVO;
//import express.vo.PriceVO;
//
//public class OrgManageBLService_stub implements OrgManageBLService{
//
//	public boolean setOrganizationInfo(OrganizationInfoVO vo){
//		System.out.println("Set succeed.");
//		return true;
//	}
//	
//	public boolean setCityDistance(String city,double distance,String city2){
//		CityDistanceVO cd = new CityDistanceVO(city,city2,distance);
//		System.out.println("Set succeed.");
//		return true;
//	}
//	
//	public boolean setPrice(double price){
//		PriceVO pv = new PriceVO(price);
//		System.out.println("Set succeed.");
//		return true;
//	}
//
//	public void endManage(){
//		LogVO lg = new LogVO("2015-10-26 10:36:40","人员机构管理");
//	}
//
//}
