package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.repoDataService.RepoDataService;
import express.po.RepoInfoPO;
import express.po.RepoPosition;

public class RepoDataService_stub implements RepoDataService{

	@Override
	public boolean deleteBlock(String orgID, RepoPosition position)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBlock(String orgID, int index) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBlock(String orgID, RepoPosition position)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRepo(RepoInfoPO repo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRepo(String orgID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RepoInfoPO getRepo(String orgID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RepoInfoPO> getAllRepo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeAllRepo() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
