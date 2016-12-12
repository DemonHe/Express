package express.presentation.financialUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.initAccountBL.InitAccountController;
import express.businesslogicService.financialBLService.InnerAccountBLService;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.po.OrgProperty;
import express.po.UserRole;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.MyTableModel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.BankAccountVO;
import express.vo.OrganizationVO;
import express.vo.RepoInfoVO;
import express.vo.UserInfoVO;
import express.vo.VehicleInfoVO;

public class FinanceInitAccountUI extends JPanel {

	private JPanel tippane;
	private JPanel staff, org, vehicle, repo, bankaccount, showprevious;
	private JTabbedPane tabpane;
	private JTable[] table;
	private MyTableModel[] tableModel;
	private JTextField nametf, phonetf, datetf, idtf, orgnametf, orgidtf,
			accountnametf, incometf, expensetf, balancetf, vehiclenumtf,
			vehicleorgtf, vehicleidtf, timetf, repoaddresstf, truckrowtf,
			citycb, orgaddtf, airrowtf, flexiblerowtf, trainrowtf;
	private JComboBox gendercb, positioncb, orgcb, butypecb;
	private DateChooser datechooser;
	private MyOtherBlueLabel ok;
	private MyOtherRedLabel detele;
	private MyOtherGreenLabel add;
	private int leftside = 10;
	private int textwidth = 120;
	private int textheight = 30;
	private int labelwidth = 50;
	private int labelheight = 30;
	private int tablewidth = 720;
	private int tableheight = 480;
	private Font font = new Font("楷体", Font.PLAIN, 20);
	private Font f = new Font("仿宋", Font.PLAIN, 18);
	private OrgInfoManageService orginfo;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";
	private InnerAccountBLService iab;
	private Color c;
	private Listener listener;
	private Foclistener foclis;

	public FinanceInitAccountUI(MainUIService main) {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		listener = new Listener();
		foclis = new Foclistener();

		table = new JTable[6];
		tableModel = new MyTableModel[6];
		orginfo = new OrgForManager();
		iab = new InitAccountController();

		initstaffPanel();
		initorgPanel();
		initvehiclePanel();
		initrepoPanel();
		initbackaccountPanel();
		initpreviousPanel();

		for (int i = 0; i < 6; i++) {
			table[i].getTableHeader().setFont(font);
			table[i].getTableHeader().setReorderingAllowed(false);
		}

		tabpane = new JTabbedPane();
		tabpane.add("人员信息", staff);
		tabpane.add("机构信息", org);
		tabpane.add("车辆信息", vehicle);
		tabpane.add("库存信息", repo);
		tabpane.add("银行账户信息", bankaccount);
		tabpane.add("查看之前期初信息", showprevious);
		tabpane.setFont(font);
		tabpane.setBounds(50, 0, 720, 610);
		this.add(tabpane);

		ok = new MyOtherBlueLabel("完成建账");
		ok.setBounds(50, 620, 180, 35);
		this.add(ok);

		detele = new MyOtherRedLabel("删除信息");
		detele.setBounds(590, 620, 180, 35);
		this.add(detele);

		add = new MyOtherGreenLabel("添加信息");
		add.setBounds(320, 620, 180, 35);
		this.add(add);

		ok.addMouseListener(listener);
		detele.addMouseListener(listener);
		add.addMouseListener(listener);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setOpaque(false);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

	}

