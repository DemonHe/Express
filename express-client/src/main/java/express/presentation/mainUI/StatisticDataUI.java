package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import express.businessLogic.statisticBL.OperateStatistic;
import express.businessLogic.statisticBL.ProfitStatistic;
import express.businesslogicService.financialBLService.OperateManagerBLService;
import express.businesslogicService.financialBLService.ProfitManagerBLService;
import express.vo.OperateFormVO;

public class StatisticDataUI extends JPanel {

	private JButton exit;
	private MainUIService m;
	private JTable table;
	private JScrollPane scrollPane;
	private String str;
	private String[] tableheader;
	private String[][] tabledata;

	public StatisticDataUI(MainUIService main, int index, String s, String date) {
		setLayout(null);
		m = main;
		str = s;
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);

		tableheader = getheader(s);
		tabledata = getdata(s, index, date);
		table = new JTable(tabledata, tableheader);
		table.setRowHeight(40);
		table.setFont(f);
		table.setBounds(100, 50, 650, 500);

		if (s.equals("经营状况表")) {
			TableColumnModel columns = table.getColumnModel();
			TableColumn column1 = columns.getColumn(0);
			column1.setPreferredWidth(120);
			TableColumn column2 = columns.getColumn(1);
			column2.setPreferredWidth(120);
			TableColumn column3 = columns.getColumn(2);
			column3.setPreferredWidth(300);
			TableColumn column4 = columns.getColumn(3);
			column4.setPreferredWidth(300);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}

		scrollPane = new JScrollPane(table);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 50, 650, 500);
		this.add(scrollPane);

		exit = new JButton("取消");
		exit.setBounds(360, 590, 110, 40);
		exit.setFont(new Font("隶书", Font.PLAIN, 20));
		this.add(exit);

		Listener listener = new Listener();

		exit.addMouseListener(listener);
	}

	private String[] getheader(String s) {
		String[] header = null;
		if (s.equals("经营状况表")) {
			String[] temp = { "开始日期", "结束日期", "收款单", "付款单" };
			header = temp;
		} else if (s.equals("成本收益表")) {
			String[] temp = { "时间", "收入", "支出", "利润" };
			header = temp;
		}
		return header;
	}

	private String[][] getdata(String s, int index, String date) {
		String[][] data = null;
		if (s.equals("经营状况表")) {
			OperateManagerBLService oper = new OperateStatistic();
			OperateFormVO op = oper.getOperateForm(index);
			int size = op.getReceiveDoc().size();
			data = new String[size][4];
			data[0][0] = op.getStartDate();
			data[0][1] = op.getEndDate();
			for (int i = 1; i < size; i++) {
				data[i][0] = " ";
				data[i][1] = " ";
				data[i][2] = op.getReceiveDoc().get(i).getReceiveDate()
						+ "    " + op.getReceiveDoc().get(i).getReceivePrice();
				data[i][3] = " ";
			}
		} else if (s.equals("成本收益表")) {
			ProfitManagerBLService profits = new ProfitStatistic();
			String[][] temp = { { profits.getProfitForm(date).getTitle(),
					profits.getProfitForm(date).getIncome() + "",
					profits.getProfitForm(date).getOutCome() + "",
					profits.getProfitForm(date).getProfit() + "" } };
			data = temp;
		}
		return data;
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
//				m.jumpToViewStatisticUI(str);
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
