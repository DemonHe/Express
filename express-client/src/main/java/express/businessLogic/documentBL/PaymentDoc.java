package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import express.businessLogic.infoManageBL.BankAccount;
import express.businessLogic.infoManageBL.SalaryManager;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.BusinessSaleShipmentDocumentblService;
import express.businesslogicService.businessSaleBLService.GetReceiveDocBLService;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.financialBLService.PaymentBLService;
import express.businesslogicService.managerBLService.SalaryManagerBLService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.businesslogicService.transcenterSaleBLService.TransCenterSaleShipmentDocblService;
import express.businesslogicService.transcenterSaleBLService.TransCenterTransferDocblService;
import express.dataService.documentDataService.PaymentDocDataService;
import express.po.BankAccountPO;
import express.po.DocumentPO;
import express.po.PaymentDocPO;
import express.po.PaymentItem;
import express.po.ReceiveDocPO;
import express.po.SalaryPO;
import express.po.ShipmentDocBusinessHallPO;
import express.po.ShipmentDocTransCenterPO;
import express.po.Strategy;
import express.po.TransferDocPO;
import express.po.UserInfoPO;
import express.po.UserRole;
import express.rmi.RMIClient;
import express.vo.BankAccountVO;
import express.vo.PaymentDocVO;

public class PaymentDoc implements PaymentBLService {

	PaymentDocDataService pay;

	public PaymentDoc() {
		pay = RMIClient.getPaymentDocObject();
	}

	public ArrayList<PaymentDocVO> createPaymentDoc() {
		TransCenterSaleShipmentDocblService tShip = new ShipmentDocController();
		TransCenterTransferDocblService tTrans = new TransferDoc();
		BusinessSaleShipmentDocumentblService bShip = new ShipmentDocBusinessHall();

		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String dateFactory = format.format(d);
		String currDate = dateFactory.substring(0, 7);
		String date = dateFactory.substring(0, 10);
		String cd = dateFactory.substring(5, 7);
		if (cd.charAt(0) == '0')
			cd = cd.substring(1, 2);
		// 如果是6月，将06变为6

		ArrayList<ShipmentDocTransCenterPO> list1 = tShip.getAllShipmentDocPO();
		ArrayList<TransferDocPO> list2 = tTrans.getAllTransferDoc();
		ArrayList<ShipmentDocBusinessHallPO> list3 = bShip.getAllShipmentDoc();

		ArrayList<PaymentDocVO> payList = new ArrayList<PaymentDocVO>();

		// 处理ShipmentDocTransCenterPO
		if (!isEmpty(list1)) {

			for (ShipmentDocTransCenterPO po : list1) {
				String dt = po.getDate();
				if (dt.startsWith(currDate)) {

					PaymentItem p = new PaymentItem(null, dt, null, "运费",
							po.getShipmentID(), po.getMoney());
					PaymentDocVO payment = new PaymentDocVO(p, null);
					payList.add(payment);
				}
			}

		}

		// 处理TransferDocPO
		if (!isEmpty(list2)) {

			for (TransferDocPO po : list2) {
				String dt = po.getdate();
				if (dt.startsWith(currDate)) {

					PaymentItem p = new PaymentItem(null, dt, null, "运费",
							po.getTransDocID(), po.getmoney());
					PaymentDocVO payment = new PaymentDocVO(p, null);
					payList.add(payment);
				}
			}

		}

		// 处理ShipmentDocBusinessHallPO
		if (!isEmpty(list3)) {

			for (ShipmentDocBusinessHallPO po : list3) {
				String dt = po.getDate();
				if (dt.startsWith(currDate)) {

					PaymentItem p = new PaymentItem(null, dt, null, "运费",
							po.getShipmentID(), po.getMoney());
					PaymentDocVO payment = new PaymentDocVO(p, null);
					payList.add(payment);
				}
			}

		}

		// 处理人员工资
		StaffManageBLService staff = new StaffForManager();
		ArrayList<UserInfoPO> userList = staff.getAllUserPO();
		SalaryManagerBLService salary = new SalaryManager();
		ArrayList<SalaryPO> sList = salary.getSalaryStrategyListPO();

		if (userList != null && userList.size() > 0) {

			for (UserInfoPO u : userList) {
				UserRole role = u.getPosition();
				if (sList != null) {
					for (SalaryPO s : sList) {
						if (s.getPosition().equals(role)) {
							double sal = calSalary(u, s, currDate);
							if (sal > 0.0) {

								PaymentItem p = new PaymentItem(null, date,
										null, "人员工资", cd + "月份工资", sal);
								PaymentDocVO payment = new PaymentDocVO(p, null);
								payList.add(payment);
							}
						}
					}
				}
			}
		}

		return payList;
	}

