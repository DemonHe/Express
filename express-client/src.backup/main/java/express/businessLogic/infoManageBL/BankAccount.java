package express.businessLogic.infoManageBL;//

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.dataService.bankAccountDataService.BankAccountDataService;
import express.po.BankAccountPO;
import express.rmi.RMIClient;
import express.vo.BankAccountVO;

public class BankAccount implements BankAccountBLService {

	BankAccountDataService bank;

	public BankAccount() {
		bank = RMIClient.getBankAccountObject();
	}

	public ArrayList<BankAccountVO> showAllBankAccount() {
		ArrayList<BankAccountVO> bankAccountList = new ArrayList<BankAccountVO>();
		try {
			ArrayList<BankAccountPO> b = bank.getAllBankAccount();
			if (b == null)
				return null;
			for (BankAccountPO po : b)
				bankAccountList.add(new BankAccountVO(po));
			return bankAccountList;
		} catch (RemoteException e) {

			return null;
		}
	}

	public boolean addBankAccount(BankAccountVO bankAccount) {
		BankAccountPO po = new BankAccountPO();
		po.setName(bankAccount.getName());
		po.addIncome(Math.abs(bankAccount.getIncome()));
		po.addOutcome(Math.abs(bankAccount.getOutcome()));
		try {
			return bank.createBankAccount(po);
		} catch (RemoteException e) {

			return false;
		}

	}

	public boolean checkDuplication(String name) {
		try {
			ArrayList<BankAccountPO> a = bank.getAllBankAccount();
			if (a != null) {
				for (BankAccountPO po : a) {
					if (po.getName().equals(name))
						return true;
				}
			}
			return false;
		} catch (RemoteException e) {

			return false;
		}

	}

	public boolean removeBankAccount(String name) {
		try {
			return bank.deleteBankAccount(name);
		} catch (RemoteException e) {

			return false;
		}
	}

	public ArrayList<BankAccountVO> findBankAccount(String name) {
		try {
			ArrayList<BankAccountPO> bp = bank.getBankAccount(name);
			ArrayList<BankAccountVO> result = new ArrayList<BankAccountVO>();
			if (bp != null) {
				for (BankAccountPO bankAccount : bp) {
					result.add(new BankAccountVO(bankAccount));
				}
			}
			if (result.size() > 0)
				return result;
			else
				return null;
		} catch (RemoteException e) {

			return null;
		}
	}

	public boolean changeBankAccount(BankAccountVO bankAccount, String name) {
		try {
			BankAccountPO po = new BankAccountPO();
			po.setName(bankAccount.getName());
			po.addIncome(Math.abs(bankAccount.getIncome()));
			po.addOutcome(Math.abs(bankAccount.getOutcome()));
			return bank.changeBankAccount(po, name);
		} catch (RemoteException e) {

			return false;
		}
	}

	public boolean changeBankAccount(String old, String change) {
		try {
			BankAccountPO po = bank.getBankAccountByName(old);
			po.setName(change);
			return bank.changeBankAccount(po, old);
		} catch (RemoteException e) {

			return false;
		}
	}

	public void endManage() {
		SysLog log = new SysLog();
		log.addSysLog("银行账户管理");
		try {
			bank.writeAllBankAccount();
		} catch (RemoteException e) {

		}
	}

	public void recordBankAccount() {
		try {
			bank.writeAllBankAccount();
		} catch (RemoteException e) {

		}
	}

	public BankAccountPO getBankAccount(String name) {
		try {
			return bank.getBankAccountByName(name);
		} catch (RemoteException e) {

			return null;
		}
	}

	public ArrayList<String> getBankAccountName() {
		try {
			return bank.getBankAccountName();
		} catch (RemoteException e) {

			return null;
		}
	}

	public ArrayList<BankAccountPO> getAllBankAccountPO() {

		try {
			return bank.getAllBankAccount();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkMoney(String money) {
		double m = 0;
		try {
			m = Double.parseDouble(money);
		} catch (Exception e) {
			return false;
		}
		if (m < 0) {
			return false;
		}
		return true;
	}

}
