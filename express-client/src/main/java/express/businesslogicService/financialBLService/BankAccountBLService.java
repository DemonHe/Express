package express.businesslogicService.financialBLService;

import java.util.ArrayList;

import express.po.BankAccountPO;
import express.vo.BankAccountVO;

public interface BankAccountBLService {
	
	public ArrayList<BankAccountVO> showAllBankAccount();
	
	public ArrayList<BankAccountPO> getAllBankAccountPO();
	
	public BankAccountPO getBankAccount(String name);
	
	public ArrayList<String> getBankAccountName();

	public boolean addBankAccount(BankAccountVO bankAccount);
	
	public boolean checkDuplication(String name);
	
	public boolean checkMoney(String money);
	
	public boolean removeBankAccount(String name);
	
	public ArrayList<BankAccountVO> findBankAccount(String name);
	
	public boolean changeBankAccount(BankAccountVO bankAccount,String name);
	
	public boolean changeBankAccount(String old,String change);
	
	public void recordBankAccount();
	
	public void endManage();
}
