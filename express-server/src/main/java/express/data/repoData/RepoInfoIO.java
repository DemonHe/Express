package express.data.repoData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.repoDataService.RepoDataService;
import express.po.Area;
import express.po.RepoInfoPO;
import express.po.RepoPosition;

public class RepoInfoIO extends UnicastRemoteObject implements RepoDataService {

	String filename = "SerializableData/RepoInfo.ser";
	ArrayList<RepoInfoPO> repoList;

	public RepoInfoIO() throws RemoteException {
		super();
		IOHelper io = new IOHelper();
		repoList = new ArrayList<RepoInfoPO>();
		try {
			repoList = (ArrayList<RepoInfoPO>) io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addRepo(RepoInfoPO repo) throws RemoteException {
		repoList.add(repo);
		return true;
	}

	@Override
	public boolean deleteRepo(String orgID) throws RemoteException {
		if (repoList.size() > 0) {
			for (RepoInfoPO repo : repoList)
				if (repo.getCity().equals(orgID)) {
					repoList.remove(repo);
					return true;
				}
		}
		return false;
	}

	@Override
	public RepoInfoPO getRepo(String orgID) throws RemoteException {
		if (repoList.size() > 0) {
			for (RepoInfoPO repo : repoList)
				if (repo.getCity().equals(orgID))
					return repo;
		}
		return null;
	}

	@Override
	public boolean deleteBlock(String orgID, RepoPosition position)
			throws RemoteException {
		if (repoList.size() > 0) {
			for (RepoInfoPO repo : repoList)
				if (repo.getCity().equals(orgID)) {
					repo.deleteRepoPosition(position);
					if (position.getblock().equals(Area.AIR)) {
						repo.changeAirSum(-1);
						repo.changeRepoSum(-1);
					} else if (position.getblock().equals(Area.TRAIN)) {
						repo.changeTrainSum(-1);
						repo.changeRepoSum(-1);
					} else if (position.getblock().equals(Area.CAR)) {
						repo.changeTruckSum(-1);
						repo.changeRepoSum(-1);
					} else {
						repo.changeRepoSum(-1);
					}
					return true;
				}
		}
		return false;
	}

	@Override
	public boolean deleteBlock(String orgID, int index) throws RemoteException {
		if (repoList.size() > 0) {
			for (RepoInfoPO repo : repoList)
				if (repo.getCity().equals(orgID)) {
					repo.deleteRepoPosition(index);
					return true;
				}
		}
		return false;
	}

	@Override
	public boolean addBlock(String orgID, RepoPosition position)
			throws RemoteException {
		if (repoList.size() > 0) {
			for (RepoInfoPO repo : repoList)
				if (repo.getCity().equals(orgID)) {
					repo.addRepoPosition(position);
					if (position.getblock().equals(Area.AIR)) {
						repo.changeAirSum(1);
						repo.changeRepoSum(1);
					} else if (position.getblock().equals(Area.TRAIN)) {
						repo.changeTrainSum(1);
						repo.changeRepoSum(1);
					} else if (position.getblock().equals(Area.CAR)) {
						repo.changeTruckSum(1);
						repo.changeRepoSum(1);
					} else {
						repo.changeRepoSum(1);
					}
					return true;
				}
		}
		return false;
	}

	@Override
	public ArrayList<RepoInfoPO> getAllRepo() throws RemoteException {
		if (repoList.size() > 0)
			return repoList;
		else
			return null;
	}

	@Override
	public boolean writeAllRepo() throws RemoteException {
		IOHelper io = new IOHelper();
		try {
			io.writeToDisk(filename, repoList);
			return true;
		} catch (IOException e) {

			return false;
		}
	}

}
