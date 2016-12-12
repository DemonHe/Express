package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.innerAccountDataService.InnerAccountDataService;
import express.po.BankAccountPO;
import express.po.InDocPO;
import express.po.InnerAccountPO;
import express.po.OrganizationPO;
import express.po.UserInfoPO;
import express.po.VehicleInfoPO;

public class InnerAccountDataService_stub implements InnerAccountDataService{

	@Override
	public boolean createInnerAccount(InnerAccountPO innerAccount)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<InnerAccountPO> getAllInnerAccount()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getInnerAccountName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InnerAccountPO getInnerAccount(int index) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeAllInnerAccount() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
