package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.initAccountBL.InitAccountController;
import express.businesslogicService.financialBLService.InnerAccountBLService;
import express.po.OrgProperty;
import express.po.UserRole;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.MyTableModel;
import express.vo.BankAccountVO;
import express.vo.InnerAccountVO;
import express.vo.OrganizationVO;
import express.vo.RepoInfoVO;
import express.vo.UserInfoVO;
import express.vo.VehicleInfoVO;

public class FinanceInitAccountPreUI extends JDialog {

	private MyOtherBlueLabel ok;
	private JPanel pane;
	private JTable[] table;
	private MyTableModel[] tableModel;
	private InnerAccountBLService iab;
	private InnerAccountVO accvo;
	private Font font = new Font("幼圆", Font.PLAIN, 20);
	private Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
	private int tablewidth = 600;
	private int tableheight = 500;

	public FinanceInitAccountPreUI(int index) {
		this.setTitle("之前期初信息");
		this.setLayout(null);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.WHITE);

		JListener lis = new JListener();
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setBounds(0, 0, 500, 400);
		
		table = new JTable[5];
		tableModel = new MyTableModel[5];
		iab = new InitAccountController();
		accvo = iab.getPrevious(index);

		initstafftable();
		initorgtable();
		initvehicletable();
		initrepotable();
		initbankaccounttable();
		
		for(int i = 0;i<5;i++){
			table[i] = new JTable(tableModel[i]);
			table[i].setRowHeight(40);
			table[i].setFont(f);
			table[i].getTableHeader().setFont(font);
			table[i].getTableHeader().setReorderingAllowed(false);
			table[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			table[i].setBounds(0, 0, tablewidth, tableheight);
			table[i].setBackground(Color.WHITE);
			JScrollPane sp = new JScrollPane(table[i]);
			MyScrollPane render = new MyScrollPane();
			sp.getVerticalScrollBar().setUI(render);
			render.setscrollbar();
			sp.setBorder(null);
			sp.setOpaque(false);
			sp.getViewport().setOpaque(false);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setPreferredSize(new Dimension(tablewidth, tableheight));
			pane.add(sp);
		}
		
		JScrollPane scrollp = new JScrollPane(pane);
		scrollp.setBounds(0, 0,  600, 420);
		MyScrollPane render = new MyScrollPane();
		// scrollp
		// .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollp.getHorizontalScrollBar().setUI(render);
		render.setscrollbar();
		scrollp.setOpaque(false);
		scrollp.getViewport().setOpaque(false);
		scrollp.setBorder(null);
		repaint();
		this.add(scrollp);
		
		ok = new MyOtherBlueLabel("返回");
		ok.setBounds(200, 445, 200, 35);
		ok.addMouseListener(lis);
		this.add(ok);
	}

	private void initstafftable() {
		Class[] typeArray = { Object.class, Object.class, Object.class,
				Object.class, Object.class, Object.class, Object.class };
		Object[][] datas = {};
		String[] headers = { "姓名", "性别", "手机�?", "入职日期", "职位", "�?在机�?", "工号" };
		ArrayList<UserInfoVO> userarr = accvo.getUserInfo();
		
		if (userarr != null) {
			datas = new Object[userarr.size()][7];
			for (int i = 0; i < userarr.size(); i++) {
				UserInfoVO temp = userarr.get(i);
				datas[i][0] = temp.getName();
				datas[i][1] = temp.getGender() ? "�?" : "�?";
				datas[i][2] = temp.getPhoneNum();
				datas[i][3] = temp.getDate();
				UserRole posit = temp.getPosition();
				datas[i][4] = temp.transposition(posit);
				datas[i][5] = temp.getCity();
				datas[i][6] = temp.getID();
			}
		}
		
		tableModel[0] = new MyTableModel(datas, headers, typeArray);
		
	}
	
