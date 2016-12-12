package express.presentation.mainUI;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import express.presentation.adminUI.AdminStartUI;
import express.presentation.businessSaleUI.businessArrivalUI;
import express.presentation.businessSaleUI.businessDeliverUI;
import express.presentation.businessSaleUI.businessDriverUI;
import express.presentation.businessSaleUI.businessMenuUI;
import express.presentation.businessSaleUI.businessReceiveUI;
import express.presentation.businessSaleUI.businessShipmentUI;
import express.presentation.businessSaleUI.businessVehicleUI;
import express.presentation.deliverUI.deliverMenuUI;
import express.presentation.deliverUI.deliverOrderUI;
import express.presentation.deliverUI.deliverReceiveUI;
import express.presentation.financialUI.FinanceCreateOperateUI;
import express.presentation.financialUI.FinanceCreateProfitUI;
import express.presentation.financialUI.FinanceInitAccountUI;
import express.presentation.financialUI.FinanceManageBankAccountUI;
import express.presentation.financialUI.FinanceMenuUI;
import express.presentation.financialUI.FinancePaymentUI;
import express.presentation.financialUI.FinanceSumReceiveDocUI;
import express.presentation.managerUI.managerCityPriceUI;
import express.presentation.managerUI.managerExamDocUI;
import express.presentation.managerUI.managerMemberUI;
import express.presentation.managerUI.managerMenuUI;
import express.presentation.managerUI.managerOrgUI;
import express.presentation.managerUI.managerSalaryUI;
import express.presentation.transRepoUI.AdjustUI;
import express.presentation.transRepoUI.InUI;
import express.presentation.transRepoUI.InventoryUI;
import express.presentation.transRepoUI.OutUI;
import express.presentation.transRepoUI.TranscenterRepoMenuUI;
import express.presentation.transRepoUI.ViewUI;
import express.presentation.transSaleUI.transSaleArrivalDocUI;
import express.presentation.transSaleUI.transSaleMenuUI;
import express.presentation.transSaleUI.transSaleShipmentDocUI;
import express.presentation.transSaleUI.transSaleTransferDocUI;
import express.presentation.userUI.LoginUI;
import express.presentation.userUI.SignInUI;

public class MainUI implements MainUIService {

	private JFrame frame;
	private CardLayout card;
	private JPanel pane;
	private CardLayout card1;
	private JPanel pane1;

	public MainUI(CardLayout card, JPanel pane) {
		this.pane = pane;
		this.card = card;
	}

	public void setframe(JFrame f) {
		this.frame = f;
	}

	public void setcard1(CardLayout card1) {
		this.card1 = card1;
	}

	public void setpane1(JPanel pane1) {
		this.pane1 = pane1;
	}

	public boolean jumpToLogInUI() {
		// TODO Auto-generated method stub
		frame.dispose();
		LoginUI login = new LoginUI();
		return true;
	}

	public boolean jumpToadminStartUI(String id) {
		AdminStartUI adminStartPanel = new AdminStartUI(this, id);
		pane.add("adminStartPanel", adminStartPanel);
		card.show(pane, "adminStartPanel");
		return true;
	}

	public boolean jumpTomanagerMenuUI(String id) {
		managerMenuUI manmenu = new managerMenuUI(this, id);
		pane.add("manmenu", manmenu);
		card.show(pane, "manmenu");
		return true;
	}

	public boolean jumpToViewSysLogUI() {
		ViewSysLogUI viewsyslog = new ViewSysLogUI(this);
		pane1.add("viewsyslog", viewsyslog);
		card1.show(pane1, "viewsyslog");
		return true;
	}

	public boolean jumpTomanagerMemberUI() {
		managerMemberUI manstaff = new managerMemberUI();
		pane1.add("manstaff", manstaff);
		card1.show(pane1, "manstaff");
		return true;
	}

	public boolean jumpTomanagerOrgUI() {
		managerOrgUI manorg = new managerOrgUI();
		pane1.add("manorg", manorg);
		card1.show(pane1, "manorg");
		return true;
	}

	public boolean jumpTomanagerExamDocUI() {
		managerExamDocUI manexam = new managerExamDocUI();
		pane1.add("manexam", manexam);
		card1.show(pane1, "manexam");
		return true;
	}

	public boolean jumpToStatisticDataUI(String s, int index, String date) {
		StatisticDataUI statisticdata = new StatisticDataUI(this, index, s,
				date);
		pane1.add("statisticdata", statisticdata);
		card1.show(pane1, "statisticdata");
		return true;
	};

	public boolean jumpToFinanceMenuUI(String id, boolean high) {
		FinanceMenuUI fianacemenu = new FinanceMenuUI(this, id, high);
		pane.add("fianacemenu", fianacemenu);
		card.show(pane, "fianacemenu");
		return true;
	}

	public boolean jumpToFinanceCreateProfitUI() {
		FinanceCreateProfitUI financecreateprofit = new FinanceCreateProfitUI(
				this);
		pane1.add("financecreateprofit", financecreateprofit);
		card1.show(pane1, "financecreateprofit");
		return true;
	}

