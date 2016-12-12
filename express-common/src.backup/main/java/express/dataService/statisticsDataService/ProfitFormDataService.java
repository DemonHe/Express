package express.dataService.statisticsDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import express.po.ProfitFormPO;

public interface ProfitFormDataService extends Remote{

	public boolean createProfitForm(ProfitFormPO profitForm) throws RemoteException;

	public ArrayList<ProfitFormPO> getProfitFormList() throws RemoteException;
	
	public boolean removeProfitForm(int index) throws RemoteException;
	
	public ProfitFormPO getProfitForm(String date) throws RemoteException;
	
	public boolean writeAllProfitForm() throws RemoteException;
}
