package express.businessLogic.initAccountBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.innerAccountDataService.InnerAccountDataService;
import express.po.BankAccountPO;
import express.po.InnerAccountPO;
import express.po.OrganizationPO;
import express.po.RepoInfoPO;
import express.po.UserInfoPO;
import express.po.VehicleInfoPO;
import express.rmi.RMIClient;
import express.vo.BankAccountVO;
import express.vo.InnerAccountVO;
import express.vo.OrganizationVO;
import express.vo.RepoInfoVO;
import express.vo.UserInfoVO;
import express.vo.VehicleInfoVO;

public class InitAccount {
	
	InnerAccountDataService rmiObj;
	
	public InitAccount(){
		rmiObj=RMIClient.getInnerAccountObject();
	}
	
	public ArrayList<String> getInnerAccountNameList(){
		try {
			return rmiObj.getInnerAccountName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public InnerAccountVO getPrevious(int index){
		try {
			InnerAccountPO inner=rmiObj.getInnerAccount(index);
			//得到期初建账po
			if(inner==null)
				return null;
			//如果是null，直接返回null
			InnerAccountVO innerAccount=new InnerAccountVO();
			//创建期初建账vo
			innerAccount.setInnerAccountID(inner.getInnerAccountID());
			//设置vo的编号
			ArrayList<UserInfoPO> userList=inner.getUserInfo();
			ArrayList<OrganizationPO> orgList=inner.getOrganizationInfo();
			ArrayList<RepoInfoPO> repoList=inner.getRepoInfo();
			ArrayList<VehicleInfoPO> vehicleList=inner.getVehicleInfo();
			ArrayList<BankAccountPO> bankList=inner.getBankAccountInfo();
			
			ArrayList<UserInfoVO> ul=new ArrayList<UserInfoVO>();
			ArrayList<OrganizationVO> ol=new ArrayList<OrganizationVO>();
			ArrayList<RepoInfoVO> rl=new ArrayList<RepoInfoVO>();
			ArrayList<VehicleInfoVO> vl=new ArrayList<VehicleInfoVO>();
			ArrayList<BankAccountVO> bl=new ArrayList<BankAccountVO>();
			
			if(userList==null)
				innerAccount.setUserInfo(null);
			else{
				for(UserInfoPO po:userList){
					UserInfoVO vo=new UserInfoVO();
					
					vo.setCity(po.getCity());
					vo.setDate(po.getDate());
					vo.setGender(po.getGender());
					vo.setID(po.getID());
					vo.setPhoneNum(po.getPhoneNum());
					vo.setPosition(po.getPosition());
					
					ul.add(vo);
				}
				innerAccount.setUserInfo(ul);
			}
			
			if(orgList==null)
				innerAccount.setOrganizationInfo(null);
			else{
				for(OrganizationPO po:orgList){
					OrganizationVO vo=new OrganizationVO();
					
					vo.setAddress(po.getAddress());
					vo.setCity(po.getCity());
					vo.setName(po.getName());
					vo.setOrgID(po.getOrgID());
					vo.setOrgProperty(po.getOrgProperty());
					
					ol.add(vo);
				}
				innerAccount.setOrganizationInfo(ol);
			}
			
			if(repoList==null)
				innerAccount.setRepoInfo(null);
			else{
				for(RepoInfoPO po:repoList){
					RepoInfoVO vo=new RepoInfoVO(po.getCity(),po.getAirShelfSize(),
							po.getTrainShelfSize(),po.getTruckShelfSize(),
							po.getFlexibleShelfSize(),po.getAirSum(),
							po.getTrainSum(),po.getTrainSum(),
							po.getRepoPosition(),po.getRepoSum());
					rl.add(vo);
				}
				innerAccount.setRepoInfo(rl);
			}
			
			if(vehicleList==null)
				innerAccount.setVehicleInfo(null);
			else{
				for(VehicleInfoPO po:vehicleList){
					VehicleInfoVO vo=new VehicleInfoVO(po.getMark(),
							po.getLicense(),po.getOrgID(),
							po.getUseYear(),po.checkIsUsing());
					
					vl.add(vo);
				}
				innerAccount.setVehicleInfo(vl);
			}
			
			if(bankList==null)
				innerAccount.setBankAccountInfo(null);
			else{
				for(BankAccountPO po:bankList){
					BankAccountVO vo=new BankAccountVO(po);
					
					bl.add(vo);
				}
				innerAccount.setBankAccountInfo(bl);
			}
			
			return innerAccount;
				
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addInnerAccount(InnerAccountPO innerAccount){
		try {
			return rmiObj.createInnerAccount(innerAccount);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeAllInnerAccount(){
		try {
			return rmiObj.writeAllInnerAccount();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
