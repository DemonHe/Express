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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businessLogic.infoManageBL.BankAccount;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.DeliveryType;
import express.po.PackageType;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.OrderVO;

public class ExamOrder {

	private JPanel order;
	private JPanel title;
	private JPanel tippane;
	private MyOtherBlueLabel fast;
	private JCheckBox all;
	private JCheckBox[] cList;
	private ExamDocumentBLService exam = new ExamDocument();
	private ArrayList<OrderVO> list = exam.getUEOrderlist();
	private boolean first = true;

	public ExamOrder(JPanel doc, JPanel title, JPanel tippane) {
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
			addNone();
		} else if (list.size() == 0) {
			cList = new JCheckBox[0];
			addNone();
		} else {
			addTitle();
			int len = list.size();
			cList = new JCheckBox[len];
			for (int i = 0; i < len; i++) {
				OrderVO vo = list.get(i);
				addLine(i, vo);
			}
			order.setPreferredSize(new Dimension(680, len * 150));
		}
	}

	private void addNone() {
		JLabel tip = new JLabel("没 有 未 审 批 的 单 据", JLabel.CENTER);
		tip.setBounds(0, 100, 640, 200);
		tip.setFont(new Font("仿宋", Font.BOLD, 49));
		tip.setOpaque(false);
		tip.setForeground(Color.LIGHT_GRAY);
		order.setPreferredSize(new Dimension(710, 500));
		order.add(tip);
	}

	private void addTitle() {
		Font f = new Font("楷体", Font.PLAIN, 21);
		JLabel title1 = new JLabel("快递编号", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, 0, 110, 35);
		title1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title1);

		JLabel title2 = new JLabel("详 细 信 息", JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(180, 0, 400, 35);
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

	private void addLine(int i, OrderVO vo) {
		int h = i * 150;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JCheckBox check = new JCheckBox();
		check.setBounds(20, h + 2, 40, 146);
		check.setBackground(Color.WHITE);
		order.add(check);
		cList[i] = check;

		JLabel l1 = new JLabel();
		l1.setBounds(0, h + 148, 70, 2);
		l1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		order.add(l1);

		JLabel title1 = new JLabel(vo.getOrderID(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, h, 110, 150);
		title1.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title1);

		String delType = getD(vo.getType());
		JLabel title2 = new JLabel();
		title2.setFont(f);
		title2.setText("快递种类 ：" + delType);
		title2.setBounds(180, h, 200, 30);
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title2);

		DecimalFormat form = new DecimalFormat("0.0");
		String money = form.format(vo.getFee());
		JLabel title3 = new JLabel();
		title3.setFont(f);
		title3.setText("费用合计：" + money);
		title3.setBounds(380, h, 200, 30);
		title3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title3);

		JLabel title4 = new JLabel();
		title4.setFont(f);
		title4.setText("出发地： " + vo.getStartCity());
		title4.setBounds(180, h + 30, 200, 30);
		title4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title4);

		JLabel title5 = new JLabel();
		title5.setFont(f);
		title5.setText("到达地： " + vo.getEndCity());
		title5.setBounds(380, h + 30, 200, 30);
		title5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title5);

		JLabel title6 = new JLabel();
		title6.setFont(f);
		title6.setText("原件数： " + vo.getNumberOfGoods());
		title6.setBounds(180, h + 60, 200, 30);
		title6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title6);

		JLabel title7 = new JLabel();
		title7.setFont(f);
		title7.setText("实际重量：" + vo.getWeight());
		title7.setBounds(380, h + 60, 200, 30);
		title7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title7);

		JLabel title8 = new JLabel();
		title8.setFont(f);
		title8.setText("体积：  " + vo.getVolume());
		title8.setBounds(180, h + 90, 200, 30);
		title8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title8);

		JLabel title9 = new JLabel();
		title9.setFont(f);
		title9.setText("内件品名：" + vo.getNameOfGoods());
		title9.setBounds(380, h + 90, 200, 30);
		title9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title9);

		String packType = getP(vo.getPackageType());
		JLabel title10 = new JLabel("包装类型： ");
		title10.setFont(f);
		title10.setText("包装类型：" + packType);
		title10.setBounds(180, h + 120, 200, 30);
		title10.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title10);

		JLabel title11 = new JLabel();
		title11.setFont(f);
		title11.setText("到达日期：" + vo.getPredictTime());
		title11.setBounds(380, h + 120, 200, 30);
		title11.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title11);

		Action action = new Action();

		JButton change = new JButton("审批");
		change.setBounds(590, h + 58, 100, 35);
		change.setOpaque(false);
		change.setContentAreaFilled(false);
		change.setBackground(Color.WHITE);
		change.setActionCommand(i + "");
		change.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		change.setFont(new Font("方正隶变简体", Font.PLAIN, 18));
		change.addActionListener(action);
		order.add(change);
	}

	private String getD(DeliveryType d) {
		switch (d) {
		case Slow:
			return "经济快递";
		case Standard:
			return "标准快递";
		default:
			return "特快快递";
		}
	}

	private String getP(PackageType p) {
		switch (p) {
		case DeliverBag:
			return "快递袋";
		case CardBox:
			return "纸箱";
		case WoodBox:
			return "木箱";
		default:
			return "其它";
		}
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
						OrderVO vo = list.get(num);
						vo.setState(true);
						exam.changeOrder(vo);
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
			OrderVO vo = list.get(index);
			vo.setState(true);

			boolean succ = exam.changeOrder(vo);
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
