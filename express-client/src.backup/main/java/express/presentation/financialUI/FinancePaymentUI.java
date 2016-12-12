package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.documentBL.PaymentDoc;
import express.businessLogic.infoManageBL.BankAccount;
import express.businessLogic.userBL.User;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.financialBLService.PaymentBLService;
import express.businesslogicService.signBLService.LogInBLService;
import express.po.PaymentItem;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.PaymentDocVO;
import express.vo.UserInfoSignVO;

public class FinancePaymentUI extends JPanel {

	private MainUIService m;
	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel exit;
	private MyOtherRedLabel cancel;
	private JTextField date, money, name;
	private JComboBox<String> account, entry;
	private JTextArea comment;
	// private LinkedList<JButton> delete;
	private JPanel tip;
	private String time = "";
	private String n;

	// private JRadioButton rent, freight, wage, award;
	// private ButtonGroup entry;
	// private JTextField datetf, moneytf, persontf, accounttf, rentyeartf,
	// trackingnumtf, wagemonthtf;
	// private String date, person, account, rentyear, trackingnum, wagemonth;
	// private double money;

	public FinancePaymentUI(MainUIService main) {
		m = main;
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);

		/*
		 * paypanel = new JPanel(); paypanel.setBounds(0, 65, 850, 635);
		 * paypanel.setLayout(null); paypanel.setBackground(Color.WHITE);
		 * JScrollPane scrollPane = new JScrollPane(paypanel);
		 * scrollPane.setFont(font); scrollPane.setOpaque(false);
		 * scrollPane.getViewport().setOpaque(false);
		 * scrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0,
		 * Color.gray)); scrollPane.setBounds(0, 65, 850, 635);
		 * this.add(scrollPane);
		 */

		JLabel title = new JLabel("�? �? �?", JLabel.CENTER);
		title.setFont(new Font("楷体", Font.BOLD, 18));
		title.setBounds(0, 20, 320, 30);
		title.setBackground(Color.WHITE);
		// title.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2,
		// Color.GRAY));
		this.add(title);

		JLabel line = new JLabel();
		line.setBounds(365, 70, 2, 520);
		line.setBackground(Color.WHITE);
		line.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.add(line);

		JLabel datel = new JLabel("付款日期", JLabel.CENTER);
		datel.setFont(font);
		datel.setBounds(5, 70, 90, 30);
		datel.setBackground(Color.WHITE);
		// datel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,
		// Color.GRAY));
		this.add(datel);

		JLabel moneyl = new JLabel("付款金额", JLabel.CENTER);
		moneyl.setFont(font);
		moneyl.setBounds(5, 150, 90, 30);
		moneyl.setBackground(Color.WHITE);
		// moneyl.setBorder(BorderFactory
		// .createMatteBorder(2, 2, 2, 0, Color.GRAY));
		this.add(moneyl);

		JLabel personl = new JLabel("付款�?", JLabel.CENTER);
		personl.setFont(font);
		personl.setBounds(5, 230, 90, 30);
		personl.setBackground(Color.WHITE);
		// personl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,
		// Color.GRAY));
		this.add(personl);

		JLabel accountl = new JLabel("付款账号", JLabel.CENTER);
		accountl.setFont(font);
		accountl.setBounds(5, 310, 90, 30);
		accountl.setBackground(Color.WHITE);
		// accountl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,
		// Color.GRAY));
		this.add(accountl);

		JLabel entryl = new JLabel("条目", JLabel.CENTER);
		entryl.setFont(font);
		entryl.setBounds(5, 390, 90, 30);
		entryl.setBackground(Color.WHITE);
		// entryl.setBorder(BorderFactory
		// .createMatteBorder(2, 2, 2, 0, Color.GRAY));
		this.add(entryl);

