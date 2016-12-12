//package express.businessLogic.stub;
//
//import java.util.ArrayList;
//
//import express.businesslogicService.adminBLService.AdminBLService;
//import express.po.UserRole;
//import express.vo.LogVO;
//import express.vo.UserInfoAdminVO;
//import express.vo.UserInfoVO;
//
//public class AdminBLService_stub implements AdminBLService{
//	String name;
//	String id;
//	UserRole position;
//	String password;
//	
//	public AdminBLService_stub(String name,String id,UserRole position,String key){
//		this.name = name;
//		this.id = id;
//		this.position = position;
//		this.password = key;
//	}
//	
//	public void addUserID(UserInfoAdminVO vo){
//		System.out.println("Add succeed.");
//	}
//	
//	public boolean isExisted (String id){
//		if(id.equals("1001001"))
//			return false;
//		else
//			return true;
//	}
//	
//	public UserInfoAdminVO getUserInfo(String id){
//		return new UserInfoAdminVO(name,id,position,password);
//	}
//	
//	public boolean removeUser (String id){
//		System.out.println("Detele succeed.");
//		return true;
//	}
//	
//	public boolean changeUserPosition (String id){
//		System.out.println("Change succeed.");
//		return true;
//	}
//	
//	public boolean changeUserPassword (String id){
//		System.out.println("Change succeed.");
//		return true;
//	}
//	
//	public void endManage(){
//		LogVO lp = new LogVO("2015年10月26日10点10分","用户管理");		
//	}
//
//	public boolean addUser(UserInfoAdminVO vo) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public UserInfoAdminVO getUser(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public ArrayList<UserInfoVO> getUnregistered() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public boolean changeUserPassword(String id, String password) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean checkUserID(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//}
