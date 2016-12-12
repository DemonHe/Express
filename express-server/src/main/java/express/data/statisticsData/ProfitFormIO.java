package express.data.statisticsData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import express.data.IOHelper.IOHelper;
import express.dataService.statisticsDataService.ProfitFormDataService;
import express.po.ProfitFormPO;

public class ProfitFormIO extends UnicastRemoteObject implements ProfitFormDataService{

	String filename="SerializableData/ProfitForm.ser";
	ArrayList<ProfitFormPO> profitFormList;
	
	public ProfitFormIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		profitFormList=new ArrayList<ProfitFormPO>();
		try {
			profitFormList=(ArrayList<ProfitFormPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createProfitForm(ProfitFormPO profitForm) throws RemoteException {
		profitFormList.add(profitForm);
		return true;
	}
	

	@Override
	public boolean removeProfitForm(int index) throws RemoteException {
		if(profitFormList.size()>0){
			profitFormList.remove(index);
			return true;
		}else
			return false;
	}

	@Override
	public ArrayList<ProfitFormPO> getProfitFormList() throws RemoteException {
		return profitFormList;
	}

	@Override
	public ProfitFormPO getProfitForm(String date) throws RemoteException {
		if(profitFormList.size()>0){
			for(ProfitFormPO po:profitFormList){
				if(po.getTitle().equals(date)){
					return po;
				}
			}
		}
		return null;
	}

	@Override
	public boolean writeAllProfitForm() throws RemoteException {
		IOHelper io=new IOHelper();
		try {
			io.writeToDisk(filename, profitFormList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}
}
