package express.dataService.documentDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.TransferDocPO;

public interface TransCenterTransferDocDataService extends Remote{
	public boolean createTransferDoc(TransferDocPO po) throws RemoteException;
	public TransferDocPO getTransferDoc(String transID) throws RemoteException;
	public boolean writeAllTransferDoc() throws RemoteException;
	public ArrayList<TransferDocPO> getTransferDoclist() throws RemoteException;
	
	public boolean changeTransferDoc(TransferDocPO po) throws RemoteException;
	
	
}