	public boolean jumpToFinanceSumReceiveDocUI() {
		FinanceSumReceiveDocUI financesumreceivedoc = new FinanceSumReceiveDocUI(
				this);
		pane1.add("financesumreceivedoc", financesumreceivedoc);
		card1.show(pane1, "financesumreceivedoc");
		return true;
	}

	public boolean jumpToFinanceInitAccountUI() {
		FinanceInitAccountUI financeinitaccount = new FinanceInitAccountUI(this);
		pane1.add("financeinitaccount", financeinitaccount);
		card1.show(pane1, "financeinitaccount");
		return true;
	}

	public boolean jumpToFinanceManageBankAccountUI() {
		FinanceManageBankAccountUI financeManageBankAccount = new FinanceManageBankAccountUI(this);
		pane1.add("financeManageBankAccount", financeManageBankAccount);
		card1.show(pane1, "financeManageBankAccount");

		return true;
	}

	public boolean jumpToFinancePaymentUI() {
		FinancePaymentUI financepayment = new FinancePaymentUI(this);
		pane1.add("financepayment", financepayment);
		card1.show(pane1, "financepayment");
		return true;
	}

	public boolean jumpToFinanceCreateOperateUI() {
		FinanceCreateOperateUI financecreateoperate = new FinanceCreateOperateUI(
				this);
		pane1.add("financecreateoperate", financecreateoperate);
		card1.show(pane1, "financecreateoperate");
		return true;
	}

	// public boolean jumpToFinanceInitRepoUI (CardLayout card1,JPanel pane1){
	// FinanceInitRepoUI financeinitrepo = new FinanceInitRepoUI(this);
	// pane1.add("financeinitrepo",financeinitrepo);
	// card1.show(pane1, "financeinitrepo");
	// return true;
	// }
	//
	// public boolean jumpToFinanceInitBankUI (CardLayout card1,JPanel pane1){
	// FinanceInitBankUI financeinitbank = new FinanceInitBankUI(this);
	// pane1.add("financeinitbank",financeinitbank);
	// card1.show(pane1, "financeinitbank");
	// return true;
	// }
	//
	// public boolean jumpToFinanceInitOrgUI (CardLayout card1,JPanel pane1){
	// FinanceInitOrgUI financeinitorg = new FinanceInitOrgUI(this);
	// pane1.add("financeinitorg",financeinitorg);
	// card1.show(pane1, "financeinitorg");
	// return true;
	// }
	//
	// public boolean jumpToFinanceInitVehicleUI (CardLayout card1,JPanel
	// pane1){
	// FinanceInitVehicleUI financeinitvehicle = new FinanceInitVehicleUI(this);
	// pane1.add("financeinitvehicle",financeinitvehicle);
	// card1.show(pane1, "financeinitvehicle");
	// return true;
	// }
	//
	// public boolean jumpToFinancePreviousInitUI (CardLayout card1,JPanel
	// pane1){
	// FinancePreviousInitUI financeinitprevious = new
	// FinancePreviousInitUI(this);
	// pane1.add("financeinitprevious",financeinitprevious);
	// card1.show(pane1, "financeinitprevious");
	// return true;
	// }
	//
	// public boolean jumpToFinanceInitUserUI (CardLayout card1,JPanel pane1){
	// FinanceInitUserUI financeinituser = new FinanceInitUserUI(this);
	// pane1.add("financeinituser",financeinituser);
	// card1.show(pane1, "financeinituser");
	// return true;
	// }

	public boolean jumpToinUI() {
		// TODO Auto-generated method stub
		InUI inPanel = new InUI(this);
		pane1.add("inPanel", inPanel);
		card1.show(pane1, "inPanel");

		return true;
	}

	public boolean jumpTooutUI() {
		OutUI outPanel = new OutUI(this);
		pane1.add("outPanel", outPanel);
		card1.show(pane1, "outPanel");
		return true;
	}

	public boolean jumpTotranscenterRepoMenuUI(String id) {
		TranscenterRepoMenuUI transcenterRepoMenuUI = new TranscenterRepoMenuUI(
				this, id);
		pane.add("repomenu", transcenterRepoMenuUI);
		card.show(pane, "repomenu");
		return true;
	}

	public boolean jumpToadjustUI() {
		AdjustUI adjustPanel = new AdjustUI(this);
		pane1.add("adjustPanel", adjustPanel);
		card1.show(pane1, "adjustPanel");
		return true;
	}

	public boolean jumpToviewUI() {
		ViewUI viewPanel = new ViewUI(this);
		pane1.add("viewPanel", viewPanel);
		card1.show(pane1, "viewPanel");
		return true;
	}

	public boolean jumpToviewShowUI() {
		return true;
	}

	public boolean jumpToinventoryUI() {
		InventoryUI inventoryPanel = new InventoryUI(this);
		pane1.add("inventoryPanel", inventoryPanel);
		card1.show(pane1, "inventoryPanel");
		return true;
	}

