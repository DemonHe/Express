package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businessLogic.infoManageBL.BankAccount;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.PaymentItem;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.PaymentDocVO;

public class ExamPayment {

	private JPanel order;
	private JPanel title;
	private JPanel tippane;
	private MyOtherBlueLabel fast;
	private JCheckBox all;
	private JCheckBox[] cList;
	private JComboBox[] accountList;
	private ExamDocumentBLService exam = new ExamDocument();
	private ArrayList<PaymentDocVO> list = exam.getUEPaymentDoclist();
	private boolean first = true;

	public ExamPayment(JPanel doc, JPanel title, JPanel tippane) {
		order = doc;
		this.title = title;
		this.tippane = tippane;
		init();
	}

	private void init() {

		title.removeAll();
		order.removeAll();
		if (list == null) {
			cList = new JCheckBox[0];
			accountList = new JComboBox[0];
			addNone();
		} else if (list.size() == 0) {
			cList = new JCheckBox[0];
			accountList = new JComboBox[0];
			addNone();
		} else {
			addTitle();
			int len = list.size();
			cList = new JCheckBox[len];
			accountList = new JComboBox[len];
			for (int i = 0; i < len; i++) {
				PaymentDocVO vo = list.get(i);
				PaymentItem pay = vo.getPaymentList();
				addLine(i, pay);
			}
			order.setPreferredSize(new Dimension(680, len * 140));
		}
	}

	private void addNone() {
		JLabel tip = new JLabel("�? �? �? �? �? �? �? �?", JLabel.CENTER);
		tip.setBounds(0, 100, 640, 200);
		tip.setFont(new Font("仿宋", Font.BOLD, 49));
		tip.setOpaque(false);
		tip.setForeground(Color.LIGHT_GRAY);
		order.setPreferredSize(new Dimension(710, 500));
		order.add(tip);
	}

	private void addTitle() {
		Font f = new Font("楷体", Font.PLAIN, 20);
		JLabel title1 = new JLabel("付款条目", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, 0, 120, 35);
		title1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title1);

		JLabel title2 = new JLabel("�? �? �? �?", JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(190, 0, 390, 35);
		title2.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title2);