	private void initorgtable(){
		Class[] typeArray = { Object.class, Object.class,
				Object.class, Object.class, Object.class };
		String[] headers = {"�?属城�?", "机构全称", "性质", "机构代号", "地址" };
		Object[][] datas = {};
		
		ArrayList<OrganizationVO> orgarr = accvo.getOrganizationInfo();

		if (orgarr != null) {
			datas = new Object[orgarr.size()][5];
			for (int i = 0; i < orgarr.size(); i++) {
				OrganizationVO temp = orgarr.get(i);
				datas[i][0] = temp.getCity();
				datas[i][1] = temp.getName();
				OrgProperty orgtemp = temp.getOrgProperty();
				if (orgtemp.equals(OrgProperty.TRANSCENTER))
					datas[i][2] = "中转中心";
				if (orgtemp.equals(OrgProperty.SALE))
					datas[i][2] = "营业�?";
				if (orgtemp.equals(OrgProperty.OTHER))
					datas[i][2] = "总部";
				datas[i][3] = temp.getOrgID();
				datas[i][4] = temp.getAddress();
			}
		}

		tableModel[1] = new MyTableModel(datas, headers, typeArray);
	}

	private void initvehicletable(){

		Class[] typeArray = {Object.class, Object.class,
				Object.class, Object.class};
		String[] headers = {  "车牌�?", "车辆�?属机�?", "车辆代号", "服役时间"};
		Object[][] datas = {};
		
		ArrayList<VehicleInfoVO> veharr = accvo.getVehicleInfo();
		if (veharr != null) {
			datas = new Object[veharr.size()][4];
			for (int i = 0; i < veharr.size(); i++) {
				VehicleInfoVO temp = veharr.get(i);
				datas[i][0] = temp.getLicense();
				datas[i][1] = temp.getOrgID();
				datas[i][2] = temp.getMark();
				datas[i][3] = temp.getUseYear();
			}
		}

		tableModel[2] = new MyTableModel(datas, headers, typeArray);
	}
	
	private void initrepotable(){
		Class[] typeArray = { Object.class, Integer.class,
				Integer.class, Integer.class, Integer.class};
		String[] headers = {  "仓库地址", "航运区排�?", "铁运区排�?", "汽运区排�?", "机动区排�?" };
		Object[][] datas = {};

		ArrayList<RepoInfoVO> repoarr = accvo.getRepoInfo();
		if (repoarr != null) {
			datas = new Object[repoarr.size()][5];
			for (int i = 0; i < repoarr.size(); i++) {
				RepoInfoVO temp = repoarr.get(i);
				datas[i][0] = temp.getCity();
				datas[i][1] = temp.getAirShelfSize();
				datas[i][2] = temp.getTrainShelfSize();
				datas[i][3] = temp.getTruckShelfSize();
				datas[i][4] = temp.getFlexibleShelfSize();
			}
		}

		tableModel[3] = new MyTableModel(datas, headers, typeArray);
	}
	
	private void initbankaccounttable(){
		Class[] typeArray = { Object.class, Double.class,
				Double.class, Double.class };
		String[] headers = {  "账户�?", "收入金额", "支出金额", "余额" };
		Object[][] datas = {};
		
		ArrayList<BankAccountVO> accarr = iab.getBankAccount();
		if(accarr!=null){
			datas = new Object[accarr.size()][4];
			for (int i = 0; i < accarr.size(); i++) {
				BankAccountVO temp = accarr.get(i);
				datas[i][0] = temp.getName();
				datas[i][1] = temp.getIncome();
				datas[i][2] = temp.getOutcome();
				datas[i][3] = temp.getBalance();
			}
		}

		tableModel[4] = new MyTableModel(datas, headers, typeArray);
	}
	
	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==ok){
				dispose();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			 if (arg0.getSource() == ok) {
					ok.whenPressed();
				}
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			 if (arg0.getSource() == ok) {
					ok.setMyColor();
				}
			
		}
		
	}
}