	public boolean jumpTobusinessMenuUI(String id) {
		businessMenuUI businessmenu = new businessMenuUI(this, id);
		pane.add("businessmenu", businessmenu);
		card.show(pane, "businessmenu");
		return true;
	}

	public boolean jumpTobusinessArrivalUI() {
		businessArrivalUI businessArrivalPanel = new businessArrivalUI();
		pane1.add("businessArrivalPanel", businessArrivalPanel);
		card1.show(pane1, "businessArrivalPanel");
		return true;
	}

	public boolean jumpTobusinessDriverUI() {
		businessDriverUI businessDriverpanel = new businessDriverUI();
		pane1.add("businessDriverpanel", businessDriverpanel);
		card1.show(pane1, "businessDriverpanel");
		return true;
	}

	public boolean jumpTobusinessDeliverUI() {
		businessDeliverUI businessDeliverPanel = new businessDeliverUI();
		pane1.add("businessDeliverPanel", businessDeliverPanel);
		card1.show(pane1, "businessDeliverPanel");

		return true;
	}

	public boolean jumpTobusinessVehicleUI() {
		businessVehicleUI businessVehiclepanel = new businessVehicleUI();
		pane1.add("businessVehiclepanel", businessVehiclepanel);
		card1.show(pane1, "businessVehiclepanel");
		return true;
	}

	public boolean jumpTobusinessReceiveUI() {
		businessReceiveUI businessReceivePanel = new businessReceiveUI();
		pane1.add("businessReceivePanel", businessReceivePanel);
		card1.show(pane1, "businessReceivePanel");
		return true;
	}

	public boolean jumpTobusinessShipmentUI() {
		businessShipmentUI businessShipmentPanel = new businessShipmentUI();
		pane1.add("businessShipmentPanel", businessShipmentPanel);
		card1.show(pane1, "businessShipmentPanel");
		return true;
	}

	public boolean jumpTodeliverOrderUI() {
		deliverOrderUI deliverOrderPanel = new deliverOrderUI();
		pane1.add("deliverOrderPanel", deliverOrderPanel);
		card1.show(pane1, "deliverOrderPanel");
		return true;
	}

	public boolean jumpTodeliverReceiveUI() {
		deliverReceiveUI deliverReceivePanel = new deliverReceiveUI();
		pane1.add("deliverReceivePanel", deliverReceivePanel);
		card1.show(pane1, "deliverReceivePanel");
		return true;
	}

	public boolean jumpTodeliverMenuUI(String id) {
		deliverMenuUI deliverMenuPanel = new deliverMenuUI(this, id);
		pane.add("deliverMenuPanel", deliverMenuPanel);
		card.show(pane, "deliverMenuPanel");
		return true;
	}

	public boolean jumpTotransSaleMenuUI(String id) {
		transSaleMenuUI transSaleMenuPanel = new transSaleMenuUI(this, id);
		pane.add("transSaleMenuPanel", transSaleMenuPanel);
		card.show(pane, "transSaleMenuPanel");
		return true;
	}

	public boolean jumpTotransSaleTransterDocUI() {
		transSaleTransferDocUI transSaleTransterDocPanel = new transSaleTransferDocUI();
		pane1.add("transSaleTransterDocPanel", transSaleTransterDocPanel);
		card1.show(pane1, "transSaleTransterDocPanel");

		return true;
	}

	public boolean jumpTotransSaleShipmentDocUI() {
		transSaleShipmentDocUI transSaleShipmentDocPanel = new transSaleShipmentDocUI();
		pane1.add("transSaleShipmentDocPanel", transSaleShipmentDocPanel);
		card1.show(pane1, "transSaleShipmentDocPanel");

		return true;
	}

	public boolean jumpTotransSaleArrivalDocUI() {
		transSaleArrivalDocUI transSaleArrivalDocPanel = new transSaleArrivalDocUI();
		pane1.add("transSaleArrivalDocPanel", transSaleArrivalDocPanel);
		card1.show(pane1, "transSaleArrivalDocPanel");

		return true;
	}

	public boolean jumpToViewProfitUI() {
		ViewProfitUI viewProfitPanel = new ViewProfitUI(this);
		pane1.add("viewProfitPanel", viewProfitPanel);
		card1.show(pane1, "viewProfitPanel");
		return true;
	}

	public boolean jumpToViewOperateUI() {
		ViewOperateUI viewOperatePanel = new ViewOperateUI(this);
		pane1.add("viewOperatePanel", viewOperatePanel);
		card1.show(pane1, "viewOperatePanel");
		return true;
	}

	@Override
	public boolean jumpTomanagerSalaryUI() {
		// TODO Auto-generated method stub
		managerSalaryUI managersalary = new managerSalaryUI();
		pane1.add("managersalary", managersalary);
		card1.show(pane1, "managersalary");
		return true;
	}

	@Override
	public boolean jumpTomanagerCityPriceUI() {
		// TODO Auto-generated method stub
		managerCityPriceUI managercityprice = new managerCityPriceUI();
		pane1.add("managercityprice", managercityprice);
		card1.show(pane1, "managercityprice");
		return true;
	}

}
