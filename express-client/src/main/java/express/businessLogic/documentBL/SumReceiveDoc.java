package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import express.businessLogic.infoManageBL.BankAccount;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.GetReceiveDocBLService;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.dataService.documentDataService.BusinessSaleReceiveDocumentDataService;
import express.po.BankAccountPO;
import express.po.ReceiveDocPO;
import express.rmi.RMIClient;
import express.vo.BankAccountVO;
import express.vo.ReceiveDocVO;
import express.vo.SumReceiveVO;

public class SumReceiveDoc implements GetReceiveDocBLService {

	BusinessSaleReceiveDocumentDataService rmiObj;

	public SumReceiveDoc() {
		rmiObj = RMIClient.getReceiveDocObject();
	}

	public SumReceiveVO getReceiveDocList(String date, String orgID) {

		try {
			ArrayList<ReceiveDocPO> list = rmiObj.getReceiveDoclist();
			if (list == null)
				return null;
			// 如果没有收款单，直接返回null
			if (date == null) {
				Date d = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				date = format.format(d);
			}
			// 如果未选择日期，则日期为今天的日期

			double sum = 0;
			ArrayList<ReceiveDocVO> transList = new ArrayList<ReceiveDocVO>();

			if (orgID == null) {
				// 如果未选择营业厅
				for (ReceiveDocPO po : list) {
					if (po.getReceiveDate().equals(date)) {
						// 选择符合时间的收款单

						sum += po.getReceivePrice();

						ReceiveDocVO vo = new ReceiveDocVO(po.getReceiveDate(),
								po.getReceivePrice(), po.getDeliverManID(),
								po.getAllOrderIDs(), po.getOrgID());
						transList.add(vo);
					}
				}
			} else {
				// 如果选择营业厅
				for (ReceiveDocPO po : list) {
					if (po.getReceiveDate().equals(date)
							&& po.getOrgID().equals(orgID)) {
						// 选择符合时间和营业厅名称的收款单

						sum += po.getReceivePrice();

						ReceiveDocVO vo = new ReceiveDocVO(po.getReceiveDate(),
								po.getReceivePrice(), po.getDeliverManID(),
								po.getAllOrderIDs(), po.getOrgID());
						transList.add(vo);
					}
				}
			}

			SumReceiveVO sumReceiveDoc = new SumReceiveVO(transList, sum);

			return sumReceiveDoc;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new SumReceiveVO(null,0);
		}
	}

	public boolean getSum(double sum, String bankAccountName) {
		
		if(sum>0.0){
			BankAccountBLService bank = new BankAccount();
			BankAccountPO po = bank.getBankAccount(bankAccountName);
			BankAccountVO vo = new BankAccountVO(po.getName(), po.getIncome(),
					po.getOutcome(), po.getBalance());
			vo.addIncome(sum);
			bank.changeBankAccount(vo, bankAccountName);
			bank.recordBankAccount();
			// 将总额加到对应银行账户的收入中
			return true;
		}
		else
			return true;
	}

	public ArrayList<ReceiveDocVO> getAllReceiveDoc() {

		try {
			ArrayList<ReceiveDocPO> list = rmiObj.getReceiveDoclist();
			if (list == null)
				return null;
			// 如果没有收款单，直接返回null
			ArrayList<ReceiveDocVO> transList = new ArrayList<ReceiveDocVO>();

			for (ReceiveDocPO po : list) {
				ReceiveDocVO vo = new ReceiveDocVO(po.getReceiveDate(),
						po.getReceivePrice(), po.getDeliverManID(),
						po.getAllOrderIDs(), po.getOrgID());
				transList.add(vo);
			}

			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void endManage() {
		SysLog log = new SysLog();
		log.addSysLog("银行账户管理");
	}

	@Override
	public ArrayList<ReceiveDocPO> getAllReceiveDocPO() {
		try {
			return rmiObj.getReceiveDoclist();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