		if (first) {
			all = new JCheckBox();
			all.setBounds(25, 1, 40, 32);
			all.setFont(new Font("仿宋", Font.PLAIN, 20));
			all.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 0,
					Color.LIGHT_GRAY));
			all.setBackground(Color.WHITE);
		}
		title.add(all);
		Item item = new Item();
		all.addItemListener(item);

		JLabel l1 = new JLabel();
		l1.setBounds(0, 0, 70, 1);
		l1.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
				Color.LIGHT_GRAY));
		title.add(l1);

		JLabel l2 = new JLabel();
		l2.setBounds(0, 33, 70, 2);
		l2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		title.add(l2);

		if (first) {
			fast = new MyOtherBlueLabel("批量审批");
			fast.setBounds(590, 0, 100, 35);
		}
		first = false;
		title.add(fast);

		Listener listener = new Listener();
		fast.addMouseListener(listener);
	}

	private void addLine(int i, PaymentItem pay) {
		int h = i * 140;
		Font f = new Font("仿宋", Font.PLAIN, 19);

		JCheckBox check = new JCheckBox();
		check.setBounds(20, h + 2, 40, 136);
		check.setBackground(Color.WHITE);
		order.add(check);
		cList[i] = check;

		JLabel l1 = new JLabel();
		l1.setBounds(0, h + 138, 70, 2);
		l1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		order.add(l1);

		JLabel title1 = new JLabel(pay.getEntry(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, h, 120, 140);
		title1.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title1);

		JLabel title2 = new JLabel();
		title2.setFont(f);
		title2.setText("日期�?" + pay.getDate());
		title2.setBounds(190, h, 195, 35);
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title2);

		JLabel title3 = new JLabel();
		title3.setFont(f);
		title3.setText("付款人：" + pay.getName());
		title3.setBounds(385, h, 195, 35);
		title3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title3);

		JLabel title4 = new JLabel();
		title4.setFont(f);
		title4.setText("付款账户");
		title4.setBounds(190, h + 35, 120, 35);
		title4.setBorder(null);
		order.add(title4);

		BankAccountBLService b = new BankAccount();
		ArrayList<String> name = b.getBankAccountName();
		String[] bank = new String[0];

		// 选择银行账户
		String currAccount = pay.getAccount();
		int pointer = -1;
		if (name != null) {
			bank = new String[name.size()];
			for (int index = 0; index < name.size(); index++) {
				bank[index] = name.get(index);
				if (bank[index].equals(currAccount)) {
					pointer = index;
				}
			}
		}
		// 银行账户

		JComboBox account = new JComboBox(bank);
		if (pointer > 0) {
			account.setSelectedIndex(pointer);
		}
		account.setFont(f);
		account.setBackground(Color.WHITE);
		account.setBounds(310, h + 37, 270, 30);
		account.setOpaque(false);
		order.add(account);
		
		accountList[i] = account;

		DecimalFormat form = new DecimalFormat("0.0");
		String money = form.format(pay.getSum());
		JLabel title5 = new JLabel();
		title5.setFont(f);
		title5.setText("付款金额�?" + money);
		title5.setBounds(190, h + 70, 390, 35);
		title5.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title5);

		JLabel title6 = new JLabel();
		title6.setFont(f);
		String comment = pay.getComment();
		if (comment.length() > 18) {
			comment = comment.substring(0, 19);
		}
		title6.setText("备注�?" + comment);
		title6.setBounds(190, h + 105, 390, 35);
		title6.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title6);

		Action action = new Action();

		JButton change = new JButton("审批");
		change.setBounds(590, h + 54, 100, 35);
		change.setOpaque(false);
		change.setContentAreaFilled(false);
		change.setBackground(Color.WHITE);
		change.setActionCommand(i + "");
		change.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		change.setFont(new Font("方正隶变�?�?", Font.PLAIN, 18));
		change.addActionListener(action);
		order.add(change);
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == fast) {
				boolean selected = false;
				int num = 0;
				for (int i = 0; i < cList.length; i++) {
					if (cList[i].isSelected()) {
						selected = true;

						String account = accountList[i].getSelectedItem()
								.toString();

						PaymentDocVO vo = list.get(num);
						PaymentItem item = vo.getPaymentList();
						if (account != null && !account.equals("")) {							
							item.setAccount(account);
						}
						vo.setPaymentList(item);
						vo.setState(true);
						exam.changePaymentDoc(vo);
						list.remove(num);
						num--;
					}
					num++;
				}
				init();
				if (selected) {
					TipBlock block = new TipBlock("审批通过");
					tippane.add(block);
					block.show();
					block = null;
				}
			}
			order.repaint();
			title.updateUI();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == fast) {
				fast.whenPressed();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == fast) {
				fast.setMyColor();
			}
		}

	}

	private class Item implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				for (int i = 0; i < cList.length; i++) {
					cList[i].setSelected(true);
				}
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
				for (int i = 0; i < cList.length; i++) {
					cList[i].setSelected(false);
				}
			}
		}

	}

	private class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String s = e.getActionCommand();
			int index = Integer.parseInt(s);
			
			
			String account = accountList[index].getSelectedItem()
					.toString();

			PaymentDocVO vo = list.get(index);
			PaymentItem item = vo.getPaymentList();
			if (account != null && !account.equals("")) {						
				item.setAccount(account);
			}
			vo.setPaymentList(item);
			vo.setState(true);	

			boolean succ = exam.changePaymentDoc(vo);
			if (succ) {
				TipBlock block = new TipBlock("审批通过");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError("未能完成审批");
				tippane.add(block);
				block.show();
				block = null;
			}
			list.remove(index);
			init();
			order.repaint();
			title.updateUI();
		}
	}
}
