package express.po;

import java.io.Serializable;
import java.util.ArrayList;

public class InnerAccountPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 196935946990671321L;
	private String innerAccountID;//日期
	private ArrayList<UserInfoPO> userInfo;
	private ArrayList<OrganizationPO> organizationInfo;
	private ArrayList<RepoInfoPO> repo;
	private ArrayList<VehicleInfoPO> vehicleInfo;
	private ArrayList<BankAccountPO> bankAccountInfo;
	
	public InnerAccountPO(String innerAccountID,ArrayList<UserInfoPO> userInfo,
			ArrayList<OrganizationPO> organizationInfo,ArrayList<RepoInfoPO> repo,
			ArrayList<VehicleInfoPO> vehicleInfo,ArrayList<BankAccountPO> bankAccountInfo){
		
		this.innerAccountID=innerAccountID;
		this.userInfo=userInfo;
		this.organizationInfo=organizationInfo;
		this.repo=repo;
		this.vehicleInfo=vehicleInfo;
		this.bankAccountInfo=bankAccountInfo;
	}
	
	public InnerAccountPO(){
		this.innerAccountID=null;
		this.userInfo=null;
		this.organizationInfo=null;
		this.repo=null;
		this.vehicleInfo=null;
		this.bankAccountInfo=null;
	}
	
	public String getInnerAccountID(){
		return innerAccountID;
	}
	public ArrayList<UserInfoPO> getUserInfo(){
		return userInfo;
	}
	public ArrayList<OrganizationPO> getOrganizationInfo(){
		return organizationInfo;
	}
	public ArrayList<RepoInfoPO> getRepoInfo(){
		return repo;
	}
	public ArrayList<VehicleInfoPO> getVehicleInfo(){
		return vehicleInfo;
	}
	public ArrayList<BankAccountPO> getBankAccountInfo(){
		return bankAccountInfo;
	}
	//
	public void setInnerAccountID(String d){
		innerAccountID=d;
	}
	public void setUserInfo(ArrayList<UserInfoPO> u){
		userInfo=u;
	}
	public void setOrganizationInfo(ArrayList<OrganizationPO> o){
		organizationInfo=o;
	}
	public void setRepoInfo(ArrayList<RepoInfoPO> r){
		repo=r;
	}
	public void setVehicleInfo(ArrayList<VehicleInfoPO> v){
		vehicleInfo=v;
	}
	public void setBankAccountInfo(ArrayList<BankAccountPO> b){
		bankAccountInfo=b;
	}
}