	private void initstaffPanel() {
		// staff
		// 姓名，�?�别，手机号，入职日期，职位（可选择），�?在机构（可�?�择），工号（�?�择机构后自动显示一半）
		staff = new JPanel();
		staff.setOpaque(false);
		staff.setLayout(null);

		Class[] typeArray = { Boolean.class, Object.class, Object.class,
				Object.class, Object.class, Object.class, Object.class,
				Object.class, Object.class };
		String[] headers = { "选择", "姓名", "性别", "手机�?", "入职日期", "职位", "�?在机�?",
				"工号", "修改" };
		Object[][] datas = {};
		String[] genders = { "�?", "�?" };
		String[] pos = { "快�?�员", "管理�?", "总经�?", "普�?�财务人�?", "�?高权限财务人�?",
				"中转中心仓库管理人员", "中转中心业务�?", "营业厅业务员" };
		String[] orgs = {};
		if (orginfo.getAllOrgName() != null) {
			orgs = new String[orginfo.getAllOrgName().size()];
			for (int x = 0; x < orginfo.getAllOrgName().size(); x++) {
				orgs[x] = orginfo.getAllOrgName().get(x);
			}
		}
		// orgs = (String[]) orginfo.getAllOrgName().toArray();

		ArrayList<UserInfoVO> userarr = iab.getUser();
		if (userarr != null) {
			datas = new Object[userarr.size()][9];
			for (int i = 0; i < userarr.size(); i++) {
				UserInfoVO temp = userarr.get(i);
				datas[i][0] = false;
				datas[i][1] = temp.getName();
				datas[i][2] = temp.getGender() ? "�?" : "�?";
				datas[i][3] = temp.getPhoneNum();
				datas[i][4] = temp.getDate();
				UserRole posit = temp.getPosition();
				datas[i][5] = temp.transposition(posit);
				datas[i][6] = temp.getCity();
				datas[i][7] = temp.getID();
				datas[i][8] = changeunder;
			}
		}

		tableModel[0] = new MyTableModel(datas, headers, typeArray);
		table[0] = new JTable(tableModel[0]);
		table[0].setRowHeight(40);
		table[0].setFont(f);
		table[0].setBounds(0, 0, tablewidth, tableheight);
		table[0].addMouseListener(listener);

		TableColumn col_1 = table[0].getColumnModel().getColumn(2);
		col_1.setCellEditor(new DefaultCellEditor(new JComboBox(genders)));
		TableColumn col_2 = table[0].getColumnModel().getColumn(5);
		col_2.setCellEditor(new DefaultCellEditor(new JComboBox(pos)));
		TableColumn col_3 = table[0].getColumnModel().getColumn(6);
		col_3.setCellEditor(new DefaultCellEditor(new JComboBox(orgs)));

		JScrollPane scrollPanes = new JScrollPane(table[0]);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(0, 0, tablewidth, tableheight);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(null);
		render.setscrollbar();
		updateUI();
		staff.add(scrollPanes);

		JLabel namel = new JLabel("姓名");
		namel.setBounds(leftside, tableheight + 10, labelwidth, labelheight);
		namel.setFont(font);
		staff.add(namel);

		nametf = new JTextField();
		nametf.setBounds(leftside + labelwidth, tableheight + 10,
				textwidth - 30, textheight);
		nametf.setFont(f);
		staff.add(nametf);
		c = nametf.getForeground();

		JLabel genderl = new JLabel("性别");
		genderl.setBounds(leftside + labelwidth + 105, tableheight + 10,
				labelwidth, labelheight);
		genderl.setFont(font);
		staff.add(genderl);

		gendercb = new JComboBox(genders);
		gendercb.setBounds(leftside + 2 * labelwidth + 110, tableheight + 10,
				labelwidth, textheight);
		gendercb.setFont(f);
		staff.add(gendercb);

		JLabel phonel = new JLabel("手机�?");
		phonel.setBounds(leftside + 3 * labelwidth + 125, tableheight + 10,
				labelwidth + 20, labelheight);
		phonel.setFont(font);
		staff.add(phonel);

		phonetf = new JTextField();
		phonetf.setBounds(leftside + 4 * labelwidth + 140, tableheight + 10,
				textwidth, textheight);
		phonetf.setFont(f);
		staff.add(phonetf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(leftside + 4 * labelwidth + 150 + textwidth,
				tableheight + 10, labelwidth, labelheight);
		positionl.setFont(font);
		staff.add(positionl);

		positioncb = new JComboBox(pos);
		positioncb.setBounds(leftside + 5 * labelwidth + 150 + textwidth,
				tableheight + 10, textwidth + 50, textheight);
		positioncb.setFont(f);
		staff.add(positioncb);

		JLabel datel = new JLabel("入职日期");
		datel.setBounds(leftside, tableheight + 2 * labelheight,
				labelwidth + 30, labelheight);
		datel.setFont(font);
		staff.add(datel);

		datetf = new JTextField();
		datetf.setBounds(leftside + labelwidth + 40, tableheight + 2
				* labelheight, textwidth, textheight);
		datetf.setFont(f);
		datetf.setEditable(false);
		staff.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(leftside + textwidth + labelwidth + 50,
				tableheight + 2 * labelheight - 5, 40, 40);
		staff.add(datechooser);

		JLabel orgl = new JLabel("机构");
		orgl.setBounds(leftside + textwidth + labelwidth + 100, tableheight + 2
				* labelheight, labelwidth, labelheight);
		orgl.setFont(font);
		staff.add(orgl);

		orgcb = new JComboBox(orgs);
		orgcb.setBounds(leftside + textwidth + 2 * labelwidth + 100,
				tableheight + 2 * labelheight, textwidth + 20, textheight);
		orgcb.setFont(f);
		staff.add(orgcb);

		JLabel idl = new JLabel("工号");
		idl.setBounds(leftside + 2 * textwidth + 2 * labelwidth + 130,
				tableheight + 2 * labelheight, labelwidth, labelheight);
		idl.setFont(font);
		staff.add(idl);

		idtf = new JTextField();
		idtf.setBounds(leftside + 2 * textwidth + 3 * labelwidth + 130,
				tableheight + 2 * labelheight, textwidth, textheight);
		idtf.setFont(f);
		staff.add(idtf);

	}

	private void initorgPanel() {
		// org
		// �?属城市，机构全称，�?�质（�?�择营业厅�?�中转中心），机构代�?
		org = new JPanel();
		org.setOpaque(false);
		org.setLayout(null);
		labelwidth = 100;
		textwidth = 150;

		Class[] typeArray = { Boolean.class, Object.class, Object.class,
				Object.class, Object.class, Object.class, Object.class };
		String[] headers = { "选择", "�?属城�?", "机构全称", "性质", "机构代号", "地址", "修改" };
		Object[][] datas = {};
		String[] butype = { "营业�?", "中转中心", "总部" };
		ArrayList<OrganizationVO> orgarr = iab.getOrg();

		if (orgarr != null) {
			datas = new Object[orgarr.size()][7];
			for (int i = 0; i < orgarr.size(); i++) {
				OrganizationVO temp = orgarr.get(i);
				datas[i][0] = false;
				datas[i][1] = temp.getCity();
				datas[i][2] = temp.getName();
				OrgProperty orgtemp = temp.getOrgProperty();
				if (orgtemp.equals(OrgProperty.TRANSCENTER))
					datas[i][3] = "中转中心";
				if (orgtemp.equals(OrgProperty.SALE))
					datas[i][3] = "营业�?";
				if (orgtemp.equals(OrgProperty.OTHER))
					datas[i][3] = "总部";
				datas[i][4] = temp.getOrgID();
				datas[i][5] = temp.getAddress();
				datas[i][6] = changeunder;
			}
		}

		tableModel[1] = new MyTableModel(datas, headers, typeArray);
		table[1] = new JTable(tableModel[1]);
		table[1].setRowHeight(40);
		table[1].setFont(f);
		table[1].setBounds(0, 0, tablewidth, tableheight);
		table[1].addMouseListener(listener);

		TableColumn col_1 = table[1].getColumnModel().getColumn(3);
		col_1.setCellEditor(new DefaultCellEditor(new JComboBox(butype)));

		JScrollPane scrollPanes = new JScrollPane(table[1]);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(0, 0, tablewidth, tableheight);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(null);
		render.setscrollbar();
		updateUI();
		org.add(scrollPanes);

		JLabel cityl = new JLabel("�?属城�?");
		cityl.setBounds(leftside, tableheight + 10, labelwidth, labelheight);
		cityl.setFont(font);
		org.add(cityl);

		citycb = new JTextField();
		citycb.setBounds(leftside + labelwidth - 10, tableheight + 10,
				labelwidth, textheight);
		citycb.setFont(f);
		org.add(citycb);

		JLabel orgnamel = new JLabel("机构全称");
		orgnamel.setBounds(leftside + 2 * labelwidth, tableheight + 10,
				labelwidth, labelheight);
		orgnamel.setFont(font);
		org.add(orgnamel);

		orgnametf = new JTextField();
		orgnametf.setBounds(leftside + 3 * labelwidth - 5, tableheight + 10,
				textwidth - 20, textheight);
		orgnametf.setFont(f);
		org.add(orgnametf);

		JLabel butypel = new JLabel("机构性质");
		butypel.setBounds(leftside + 3 * labelwidth + 10 + textwidth,
				tableheight + 10, labelwidth, labelheight);
		butypel.setFont(font);
		org.add(butypel);

		butypecb = new JComboBox(butype);
		butypecb.setBounds(leftside + 4 * labelwidth + 10 + textwidth,
				tableheight + 10, labelwidth, textheight);
		butypecb.setFont(f);
		org.add(butypecb);

		JLabel orgaddl = new JLabel("机构地址");
		orgaddl.setBounds(leftside + 50, tableheight + 2 * labelheight,
				labelwidth, labelheight);
		orgaddl.setFont(font);
		org.add(orgaddl);

		orgaddtf = new JTextField();
		orgaddtf.setBounds(leftside + labelwidth + 60, tableheight + 2
				* labelheight, textwidth, textheight);
		orgaddtf.setFont(f);
		org.add(orgaddtf);

		JLabel orgidl = new JLabel("机构代号");
		orgidl.setBounds(leftside + 50 + labelwidth + textwidth + 40,
				tableheight + 2 * labelheight, labelwidth, labelheight);
		orgidl.setFont(font);
		org.add(orgidl);

		orgidtf = new JTextField();
		orgidtf.setBounds(leftside + 50 + 2 * labelwidth + textwidth + 50,
				tableheight + 2 * labelheight, textwidth, textheight);
		orgidtf.setFont(f);
		org.add(orgidtf);
	}

	private void initbackaccountPanel() {
		// 账户名，收入金额，支出金额，余额（自动生成）
		bankaccount = new JPanel();
		bankaccount.setOpaque(false);
		bankaccount.setLayout(null);
		leftside = 50;
		labelwidth = 100;
		textwidth = 150;

		Class[] typeArray = { Boolean.class, Object.class, Double.class,
				Double.class, Double.class, Object.class };
		String[] headers = { "选择", "账户�?", "收入金额", "支出金额", "余额", changeunder };
		Object[][] datas = {};

		ArrayList<BankAccountVO> accarr = iab.getBankAccount();
		if (accarr != null) {
			datas = new Object[accarr.size()][6];
			for (int i = 0; i < accarr.size(); i++) {
				BankAccountVO temp = accarr.get(i);
				datas[i][0] = false;
				datas[i][1] = temp.getName();
				datas[i][2] = temp.getIncome();
				datas[i][3] = temp.getOutcome();
				datas[i][4] = temp.getBalance();
				datas[i][5] = changeunder;
			}
		}

		tableModel[4] = new MyTableModel(datas, headers, typeArray);
		table[4] = new JTable(tableModel[4]);
		table[4].setRowHeight(40);
		table[4].setFont(f);
		table[4].setBounds(0, 0, tablewidth, tableheight);
		table[4].addMouseListener(listener);

		JScrollPane scrollPanes = new JScrollPane(table[4]);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(0, 0, tablewidth, tableheight);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(null);
		render.setscrollbar();
		updateUI();
		bankaccount.add(scrollPanes);

		JLabel accountnamel = new JLabel("账户�?");
		accountnamel.setBounds(leftside, tableheight + 10, labelwidth,
				labelheight);
		accountnamel.setFont(font);
		bankaccount.add(accountnamel);

		accountnametf = new JTextField();
		accountnametf.setBounds(leftside + labelwidth + 10, tableheight + 10,
				textwidth, textheight);
		accountnametf.setFont(f);
		bankaccount.add(accountnametf);

		JLabel incomel = new JLabel("收入金额");
		incomel.setBounds(leftside + labelwidth + textwidth + 40,
				tableheight + 10, labelwidth, labelheight);
		incomel.setFont(font);
		bankaccount.add(incomel);

		incometf = new JTextField();
		incometf.setBounds(leftside + 2 * labelwidth + textwidth + 50,
				tableheight + 10, textwidth, textheight);
		incometf.setFont(f);
		incometf.addFocusListener(foclis);
		bankaccount.add(incometf);

		JLabel expensel = new JLabel("支出金额");
		expensel.setBounds(leftside, tableheight + 2 * labelheight, labelwidth,
				labelheight);
		expensel.setFont(font);
		bankaccount.add(expensel);

		expensetf = new JTextField();
		expensetf.setBounds(leftside + labelwidth + 10, tableheight + 2
				* labelheight, textwidth, textheight);
		expensetf.setFont(f);
		expensetf.addFocusListener(foclis);
		bankaccount.add(expensetf);

		JLabel balancel = new JLabel("余额");
		balancel.setBounds(leftside + labelwidth + textwidth + 40, tableheight
				+ 2 * labelheight, labelwidth, labelheight);
		balancel.setFont(font);
		bankaccount.add(balancel);

		balancetf = new JTextField();
		balancetf.setBounds(leftside + 2 * labelwidth + textwidth + 50,
				tableheight + 2 * labelheight, textwidth, textheight);
		balancetf.setFont(f);
		balancetf.setEditable(false);
		bankaccount.add(balancetf);
	}

	private void initvehiclePanel() {
		// 车牌号，车辆�?属机构，车辆代号，服役时�?
		vehicle = new JPanel();
		vehicle.setOpaque(false);
		vehicle.setLayout(null);

		leftside = 50;

		Class[] typeArray = { Boolean.class, Object.class, Object.class,
				Object.class, Integer.class, Object.class };
		String[] headers = { "选择", "车牌�?", "车辆�?属机�?", "车辆代号", "服役时间", "修改" };
		Object[][] datas = {};

		ArrayList<VehicleInfoVO> veharr = iab.getVehicle();
		if (veharr != null) {
			datas = new Object[veharr.size()][6];
			for (int i = 0; i < veharr.size(); i++) {
				VehicleInfoVO temp = veharr.get(i);
				datas[i][0] = false;
				datas[i][1] = temp.getLicense();
				datas[i][2] = temp.getOrgID();
				datas[i][3] = temp.getMark();
				datas[i][4] = temp.getUseYear();
				datas[i][5] = changeunder;
			}
		}

		tableModel[2] = new MyTableModel(datas, headers, typeArray);
		table[2] = new JTable(tableModel[2]);
		table[2].setRowHeight(40);
		table[2].setFont(f);
		table[2].setBounds(0, 0, tablewidth, tableheight);
		table[2].addMouseListener(listener);

		JScrollPane scrollPanes = new JScrollPane(table[2]);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(0, 0, tablewidth, tableheight);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(null);
		updateUI();
		vehicle.add(scrollPanes);

		JLabel vehiclenuml = new JLabel("车牌�?");
		vehiclenuml.setBounds(leftside, tableheight + 10, labelwidth,
				labelheight);
		vehiclenuml.setFont(font);
		vehicle.add(vehiclenuml);

		vehiclenumtf = new JTextField();
		vehiclenumtf.setBounds(leftside + labelwidth + 10, tableheight + 10,
				textwidth, textheight);
		vehiclenumtf.setFont(f);
		vehicle.add(vehiclenumtf);

		JLabel vehicleorgl = new JLabel("车辆�?属机�?");
		vehicleorgl.setBounds(leftside + labelwidth + textwidth + 40,
				tableheight + 10, labelwidth + 20, labelheight);
		vehicleorgl.setFont(font);
		vehicle.add(vehicleorgl);

		vehicleorgtf = new JTextField();
		vehicleorgtf.setBounds(leftside + 2 * labelwidth + textwidth + 70,
				tableheight + 10, textwidth, textheight);
		vehicleorgtf.setFont(f);
		vehicle.add(vehicleorgtf);

		JLabel vehicleidl = new JLabel("车辆代号");
		vehicleidl.setBounds(leftside, tableheight + 2 * labelheight,
				labelwidth, labelheight);
		vehicleidl.setFont(font);
		vehicle.add(vehicleidl);

		vehicleidtf = new JTextField();
		vehicleidtf.setBounds(leftside + labelwidth + 10, tableheight + 2
				* labelheight, textwidth, textheight);
		vehicleidtf.setFont(f);
		vehicle.add(vehicleidtf);

		JLabel timel = new JLabel("服役时间");
		timel.setBounds(leftside + labelwidth + textwidth + 40, tableheight + 2
				* labelheight, labelwidth, labelheight);
		timel.setFont(font);
		vehicle.add(timel);

		timetf = new JTextField();
		timetf.setBounds(leftside + 2 * labelwidth + textwidth + 70,
				tableheight + 2 * labelheight, textwidth, textheight);
		timetf.setFont(f);
		timetf.addFocusListener(foclis);
		vehicle.add(timetf);
	}

	private void initrepoPanel() {
		// 仓库地址，仓库（航运区�?�铁运区、汽运区、机动区）排�?
		repo = new JPanel();
		repo.setOpaque(false);
		repo.setLayout(null);
		leftside = 10;
		labelwidth = 120;
		textwidth = 120;

		Class[] typeArray = { Boolean.class, Object.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Object.class };
		String[] headers = { "选择", "仓库地址", "航运区排�?", "铁运区排�?", "汽运区排�?", "机动区排�?",
				"修改" };
		Object[][] datas = {};

		ArrayList<RepoInfoVO> repoarr = iab.getRepo();
		if (repoarr != null) {
			datas = new Object[repoarr.size()][7];
			for (int i = 0; i < repoarr.size(); i++) {
				RepoInfoVO temp = repoarr.get(i);
				datas[i][0] = false;
				datas[i][1] = temp.getCity();
				datas[i][2] = temp.getAirShelfSize();
				datas[i][3] = temp.getTrainShelfSize();
				datas[i][4] = temp.getTruckShelfSize();
				datas[i][5] = temp.getFlexibleShelfSize();
				datas[i][6] = changeunder;
			}
		}

		tableModel[3] = new MyTableModel(datas, headers, typeArray);
		table[3] = new JTable(tableModel[3]);
		table[3].setRowHeight(40);
		table[3].setFont(f);
		table[3].setBounds(0, 0, tablewidth, tableheight);
		table[3].addMouseListener(listener);

		JScrollPane scrollPanes = new JScrollPane(table[3]);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(0, 0, tablewidth, tableheight);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(null);
		updateUI();
		repo.add(scrollPanes);

		JLabel repoaddressl = new JLabel("仓库�?在机构代�?");
		repoaddressl.setBounds(leftside + 20, tableheight + 10,
				labelwidth + 40, labelheight);
		repoaddressl.setFont(font);
		repo.add(repoaddressl);

		repoaddresstf = new JTextField();
		repoaddresstf.setBounds(leftside + labelwidth + 60, tableheight + 10,
				textwidth + 50, textheight);
		repoaddresstf.setFont(f);
		repoaddresstf.addFocusListener(foclis);
		repo.add(repoaddresstf);

		JLabel airrowl = new JLabel("航运区排�?");
		airrowl.setBounds(leftside + labelwidth + textwidth + 140,
				tableheight + 10, labelwidth, labelheight);
		airrowl.setFont(font);
		repo.add(airrowl);

		airrowtf = new JTextField();
		airrowtf.setBounds(leftside + 2 * labelwidth + textwidth + 150,
				tableheight + 10, textwidth, textheight);
		airrowtf.setFont(f);
		airrowtf.addFocusListener(foclis);
		repo.add(airrowtf);

		JLabel trainrowl = new JLabel("铁运区排�?");
		trainrowl.setBounds(leftside, tableheight + 2 * labelheight,
				labelwidth, labelheight);
		trainrowl.setFont(font);
		repo.add(trainrowl);

		trainrowtf = new JTextField();
		trainrowtf.setBounds(leftside + labelwidth - 20, tableheight + 2
				* labelheight, textwidth, textheight);
		trainrowtf.setFont(f);
		trainrowtf.addFocusListener(foclis);
		repo.add(trainrowtf);

		JLabel truckrowl = new JLabel("汽运区排�?");
		truckrowl.setBounds(leftside + labelwidth + textwidth + 5, tableheight
				+ 2 * labelheight, labelwidth, labelheight);
		truckrowl.setFont(font);
		repo.add(truckrowl);

		truckrowtf = new JTextField();
		truckrowtf.setBounds(leftside + 2 * labelwidth + textwidth - 15,
				tableheight + 2 * labelheight, textwidth, textheight);
		truckrowtf.setFont(f);
		truckrowtf.addFocusListener(foclis);
		repo.add(truckrowtf);

		JLabel flexiblerowl = new JLabel("机动区排�?");
		flexiblerowl.setBounds(leftside + 2 * labelwidth + 2 * textwidth + 5,
				tableheight + 2 * labelheight, labelwidth, labelheight);
		flexiblerowl.setFont(font);
		repo.add(flexiblerowl);

		flexiblerowtf = new JTextField();
		flexiblerowtf.setBounds(leftside + 3 * labelwidth + 2 * textwidth - 15,
				tableheight + 2 * labelheight, textwidth, textheight);
		flexiblerowtf.setFont(f);
		flexiblerowtf.addFocusListener(foclis);
		repo.add(flexiblerowtf);
	}

	private void initpreviousPanel() {
		showprevious = new JPanel();
		showprevious.setOpaque(false);
		showprevious.setLayout(null);

		Class[] typeArray = { Object.class };
		String[] headers = { "名称" };
		ArrayList<String> titles = iab.getInnerAccountNameList();
		Object[][] datas = null;

		if (titles != null) {
			datas = new Object[titles.size()][1];
			for (int i = 0; i < titles.size(); i++) {
				datas[i][0] = titles.get(i);
			}
		}

		tableModel[5] = new MyTableModel(datas, headers, typeArray);
		table[5] = new JTable(tableModel[5]);
		table[5].setRowHeight(40);
		table[5].setFont(f);
		table[5].setBounds(0, 0, tablewidth, tableheight);
		table[5].addMouseListener(listener);

		JScrollPane scrollPanes = new JScrollPane(table[5]);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(0, 0, tablewidth, 610);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(null);
		updateUI();
		showprevious.add(scrollPanes);
	}

	private boolean checknumber(String str) {
		boolean res = true;

		for (int i = 0; i < str.length(); i++) {
			if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
				res = false;
				break;
			}
		}
		return res;
	}

