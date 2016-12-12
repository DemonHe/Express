//package express.businessLogic.stub;
//
//import java.util.ArrayList;
//
//import express.businesslogicService.managerBLService.StaffManageBLService;
//import express.vo.CityDistanceVO;
//import express.vo.LogVO;
//import express.vo.OrganizationInfoVO;
//import express.vo.PriceVO;
//import express.vo.SalaryStrategyVO;
//import express.vo.UserInfoVO;
//
//public class StaffManageBLService_stub implements StaffManageBLService{
//
//	public boolean addUserFromManager(UserInfoVO vo){
//		System.out.println("Add succeed.");
//		return true;		
//	}
//	
//	public boolean setSalaryStrategy(SalaryStrategyVO vo){
//		System.out.println("Set succeed.");
//		return true;
//	}
//	
//	public boolean isUserIDAvailable(String id){
//		return true;
//	}
//	
//	public boolean isCellPhoneAvailable(String phoneNum){
//		return true;
//	}
//	
//	public boolean isJoininDateAvailable(String Date){
//		return true;
//	}
//	
//	public void endManage(){
//		LogVO lg = new LogVO("2015-10-26 10:36:40","人员机构管理");
//	}
//
//	public boolean removeUser(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public UserInfoVO getUser(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public ArrayList<UserInfoVO> getAllUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public boolean changeUser(UserInfoVO vo, String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public ArrayList<UserInfoVO> getUnRegistered() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public boolean register(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//}
