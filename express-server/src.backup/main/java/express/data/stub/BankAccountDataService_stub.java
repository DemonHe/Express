package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.bankAccountDataService.BankAccountDataService;
import express.po.BankAccountPO;

public class BankAccountDataService_stub implements BankAccountDataService{

	@Override
	public ArrayList<BankAccountPO> getAllBankAccount() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BankAccountPO> getBankAccount(String name)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getBankAccountName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccountPO getBankAccountByName(String name)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBankAccount(BankAccountPO bankAccount)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBankAccount(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeBankAccount(BankAccountPO bankAccount, String name)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean writeAllBankAccount() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
