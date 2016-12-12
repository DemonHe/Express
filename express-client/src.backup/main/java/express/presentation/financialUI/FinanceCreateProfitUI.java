package express.presentation.financialUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.statisticBL.ProfitStatistic;
import express.businesslogicService.financialBLService.ProfitFinanceBLService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.ProfitFormVO;

public class FinanceCreateProfitUI extends JPanel {

	private MainUIService m;
	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherRedLabel exit;
	private MyOtherGreenLabel count;
	private MyOtherOrangeLabel back;
	private JTextField time, income, outcome, profit;

	/*
	 * private JLabel success; private JTable profittable; private JScrollPane
	 * scrollPane; private String[] tableheader = { "统计时间", "总收�?", "总支�?", "总利�?"
	 * }; private String[][] data = { { " ", " ", " ", " " } }; private
	 * DefaultTableModel tableModel;
	 */

	public FinanceCreateProfitUI(MainUIService main) {
		setLayout(null);
		m = main;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JLabel timeLabel = new JLabel("统计时间");
		timeLabel.setBounds(185, 90, 100, 30);
		timeLabel.setFont(font);
		this.add(timeLabel);

		JLabel incomeLabel = new JLabel("总收�?");
		incomeLabel.setBounds(200, 190, 100, 30);
		incomeLabel.setFont(font);
		this.add(incomeLabel);

		JLabel outcomeLabel = new JLabel("总支�?");
		outcomeLabel.setBounds(200, 290, 100, 30);
		outcomeLabel.setFont(font);
		this.add(outcomeLabel);

		JLabel profitLabel = new JLabel("总利�?");
		profitLabel.setBounds(200, 390, 100, 30);
		profitLabel.setFont(font);
		this.add(profitLabel);

		time = new JTextField();
		time.setBounds(300, 90, 200, 30);
		time.setEditable(false);
		time.setFont(f);
		time.setBackground(Color.WHITE);
		this.add(time);

		income = new JTextField();
		income.setBounds(300, 190, 200, 30);
		income.setEditable(false);
		income.setFont(f);
		income.setBackground(Color.WHITE);
		this.add(income);

		outcome = new JTextField();
		outcome.setBounds(300, 290, 200, 30);
		outcome.setEditable(false);
		outcome.setFont(f);
		outcome.setBackground(Color.WHITE);
		this.add(outcome);

		profit = new JTextField();
		profit.setBounds(300, 390, 200, 30);
		profit.setEditable(false);
		profit.setFont(f);
		profit.setBackground(Color.WHITE);
		this.add(profit);
		//
		/*
		 * tableModel = new DefaultTableModel(data, tableheader); profittable =
		 * new JTable(tableModel); profittable.getTableHeader().setFont(f);
		 * profittable.setRowHeight(40);
		 * 
		 * TableColumnModel columns = profittable.getColumnModel(); TableColumn
		 * column1 = columns.getColumn(0); column1.setPreferredWidth(200);
		 * TableColumn column2 = columns.getColumn(1);
		 * column2.setPreferredWidth(160); TableColumn column3 =
		 * columns.getColumn(2); column2.setPreferredWidth(160); TableColumn
		 * column4 = columns.getColumn(3); column2.setPreferredWidth(160); //
		 * profittable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 * profittable.setFont(f); profittable.setBounds(100, 100, 650, 300); //
		 * logtable.setBorder(BorderFactory.createEtchedBorder()); //
		 * this.add(logtable);
		 * 
		 * scrollPane = new JScrollPane(profittable); scrollPane.setFont(font);
		 * // scrollPane.setViewportView(logtable); scrollPane.setBounds(100,
		 * 100, 650, 300); this.add(scrollPane);
		 */

		Listener listen = new Listener();

		count = new MyOtherGreenLabel("统计成本收益");
		count.setBounds(200, 480, 300, 40);
		this.add(count);

		ok = new MyOtherBlueLabel("添加表格");
		ok.setBounds(200, 480, 120, 40);
		ok.setVisible(false);
		this.add(ok);

		exit = new MyOtherRedLabel("取消");
		exit.setBounds(380, 480, 120, 40);
		exit.setVisible(false);
		this.add(exit);
		
		back = new MyOtherOrangeLabel("返回菜单");
		back.setBounds(550, 480, 120, 40);
		back.setVisible(true);
		this.add(back);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		count.addMouseListener(listen);
		ok.addMouseListener(listen);
		exit.addMouseListener(listen);
		back.addMouseListener(listen);
		time.addMouseListener(listen);
		income.addMouseListener(listen);
		outcome.addMouseListener(listen);
		profit.addMouseListener(listen);
	}

	private void getdata() {
		ProfitFinanceBLService pf = new ProfitStatistic();
		ProfitFormVO vo = pf.createProfitForm();

		time.setText(vo.getTitle());
		income.setText(vo.getIncome() + "");
		outcome.setText(vo.getOutCome() + "");
		profit.setText(vo.getProfit() + "");
	}

	private void addData() {
		ProfitFinanceBLService pf = new ProfitStatistic();
		String t = time.getText();
		String i = income.getText();
		String o = outcome.getText();
		String p = profit.getText();
		try {
			double in = Double.parseDouble(i);
			double out = Double.parseDouble(o);
			double pro = Double.parseDouble(p);
			ProfitFormVO vo = new ProfitFormVO(t, in, out, pro);
			boolean succ = pf.addProfitForm(vo);
			// Font font = new Font("楷体", Font.PLAIN, 21);
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
		} catch (Exception e) {

		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				time.setText("");
				income.setText("");
				outcome.setText("");
				profit.setText("");
				count.setVisible(true);
				ok.setVisible(false);
				exit.setVisible(false);
			} else if (e.getSource() == ok) {
				addData();
			} else if (e.getSource() == count || e.getSource() == time
					|| e.getSource() == income || e.getSource() == outcome
					|| e.getSource() == profit) {
				getdata();
				count.setVisible(false);
				ok.setVisible(true);
				exit.setVisible(true);
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
