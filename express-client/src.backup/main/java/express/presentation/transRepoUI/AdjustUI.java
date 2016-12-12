package express.presentation.transRepoUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.documentBL.InDoc;
import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.businesslogicService.transcenterRepoBLService.InDocblService;
import express.po.Area;
import express.po.RepoPosition;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.InDocVO;

public class AdjustUI extends JPanel {

	private JPanel tippane;
	private JComboBox<String> idbox, areaBox, rowBox;
	private MyOtherBlueLabel button_confirm;
	private MyOtherGreenLabel button_cancel;
	private MyOtherOrangeLabel button_exit;
	private JTextField preArea, preRow, preShelf, preBlock, textArea6,
			textArea7;
	private String orgID = IDKeeper.getOrgID();
	private MainUIService m;

	public AdjustUI(MainUIService main) {

		setLayout(null);
		this.m = main;

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		int textlength = 200;
		int textwidth = 30;
		int labellength = 100;
		int labelwidth = 30;

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("隶书", Font.PLAIN, 20);

		AdjustRepoBLService adjust = new RepoController();
		String[] idList = adjust.getAllInDoc(orgID);
		idbox = new JComboBox<String>(idList);
		idbox.setBounds(350, 90, textlength, textwidth);
		idbox.setBackground(Color.WHITE);
		idbox.setFont(font);
		this.add(idbox);

		preArea = new JTextField();
		preArea.setFont(f);
		preArea.setEditable(false);
		preArea.setBounds(150, 200, textlength, textwidth);
		this.add(preArea);

		preRow = new JTextField();
		preRow.setFont(f);
		preRow.setEditable(false);
		preRow.setBounds(150, 300, textlength, textwidth);
		this.add(preRow);

		preShelf = new JTextField();
		preShelf.setFont(f);
		preShelf.setEditable(false);
		preShelf.setBounds(150, 400, textlength, textwidth);
		this.add(preShelf);

		preBlock = new JTextField();
		preBlock.setFont(f);
		preBlock.setEditable(false);
		preBlock.setBounds(150, 500, textlength, textwidth);
		this.add(preBlock);

		String area[] = { "航运�?", "铁运�?", "汽运�?", "机动�?" };
		areaBox = new JComboBox<String>(area);
		areaBox.setBounds(550, 200, textlength, textwidth);
		areaBox.setFont(f);
		areaBox.setBackground(Color.WHITE);
		// textArea4.setBackground(Color.BLUE);
		// textArea4.setLineWrap(true);
		// textArea4.setWrapStyleWord(true);
		this.add(areaBox);

		// Item i = new Item();
		// areaBox.addItemListener(i);

		String[] rowList = adjust.getRow(orgID, Area.AIR);
		rowBox = new JComboBox<String>(rowList);
		rowBox.setBounds(550, 300, textlength, textwidth);
		rowBox.setFont(f);
		rowBox.setBackground(Color.WHITE);
		// textArea5.setBackground(Color.BLUE);
		// textArea5.setLineWrap(true);
		// textArea5.setWrapStyleWord(true);
		this.add(rowBox);

		textArea6 = new JTextField();
		textArea6.setBounds(550, 400, textlength, textwidth);
		textArea6.setFont(f);
		textArea6.setText("");
		textArea6.setEditable(false);
		textArea6.setBackground(Color.WHITE);
		textArea6.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// textArea6.setBackground(Color.BLUE);
		// textArea6.setLineWrap(true);
		// textArea6.setWrapStyleWord(true);
		this.add(textArea6);

		textArea7 = new JTextField();
		textArea7.setBounds(550, 500, textlength, textwidth);
		textArea7.setFont(f);
		textArea7.setText("");
		textArea7.setEditable(false);
		textArea7.setBackground(Color.WHITE);
		textArea7.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// textArea7.setBackground(Color.BLUE);
		// textArea7.setLineWrap(true);
		// textArea7.setWrapStyleWord(true);
		this.add(textArea7);

		JLabel title = new JLabel("�? �? �? �?");
		title.setBounds(350, 50, 200, labelwidth);
		title.setFont(new Font("楷体", Font.BOLD, 20));
		this.add(title);

		JLabel label1 = new JLabel("快�?�编�?");
		label1.setBounds(250, 90, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel pre = new JLabel("原快递位置：");
		pre.setBounds(50, 150, 150, labelwidth);
		pre.setFont(font);
		this.add(pre);

		JLabel label2 = new JLabel("区号");
		label2.setBounds(50, 200, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("排号");
		label3.setBounds(50, 300, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label8 = new JLabel("架号");
		label8.setBounds(50, 400, labellength, labelwidth);
		label8.setFont(font);
		this.add(label8);

		JLabel label9 = new JLabel("位号");
		label9.setBounds(50, 500, labellength, labelwidth);
		label9.setFont(font);
		this.add(label9);

		JLabel newp = new JLabel("调整位置�?");
		newp.setBounds(450, 150, labellength, labelwidth);
		newp.setFont(font);
		this.add(newp);

		JLabel label4 = new JLabel("区号");
		label4.setBounds(450, 200, labellength, labelwidth);
		label4.setFont(font);
		this.add(label4);

		JLabel label5 = new JLabel("排号");
		label5.setBounds(450, 300, labellength, labelwidth);
		label5.setFont(font);
		this.add(label5);

		JLabel label6 = new JLabel("架号");
		label6.setBounds(450, 400, labellength, labelwidth);
		label6.setFont(font);
		this.add(label6);

		JLabel label7 = new JLabel("位号");
		label7.setBounds(450, 500, labellength, labelwidth);
		label7.setFont(font);
		this.add(label7);

		JListener listener = new JListener();

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(50, 600, 160, 40);
		button_confirm.addMouseListener(listener);

		this.add(button_confirm);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(320, 600, 160, 40);
		button_cancel.addMouseListener(listener);

		this.add(button_cancel);

		button_exit = new MyOtherOrangeLabel("返回菜单");
		button_exit.setBounds(590, 600, 160, 40);
		button_exit.addMouseListener(listener);

		this.add(button_exit);

		// areaBox.addMouseListener(listener);
		textArea6.addMouseListener(listener);
		textArea7.addMouseListener(listener);

		Item itemListen = new Item();
		idbox.addItemListener(itemListen);
		areaBox.addItemListener(itemListen);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		init();
	}

	private void init() {
		String id = (String) idbox.getSelectedItem();
		if (id.charAt(0) >= '0' && id.charAt(0) <= '9') {
			AdjustRepoBLService adjust = new RepoController();
			RepoPosition rp = adjust.getPosition(orgID, id);
			if (rp != null) {
				String a = getArea(rp.getblock());
				String row = "�?" + rp.getshelf() + "�?";
				String shelf = String.valueOf(rp.getrow());
				String pos = String.valueOf(rp.getposition());
				setText(a, row, shelf, pos);
			} else {
				setText("�?", "�?", "�?", "�?");
			}
		} else {
			setText("�?", "�?", "�?", "�?");
		}
	}

	private void setText(String s1, String s2, String s3, String s4) {
		preArea.setText(s1);
		preRow.setText(s2);
		preShelf.setText(s3);
		preBlock.setText(s4);
	}

	private String getArea(Area a) {
		switch (a) {
		case AIR:
			return "航运�?";
		case TRAIN:
			return "铁运�?";
		case CAR:
			return "汽运�?";
		default:
			return "机动�?";
		}
	}

	private Area getArea(String s) {
		switch (s) {
		case "航运�?":
			return Area.AIR;
		case "铁运�?":
			return Area.TRAIN;
		case "汽运�?":
			return Area.CAR;
		default:
			return Area.FLEXIBLE;
		}
	}

	private boolean checkFilled() {
		String shelf = textArea6.getText();
		String pos = textArea7.getText();

		boolean fill = true;
		Color yellow = new Color(255, 215, 0);

		if (shelf.equals("")) {
			textArea6.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (pos.equals("")) {
			textArea7.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (!fill) {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
			tippane.add(block);
			block.show();
			block = null;
		}
		return fill;
	}

	private void adjust() {
		// 旧库�?
		String id = (String) idbox.getSelectedItem();
		AdjustRepoBLService adjust = new RepoController();
		RepoPosition rp = adjust.getPosition(orgID, id);
		rp.setIsUsed(true);
		// 新库�?
		String area = (String) areaBox.getSelectedItem();
		String row = (String) rowBox.getSelectedItem();
		String shelf = textArea6.getText();
		String pos = textArea7.getText();
		Area a = getArea(area);
		row = row.substring(1, row.length() - 1);
		int r = Integer.parseInt(row);
		int s = Integer.parseInt(shelf);
		int p = Integer.parseInt(pos);
		RepoPosition position = new RepoPosition(id, a, r, s, p, true);
		// �?查是否使�?
		if (!adjust.checkRepoBlockUsed(orgID, position)) {
			InDocblService inDoc = new InDoc();
			InDocVO vo = inDoc.getInDoc(id);
			vo.setRepoPosition(position);
			inDoc.changeInDoc(vo);
			inDoc.endInDoc();

			boolean succ = adjust.adjustRepo(orgID, rp, position);
			adjust.endRepoManage();
			if (succ) {
				TipBlock block = new TipBlock("调整成功");
				tippane.add(block);
				block.show();
				block = null;
			} else {
				TipBlockError block = new TipBlockError("调整失败");
				tippane.add(block);
				block.show();
				block = null;
			}
		}else{
			TipBlockError block = new TipBlockError("库区位置正在使用");
			tippane.add(block);
			block.show();
			block = null;
		}
	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == button_confirm) {
				if (checkFilled()) {
					String id = (String) idbox.getSelectedItem();
					if (id.charAt(0) >= '0' && id.charAt(0) <= '9') {
						adjust();
						textArea6.setBorder(BorderFactory.createLineBorder(
								Color.GRAY, 1));
						textArea7.setBorder(BorderFactory.createLineBorder(
								Color.GRAY, 1));
					} else {
						TipBlockEmpty block = new TipBlockEmpty("请�?�择单号");
						tippane.add(block);
						block.show();
						block = null;
					}
				}
			} else if (e.getSource() == button_cancel) {
				textArea6.setText("");
				textArea7.setText("");
				textArea6.setBorder(BorderFactory.createLineBorder(Color.GRAY,
						1));
				textArea7.setBorder(BorderFactory.createLineBorder(Color.GRAY,
						1));
			} else if (e.getSource() == button_exit) {
				m.jumpTotranscenterRepoMenuUI(IDKeeper.getID());
			} else if (e.getSource() == textArea6 || e.getSource() == textArea7) {
				String row = (String) rowBox.getSelectedItem();
				row = row.substring(1, row.length() - 1);
				if (row.charAt(0) >= '0' && row.charAt(0) <= '9') {
					int r = Integer.parseInt(row);
					String area = (String) areaBox.getSelectedItem();
					ShowRepoUI show = new ShowRepoUI(orgID, getArea(area), r,
							textArea6, textArea7);
					show.setVisible(true);
				} else {
					TipBlockEmpty block = new TipBlockEmpty("仓库没有空余资源");
					tippane.add(block);
					block.show();
					block = null;
				}
			}
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {

		}

		public void mousePressed(MouseEvent arg0) {
			if(arg0.getSource()==button_confirm){
				button_confirm.whenPressed();
			}else if (arg0.getSource()==button_cancel) {
				button_cancel.whenPressed();
			}else if (arg0.getSource()==button_exit) {
				button_exit.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent arg0) {
			if(arg0.getSource()==button_confirm){
				button_confirm.setMyColor();
			}else if (arg0.getSource()==button_cancel) {
				button_cancel.setMyColor();
			}else if (arg0.getSource()==button_exit) {
				button_exit.setMyColor();
			}
		}

	}

	private class Item implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {

				if (e.getSource() == areaBox) {
					String a = (String) areaBox.getSelectedItem();
					AdjustRepoBLService adjust = new RepoController();
					String[] rowList;
					if (a.equals("航运�?")) {
						rowList = adjust.getAllRow(orgID, Area.AIR);
					} else if (a.equals("铁运�?")) {
						rowList = adjust.getAllRow(orgID, Area.TRAIN);
					} else if (a.equals("汽运�?")) {
						rowList = adjust.getAllRow(orgID, Area.CAR);
					} else {
						rowList = adjust.getAllRow(orgID, Area.FLEXIBLE);
					}
					rowBox.removeAllItems();
					for (String s : rowList)
						rowBox.addItem(s);

				} else if (e.getSource() == idbox) {
					init();
				}
				updateUI();
			}

		}
	}
}
