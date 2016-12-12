package express.dataService.statisticsDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import express.po.OperateFormPO;

public interface OperateFormDataService extends Remote{
	
	public boolean createOperateForm(OperateFormPO operateForm) throws RemoteException;
	
	public ArrayList<String> getOperateFormListTitle() throws RemoteException;
	
	public boolean removeOperateForm(int index) throws RemoteException;
	
	public OperateFormPO getOperateForm(int index) throws RemoteException;
	
	public boolean writeAllOperateForm() throws RemoteException;
}
