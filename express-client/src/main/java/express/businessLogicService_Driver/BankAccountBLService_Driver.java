package express.businessLogicService_Driver;

import express.businesslogicService.financialBLService.BankAccountBLService;

public class BankAccountBLService_Driver {

	public void drive(BankAccountBLService bankAccountBLService){
		if(bankAccountBLService.addBankAccount(null))
			System.out.println("Add bank account");
		if(bankAccountBLService.checkDuplication("1001001001001"))
			System.out.println("Name duplicated");
		if(bankAccountBLService.checkDuplication("1001001001002"))
			System.out.println("Name valid");
		if(bankAccountBLService.removeBankAccount(null))
			System.out.println("Remove bank account");
		//if(bankAccountBLService.changeBankAccount(null,0))
			System.out.println("Change bank account");
		bankAccountBLService.endManage();
	}
}
