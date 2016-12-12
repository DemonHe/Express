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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.statisticBL.OperateStatistic;
import express.businesslogicService.financialBLService.OperateFinanceBLService;
import express.po.PaymentItem;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.OperateFormVO;
import express.vo.ReceiveDocVO;

public class FinanceCreateOperateUI extends JPanel {

	private MainUIService m;
	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel back;
	private MyOtherGreenLabel count;
	private MyOtherRedLabel exit;
	private JTable operatetable;
	private JTextField startdatetf, enddatetf;
	private DateChooser datechoosers, datechoosere;
	private String beginDate = "", endDate = "";
	private String[] tableheader = { "收款日期", "收款金额", "付款日期", "付款金额", "付款条目" };
	private DefaultTableModel tableModel;
	private String[][] data = null;
	
	private String time;

	public FinanceCreateOperateUI(MainUIService main) {
		setLayout(null);
		m = main;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		int textlength = 150;
		int textwidth = 30;
		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("仿宋", Font.PLAIN, 22);

		JLabel begindatel = new JLabel("开始日期");
		begindatel.setFont(font);
		begindatel.setBounds(100, 50, 100, 40);
		this.add(begindatel);
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		time=format.format(date);
		
		startdatetf = new JTextField();
		startdatetf.setFont(f);
		startdatetf.setText(time);
		startdatetf.setBounds(210, 50, textlength, textwidth);
		startdatetf.setBackground(Color.WHITE);
		startdatetf.setEditable(false);
		this.add(startdatetf);

		datechoosers = new DateChooser("yyyy-MM-dd", startdatetf);
		datechoosers.setBounds(370, 45, 40, 40);
		this.add(datechoosers);

		JLabel enddatel = new JLabel("结束日期");
		enddatel.setFont(font);
		enddatel.setBounds(430, 50, 100, 40);
		this.add(enddatel);

		enddatetf = new JTextField();
		enddatetf.setFont(f);
		enddatetf.setText(time);
		enddatetf.setBounds(540, 50, textlength, textwidth);
		enddatetf.setBackground(Color.WHITE);
		enddatetf.setEditable(false);
		this.add(enddatetf);

		datechoosere = new DateChooser("yyyy-MM-dd", enddatetf);
		datechoosere.setBounds(700, 45, 40, 40);
		this.add(datechoosere);

		tableModel = new DefaultTableModel(data, tableheader);
		operatetable = new JTable(tableModel);
		operatetable.getTableHeader().setFont(f1);
		operatetable.getTableHeader().setBorder(
				BorderFactory.createMatteBorder(1, 1, 2, 1, Color.LIGHT_GRAY));
		operatetable.getTableHeader().setBackground(Color.WHITE);
		operatetable.getTableHeader().setPreferredSize(new Dimension(650, 35));
		// 设置居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		operatetable.getTableHeader().setDefaultRenderer(r);
		operatetable.setDefaultRenderer(Object.class, r);
		operatetable.setRowHeight(35);
		operatetable.setFont(f);
		operatetable.setBounds(100, 100, 650, 450);
		operatetable.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				1));
		// logtable.setBorder(BorderFactory.createEtchedBorder());
		// this.add(logtable);

		JScrollPane scrollPane = new JScrollPane(operatetable);
		scrollPane.setFont(font);
		// scrollPane.setViewportView(logtable);
		scrollPane.setBounds(100, 100, 650, 450);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY, 0));
		this.add(scrollPane);

		Listener listen = new Listener();

		count = new MyOtherGreenLabel("查看经营状态");
		count.setBounds(100, 570, 200, 40);
		count.setVisible(true);
		count.addMouseListener(listen);
		this.add(count);

		ok = new MyOtherBlueLabel("添加表格");
		ok.setBounds(100, 570, 170, 40);
		ok.setVisible(false);
		ok.addMouseListener(listen);
		this.add(ok);

		exit = new MyOtherRedLabel("取消");
		exit.setBounds(340, 570, 170, 40);
		exit.setVisible(false);
		exit.addMouseListener(listen);
		this.add(exit);
		
		back = new MyOtherOrangeLabel("返回菜单");
		back.setBounds(580, 570, 170, 40);
		back.addMouseListener(listen);
		this.add(back);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

	}

	private void getdocs() {
		String[][] docs = null;

		if (endDate.equals("")) {
			Date d = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(d);
			endDate = time;
		}
		if (beginDate.equals("")) {
			beginDate = " ";
		}

		OperateFinanceBLService oper = new OperateStatistic();

		if (!oper.checkDateAvailable(beginDate, endDate)) {
			TipBlockError block = new TipBlockError("起始日期晚于终止日期");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			OperateFormVO vo = oper.createOperateForm(beginDate, endDate);

			beginDate = vo.getStartDate();
			endDate = vo.getEndDate();
			ArrayList<ReceiveDocVO> receList = vo.getReceiveDoc();
			ArrayList<PaymentItem> payList = vo.getPaymentDoc();
			int lenRece = 0;
			int lenPay = 0;
			int max = 0;

			if (receList != null) {
				lenRece = receList.size();
			}
			if (payList != null) {
				lenPay = payList.size();
			}

			max = Math.max(lenRece, lenPay);

			docs = new String[max][5];
			for (int i = 0; i < lenRece; i++) {
				ReceiveDocVO rece = receList.get(i);
				docs[i][0] = rece.getReceiveDate();
				docs[i][1] = rece.getReceivePrice() + "";
			}

			for (int i = 0; i < lenPay; i++) {
				PaymentItem pay = payList.get(i);
				docs[i][2] = pay.getDate();
				docs[i][3] = pay.getSum() + "";
				docs[i][4] = pay.getEntry();
			}

			tableModel.setDataVector(docs, tableheader);
		}

	}

	private void addOperateForm() {

		OperateFinanceBLService oper = new OperateStatistic();
		boolean succ = oper.addOperateForm(beginDate, endDate);
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
		count.setVisible(true);
		ok.setVisible(false);
		exit.setVisible(false);
		repaint();
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				startdatetf.setText(time);
				enddatetf.setText(time);
				tableModel.setRowCount(0);
				repaint();
				count.setVisible(true);
				ok.setVisible(false);
				exit.setVisible(false);
			} else if (e.getSource() == count) {
				count.setVisible(false);
				ok.setVisible(true);
				exit.setVisible(true);
				beginDate = startdatetf.getText();
				endDate = enddatetf.getText();
				getdocs();
				startdatetf.setText(beginDate);
				enddatetf.setText(endDate);
			} else if (e.getSource() == ok) {
				addOperateForm();
			}else if(e.getSource() == back){
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
			} else if (e.getSource() == count) {
				count.whenPressed();
			} else if (e.getSource() == ok) {
				ok.whenPressed();
			}else if(e.getSource() == back){
				back.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.setMyColor();
			} else if (e.getSource() == count) {
				count.setMyColor();
			} else if (e.getSource() == ok) {
				ok.setMyColor();
			}else if(e.getSource() == back){
				back.setMyColor();
			}
		}
	}
}
