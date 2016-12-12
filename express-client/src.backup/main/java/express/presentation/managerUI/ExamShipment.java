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

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.DeliveryType;
import express.po.PackageType;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.OrderVO;
import express.vo.ShipmentDocBusinessHallVO;
import express.vo.ShipmentDocTransCenterVO;

public class ExamShipment {

	private JPanel order;
	private JPanel title;
	private JPanel tippane;
	private MyOtherBlueLabel fast;
	private JCheckBox all;
	private JCheckBox[] cList;
	private ExamDocumentBLService exam = new ExamDocument();
	private ArrayList<ShipmentDocBusinessHallVO> list1 = exam
			.getUEBusinessHallShipmentDoclist();
	private ArrayList<ShipmentDocTransCenterVO> list2 = exam
			.getUETransCenterShipmentDoclist();
	private ArrayList<String> choose = new ArrayList<String>();
	private boolean first = true;

	public ExamShipment(JPanel doc, JPanel title, JPanel tippane) {
		order = doc;
		this.title = title;
		this.tippane = tippane;
		init();
	}

	private void init() {

		title.removeAll();
		order.removeAll();
		if (list1 == null && list2 == null) {
			cList = new JCheckBox[0];
			addNone();
		} else if (list1.size() == 0 && list2.size() == 0) {
			cList = new JCheckBox[0];
			addNone();
		} else {
			addTitle();
			int len = list1.size() + list2.size();
			cList = new JCheckBox[len];
			for (int i = 0; i < list1.size(); i++) {
				ShipmentDocBusinessHallVO vo = list1.get(i);
				addLine1(i, vo);
				String s = "0";
				choose.add(s);
			}
			for (int i = 0; i < list2.size(); i++) {
				ShipmentDocTransCenterVO vo = list2.get(i);
				addLine2(list1.size() + i, vo);
				String s = "1";
				choose.add(s);
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
		Font f = new Font("楷体", Font.PLAIN, 21);
		JLabel title1 = new JLabel("汽运编号", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, 0, 170, 35);
		title1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title1);

		JLabel title2 = new JLabel("�? �? �? �?", JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(240, 0, 340, 35);
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

	private void addLine1(int i, ShipmentDocBusinessHallVO vo) {
		int h = i * 140;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JCheckBox check = new JCheckBox();
		check.setBounds(20, h + 2, 40, 136);
		check.setBackground(Color.WHITE);
		order.add(check);
		cList[i] = check;

		JLabel l1 = new JLabel();
		l1.setBounds(0, h + 138, 70, 2);
		l1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		order.add(l1);

		JLabel title1 = new JLabel(vo.getShipmentID(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, h, 170, 140);
		title1.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title1);

		JLabel title2 = new JLabel();
		title2.setFont(f);
		title2.setText("日期�?" + vo.getDate());
		title2.setBounds(240, h, 170, 35);
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title2);

		JLabel title4 = new JLabel();
		title4.setFont(f);
		title4.setText("营业厅：" + vo.getBusinessHallNum());
		title4.setBounds(410, h, 170, 35);
		title4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title4);

		JLabel title5 = new JLabel();
		title5.setFont(f);
		title5.setText("到达地：" + vo.getArrivalPlace());
		title5.setBounds(240, h + 35, 170, 35);
		title5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title5);

		JLabel title6 = new JLabel();
		title6.setFont(f);
		title6.setText("车辆代号�?" + vo.getVanID());
		title6.setBounds(410, h + 35, 170, 35);
		title6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title6);

		JLabel title7 = new JLabel();
		title7.setFont(f);
		title7.setText("监装员：" + vo.getCheckMan());
		title7.setBounds(240, h + 70, 170, 35);
		title7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title7);

		JLabel title8 = new JLabel();
		title8.setFont(f);
		title8.setText("押运员：" + vo.getTransMan());
		title8.setBounds(410, h + 70, 170, 35);
		title8.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title8);

		DecimalFormat form = new DecimalFormat("0.0");
		String money = form.format(vo.getMoney());
		JLabel title3 = new JLabel();
		title3.setFont(f);
		title3.setText("费用�?" + money);
		title3.setBounds(240, h + 105, 340, 35);
		title3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title3);

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

	private void addLine2(int i, ShipmentDocTransCenterVO vo) {
		int h = i * 140;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JCheckBox check = new JCheckBox();
		check.setBounds(20, h + 2, 40, 136);
		check.setBackground(Color.WHITE);
		order.add(check);
		cList[i] = check;

		JLabel l1 = new JLabel();
		l1.setBounds(0, h + 138, 70, 2);
		l1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		order.add(l1);

		JLabel title1 = new JLabel(vo.getShipmentID(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, h, 170, 140);
		title1.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title1);

		JLabel title2 = new JLabel();
		title2.setFont(f);
		title2.setText("日期�?" + vo.getDate());
		title2.setBounds(240, h, 170, 35);
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title2);

		JLabel title4 = new JLabel();
		title4.setFont(f);
		title4.setText("中转中心�?" + vo.getTransId());
		title4.setBounds(410, h, 170, 35);
		title4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title4);

		JLabel title5 = new JLabel();
		title5.setFont(f);
		title5.setText("到达地：" + vo.getArrivalPlace());
		title5.setBounds(240, h + 35, 170, 35);
		title5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title5);

		JLabel title6 = new JLabel();
		title6.setFont(f);
		title6.setText("车辆代号�?" + vo.getVanID());
		title6.setBounds(410, h + 35, 170, 35);
		title6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title6);

		JLabel title7 = new JLabel();
		title7.setFont(f);
		title7.setText("监装员：" + vo.getCheckMan());
		title7.setBounds(240, h + 70, 170, 35);
		title7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		order.add(title7);

		JLabel title8 = new JLabel();
		title8.setFont(f);
		title8.setText("押运员：" + vo.getTransMan());
		title8.setBounds(410, h + 70, 170, 35);
		title8.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title8);

		DecimalFormat form = new DecimalFormat("0.0");
		String money = form.format(vo.getMoney());
		JLabel title3 = new JLabel();
		title3.setFont(f);
		title3.setText("费用�?" + money);
		title3.setBounds(240, h + 105, 340, 35);
		title3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title3);

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
						String ch = choose.get(num);
						if (ch.equals("0")) {
							ShipmentDocBusinessHallVO vo = list1.get(num);
							vo.setState(true);
							exam.changeBusinessHallShipmentDoc(vo);
							list1.remove(num);
						} else {
							ShipmentDocTransCenterVO vo = list2.get(num
									- list1.size());
							vo.setState(true);
							exam.changeTransCenterShipmentDoc(vo);
							list2.remove(num - list1.size());
						}
						choose.remove(num);
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
			String ch = choose.get(index);

			boolean succ;

			if (ch.equals("0")) {
				ShipmentDocBusinessHallVO vo = list1.get(index);
				vo.setState(true);
				succ = exam.changeBusinessHallShipmentDoc(vo);
				list1.remove(index);
			} else {
				ShipmentDocTransCenterVO vo = list2.get(index - list1.size());
				vo.setState(true);
				succ = exam.changeTransCenterShipmentDoc(vo);
				list2.remove(index - list1.size());
			}
			choose.remove(index);
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

			init();
			order.repaint();
			title.updateUI();
		}
	}
}
