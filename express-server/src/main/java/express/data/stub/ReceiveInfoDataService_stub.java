package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.documentDataService.ReceiveInfoDataService;
import express.po.ReceiveInfoPO;

public class ReceiveInfoDataService_stub implements ReceiveInfoDataService{

	@Override
	public boolean createReceiveInfo(ReceiveInfoPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReceiveInfoPO getReceiveInfo(String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeAllReceiveInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ReceiveInfoPO> getReceiveInfolist() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