	private boolean checkrepoinfo(String[] s) {
		boolean right = true;

		if (!checknumber(s[0])) {
			right = false;
			repoaddresstf.setForeground(Color.BLUE);
			repoaddresstf.setText("这里应该填数�?");
		}
		if (!checknumber(s[1])) {
			right = false;
			airrowtf.setForeground(Color.BLUE);
			airrowtf.setText("这里应该填数�?");
		}
		if (!checknumber(s[2])) {
			right = false;
			trainrowtf.setForeground(Color.BLUE);
			trainrowtf.setText("这里应该填数�?");
		}
		if (!checknumber(s[3])) {
			right = false;
			truckrowtf.setForeground(Color.BLUE);
			truckrowtf.setText("这里应该填数�?");
		}
		if (!checknumber(s[4])) {
			right = false;
			flexiblerowtf.setForeground(Color.BLUE);
			flexiblerowtf.setText("这里应该填数�?");
		}
		return right;
	}

	private boolean checkdouble(String str) {
		boolean right = true;

		for (int i = 0; i < str.length(); i++) {
			if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str
					.charAt(i) == '.')) {
				right = false;
				break;
			}
		}
		return right;
	}

	private boolean checkaccount(String incomestr, String expensestr) {
		boolean right = true;

		if (!checkdouble(incomestr)) {
			right = false;
			incometf.setForeground(Color.BLUE);
			incometf.setText("这里应该填小�?");
		}
		if (!checkdouble(expensestr)) {
			right = false;
			expensetf.setForeground(Color.BLUE);
			expensetf.setText("这里应该填小�?");
		}
		return right;
	}

	private boolean checkID(String id) {
		boolean right = false;
		if (id.length() <= 7) {
			if (checknumber(id)) {
				right = true;
			}
		}
		return right;
	}

	private boolean checkempty(String[] s) {
		boolean complete = true;
		for (int i = 0; i < s.length; i++) {
			if (s[i].isEmpty()) {
				complete = false;
			}
		}
		return complete;
	}

	private String checkstaffinfo(String idtemp, String phonetemp, boolean add) {
		String result = "";
		if (add) {
			if ((!iab.isUserIDAvailable(idtemp)) || (!checkID(idtemp))) {
				result = "ID填写错误";
			}
		} else {
			if ((iab.isUserIDAvailable(idtemp)) || (!checkID(idtemp))) {
				result = "ID填写错误";
			}
		}
		if (!iab.isCellPhoneAvailable(phonetemp)) {
			result = "手机号填写错�?";
		}
		return result;
	}

	private String checkorginfo(String orgid, String orgname, boolean add) {
		String result = "";

		if (add) {
			if (iab.isOrgIDAvailable(orgid) || (!checkID(orgid))) {
				result = "机构代号错误";
			}
		} else {
			if ((!iab.isOrgIDAvailable(orgid)) || (!checkID(orgid))) {
				result = "机构代号错误";
			}
		}
		if (iab.isOrgNameAvailable(orgname)) {
			result = "机构名称错误";
		}
		return result;
	}

	private OrgProperty tansorgtype(String orgtype) {
		OrgProperty orgpro = null;

		if (orgtype.equals("中转中心"))
			orgpro = OrgProperty.TRANSCENTER;
		if (orgtype.equals("营业�?"))
			orgpro = OrgProperty.SALE;
		if (orgtype.equals("总部"))
			orgpro = OrgProperty.OTHER;

		return orgpro;
	}

	private String checkvehicleinfo(String vehicleid, String vehiclenum,
			int time, boolean add) {
		String result = "";

		if (add) {
			if (iab.isCarIDAvailable(vehicleid)) {
				result = "车辆代号错误";
			}
		} else {
			if (!iab.isCarIDAvailable(vehicleid)) {
				result = "车辆代号错误";
			}
		}
		if (iab.isCarLicenseAvailable(vehiclenum)) {
			result = "车牌号错�?";
		}
		if (time < 0) {
			result = "时间错误";
		}
		return result;
	}

	private String checkaccountinfo(String accountname, String total,
			boolean add) {
		String result = "";

		if (add) {
			if (iab.checkDuplication(accountname)) {
				result = "该账户名已存�?";
			}
		} else {
			if (!iab.checkDuplication(accountname)) {
				result = "该账户名不存�?";
			}
		}
		if (!iab.isMoneyValid(total)) {
			result = "金额错误";
		}
		return result;
	}

	private void detele(int index, int i) {
		if (index == 0) {
			iab.deleteUserInfo((String) tableModel[index].getValueAt(i, 7));
		}
		if (index == 1) {
			iab.deleteOrganizationInfo((String) tableModel[index].getValueAt(i,
					4));
		}
		if (index == 2) {
			iab.deleteVehicleInfo((String) tableModel[index].getValueAt(i, 3));
		}
		if (index == 3) {
			iab.deleteRepoInfo((String) tableModel[index].getValueAt(i, 1));
		}
		if (index == 4) {
			iab.deleteBankAccountInfo((String) tableModel[index].getValueAt(i,
					1));
		}
		if (index == 5) {
			// iab
		}
		tableModel[index].removeRow(i);
	}

	private void addstaff() {

		String nametemp = nametf.getText();
		String gendertemp = gendercb.getSelectedItem().toString();
		String phonetemp = phonetf.getText();
		String positiontemp = positioncb.getSelectedItem().toString();
		String datetemp = datetf.getText();
		String orgtemp = orginfo.getOrgID(orgcb.getSelectedItem().toString());
		String idtemp = idtf.getText();

		String[] temp = { idtemp, phonetemp, nametemp, datetemp };

		if (checkempty(temp)) {
			String result = checkstaffinfo(idtemp, phonetemp, true);
			if (result.equals("")) {
				Object[] values = { false, nametemp, gendertemp, phonetemp,
						datetemp, positiontemp, orgtemp, idtemp, changeunder };
				boolean sex = (gendertemp.equals("�?"));
				UserRole posit = UserRole.values()[positioncb
						.getSelectedIndex()];

				UserInfoVO vo = new UserInfoVO(nametemp, sex, idtemp,
						phonetemp, posit, orgtemp, datetemp);
				iab.initUserInfo(vo);
				tableModel[0].addRow(values);
				TipBlock block = new TipBlock("添加成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError(result);
				tippane.add(block);
				block.show();
				block = null;
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void addorg() {

		String city = citycb.getText();
		String orgname = orgnametf.getText();
		String orgtype = butypecb.getSelectedItem().toString();
		String orgid = orgidtf.getText();
		String orgadd = orgaddtf.getText();
		String[] temp = { city, orgname, orgid, orgadd };

		if (checkempty(temp)) {
			String result = checkorginfo(orgid, orgname, true);

			if (result.equals("")) {
				Object[] values = { false, city, orgname, orgtype, orgid,
						orgadd, changeunder };
				OrgProperty orgpro = OrgProperty.values()[butypecb
						.getSelectedIndex()];
				OrganizationVO vo = new OrganizationVO(city, orgname, orgadd,
						orgpro, orgid);
				iab.initOrganizationInfo(vo);
				tableModel[1].addRow(values);
				TipBlock block = new TipBlock("添加成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError(result);
				tippane.add(block);
				block.show();
				block = null;
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}

	}

	private void addvehicle() {

		String vehiclenum = vehiclenumtf.getText();
		String org = vehicleorgtf.getText();
		String vehicleid = vehicleidtf.getText();
		String timestr = timetf.getText();
		String[] temp = { vehiclenum, org, vehicleid, timestr };

		if (checkempty(temp)) {
			if (checknumber(timestr)) {
				int time = Integer.parseInt(timetf.getText());
				String result = checkvehicleinfo(vehicleid, vehiclenum, time,
						true);
				if (result.equals("")) {
					Object[] values = { false, vehiclenum, org, vehicleid,
							time, changeunder };
					VehicleInfoVO vo = new VehicleInfoVO(vehicleid, vehiclenum,
							org, time, false);
					iab.initVehicleInfo(vo);
					tableModel[2].addRow(values);
					TipBlock block = new TipBlock("添加成功");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					TipBlockError block = new TipBlockError(result);
					tippane.add(block);
					block.show();
					block = null;
				}
			} else {
				timetf.setForeground(Color.BLUE);
				timetf.setText("这里应该填整�?");
				TipBlockError block = new TipBlockError("信息输入错误");
				tippane.add(block);
				block.show();
				block = null;
			}

		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void addrepo() {

		String repoaddress = repoaddresstf.getText();
		String airrowstr = airrowtf.getText();
		String trainrowstr = trainrowtf.getText();
		String truckrowstr = truckrowtf.getText();
		String flexiblerowstr = flexiblerowtf.getText();
		String[] temp = { repoaddress, airrowstr, trainrowstr, truckrowstr,
				flexiblerowstr };

		if (checkempty(temp)) {

			if (checkrepoinfo(temp)) {
				int airrow = Integer.parseInt(airrowstr);
				int trainrow = Integer.parseInt(trainrowstr);
				int truckrow = Integer.parseInt(truckrowstr);
				int flexiblerow = Integer.parseInt(flexiblerowstr);

				Object[] values = { false, repoaddress, airrow, trainrow,
						truckrow, flexiblerow, changeunder };
				RepoInfoVO vo = new RepoInfoVO(repoaddress, airrow, trainrow,
						truckrow, flexiblerow);
				tableModel[3].addRow(values);
				iab.initRepoInfo(vo);
				TipBlock block = new TipBlock("添加成功");
				tippane.add(block);
				block.show();
				block = null;
			}

		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void addbankaccount() {

		String accountname = accountnametf.getText();
		String incomestr = incometf.getText();
		String expensestr = expensetf.getText();
		String[] temp = { accountname, incomestr, expensestr };

		if (checkempty(temp)) {
			if (checkaccount(incomestr, expensestr)) {
				double income = Double.parseDouble(incometf.getText());
				double expense = Double.parseDouble(expensetf.getText());
				double total = income - expense;
				String result = checkaccountinfo(accountname, total + "", true);
				System.out.println(income);

				if (result.equals("")) {
					balancetf.setText(total + "");
					Object[] values = { false, accountname, income, expense,
							total ,changeunder};
					BankAccountVO vo = new BankAccountVO(accountname, income,
							expense, total);
					iab.initBankAccountInfo(vo);
					tableModel[4].addRow(values);
					TipBlock block = new TipBlock("添加成功");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					TipBlockError block = new TipBlockError(result);
					tippane.add(block);
					block.show();
					block = null;
				}
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}

	}

	private void changestaff(int row) {
		boolean ok = true;
		boolean complete = true;

		String name = (String) tableModel[0].getValueAt(row, 1);
		String gender = (String) tableModel[0].getValueAt(row, 2);
		String id = (String) tableModel[0].getValueAt(row, 7);
		String city = (String) tableModel[0].getValueAt(row, 6);
		String phone = (String) tableModel[0].getValueAt(row, 3);
		String date = (String) tableModel[0].getValueAt(row, 4);
		UserRole posit = UserRole.values()[positioncb.getSelectedIndex() + 1];
		boolean sex = gender.equals("�?");
		String[] temp = { id, phone, name, date };
		complete = checkempty(temp);

		// if (tableModel[0].getValueAt(row, 1) == null)
		// complete = false;
		// if (tableModel[0].getValueAt(row, 3) == null)
		// complete = false;
		// if (tableModel[0].getValueAt(row, 4) == null)
		// complete = false;
		// if (tableModel[0].getValueAt(row, 6) == null)
		// complete = false;

		if (complete) {

			String result = checkstaffinfo(id, phone, false);
			if (!result.isEmpty()) {
				ok = false;
			}
			if (!iab.isJoininDateAvailable(date)) {
				if (!ok)
					result += "\n";
				ok = false;
				result += "日期格式错误";
			}

			if (ok) {
				UserInfoVO vo = new UserInfoVO(name, sex, id, phone, posit,
						city, date);
				iab.changeUserInfo(vo, id);
				TipBlock block = new TipBlock("修改成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError(result);
				tippane.add(block);
				block.show();
				block = null;
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void changeorg(int row) {
		boolean complete = true;

		// if (tableModel[1].getValueAt(row, 1) == null)
		// complete = false;
		// if (tableModel[1].getValueAt(row, 2) == null)
		// complete = false;
		// if (tableModel[1].getValueAt(row, 4) == null)
		// complete = false;
		// if (tableModel[1].getValueAt(row, 5) == null)
		// complete = false;

		String city = (String) tableModel[1].getValueAt(row, 1);
		String orgname = (String) tableModel[1].getValueAt(row, 2);
		String orgtype = (String) tableModel[1].getValueAt(row, 3);
		String orgid = (String) tableModel[1].getValueAt(row, 4);
		String orgadd = (String) tableModel[1].getValueAt(row, 5);
		String result = checkorginfo(orgid, orgname, false);
		String[] temp = { city, orgname, orgid, orgadd };
		complete = checkempty(temp);

		if (complete) {
			if (result.isEmpty()) {
				OrgProperty orgpro = tansorgtype(orgtype);
				OrganizationVO vo = new OrganizationVO(city, orgname, orgadd,
						orgpro, orgid);
				iab.changeOrganizationInfo(vo, orgid);
				TipBlock block = new TipBlock("修改成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError(result);
				tippane.add(block);
				block.show();
				block = null;
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void changevehicle(int row) {
		boolean complete = true;

		// if (tableModel[2].getValueAt(row, 1) == null)
		// complete = false;
		// if (tableModel[2].getValueAt(row, 2) == null)
		// complete = false;
		// if (tableModel[2].getValueAt(row, 3) == null)
		// complete = false;
		// if (tableModel[2].getValueAt(row, 4) == null)
		// complete = false;

		String vehiclenum = (String) tableModel[2].getValueAt(row, 1);
		String org = (String) tableModel[2].getValueAt(row, 2);
		String vehicleid = (String) tableModel[2].getValueAt(row, 3);
		int time = (int) tableModel[2].getValueAt(row, 4);
		String[] temp = { vehiclenum, org, vehicleid, time + "" };
		complete = checkempty(temp);

		if (complete) {
			String result = checkvehicleinfo(vehicleid, vehiclenum, time, false);
			if (result.isEmpty()) {
				VehicleInfoVO vo = new VehicleInfoVO(vehiclenum, vehicleid,
						org, time, false);
				iab.changeVehicleInfo(vo, vehicleid);
				TipBlock block = new TipBlock("修改成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError(result);
				tippane.add(block);
				block.show();
				block = null;
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void changerepo(int row) {
		boolean complete = true;

		// if (tableModel[3].getValueAt(row, 1) == null)
		// complete = false;
		// if (tableModel[3].getValueAt(row, 2) == null)
		// complete = false;
		// if (tableModel[3].getValueAt(row, 3) == null)
		// complete = false;
		// if (tableModel[3].getValueAt(row, 4) == null)
		// complete = false;
		// if (tableModel[3].getValueAt(row, 5) == null)
		// complete = false;

		String repoaddress = (String) tableModel[3].getValueAt(row, 1);
		int airrow = (int) tableModel[3].getValueAt(row, 2);
		int trainrow = (int) tableModel[3].getValueAt(row, 3);
		int truckrow = (int) tableModel[3].getValueAt(row, 4);
		int flexiblerow = (int) tableModel[3].getValueAt(row, 4);
		String[] temp = { repoaddress, airrow + "", trainrow + "",
				truckrow + "", flexiblerow + "" };
		complete = checkempty(temp);

		if (complete) {

			RepoInfoVO vo = new RepoInfoVO(repoaddress, airrow, trainrow,
					truckrow, flexiblerow);
			iab.changeRepoInfo(vo, repoaddress);
			TipBlock block = new TipBlock("修改成功");
			tippane.add(block);
			block.show();
			block = null;

		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private void changebankaccount(int row) {
		boolean complete = true;

		// if (tableModel[4].getValueAt(row, 1) == null)
		// complete = false;
		// if (tableModel[4].getValueAt(row, 2) == null)
		// complete = false;
		// if (tableModel[4].getValueAt(row, 3) == null)
		// complete = false;

		String accountname = (String) tableModel[4].getValueAt(row, 1);
		double income = (Double) tableModel[4].getValueAt(row, 2);
		double expense = (Double) tableModel[4].getValueAt(row, 3);
		String[] temp = { accountname, income + "", expense + "" };
		complete = checkempty(temp);

		if (complete) {
			double total = income - expense;
			String result = checkaccountinfo(accountname, total + "", false);

			if (result.isEmpty()) {
				tableModel[4].setValueAt(total, row, 4);
				BankAccountVO vo = new BankAccountVO(accountname, income,
						expense, total);
				iab.changeBankAccountInfo(vo, accountname);
				TipBlock block = new TipBlock("修改成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError(result);
				tippane.add(block);
				block.show();
				block = null;
			}
		} else {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == ok) {

				iab.endInitial();

				for (int i = 0; i < tableModel[5].getRowCount(); i++) {
					tableModel[5].removeRow(i);
				}
				ArrayList<String> titles = iab.getInnerAccountNameList();
				String[] datas = null;

				if (titles != null) {
					datas = new String[titles.size()];
					for (int i = 0; i < titles.size(); i++) {
						datas[i] = titles.get(i);
						tableModel[5].addRow(datas);
					}
				}
				TipBlock block = new TipBlock("期初建账成功");
				tippane.add(block);
				block.show();
				block = null;

			} else if (e.getSource() == detele) {

				int index = tabpane.getSelectedIndex();
				if (index < 5) {
					for (int i = tableModel[index].getRowCount() - 1; i >= 0; i--) {
						if ((boolean) tableModel[index].getValueAt(i, 0)) {
							detele(index, i);
						}
					}
				}

			} else if (e.getSource() == add) {
				int index = tabpane.getSelectedIndex();

				if (index == 0) {
					addstaff();

				} else if (index == 1) {
					addorg();

				} else if (index == 2) {
					addvehicle();

				} else if (index == 3) {
					addrepo();

				} else if (index == 4) {
					addbankaccount();

				}
			}
			for (int i = 0; i < 6; i++) {
				if (e.getSource() == table[i]) {
					int row = table[i].getSelectedRow();
					int col = table[i].getSelectedColumn();

					if (i == 5) {
						FinanceInitAccountPreUI pre = new FinanceInitAccountPreUI(
								row);
						pre.setVisible(true);
					}

					if (tableModel[i].getValueAt(row, col).equals(changeunder)) {
						tableModel[i].setrowedit();
						tableModel[i].setValueAt(confirmunder, row, col);
					} else if (tableModel[i].getValueAt(row, col).equals(
							confirmunder)) {
						tableModel[i].setrowunedit();
						tableModel[i].setValueAt(changeunder, row, col);

						if (i == 0) {
							changestaff(row);
						}
						if (i == 1) {
							changeorg(row);

						}
						if (i == 2) {
							changevehicle(row);

						}
						if (i == 3) {
							changerepo(row);
						}
						if (i == 4) {
							changebankaccount(row);
						}

					}
				}
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == detele) {
				detele.whenPressed();
			} else if (e.getSource() == add) {
				add.whenPressed();
			} else if (e.getSource() == ok) {
				ok.whenPressed();
			}

		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == detele) {
				detele.setMyColor();
			} else if (e.getSource() == add) {
				add.setMyColor();
			} else if (e.getSource() == ok) {
				ok.setMyColor();
			}

		}
	}

	private class Foclistener implements FocusListener {

		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == repoaddresstf) {
				if (repoaddresstf.getText().equals("这里应该填数�?")) {
					repoaddresstf.setForeground(c);
					repoaddresstf.setText("");
				}
			}

			if (e.getSource() == airrowtf) {
				if (airrowtf.getText().equals("这里应该填数�?")) {
					airrowtf.setForeground(c);
					airrowtf.setText("");
				}
			}

			if (e.getSource() == airrowtf) {
				if (trainrowtf.getText().equals("这里应该填数�?")) {
					trainrowtf.setForeground(c);
					trainrowtf.setText("");
				}
			}

			if (e.getSource() == truckrowtf) {
				if (truckrowtf.getText().equals("这里应该填数�?")) {
					truckrowtf.setForeground(c);
					truckrowtf.setText("");
				}
			}

			if (e.getSource() == flexiblerowtf) {
				if (flexiblerowtf.getText().equals("这里应该填数�?")) {
					flexiblerowtf.setForeground(c);
					flexiblerowtf.setText("");
				}
			}

			if (e.getSource() == incometf) {
				if (incometf.getText().equals("这里应该填小�?")) {
					incometf.setForeground(c);
					incometf.setText("");
				}
			}

			if (e.getSource() == expensetf) {
				if (expensetf.getText().equals("这里应该填小�?")) {
					expensetf.setForeground(c);
					expensetf.setText("");
				}
			}

			if (e.getSource() == timetf) {
				if (timetf.getText().equals("这里应该填整�?")) {
					timetf.setForeground(c);
					timetf.setText("");
				}
			}
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