		JLabel remark = new JLabel("备注", JLabel.CENTER);
		remark.setFont(font);
		remark.setBounds(5, 470, 90, 30);
		remark.setBackground(Color.WHITE);
		// remark.setBorder(BorderFactory
		// .createMatteBorder(2, 2, 2, 2, Color.GRAY));
		this.add(remark);

		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		time = format.format(d);
		date = new JTextField();
		date.setText(time);
		date.setFont(f);
		date.setBounds(100, 70, 190, 30);
		date.setEditable(false);
		this.add(date);

		DateChooser datechoosers = new DateChooser("yyyy-MM-dd", date);
		datechoosers.setBounds(300, 65, 40, 40);
		this.add(datechoosers);

		money = new JTextField();
		money.setText("");
		money.setFont(f);
		money.setBounds(100, 150, 240, 30);
		this.add(money);

		String id = IDKeeper.getID();
		LogInBLService login = new User();
		UserInfoSignVO vo = login.getUserInfo(id);
		n = vo.getName();
		name = new JTextField();
		name.setText(n);
		name.setFont(f);
		name.setBounds(100, 230, 240, 30);
		this.add(name);

		BankAccountBLService b = new BankAccount();
		ArrayList<String> name = b.getBankAccountName();
		String[] bank = new String[0];
		if (name != null) {
			bank = new String[name.size()];
			for (int i = 0; i < name.size(); i++)
				bank[i] = name.get(i);
		}
		// 银行账户
		account = new JComboBox<String>(bank);
		account.setFont(f);
		account.setBackground(Color.WHITE);
		account.setBounds(100, 310, 240, 30);
		this.add(account);

		// 条目
		String[] e = { "人员工资", "奖励", "运费", "租金" };
		entry = new JComboBox<String>(e);
		entry.setFont(f);
		entry.setBackground(Color.WHITE);
		entry.setBounds(100, 390, 240, 30);
		this.add(entry);

		comment = new JTextArea(200, 80);
		comment.setText("");
		comment.setFont(f);
		comment.setVisible(true);
		comment.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		comment.setBounds(100, 470, 240, 70);
		this.add(comment);

		Listener listen = new Listener();

		/*
		 * (add = new JButton("添加"); add.setBackground(Color.WHITE);
		 * add.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
		 * Color.GRAY)); add.setBounds(0, 0, 720, 35); add.setFont(new
		 * Font("隶书", Font.PLAIN, 20)); this.add(add);
		 */

		ok = new MyOtherBlueLabel("生成单据");
		// ok.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,
		// Color.GRAY));
		ok.setBounds(30, 560, 125, 40);
		this.add(ok);

		cancel = new MyOtherRedLabel("取消");
		// cancel.setBorder(BorderFactory
		// .createMatteBorder(2, 2, 2, 0, Color.GRAY));
		cancel.setBounds(210, 560, 130, 40);
		this.add(cancel);
		
		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		exit = new MyOtherOrangeLabel("返回菜单");
		// exit.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,
		// Color.GRAY));
		exit.setBounds(30, 620, 310, 40);
		this.add(exit);

		// add.addMouseListener(listen);
		ok.addMouseListener(listen);
		cancel.addMouseListener(listen);
		exit.addMouseListener(listen);

