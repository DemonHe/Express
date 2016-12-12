package express.data.statisticsData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import express.data.IOHelper.IOHelper;
import express.dataService.statisticsDataService.OperateFormDataService;
import express.po.OperateFormPO;

public class OperateFormIO extends UnicastRemoteObject implements
		OperateFormDataService {

	String filename = "SerializableData/OperateForm.ser";
	ArrayList<OperateFormPO> operateFormList;

	public OperateFormIO() throws RemoteException {
		super();
		IOHelper io = new IOHelper();
		operateFormList = new ArrayList<OperateFormPO>();
		try {
			operateFormList = (ArrayList<OperateFormPO>) io
					.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createOperateForm(OperateFormPO operateForm)
			throws RemoteException {
		operateFormList.add(operateForm);
		return true;
	}

	@Override
	public boolean removeOperateForm(int index) throws RemoteException {
		if (operateFormList.size() > 0) {
			operateFormList.remove(index);
			return true;
		} else
			return false;
	}

	@Override
	public ArrayList<String> getOperateFormListTitle() throws RemoteException {
		ArrayList<String> title = new ArrayList<String>();
		if (operateFormList.size() > 0)
			for (OperateFormPO po : operateFormList)
				title.add(po.getStartDate() + "~" + po.getEndDate());
		return title;
	}

	@Override
	public OperateFormPO getOperateForm(int index) throws RemoteException {
		if (index >= 0 && index < operateFormList.size())
			return operateFormList.get(index);
		else
			return null;
	}

	@Override
	public boolean writeAllOperateForm() throws RemoteException {
		IOHelper io = new IOHelper();
		try {
			io.writeToDisk(filename, operateFormList);
			return true;
		} catch (IOException e) {

			return false;
		}
	}
}
