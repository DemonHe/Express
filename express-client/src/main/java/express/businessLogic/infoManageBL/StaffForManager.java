package express.businessLogic.infoManageBL;//

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.adminBLService.RemoveUserBLService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.businesslogicService.managerBLService.UserRegisterService;
import express.dataService.userDataService.UserDataService;
import express.po.UserInfoPO;
import express.rmi.RMIClient;
import express.vo.UserInfoVO;

public class StaffForManager implements StaffManageBLService ,UserRegisterService {
	
	UserDataService user;
	
	public StaffForManager(){
		user=RMIClient.getUserInfoObject();
	}

	public boolean addUserFromManager(UserInfoVO vo) {
		
		UserInfoPO po=transVOToPO(vo, false);
		
		try {
			return user.createuser(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeUser(String id) {
		
		RemoveUserBLService admin=new Admin();
		
		try {
			
			admin.removeUser(id);
			//将管理员持有的用户删除
			return user.deleteUser(id);
			//将总经理持有的员工信息删除
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public UserInfoVO getUser(String id) {
		
		try {
			UserInfoPO po=user.getUserInfo(id);
			
			if(po!=null){
				//将po转化成vo
				UserInfoVO vo=transPOToVO(po);
				
				return vo;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<UserInfoVO> getAllUser() {
		try {
			ArrayList<UserInfoPO> list=user.getAllUserInfo();
			ArrayList<UserInfoVO> transList=new ArrayList<UserInfoVO>();
			
			if(list!=null){
				//判断是否为空，将po转化为vo
				for(UserInfoPO po:list){
					UserInfoVO vo=transPOToVO(po);
					transList.add(vo);
				}
			}
			if(transList.size()>0)
				return transList;
			else
				return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public ArrayList<UserInfoPO> getAllUserPO() {
		
		try {
			return user.getAllUserInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean changeUser(UserInfoVO vo, String id) {
		
		try {
			
			UserInfoPO po=transVOToPO(vo,user.getUserInfo(id).checkAdmin());
			
			return user.changeUserInfo(po, id);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<UserInfoVO> getUnregistered() {
		
		try {
			ArrayList<UserInfoPO> list=user.getUnregistered();
			ArrayList<UserInfoVO> select=new ArrayList<UserInfoVO>();
			
			if(list!=null){
				for(UserInfoPO po:list){
					UserInfoVO vo=transPOToVO(po);
					select.add(vo);
				}
			}
			if(select.size()>0)
				return select;
			else
				return null;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean isUserIDAvailable(String id) {
		
		try {
			ArrayList<UserInfoPO> list=user.getAllUserInfo();
			if(list!=null){
				for(UserInfoPO po:list){
					if(po.getID().equals(id)){
						return false;
					}
				}
			}
			return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean isCellPhoneAvailable(String phoneNum) {
		if(phoneNum.length()!=11)
			return false;
		for(int i=0;i<11;i++)
			if(phoneNum.charAt(i)>'9'||phoneNum.charAt(i)<'0')
				return false;
		return true;
	}

	public boolean isJoininDateAvailable(String Date) {
		Date dt=new Date();     
		SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		String d=matter.format(dt);
		
		if(d.compareTo(Date) < 0)
			return false;
		else
			return true;
	}

	public boolean register(String id) {
		try {
			return user.register(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void recordStaffInfo() {
		try {
			user.writeAllUserInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endManage() {
		SysLog log=new SysLog();
		log.addSysLog("管理人员信息");
		try {
			user.writeAllUserInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private UserInfoPO transVOToPO(UserInfoVO vo,boolean admin){
		
		UserInfoPO po=new UserInfoPO();
		
		po.setAdmin(admin);
		po.setCity(vo.getCity());
		po.setDate(vo.getDate());
		po.setGender(vo.getGender());
		po.setID(vo.getID());
		po.setName(vo.getName());
		po.setPhoneNum(vo.getPhoneNum());
		po.setPosition(vo.getPosition());
		
		return po;
	}
	
	private UserInfoVO transPOToVO(UserInfoPO po){
		
		UserInfoVO vo=new UserInfoVO();
		
		vo.setName(po.getName());
		vo.setCity(po.getCity());
		vo.setDate(po.getDate());
		vo.setGender(po.getGender());
		vo.setID(po.getID());
		vo.setPhoneNum(po.getPhoneNum());
		vo.setPosition(po.getPosition());
		
		return vo;
	}

}
