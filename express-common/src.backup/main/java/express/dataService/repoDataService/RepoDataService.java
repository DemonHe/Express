package express.dataService.repoDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.RepoInfoPO;
import express.po.RepoPosition;

public interface RepoDataService extends Remote{
	
	public boolean deleteBlock(String orgID,RepoPosition position) throws RemoteException;
	
	public boolean deleteBlock(String orgID,int index) throws RemoteException;
	
	public boolean addBlock(String orgID,RepoPosition position) throws RemoteException;
	
	public boolean addRepo(RepoInfoPO repo) throws RemoteException;
	
	public boolean deleteRepo(String orgID) throws RemoteException;
	
	public RepoInfoPO getRepo(String orgID) throws RemoteException;
	
	public ArrayList<RepoInfoPO> getAllRepo() throws RemoteException;
	
	public boolean writeAllRepo() throws RemoteException;
}
