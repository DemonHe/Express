package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.documentBL.SumReceiveDoc;
import express.businessLogic.infoManageBL.BankAccount;
import express.businessLogic.infoManageBL.OrgForManager;
import express.businesslogicService.businessSaleBLService.GetReceiveDocBLService;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.po.OrgProperty;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.vo.ReceiveDocVO;
import express.vo.SumReceiveVO;

public class FinanceSumReceiveDocUI extends JPanel {

	private JComboBox<String> businesshallcb, bankaccountcb;
	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel exit;
	private MyOtherGreenLabel confirm;
	private JTextField datetf;
	private JLabel totalmoney, totallabel, empty;
	private DateChooser datechooser;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[][] data;
	private String[] header = { "收款日期", "收款金额", "收款快递员" };
	private String date, orgName, bankaccountID;
	private MainUIService m;

	public FinanceSumReceiveDocUI(MainUIService main) {
		setLayout(null);
		m = main;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("楷体", Font.PLAIN, 24);

		JLabel businesslabel = new JLabel("营业厅");
		businesslabel.setBounds(100, 50, 70, 30);
		businesslabel.setFont(font);
		this.add(businesslabel);

		businesshallcb = new JComboBox<String>(getbussinesshall());
		businesshallcb.setBounds(180, 50, 150, 30);
		businesshallcb.setBackground(Color.WHITE);
		businesshallcb.setFont(f);
		this.add(businesshallcb);

		JLabel datelabel = new JLabel("日期");
		datelabel.setBounds(340, 50, 60, 30);
		datelabel.setFont(font);
		this.add(datelabel);

		datetf = new JTextField(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(400, 50, 150, 30);
		datetf.setFont(f);
		datetf.setBackground(Color.WHITE);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(560, 45, 40, 40);
		this.add(datechooser);

		// 收款日期、收款金额、收款快递员
		data = null;
		tableModel = new DefaultTableModel(data, header);

		table = new JTable(tableModel);
		table.getTableHeader().setFont(f1);
		table.getTableHeader().setBorder(
				BorderFactory.createMatteBorder(1, 0, 2, 0, Color.LIGHT_GRAY));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setPreferredSize(new Dimension(625, 35));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setDefaultRenderer(r);
		table.setRowHeight(40);
		table.setFont(f);
		table.setBounds(100, 100, 650, 430);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 100, 650, 430);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
				Color.LIGHT_GRAY));
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		this.add(scrollPane);

		totallabel = new JLabel("", JLabel.CENTER);
		totallabel.setBounds(100, 530, 217, 30);
		totallabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0,
				Color.LIGHT_GRAY));
		totallabel.setFont(font);
		this.add(totallabel);

		totalmoney = new JLabel("", JLabel.CENTER);
		totalmoney.setBounds(317, 530, 217, 30);
		totalmoney.setFont(font);
		totalmoney.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.LIGHT_GRAY));
		this.add(totalmoney);

		empty = new JLabel("", JLabel.CENTER);
		empty.setBounds(534, 530, 216, 30);
		empty.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		empty.setFont(font);
		this.add(empty);

		JLabel bankaccountl = new JLabel("银行账户");
		bankaccountl.setBounds(100, 600, 100, 35);
		bankaccountl.setFont(font);
		this.add(bankaccountl);

		bankaccountcb = new JComboBox<String>(getbankaccount());
		bankaccountcb.setBounds(200, 600, 250, 35);
		bankaccountcb.setFont(f);
		bankaccountcb.setBackground(Color.WHITE);
		this.add(bankaccountcb);

		JListener listener = new JListener();

		confirm = new MyOtherGreenLabel("确认");
		confirm.setBounds(630, 50, 120, 35);
		confirm.addMouseListener(listener);
		this.add(confirm);

		ok = new MyOtherBlueLabel("合计");
		ok.setBounds(480, 600, 120, 40);
		ok.addMouseListener(listener);
		this.add(ok);

		exit = new MyOtherOrangeLabel("返回菜单");
		exit.setBounds(630, 600, 120, 40);
		exit.addMouseListener(listener);
		this.add(exit);
		
		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
	}

	private String[] getbussinesshall() {
		OrgInfoManageService org = new OrgForManager();
		ArrayList<String> list = org.getAllOrgNameByProperty(OrgProperty.SALE);
		String[] city;

		if (list != null) {
			city = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				city[i] = list.get(i);
			}
		} else {
			city = new String[0];
		}

		return city;
	}

	private String[] getbankaccount() {
		BankAccountBLService bankAccount = new BankAccount();
		ArrayList<String> list = bankAccount.getBankAccountName();

		String[] account;
		if (list != null) {
			account = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				account[i] = list.get(i);
			}
		} else {
			account = new String[0];
		}
		return account;
	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			GetReceiveDocBLService getreceive = new SumReceiveDoc();
			if (e.getSource() == confirm) {
				date = datetf.getText();
				if (businesshallcb.getSelectedItem() != null)
					orgName = businesshallcb.getSelectedItem().toString();
				else
					orgName = "";
				OrgInfoManageService org = new OrgForManager();
				
				String orgID = org.getOrgID(orgName);
				SumReceiveVO rece = getreceive.getReceiveDocList(date, orgID);
				ArrayList<ReceiveDocVO> receList = rece.getReceiveDoc();
				// 没有收款单
				data = new String[0][0];
				totallabel.setText("没有收款信息");
				totalmoney.setText("");
				totallabel.setBorder(BorderFactory.createMatteBorder(0, 1,
						1, 0, Color.LIGHT_GRAY));
				totalmoney.setBorder(BorderFactory.createMatteBorder(0, 0,
						1, 0, Color.LIGHT_GRAY));
				empty.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
						Color.LIGHT_GRAY));
				repaint();

				if (receList != null && receList.size() > 0) {

					totallabel.setText("总额");
					totalmoney.setText(rece.getSum() + "");
					totallabel.setBorder(BorderFactory.createMatteBorder(1, 1,
							1, 1, Color.LIGHT_GRAY));
					totalmoney.setBorder(BorderFactory.createMatteBorder(1, 1,
							1, 1, Color.LIGHT_GRAY));
					empty.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
							Color.LIGHT_GRAY));
					repaint();

					data = new String[receList.size()][3];
					for (int i = 0; i < receList.size(); i++) {
						ReceiveDocVO vo = receList.get(i);
						data[i][0] = vo.getReceiveDate();
						data[i][1] = vo.getReceivePrice() + "";
						data[i][2] = vo.getDeliverManID();
					}
				}
				tableModel.setDataVector(data, header);

			} else if (e.getSource() == ok) {
				bankaccountID = bankaccountcb.getSelectedItem().toString();
				String sumString = totalmoney.getText().toString();
				if (sumString.equals("")) {
					TipBlockEmpty block = new TipBlockEmpty("没有收款信息");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					double sum = Double.parseDouble(sumString);
					getreceive.getSum(sum, bankaccountID);
					TipBlock block = new TipBlock("合计成功");
					tippane.add(block);
					block.show();
					block = null;
				}
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
			} else if (e.getSource() == confirm) {
				confirm.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.setMyColor();
			} else if (e.getSource() == ok) {
				ok.setMyColor();
			} else if (e.getSource() == confirm) {
				confirm.setMyColor();
			}
		}

	}
}
