package express.presentation.transRepoUI;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.ScanRepoBLService;
import express.po.Area;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlockError;
import express.vo.RepoCacheVO;
import express.vo.RepoPositionVO;

public class ViewUI extends JPanel {

	private MainUIService m;
	private JPanel tippane;
	private MyOtherBlueLabel button_view;
	private MyOtherOrangeLabel button_return;
	private JPanel inventory, sum;
	private JLabel title, tip, title1, title4, title5, title6, title7;
	private JTextField datetf, datetf2;
	private DateChooser datechooser, datechooser2;
	private String orgID = IDKeeper.getOrgID();
	private String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

	public ViewUI(MainUIService main) {

		int textlength = 150;
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

		JLabel label1 = new JLabel("起始日期");
		label1.setBounds(100, 50, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		datetf = new JTextField();
		datetf.setText(time);
		datetf.setBounds(200, 50, textlength, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(360, 45, 40, 40);
		this.add(datechooser);

		JLabel label2 = new JLabel("终止日期");
		label2.setBounds(450, 50, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		datetf2 = new JTextField();
		datetf2.setText(time);
		datetf2.setBounds(550, 50, textlength, textwidth);
		datetf2.setFont(f);
		datetf2.setEditable(false);
		this.add(datetf2);

		datechooser2 = new DateChooser("yyyy-MM-dd", datetf2);
		datechooser2.setBounds(710, 45, 40, 40);
		this.add(datechooser2);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		sum = new JPanel();
		sum.setBounds(601, 95, 150, 470);
		sum.setOpaque(false);
		sum.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		sum.setLayout(null);
		this.add(sum);
		inventory = new JPanel();
		inventory.setLocation(100, 130);
		inventory.setPreferredSize(new Dimension(490, 425));
		inventory.setOpaque(false);
		inventory.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(inventory);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 130, 500, 435);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0,
				Color.GRAY));
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);

		button_view = new MyOtherBlueLabel("查看库存信息");

		button_view.setBounds(100, 600, 200, 40);
		this.add(button_view);

		button_return = new MyOtherOrangeLabel("返回菜单");

		button_return.setBounds(550, 600, 200, 40);
		this.add(button_return);

		Listener listener = new Listener();
		button_view.addMouseListener(listener);
		button_return.addMouseListener(listener);

