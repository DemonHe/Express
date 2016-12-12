package express.dataService.ipandname;

import java.rmi.Remote;
import java.rmi.RemoteException;

import express.po.IPPO;

public interface IPDataService extends Remote{
	public void addIP(IPPO ip) throws RemoteException;
	public void minusIP(IPPO ip) throws RemoteException;
}