		String cd = time.substring(5, 7);
		if (cd.charAt(0) == '0')
			cd = cd.substring(1, 2);
		JLabel title2 = new JLabel(cd + "月份应付款提�?", JLabel.CENTER);
		title2.setFont(new Font("楷体", Font.PLAIN, 20));
		title2.setBounds(500, 20, 200, 30);
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
		Color.LIGHT_GRAY));
		this.add(title2);

		tip = new JPanel();
		tip.setLocation(390, 60);
		tip.setPreferredSize(new Dimension(405, 600));
		// bankAccount.setBounds(350, 140, 435, 1000);
		tip.setOpaque(false);
		tip.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tip);
		scrollPane.setFont(font);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,
				Color.LIGHT_GRAY));
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(390, 60, 420, 600);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);

		init();
		/*
		 * JLabel rentyearl = new JLabel("租金年份"); rentyearl.setFont(font);
		 * rentyearl.setBounds(leftside1, 450, 100, 40); this.add(rentyearl);
		 * 
		 * JLabel trackingnuml = new JLabel("运单�?"); trackingnuml.setFont(font);
		 * trackingnuml.setBounds(leftside1, 500, 100, 40);
		 * this.add(trackingnuml);
		 * 
		 * JLabel wagemonthl = new JLabel("运单�?"); wagemonthl.setFont(font);
		 * wagemonthl.setBounds(leftside1, 550, 100, 40); this.add(wagemonthl);
		 */

		/*
		 * datetf = new JTextField(); datetf.setBounds(leftside2, 40, textwidth,
		 * textheight); datetf.setFont(f); this.add(datetf); moneytf = new
		 * JTextField(); moneytf.setBounds(leftside2, 90, textwidth,
		 * textheight); moneytf.setFont(f); this.add(moneytf); persontf = new
		 * JTextField(); persontf.setBounds(leftside2, 140, textwidth,
		 * textheight); persontf.setFont(f); this.add(persontf); accounttf = new
		 * JTextField(); accounttf.setBounds(leftside2, 190, textwidth,
		 * textheight); accounttf.setFont(f); this.add(accounttf); entry = new
		 * ButtonGroup(); rent = new JRadioButton("租金");
		 * rent.setBounds(leftside2, 240, 100, 30); rent.setFont(font); freight
		 * = new JRadioButton("运费"); freight.setBounds(leftside2, 280, 100, 30);
		 * freight.setFont(font); wage = new JRadioButton("人员工资");
		 * wage.setBounds(leftside2, 320, 100, 30); wage.setFont(font); award =
		 * new JRadioButton("奖励"); award.setBounds(leftside2, 360, 100, 30);
		 * award.setFont(font);
		 * 
		 * entry.add(rent); entry.add(freight); entry.add(wage);
		 * entry.add(award); this.add(rent); this.add(freight); this.add(wage);
		 * this.add(award); rentyeartf = new JTextField();
		 * rentyeartf.setBounds(leftside2, 450, textwidth, textheight);
		 * rentyeartf.setFont(f); this.add(rentyeartf); trackingnumtf = new
		 * JTextField(); trackingnumtf.setBounds(leftside2, 500, textwidth,
		 * textheight); trackingnumtf.setFont(f); this.add(trackingnumtf);
		 * wagemonthtf = new JTextField(); wagemonthtf.setBounds(leftside2, 550,
		 * textwidth, textheight); wagemonthtf.setFont(f);
		 * this.add(wagemonthtf);
		 */
	}

	private void addLine() {
		/*
		 * if (first) { // 如果是第�?次添加，先初始化表格 first = false; Font f = new
		 * Font("仿宋", Font.PLAIN, 16);
		 * 
		 * tableModel = new DefaultTableModel(0, 6); table = new
		 * JTable(tableModel); table.setRowHeight(40); table.setBounds(0, 65,
		 * 750, 600); table.setFont(f); paypanel.add(table);
		 * 
		 * tcm = table.getColumnModel(); int[] width = { 100, 120, 100, 210, 90,
		 * 100 }; // 条目 String[] e = { "人员工资", "奖励", "运费", "租金" }; entry = new
		 * JComboBox<String>(e); entry.setFont(f); // 银行账户获得
		 * BankAccountBLService b = new BankAccount(); ArrayList<String> name =
		 * b.getBankAccountName(); String[] bank = new String[0]; if (name !=
		 * null) { bank = new String[name.size()]; for (int i = 0; i <
		 * name.size(); i++) bank[i] = name.get(i); } // 银行账户 bankAccount = new
		 * JComboBox<String>(bank); bankAccount.setFont(f);
		 * 
		 * tcm.getColumn(4).setCellEditor(new DefaultCellEditor(entry));
		 * tcm.getColumn(3).setCellEditor(new DefaultCellEditor(bankAccount));
		 * // 设置每一列的宽度 for (int i = 0; i < 6; i++) {
		 * tcm.getColumn(i).setPreferredWidth(width[i]); }
		 * 
		 * initialize(); } else { Date date = new Date(); DateFormat format =
		 * new SimpleDateFormat("yyyy-MM-dd"); String time =
		 * format.format(date); String[] data = { time, "", "", "", "", "" };
		 * tableModel.addRow(data); adjust(1); }
		 */
	}

	private void init() {
		PaymentBLService pay = new PaymentDoc();

		ArrayList<PaymentDocVO> list = pay.createPaymentDoc();
		Font f = new Font("楷体", Font.PLAIN, 20);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PaymentDocVO vo = list.get(i);
				PaymentItem payment = vo.getPaymentList();
				JTextArea prompt = new JTextArea();
				prompt.setBounds(5, i * 105, 400, 100);
				prompt.setEditable(false);
				prompt.setOpaque(false);
				String text = "付款日期�?  " + payment.getDate() + "\n" + "付款金额�?  "
						+ payment.getSum() + "\n" + "条目�?      "
						+ payment.getEntry() + "\n" + "备注�?      " + payment.getComment();
				prompt.setText(text);
				prompt.setFont(f);
				prompt.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
				tip.add(prompt);
			}
			tip.setPreferredSize(new Dimension(415,list.size() * 90));
			this.updateUI();
		}
	}

	private boolean check() {
		String n = name.getText();
		String m = money.getText();
		String c = comment.getText();

		Color yellow = new Color(255, 215, 0);

		boolean fill = true;
		if (n.equals("")) {
			name.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (m.equals("")) {
			money.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (c.equals("")) {
			comment.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}

		return fill;
	}
	
	private boolean correct(){
		boolean correct = true;
		PaymentBLService pay = new PaymentDoc();
		
		time = date.getText();
		String m = money.getText();
		
		if(!pay.checkDateAvailable(time)){
			date.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			correct = false;
		}
		
		if(!pay.checkMoney(m)){
			money.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			correct = false;
		}
		
		return correct;
	}
	
	private void addPayment(){
		PaymentBLService pay = new PaymentDoc();
		
		time = date.getText();
		String n = name.getText();
		String m = money.getText();
		String a = account.getSelectedItem().toString();
		String e = entry.getSelectedItem().toString();
		String c = comment.getText();
		
		double sum = Double.parseDouble(m);
		PaymentItem payment = new PaymentItem(n,time,a,e,c,sum);
		long source = System.currentTimeMillis();
		String id = String.valueOf(source);
		PaymentDocVO vo = new PaymentDocVO(payment, id);
		
		boolean succ = pay.addPaymentDoc(vo);
		if (succ) {
			TipBlock block = new TipBlock("添加成功");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			TipBlockError block = new TipBlockError("添加失败");
			tippane.add(block);
			block.show();
			block = null;
		}
		
		pay.endPaymentDoc();
		date.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		name.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		money.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		comment.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		 if (e.getSource() == ok) {
				if (check()) {
					if(correct()){
						addPayment();
					} else {
						TipBlockError block = new TipBlockError("信息错误");
						tippane.add(block);
						block.show();
						block = null;
					}
				} else {
					TipBlockEmpty block = new TipBlockEmpty("您还有信息未�?");
					tippane.add(block);
					block.show();
					block = null;
				}
			} else if (e.getSource() == cancel) {
				Date d = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				time = format.format(d);
				date.setText(time);
				money.setText("");
				name.setText(n);
				comment.setText("");
				date.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				name.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				money.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				comment.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			}else if(e.getSource() == exit){
				m.jumpToFinanceMenuUI(IDKeeper.getID(), IDKeeper.getHigh());
			}
			repaint();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.whenPressed();
			} else if (e.getSource() == ok) {
				ok.whenPressed();
			} else if (e.getSource() == cancel) {
				cancel.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.setMyColor();
			} else if (e.getSource() == ok) {
				ok.setMyColor();
			} else if (e.getSource() == cancel) {
				cancel.setMyColor();
			}
		}
	}
}
