package express.businessLogic.userBL;//

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.Admin;
import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.managerBLService.OrgManageBLService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.businesslogicService.signBLService.LogInBLService;
import express.dataService.ipandname.IPDataService;
import express.dataService.userDataService.AdminUserDataService;
import express.dataService.userDataService.SignUserDataService;
import express.dataService.userDataService.UserDataService;
import express.po.IPPO;
import express.po.UserInfoAdminPO;
import express.po.UserInfoPO;
import express.po.UserPO;
import express.rmi.AboutSystem;
import express.rmi.RMIClient;
import express.vo.OrganizationVO;
import express.vo.SignInVO;
import express.vo.UserInfoAdminVO;
import express.vo.UserInfoSignVO;
import express.vo.UserInfoVO;

public class User implements LogInBLService {

	SignUserDataService sign;

	public User() {
		sign = RMIClient.getUserSignObject();
	}

	public SignInVO signIn(String id, String password) {

		AdminUserDataService adminUser = RMIClient.getUserAdminObject();
		StaffManageBLService staffManager = new StaffForManager();
		OrgManageBLService orgManager = new OrgForManager();

		try {
			ArrayList<UserInfoAdminPO> userList = adminUser.getAllUserAdmin();
			if (userList != null) {
				for (UserInfoAdminPO user : userList) {
					// 查找id和password
					if (user.getID().equals(id)
							&& user.getPassword().equals(password)) {
						// 如果id正确，且password正确
						if (sign.checkLogIn(id))
							// 如果已登�?
							return SignInVO.SIGNED;
						else {
							// 如果未登�?
							UserPO u = new UserPO(user.getID(),
									user.getPosition());

							sign.logInRegister(u);
							// 标记为登�?
							IDKeeper.setID(id);
							String orgID = "";
							UserInfoVO userInfo = staffManager.getUser(id);
							if (userInfo != null) {
								orgID = userInfo.getCity();
								IDKeeper.setOrgID(orgID);
							}

							OrganizationVO org = orgManager.getOrgInfo(orgID);
							if (org != null) {
								String city = org.getCity();
								IDKeeper.setCity(city);
							}
							// 保存id
							return SignInVO.PERMIT;
						}
					} else if (user.getID().equals(id)
							&& !user.getPassword().equals(password)) {
						// 如果id正确，但password不正�?
						return SignInVO.PASSWORD_ERROR;
					}
				}
			}
			// 如果没找到id，说明id不存�?
			return SignInVO.ID_ERROR;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserInfoSignVO getUserInfo(String id) {

		Admin adminUser = new Admin();
		UserInfoAdminVO user = adminUser.getUser(id);

		if (user != null)
			return new UserInfoSignVO(user.getName(), user.getID(),
					user.getPosition());
		else
			return null;
	}

	public boolean SignOut(String id) {

		try {
			return sign.logOutRegister(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