	/**
	 * 
	 * @param list
	 * @return 判断list是否为空
	 */

	private boolean isEmpty(ArrayList<? extends DocumentPO> list) {
		if (list == null)
			return true;
		if (list.size() == 0)
			return true;
		return false;
	}

	private double calSalary(UserInfoPO u, SalaryPO s, String d) {
		if (s.getStrategy().equals(Strategy.FIXED))
			return s.getValue();

		if (s.getStrategy().equals(Strategy.PERCENT)) {
			double percent = s.getValue();
			double sum = 0;
			String id = u.getID();

			GetReceiveDocBLService receive = new SumReceiveDoc();
			ArrayList<ReceiveDocPO> list = receive.getAllReceiveDocPO();

			if (list != null && list.size() > 0) {

				for (ReceiveDocPO r : list) {
					if (r.getDeliverManID().equals(id)
							&& r.getReceiveDate().startsWith(d)) {
						sum += r.getReceivePrice();
					}
				}

			}
			return sum * percent;
		}

		return 0;
	}

	public boolean addPaymentDoc(PaymentDocVO vo) {
		PaymentDocPO po = new PaymentDocPO(vo.getPaymentList(),
				vo.getPaymentID());
		try {
			return pay.createPaymentDoc(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<PaymentDocVO> getUnexamedPaymentDoc() {
		try {
			ArrayList<PaymentDocPO> list = pay.getAllPaymentDoc();
			ArrayList<PaymentDocVO> unexam = new ArrayList<PaymentDocVO>();

			if (list == null)
				return unexam;

			for (PaymentDocPO po : list) {
				if (!po.getState()) {
					PaymentDocVO vo = new PaymentDocVO(po.getPaymentList(),
							po.getPaymentID());

					unexam.add(vo);
				}
			}
			return unexam;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PaymentDocVO getPaymentDoc(String id) {
		try {
			PaymentDocPO po = pay.getPaymentDoc(id);

			if (po == null)
				return null;

			PaymentDocVO vo = new PaymentDocVO(po.getPaymentList(),
					po.getPaymentID());

			return vo;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkDateAvailable(String date) {
		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currDate = format.format(d);
		String month = currDate.substring(0, 7);

		String m = date.substring(0, 7);

		if (month.equals(m)) {
			if (currDate.compareTo(date) < 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	public void endPaymentDoc() {
		SysLog log = new SysLog();
		log.addSysLog("生成付款单");

		try {
			pay.writeAllPaymentDoc();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean changePaymentDoc(PaymentDocVO vo, String id) {

		PaymentItem item = vo.getPaymentList();
		double sum = item.getSum();
		if (sum > 0.0) {
			BankAccountBLService bank = new BankAccount();
			BankAccountPO po = bank.getBankAccount(item.getAccount());
			BankAccountVO bvo = new BankAccountVO(po.getName(), po.getIncome(),
					po.getOutcome(), po.getBalance());
			bvo.addOutcome(sum);
			bank.changeBankAccount(bvo, item.getAccount());
			bank.recordBankAccount();
			// 将总额加到对应银行账户的收入中
		}
		PaymentDocPO po = new PaymentDocPO(vo.getPaymentList(),
				vo.getPaymentID());
		po.setState(true);
		try {
			boolean succ = pay.changePaymentDoc(po, id);
			pay.writeAllPaymentDoc();
			return succ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<PaymentDocVO> getAllPaymentDoc() {

		try {
			ArrayList<PaymentDocPO> list = pay.getAllPaymentDoc();

			if (list == null)
				return null;
			if (list.size() == 0)
				return null;

			ArrayList<PaymentDocVO> tList = new ArrayList<PaymentDocVO>();
			for (PaymentDocPO po : list) {
				PaymentDocVO vo = new PaymentDocVO(po.getPaymentList(),
						po.getPaymentID());

				tList.add(vo);
			}

			return tList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<PaymentDocPO> getAllPaymentDocPO() {

		try {
			return pay.getAllPaymentDoc();
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
