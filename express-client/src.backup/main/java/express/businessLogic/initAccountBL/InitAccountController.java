package express.businessLogic.initAccountBL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import express.businessLogic.infoManageBL.BankAccount;
import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businessLogic.infoManageBL.Vehicle;
import express.businessLogic.repoBL.RepoController;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.financialBLService.InnerAccountBLService;
import express.businesslogicService.managerBLService.OrgManageBLService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.businesslogicService.transcenterRepoBLService.InitRepoBLService;
import express.po.InnerAccountPO;
import express.vo.BankAccountVO;
import express.vo.InnerAccountVO;
import express.vo.OrganizationVO;
import express.vo.RepoInfoVO;
import express.vo.UserInfoVO;
import express.vo.VehicleInfoVO;

public class InitAccountController implements InnerAccountBLService {

	StaffManageBLService staff;
	OrgManageBLService org;
	InitRepoBLService repo;
	VehicleBusinessSaleblService vehicle;
	BankAccountBLService bank;
	InitAccount innerAccount;

	public InitAccountController() {
		staff = new StaffForManager();
		org = new OrgForManager();
		repo = new RepoController();
		vehicle = new Vehicle();
		bank = new BankAccount();
		innerAccount = new InitAccount();
	}

	public boolean initUserInfo(UserInfoVO user) {
		return staff.addUserFromManager(user);
	}

	public boolean initOrganizationInfo(OrganizationVO organization) {
		return org.addOrgInfo(organization);
	}

	public boolean initRepoInfo(RepoInfoVO vo) {
		return repo.initRepo(vo);
	}

	public boolean initVehicleInfo(VehicleInfoVO car) {
		return vehicle.addVehicleInfo(car);
	}

	public boolean initBankAccountInfo(BankAccountVO bankAccount) {
		return bank.addBankAccount(bankAccount);
	}

	public boolean changeUserInfo(UserInfoVO user, String id) {
		return staff.changeUser(user, id);
	}

	public boolean changeOrganizationInfo(OrganizationVO organization,
			String orgID) {
		return org.changeOrgInfo(organization, orgID);
	}

	public boolean changeRepoInfo(RepoInfoVO vo, String orgID) {
		return repo.changeRepoInfo(orgID, vo);
	}

	public boolean changeVehicleInfo(VehicleInfoVO car, String carID) {
		return vehicle.changeVehicleInfo(car, carID);
	}

	public boolean changeBankAccountInfo(BankAccountVO bankAccount, String name) {
		return bank.changeBankAccount(bankAccount, name);
	}

	public boolean deleteUserInfo(String id) {
		return staff.removeUser(id);
	}

	public boolean deleteOrganizationInfo(String orgID) {
		return org.removeOrgInfo(orgID);
	}

	public boolean deleteRepoInfo(String orgID) {
		return repo.deleteRepo(orgID);
	}

	public boolean deleteVehicleInfo(String carID) {
		return vehicle.removeVehicleInfo(carID);
	}

	public boolean deleteBankAccountInfo(String name) {
		return bank.removeBankAccount(name);
	}

	public ArrayList<UserInfoVO> getUser() {
		return staff.getAllUser();
	}

	public ArrayList<OrganizationVO> getOrg() {
		return org.getAllOrgInfo();
	}

	public ArrayList<RepoInfoVO> getRepo() {
		return repo.getAllRepo();
	}

	public ArrayList<VehicleInfoVO> getVehicle() {
		return vehicle.getVehicleInfoList();
	}

	public ArrayList<BankAccountVO> getBankAccount() {
		return bank.showAllBankAccount();
	}

	public ArrayList<String> getInnerAccountNameList() {
		return innerAccount.getInnerAccountNameList();
	}

	public InnerAccountVO getPrevious(int index) {
		return innerAccount.getPrevious(index);
	}

	public boolean isUserIDAvailable(String id) {
		return staff.isUserIDAvailable(id);
	}

	public boolean isCellPhoneAvailable(String phoneNum) {
		return staff.isCellPhoneAvailable(phoneNum);
	}

	public boolean isJoininDateAvailable(String Date) {
		return staff.isJoininDateAvailable(Date);
	}

	public boolean isOrgNameAvailable(String name) {
		return org.isOrgNameAvailable(name);
	}

	public boolean isOrgIDAvailable(String id) {
		return org.isOrgIDAvailable(id);
	}

	public boolean isCarIDAvailable(String carID) {
		return vehicle.isCarIDAvailable(carID);
	}

	public boolean isCarLicenseAvailable(String license) {
		return vehicle.isCarLicenseAvailable(license);
	}
	
	public boolean isNumValid(String num){
		return repo.isNumValid(num);
	}
	
	public boolean checkReset(String orgID){
		return repo.checkReset(orgID);
	}

	public boolean checkDuplication(String name) {
		return bank.checkDuplication(name);
	}

	public boolean isMoneyValid(String money) {
		return bank.checkMoney(money);
	}

	public void recordStaffInfo() {
		staff.recordStaffInfo();

	}

	public void recordOrgInfo() {
		org.recordOrgInfo();

	}
	
	public void recordRepoInfo() {
		AdjustRepoBLService adjust = new RepoController();
		adjust.recordRepo();
	}

	public void recordVehicleInfo() {
		vehicle.recordVehicleInfo();

	}

	public void recordBankAccount() {
		bank.recordBankAccount();

	}

	public void endInitial() {
		InnerAccountPO inner = new InnerAccountPO();
		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(d);

		inner.setInnerAccountID(date);
		inner.setUserInfo(staff.getAllUserPO());
		inner.setOrganizationInfo(org.getAllOrgInfoPO());


		inner.setVehicleInfo(vehicle.getVehicleInfoListPO());
		inner.setBankAccountInfo(bank.getAllBankAccountPO());

		recordStaffInfo();
		recordOrgInfo();
		recordRepoInfo();
		recordVehicleInfo();
		recordBankAccount();
		innerAccount.addInnerAccount(inner);

		SysLog log = new SysLog();
		log.addSysLog("期初建账");

		innerAccount.writeAllInnerAccount();
	}
}
