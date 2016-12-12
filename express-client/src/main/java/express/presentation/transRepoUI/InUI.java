package express.presentation.transRepoUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.documentBL.CheckOrder;
import express.businessLogic.documentBL.InDoc;
import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.businesslogicService.transcenterRepoBLService.InDocblService;
import express.po.Area;
import express.po.RepoPosition;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.InDocVO;

public class InUI extends JPanel {

	// private JButton button_afterin;
	// private JButton button_return;

	private JPanel tippane;
	private MyOtherBlueLabel button_confirm;
	private MyOtherGreenLabel button_cancel;
	private MyOtherOrangeLabel button_exit;
	private MainUIService m;
	private JTextField textArea1, textArea6, textArea7, datetf;
	// private String number, date, arrival, district, row, shelf, position;
	private DateChooser datechooser;
	private JComboBox<String> combobox, areaBox, rowBox;
	private String orgID = IDKeeper.getOrgID();

	public InUI(MainUIService main) {

		int textlength = 200;
		int textwidth = 30;

		int labellength = 100;
		int labelwidth = 30;

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("隶书", Font.PLAIN, 20);

		setLayout(null);
		this.m = main;

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		// 快递编号
		textArea1 = new JTextField();
		textArea1.setBounds(300, 110, textlength, textwidth);
		textArea1.setFont(f);
		textArea1.setText("");
		// textArea1.setLineWrap(true); // 激活自动换行功能
		// textArea1.setWrapStyleWord(true);// 激活断行不断字功能
		this.add(textArea1);

		datetf = new JTextField();
		datetf.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(300, 180, textlength, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(510, 175, 40, 40);
		this.add(datechooser);

		String[] combo = { "北京", "南京", "上海", "广州" };
		combobox = new JComboBox<String>(combo);
		combobox.setBounds(300, 250, textlength, textwidth);
		combobox.setBackground(Color.WHITE);
		combobox.setFont(font);
		this.add(combobox);

		String area[] = { "航运区", "铁运区", "汽运区" };
		areaBox = new JComboBox<String>(area);
		areaBox.setBounds(300, 320, textlength, textwidth);
		areaBox.setFont(f);
		areaBox.setBackground(Color.WHITE);
		// textArea4.setBackground(Color.BLUE);
		// textArea4.setLineWrap(true);
		// textArea4.setWrapStyleWord(true);
		this.add(areaBox);

		Item i = new Item();
		areaBox.addItemListener(i);

		AdjustRepoBLService adjust = new RepoController();
		String[] rowList = adjust.getRow(orgID, Area.AIR);
		rowBox = new JComboBox<String>(rowList);
		rowBox.setBounds(300, 390, textlength, textwidth);
		rowBox.setFont(f);
		rowBox.setBackground(Color.WHITE);
		// textArea5.setBackground(Color.BLUE);
		// textArea5.setLineWrap(true);
		// textArea5.setWrapStyleWord(true);
		this.add(rowBox);

		textArea6 = new JTextField();
		textArea6.setBounds(300, 460, textlength, textwidth);
		textArea6.setFont(f);
		textArea6.setText("");
		textArea6.setEditable(false);
		textArea6.setBackground(Color.WHITE);
		textArea6.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// textArea6.setBackground(Color.BLUE);
		// textArea6.setLineWrap(true);
		// textArea6.setWrapStyleWord(true);
		this.add(textArea6);

		textArea7 = new JTextField("位号");
		textArea7.setBounds(300, 530, textlength, textwidth);
		textArea7.setFont(f);
		textArea7.setText("");
		textArea7.setEditable(false);
		textArea7.setBackground(Color.WHITE);
		textArea7.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// textArea7.setBackground(Color.BLUE);
		// textArea7.setLineWrap(true);
		// textArea7.setWrapStyleWord(true);
		this.add(textArea7);

		JLabel title = new JLabel("入 库 单");
		title.setBounds(300, 50, labellength, labelwidth);
		title.setFont(new Font("楷体", Font.BOLD, 20));
		this.add(title);

		JLabel label1 = new JLabel("快递编号");
		label1.setBounds(200, 110, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("入库日期");
		label2.setBounds(200, 180, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("目的地");
		label3.setBounds(200, 250, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label4 = new JLabel("区号");
		label4.setBounds(200, 320, labellength, labelwidth);
		label4.setFont(font);
		this.add(label4);

		JLabel label5 = new JLabel("排号");
		label5.setBounds(200, 390, labellength, labelwidth);
		label5.setFont(font);
		this.add(label5);

		JLabel label6 = new JLabel("架号");
		label6.setBounds(200, 460, labellength, labelwidth);
		label6.setFont(font);
		this.add(label6);

		JLabel label7 = new JLabel("位号");
		label7.setBounds(200, 530, labellength, labelwidth);
		label7.setFont(font);
		this.add(label7);

		JListener listener = new JListener();

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(200, 600, 130, 40);
		button_confirm.addMouseListener(listener);

		this.add(button_confirm);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(370, 600, 130, 40);
		button_cancel.addMouseListener(listener);
		
		this.add(button_cancel);

		button_exit = new MyOtherOrangeLabel("返回菜单");
		button_exit.setBounds(550, 600, 130, 40);
		button_exit.addMouseListener(listener);

		this.add(button_exit);

		// areaBox.addMouseListener(listener);
		textArea6.addMouseListener(listener);
		textArea7.addMouseListener(listener);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

	}

	private boolean checkFilled() {
		String id = textArea1.getText();
		String shelf = textArea6.getText();
		String pos = textArea7.getText();

		boolean fill = true;
		Color yellow = new Color(255, 215, 0);

		if (id.equals("")) {
			textArea1.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (shelf.equals("")) {
			textArea6.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (pos.equals("")) {
			textArea7.setBorder(BorderFactory.createLineBorder(yellow, 2));
			fill = false;
		}
		if (!fill) {
			TipBlockEmpty block = new TipBlockEmpty("信息未填写完整");
			tippane.add(block);
			block.show();
			block = null;
		}
		return fill;
	}

	private boolean checkOrderID() {
		CheckOrder check = new CheckOrder();
		String id = textArea1.getText();
		boolean correct = check.isOrderIDAvailable(id);
		boolean repeat = true;
		if (!correct) {
			textArea1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

			TipBlockError block = new TipBlockError("订单条形码号错误");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			AdjustRepoBLService adjust = new RepoController();
			repeat = adjust.checkIn(orgID, id);

			if (!repeat) {
				TipBlockError block = new TipBlockError("该订单已入库");
				tippane.add(block);
				block.show();
				block = null;
			}
		}
		return correct && repeat;
	}

	private boolean checkDate() {
		String date = datetf.getText();
		String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (d.compareTo(date) <= 0)
			return true;
		else {
			datetf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

			TipBlockError block = new TipBlockError("日期不能晚于今天");
			tippane.add(block);
			block.show();
			block = null;
			return false;
		}
	}

	private Area getArea() {
		String area = (String) areaBox.getSelectedItem();
		Area a;
		switch (area) {
		case "航运区":
			a = Area.AIR;
			break;
		case "铁运区":
			a = Area.TRAIN;
			break;
		case "汽运区":
			a = Area.CAR;
			break;
		default:
			a = Area.FLEXIBLE;
			break;
		}
		return a;
	}

	private void addInDoc() {

		String id = textArea1.getText();
		String city = (String) combobox.getSelectedItem();
		String area = (String) areaBox.getSelectedItem();
		String row = (String) rowBox.getSelectedItem();
		String shelf = textArea6.getText();
		String pos = textArea7.getText();
		String date = datetf.getText();
		Area a = getArea();

		row = row.substring(1, row.length() - 1);
		int r = Integer.parseInt(row);
		int s = Integer.parseInt(shelf);
		int p = Integer.parseInt(pos);
		RepoPosition position = new RepoPosition(id, a, r, s, p, true);
		InDocVO vo = new InDocVO(id, date, city, position, orgID);

		AdjustRepoBLService adjust = new RepoController();

		adjust.checkRepoBlockUsed(orgID, position);

		if (!adjust.checkRepoBlockUsed(orgID, position)) {
			adjust.setRepoBlock(orgID, position);

			InDocblService inDoc = new InDoc();
			boolean succ = inDoc.addInDoc(vo);
			inDoc.endInDoc();
			if (succ) {
				TipBlock block = new TipBlock("生成成功");
				tippane.add(block);
				block.show();
				block = null;

			} else {
				TipBlockError block = new TipBlockError("生成失败");
				tippane.add(block);
				block.show();
				block = null;
			}

			if (adjust.alarm(IDKeeper.getOrgID(), position)) {
				TipBlockEmpty block = new TipBlockEmpty("库存已达到90%");
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
				// 点击确认
				if (checkFilled()) {
					if (checkOrderID()) {
						if (checkDate()) {
							addInDoc();
							textArea1.setBorder(BorderFactory.createLineBorder(
									Color.GRAY, 1));
							textArea6.setBorder(BorderFactory.createLineBorder(
									Color.GRAY, 1));
							textArea7.setBorder(BorderFactory.createLineBorder(
									Color.GRAY, 1));
						}
					}
				}
			} else if (e.getSource() == button_cancel) {
				textArea1.setText("");
				datetf.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				textArea6.setText("");
				textArea7.setText("");
				textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY,
						1));
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

					ShowRepoUI show = new ShowRepoUI(orgID, getArea(), r,
							textArea6, textArea7);
					show.setVisible(true);
				} else {
					TipBlockError block = new TipBlockError("库存没有空余资源");
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
				String a = (String) areaBox.getSelectedItem();
				AdjustRepoBLService adjust = new RepoController();
				// TODO Auto-generated method stub
				// adjust.getRow(IDKeeper.getOrgID(), Area.AIR);
				String[] rowList;
				if (a.equals("航运区")) {
					rowList = adjust.getRow(orgID, Area.AIR);
				} else if (a.equals("铁运区")) {
					rowList = adjust.getRow(orgID, Area.TRAIN);
				} else {
					rowList = adjust.getRow(orgID, Area.CAR);
				}
				rowBox.removeAllItems();
				for (String s : rowList)
					rowBox.addItem(s);
				updateUI();
			}

		}

	}

}