		init();
	}

	private void view() {
		ScanRepoBLService view = new RepoController();
		ArrayList<RepoPositionVO> list = view.getRepoPositionList(orgID);

		sum.removeAll();
		inventory.removeAll();
		repaint();
		if (list == null) {
			addNone();
			return;
		}
		if (list.size() == 0) {
			addNone();
			return;
		}
		//
		title.setVisible(false);
		title1.setVisible(true);
		title4.setVisible(true);
		title5.setVisible(true);
		title6.setVisible(true);
		title7.setVisible(true);
		sum.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY));
		int index = 0;
		for (RepoPositionVO vo : list) {
			addLine(vo, index);
			index++;
		}
		int height = 40 * list.size();
		inventory.setPreferredSize(new Dimension(640, height));

		String startDate = datetf.getText();
		String endDate = datetf2.getText();
		RepoCacheVO cache = view.getRepoInfo(orgID, startDate, endDate);
		addSum(cache, startDate, endDate);
	}

	private void addSum(RepoCacheVO cache, String startDate, String endDate) {
		JTextArea inSum, outSum, allSum;
		String text;
		Font font = new Font("楷体", Font.PLAIN, 20);

		String date = startDate.substring(5) + "~" + endDate.substring(5);
		inSum = new JTextArea();
		inSum.setBounds(10, 20, 130, 150);
		inSum.setFont(font);
		text = date + "\n" + "\n" + "入库总量：" + "\n" + "\n"
				+ cache.getImportSum() + " 件";
		inSum.setText(text);
		inSum.setEditable(false);
		inSum.setOpaque(false);
		inSum.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));
		sum.add(inSum);

		outSum = new JTextArea();
		outSum.setBounds(10, 190, 130, 150);
		outSum.setFont(font);
		text = date + "\n" + "\n" + "出库总量：" + "\n" + "\n"
				+ cache.getExportSum() + " 件";
		outSum.setText(text);
		outSum.setEditable(false);
		outSum.setOpaque(false);
		outSum.setBorder(BorderFactory
				.createMatteBorder(0, 0, 3, 0, Color.GRAY));
		sum.add(outSum);

		allSum = new JTextArea();
		allSum.setBounds(10, 360, 130, 100);
		allSum.setFont(font);
		text = "当前库存总量：" + "\n" + "\n" + cache.getSum() + " 件";
		allSum.setText(text);
		allSum.setEditable(false);
		allSum.setOpaque(false);
		allSum.setBorder(BorderFactory
				.createMatteBorder(0, 0, 3, 0, Color.GRAY));
		sum.add(allSum);
	}

	private void addLine(RepoPositionVO vo, int i) {

		int y = i * 40;
		int height = 40;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JLabel title1 = new JLabel(vo.getOrderID(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(0, y, 120, height);
		title1.setOpaque(false);
		title1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title1);

		Area a = vo.getblock();
		JLabel title4 = new JLabel(getArea(a), JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(120, y, 110, height);
		title4.setOpaque(false);
		title4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title4);

		JLabel title5 = new JLabel(vo.getrow() + "", JLabel.CENTER);
		title5.setFont(f);
		title5.setBounds(230, y, 90, height);
		title5.setOpaque(false);
		title5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title5);

		JLabel title6 = new JLabel(vo.getshelf() + "", JLabel.CENTER);
		title6.setFont(f);
		title6.setBounds(320, y, 90, height);
		title6.setOpaque(false);
		title6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title6);

		JLabel title7 = new JLabel(vo.getposition() + "", JLabel.CENTER);
		title7.setFont(f);
		title7.setBounds(410, y, 90, height);
		title7.setOpaque(false);
		title7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title7);
	}

	private void addTitle() {
		Font f = new Font("仿宋", Font.PLAIN, 18);

		title1 = new JLabel("快递编号", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(100, 95, 120, 35);
		title1.setOpaque(true);
		title1.setBackground(new Color(250, 235, 215));
		title1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title1);

		/*
		 * JLabel title2 = new JLabel("入库日期", JLabel.CENTER); title2.setFont(f);
		 * title2.setBounds(200, 60, 100, 35); title2.setOpaque(true);
		 * title2.setBackground(new Color(250, 235, 215));
		 * title2.setBorder(BorderFactory .createMatteBorder(3, 0, 2, 1,
		 * Color.GRAY)); this.add(title2);
		 * 
		 * JLabel title3 = new JLabel("目的地", JLabel.CENTER); title3.setFont(f);
		 * title3.setBounds(300, 60, 90, 35); title3.setOpaque(true);
		 * title3.setBackground(new Color(250, 235, 215));
		 * title3.setBorder(BorderFactory .createMatteBorder(3, 0, 2, 1,
		 * Color.GRAY)); this.add(title3);
		 */

		title4 = new JLabel("区号", JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(220, 95, 110, 35);
		title4.setOpaque(true);
		title4.setBackground(new Color(250, 235, 215));
		title4.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title4);

		title5 = new JLabel("排号", JLabel.CENTER);
		title5.setFont(f);
		title5.setBounds(330, 95, 90, 35);
		title5.setOpaque(true);
		title5.setBackground(new Color(250, 235, 215));
		title5.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title5);

		title6 = new JLabel("架号", JLabel.CENTER);
		title6.setFont(f);
		title6.setBounds(420, 95, 90, 35);
		title6.setOpaque(true);
		title6.setBackground(new Color(250, 235, 215));
		title6.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title6);

		title7 = new JLabel("位号", JLabel.CENTER);
		title7.setFont(f);
		title7.setBounds(510, 95, 90, 35);
		title7.setOpaque(true);
		title7.setBackground(new Color(250, 235, 215));
		title7.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title7);
	}

	private void init() {
		title = new JLabel();
		title.setBounds(100, 95, 500, 35);
		title.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
		this.add(title);

		tip = new JLabel("未 查 看 库 存", JLabel.CENTER);
		tip.setBounds(0, 80, 490, 200);
		tip.setFont(new Font("仿宋", Font.BOLD, 45));
		tip.setOpaque(false);
		tip.setForeground(Color.LIGHT_GRAY);
		inventory.add(tip);

		addTitle();
		title1.setVisible(false);
		title4.setVisible(false);
		title5.setVisible(false);
		title6.setVisible(false);
		title7.setVisible(false);
	}

	private void addNone() {
		title.setVisible(true);
		inventory.add(tip);
		tip.setVisible(true);
		tip.setText("没 有 入 库 信 息");
		title1.setVisible(false);
		title4.setVisible(false);
		title5.setVisible(false);
		title6.setVisible(false);
		title7.setVisible(false);
		//updateUI();
	}

	private String getArea(Area a) {
		switch (a) {
		case AIR:
			return "航运区";
		case TRAIN:
			return "铁运区";
		case CAR:
			return "汽运区";
		default:
			return "机动区";
		}
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource() == button_view) {
				String d1 = datetf.getText();
				String d2 = datetf2.getText();
				if (d1.compareTo(d2) > 0) {
					sum.removeAll();
					inventory.removeAll();
					sum.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
					addNone();
					TipBlockError block = new TipBlockError("起始日期应早于终止日期");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					view();
				}
			} else if (e.getSource() == button_return) {
				m.jumpTotranscenterRepoMenuUI(IDKeeper.getID());
			}
			updateUI();
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
			if (e.getSource() == button_return) {
				button_return.whenPressed();
			} else if (e.getSource() == button_view) {
				button_view.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == button_return) {
				button_return.setMyColor();
			} else if (e.getSource() == button_view) {
				button_view.setMyColor();
			}
		}

	}

}
