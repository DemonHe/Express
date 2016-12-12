package express.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import express.data.bankAccountData.BankAccountIO;
import express.po.BankAccountPO;
import junit.framework.TestCase;

public class BankAccountIOTester extends TestCase {

	@Test
	public void test() {
		try{
		BankAccountIO bank=new BankAccountIO();
		assertEquals(null,bank.getAllBankAccount());
		BankAccountPO account1=new BankAccountPO("S快�?�公�?",300000,100000,200000);
		BankAccountPO account2=new BankAccountPO("S快�?�公司南京分公司",100000,50000,50000);
		BankAccountPO account3=new BankAccountPO("S快�?�公司北京分公司",150000,80000,70000);
		bank.createBankAccount(account1);
		bank.createBankAccount(account2);
		bank.createBankAccount(account3);
		assertEquals(account1,bank.getAllBankAccount().get(0));
		assertEquals(account2,bank.getAllBankAccount().get(1));
		assertEquals(account3,bank.getAllBankAccount().get(2));
		bank.writeAllBankAccount();
		//BankAccountPO account4=new BankAccountPO("S快�?�公司南京分公司",150000,80000,70000);
		//bank.changeBankAccount(account4, "S快�?�公司南京分公司");
		//bank.writeAllBankAccount();
		assertEquals("S快�?�公�?",bank.getAllBankAccount().get(0).getName());
		}catch(RemoteException e){
			
		}
	}
}
