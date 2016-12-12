package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.BankAccount;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.BankAccountVO;

public class FinanceManageBankAccountUI extends JPanel {

	MainUIService m;
	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherRedLabel cancel;
	private MyOtherGreenLabel find;
	private MyOtherOrangeLabel exit;
	private JTextField name, income, outcome, profit, search;
	// private JButton[] buttonList;
	private JTextField[] textList;
	ArrayList<String> nameList = new ArrayList<String>();
	private String account, inmoney, outmoney;
	private JPanel bankAccount;

	public FinanceManageBankAccountUI(MainUIService main) {
		m = main;
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font font2 = new Font("楷体", Font.BOLD, 20);

		JLabel title = new JLabel("新建银行账户");
		title.setBounds(120, 60, 140, 30);
		title.setFont(font2);
		this.add(title);

		JLabel timeLabel = new JLabel("账户�?");
		timeLabel.setBounds(5, 120, 60, 30);
		timeLabel.setFont(font);
		this.add(timeLabel);

		JLabel incomeLabel = new JLabel("收入");
		incomeLabel.setBounds(20, 210, 50, 30);
		incomeLabel.setFont(font);
		this.add(incomeLabel);

		JLabel outcomeLabel = new JLabel("支出");
		outcomeLabel.setBounds(20, 300, 50, 30);
		outcomeLabel.setFont(font);
		this.add(outcomeLabel);

		JLabel profitLabel = new JLabel("余额");
		profitLabel.setBounds(20, 390, 50, 30);
		profitLabel.setFont(font);
		this.add(profitLabel);

		name = new JTextField();
		name.setBounds(80, 120, 200, 30);
		name.setFont(f);
		name.setText("");
		name.setBackground(Color.WHITE);
		name.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(name);

		income = new JTextField();
		income.setBounds(80, 210, 200, 30);
		income.setText("");
		income.setBackground(Color.WHITE);
		income.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		income.setFont(f);
		this.add(income);

		outcome = new JTextField();
		outcome.setBounds(80, 300, 200, 30);
		outcome.setText("");
		outcome.setBackground(Color.WHITE);
		outcome.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		outcome.setFont(f);
		this.add(outcome);

		profit = new JTextField();
		profit.setBounds(80, 390, 200, 30);
		profit.setEditable(false);
		// profit.setBackground(Color.WHITE);
		profit.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		profit.setFont(f);
		this.add(profit);

		JLabel line = new JLabel();
		line.setBounds(310, 67, 2, 460);
		line.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.add(line);

		Listener listen = new Listener();
		
		ok = new MyOtherBlueLabel("新建账户");
		ok.setBounds(20, 487, 110, 40);
		ok.addMouseListener(listen);
		this.add(ok);

		cancel = new MyOtherRedLabel("取消");
		cancel.setBounds(170, 487, 110, 40);
		cancel.addMouseListener(listen);
		this.add(cancel);

		exit = new MyOtherOrangeLabel("返回菜单");
		exit.setBounds(20, 600, 260, 40);
		exit.addMouseListener(listen);
		this.add(exit);

		search = new JTextField();
		search.setBounds(350, 80, 350, 35);
		search.setBackground(Color.WHITE);
		search.setText("");
		search.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		search.setFont(f);
		this.add(search);

		find = new MyOtherGreenLabel("查找");
		find.setBounds(701, 80, 100, 35);
		find.addMouseListener(listen);
		this.add(find);
		
		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		bankAccount = new JPanel();
		bankAccount.setLocation(350, 140);
		bankAccount.setPreferredSize(new Dimension(440, 490));
		//bankAccount.setBounds(350, 140, 435, 1000);
		bankAccount.setOpaque(false);
		bankAccount.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(bankAccount);
		scrollPane.setFont(font);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
				Color.LIGHT_GRAY));
		scrollPane.setBounds(350, 140, 450, 500);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		this.add(scrollPane);
	}

	private void findBankAccount(String s) {
		BankAccountBLService bank = new BankAccount();
		ArrayList<BankAccountVO> list = bank.findBankAccount(s);

		bankAccount.removeAll();
		nameList.clear();
		if (list != null) {
			int len = list.size();
			// buttonList = new JButton[len];
			textList = new JTextField[len];
			for (int i = 0; i < len; i++) {

				BankAccountVO vo = list.get(i);
				addLine(vo, i);
			}
			bankAccount.setPreferredSize(new Dimension(430, len * 95));
			this.updateUI();
		}
	}

	private void addLine(BankAccountVO vo, int i) {

		Font f = new Font("楷体", Font.PLAIN, 20);
		Font f1 = new Font("楷体", Font.PLAIN, 22);
		int len = 95 * i;
		
		JLabel mLabel = new JLabel();
		mLabel.setBounds(5, 45 + len, 425, 50);
		String title = "余额 �?" + vo.getBalance();
		mLabel.setText(title);
		mLabel.setFont(f1);
		mLabel.setBorder(BorderFactory
				.createMatteBorder(0, 0, 2, 0, Color.lightGray));
		bankAccount.add(mLabel);

		JLabel nLabel = new JLabel("账户�?");
		nLabel.setBounds(5, 5+len, 60, 40);
		nLabel.setFont(f);
		bankAccount.add(nLabel);

		JTextField n = new JTextField();
		//n.setForeground(Color.WHITE);
		//n.setBackground(new Color(250, 235, 215));
		n.setBounds(70, 7+len, 250, 35);
		n.setFont(f);
		n.setText(vo.getName());
		n.setBackground(Color.WHITE);
		n.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		bankAccount.add(n);
		textList[i] = n;

		Action action = new Action();

		JButton change = new JButton("修改");
		change.setBounds(335, 7+len, 90, 35);
		change.setOpaque(false);
		change.setContentAreaFilled(false);
		change.setBackground(Color.WHITE);
		change.setActionCommand("#" + i);
		change.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		change.setFont(f);
		change.addActionListener(action);
		bankAccount.add(change);
		nameList.add(vo.getName());
		// buttonList[i] = change;

		JButton delete = new JButton("删除");
		delete.setBounds(330, 7, 90, 35);
		delete.setOpaque(false);
		delete.setContentAreaFilled(false);
		delete.setBackground(Color.WHITE);
		delete.setActionCommand(i+"");
		delete.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		delete.setFont(f);
		delete.addActionListener(action);
		mLabel.add(delete);
	}

	private void addBankAccount() {
		BankAccountBLService bank = new BankAccount();
		account = name.getText();
		inmoney = income.getText();
		outmoney = outcome.getText();
		double in = Double.parseDouble(inmoney);
		double out = Double.parseDouble(outmoney);
		String pro = String.valueOf(in - out);
		profit.setText(pro);

		BankAccountVO vo = new BankAccountVO(account, in, out, in - out);
		boolean succ = bank.addBankAccount(vo);
		bank.endManage();

		if (succ) {
			TipBlock block = new TipBlock("新建成功");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			TipBlockError block = new TipBlockError("新建失败");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private boolean check() {

		if (checkNotFill()) {
			TipBlockEmpty block = new TipBlockEmpty("您还有信息未�?");
			tippane.add(block);
			block.show();
			block = null;

			return false;
		} else {
			boolean isValid = true, isDup = false;

			BankAccountBLService bank = new BankAccount();

			if (!bank.checkMoney(inmoney)) {
				income.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				isValid = false;
			}
			if (!bank.checkMoney(outmoney)) {
				outcome.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				isValid = false;
			}
			if (bank.checkDuplication(account)) {
				name.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				TipBlockError block = new TipBlockError("账户重名");
				tippane.add(block);
				block.show();
				block = null;
				isDup = true;
			}
			if (!isValid) {
				TipBlockError block = new TipBlockError("金额输入错误");
				tippane.add(block);
				block.show();
				block = null;
				isDup = true;
			}
			return isValid && (!isDup);
		}
	}

	private boolean checkNotFill() {
		boolean fill = false;
		Color yellow = new Color(255,215,0);
		
		if (account.equals("")) {
			name.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = true;
		}
		if (inmoney.equals("")) {
			income.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = true;
		}
		if (outmoney.equals("")) {
			outcome.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = true;
		}
		return fill;
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == exit){
				m.jumpToFinanceMenuUI(IDKeeper.getID(), true);
			}else if (e.getSource() == cancel) {
				name.setText("");
				income.setText("");
				outcome.setText("");
				profit.setText("");
			} else if (e.getSource() == find) {
				String s = search.getText();
				findBankAccount(s);
			} else if (e.getSource() == ok) {
				account = name.getText();
				inmoney = income.getText();
				outmoney = outcome.getText();

				if (check()) {
					addBankAccount();
					name.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
					income.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
					outcome.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				}
			} 
			repaint();
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.whenPressed();
			} else if (e.getSource() == find) {
				find.whenPressed();
			} else if (e.getSource() == ok) {
				ok.whenPressed();
			}else if(e.getSource() == cancel){
				cancel.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.setMyColor();
			} else if (e.getSource() == find) {
				find.setMyColor();
			} else if (e.getSource() == ok) {
				ok.setMyColor();
			}else if(e.getSource() == cancel){
				cancel.setMyColor();
			}
		}
	}

	private class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String s = e.getActionCommand();
			BankAccountBLService bank = new BankAccount();

			if (s.startsWith("#")) {
				s=s.substring(1);
				int i=Integer.parseInt(s);
				JTextField text = textList[i];
				String n = "";

				n = text.getText();
				if (n.equals("")) {
					text.setBorder(BorderFactory.createLineBorder(new Color(255,215,0), 1));
					
					TipBlockEmpty block = new TipBlockEmpty("账户名未�?");
					tippane.add(block);
					block.show();
					block = null;
				
				} else {
					if (bank.checkDuplication(n)) {
						text.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

						TipBlockError block = new TipBlockError("账户重名");
						tippane.add(block);
						block.show();
						block = null;
						
					} else {

						boolean succ = bank.changeBankAccount(nameList.get(i), n);
						nameList.set(i, n);
						bank.endManage();
						text.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

						if (succ) {
							TipBlock block = new TipBlock("修改成功");
							tippane.add(block);
							block.show();
							block = null;
						} else {
							TipBlockError block = new TipBlockError("修改失败");
							tippane.add(block);
							block.show();
							block = null;
						}
					}
				}
			} else {
				int i = Integer.parseInt(s);
				boolean succ = bank.removeBankAccount(nameList.get(i));
				
				bank.endManage();

				String key = search.getText();
				findBankAccount(key);
				repaint();
				
				if (succ) {
					TipBlock block = new TipBlock("删除成功");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					TipBlockError block = new TipBlockError("删除失败");
					tippane.add(block);
					block.show();
					block = null;
				}
			}
		}
	}
}
