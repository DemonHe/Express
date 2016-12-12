package express.dataService.bankAccountDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.po.BankAccountPO;

public interface BankAccountDataService extends Remote{
	
	public ArrayList<BankAccountPO> getAllBankAccount() throws RemoteException;

	public ArrayList<BankAccountPO> getBankAccount(String name) throws RemoteException;
	
	public ArrayList<String> getBankAccountName() throws RemoteException;
	
	public BankAccountPO getBankAccountByName(String name) throws RemoteException;
	
	public boolean createBankAccount(BankAccountPO bankAccount) throws RemoteException;
	
	public boolean deleteBankAccount(String name) throws RemoteException;
	
	public boolean changeBankAccount(BankAccountPO bankAccount,String name) throws RemoteException;
	
	public boolean writeAllBankAccount() throws RemoteException;
	
}
