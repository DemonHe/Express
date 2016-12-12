package express.vo;

import java.util.ArrayList;

import express.po.BankAccountPO;
import express.po.OrganizationPO;
import express.po.RepoInfoPO;
import express.po.UserInfoPO;
import express.po.VehicleInfoPO;

public class InnerAccountVO {

	private String innerAccountID;//日期
	private ArrayList<UserInfoVO> userInfo;
	private ArrayList<OrganizationVO> organizationInfo;
	private ArrayList<RepoInfoVO> repo;
	private ArrayList<VehicleInfoVO> vehicleInfo;
	private ArrayList<BankAccountVO> bankAccountInfo;
	
	public InnerAccountVO(String innerAccountID,ArrayList<UserInfoVO> userInfo,
			ArrayList<OrganizationVO> organizationInfo,ArrayList<RepoInfoVO> repo,
			ArrayList<VehicleInfoVO> vehicleInfo,ArrayList<BankAccountVO> bankAccountInfo){
		
		this.innerAccountID=innerAccountID;
		this.userInfo=userInfo;
		this.organizationInfo=organizationInfo;
		this.repo=repo;
		this.vehicleInfo=vehicleInfo;
		this.bankAccountInfo=bankAccountInfo;
	}
	
	public InnerAccountVO(){
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
	public ArrayList<UserInfoVO> getUserInfo(){
		return userInfo;
	}
	public ArrayList<OrganizationVO> getOrganizationInfo(){
		return organizationInfo;
	}
	public ArrayList<RepoInfoVO> getRepoInfo(){
		return repo;
	}
	public ArrayList<VehicleInfoVO> getVehicleInfo(){
		return vehicleInfo;
	}
	public ArrayList<BankAccountVO> getBankAccountInfo(){
		return bankAccountInfo;
	}
	//
	public void setInnerAccountID(String d){
		innerAccountID=d;
	}
	public void setUserInfo(ArrayList<UserInfoVO> u){
		userInfo=u;
	}
	public void setOrganizationInfo(ArrayList<OrganizationVO> o){
		organizationInfo=o;
	}
	public void setRepoInfo(ArrayList<RepoInfoVO> r){
		repo=r;
	}
	public void setVehicleInfo(ArrayList<VehicleInfoVO> v){
		vehicleInfo=v;
	}
	public void setBankAccountInfo(ArrayList<BankAccountVO> b){
		bankAccountInfo=b;
	}
}
