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
import express.po.GoodsArrivalStatus;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.ArrivalDocBusinessHallVO;
import express.vo.ReceiveDocVO;

public class ExamBusinessArrival {

	private JPanel order;
	private JPanel title;
	private JPanel tippane;
	private MyOtherBlueLabel fast;
	private JCheckBox all;
	private JCheckBox[] cList;
	private ExamDocumentBLService exam = new ExamDocument();
	private ArrayList<ArrivalDocBusinessHallVO> list = exam
			.getUEBusinessHallArrivalDoclist();
	private boolean first = true;

	public ExamBusinessArrival(JPanel doc, JPanel title, JPanel tippane) {
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
				ArrivalDocBusinessHallVO vo = list.get(i);
				addLine(i, vo);
			}
			order.setPreferredSize(new Dimension(680, len * 40));
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
		Font f = new Font("楷体", Font.PLAIN, 20);
		JLabel title1 = new JLabel("到达日期", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, 0, 110, 35);
		title1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title1);

		JLabel title2 = new JLabel("中转单编号", JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(180, 0, 170, 35);
		title2.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title2);

		JLabel title3 = new JLabel("出发地", JLabel.CENTER);
		title3.setFont(f);
		title3.setBounds(350, 0, 100, 35);
		title3.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title3);

		JLabel title4 = new JLabel("货物到达状态", JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(450, 0, 130, 35);
		title4.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		title.add(title4);

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

	private void addLine(int i, ArrivalDocBusinessHallVO vo) {
		int h = i * 40;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JCheckBox check = new JCheckBox();
		check.setBounds(20, h + 2, 40, 36);
		check.setBackground(Color.WHITE);
		order.add(check);
		cList[i] = check;

		JLabel l1 = new JLabel();
		l1.setBounds(0, h + 38, 70, 2);
		l1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		order.add(l1);

		JLabel title1 = new JLabel(vo.getArriveTime(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(70, h, 110, 40);
		title1.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title1);

		JLabel title2 = new JLabel(vo.getTransferDocID(), JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(180, h, 170, 40);
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title2);

		JLabel title3 = new JLabel(vo.getDeparture(), JLabel.CENTER);
		title3.setFont(f);
		title3.setBounds(350, h, 100, 40);
		title3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title3);

		String status = getStatus(vo.getArrivalStatus());
		JLabel title4 = new JLabel(status, JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(450, h, 130, 40);
		title4.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1,
				Color.LIGHT_GRAY));
		order.add(title4);

		Action action = new Action();

		JButton change = new JButton("审批");
		change.setBounds(590, h + 3, 100, 35);
		change.setOpaque(false);
		change.setContentAreaFilled(false);
		change.setBackground(Color.WHITE);
		change.setActionCommand(i + "");
		change.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		change.setFont(new Font("方正隶变简体", Font.PLAIN, 18));
		change.addActionListener(action);
		order.add(change);
	}

	private String getStatus(GoodsArrivalStatus s) {
		switch (s) {
		case Complete:
			return "完整";
		case Damage:
			return "损坏";
		default:
			return "丢失";
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
						ArrivalDocBusinessHallVO vo = list.get(num);
						vo.setState(true);
						exam.changeBusinessHallArrivalDoc(vo);
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
			ArrivalDocBusinessHallVO vo = list.get(index);
			vo.setState(true);

			boolean succ = exam.changeBusinessHallArrivalDoc(vo);
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
