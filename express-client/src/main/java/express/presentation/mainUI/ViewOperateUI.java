package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.statisticBL.OperateStatistic;
import express.businesslogicService.financialBLService.OperateFinanceBLService;
import express.businesslogicService.financialBLService.OperateManagerBLService;
import express.po.PaymentItem;
import express.vo.OperateFormVO;
import express.vo.ReceiveDocVO;

public class ViewOperateUI extends JPanel {

	MainUIService m;
	private JPanel tippane;
	private JTabbedPane tabpane;
	private String[][] docs = null;
	private MyOtherBlueLabel excel;
	private MyOtherOrangeLabel exit;
	private MyOtherRedLabel delete;

	private int tablewidth = 720;
	private int tableheight = 500;
	private Font font = new Font("楷体", Font.PLAIN, 20);
	private Font f = new Font("仿宋", Font.PLAIN, 18);
	private Font f1 = new Font("仿宋", Font.PLAIN, 22);

	public ViewOperateUI(MainUIService main) {
		m = main;
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		Font font2 = new Font("楷体", Font.BOLD, 18);

		JLabel title = new JLabel("经 营 状 态 表", JLabel.CENTER);
		title.setBounds(50, 50, tablewidth, 30);
		title.setFont(font2);
		this.add(title);

		tabpane = new JTabbedPane(JTabbedPane.TOP);
		initPanelList();
		tabpane.setFont(f);
		tabpane.setOpaque(false);
		tabpane.setBounds(50, 80, tablewidth, tableheight);
		tabpane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
		this.add(tabpane);

		Listener listen = new Listener();

		excel = new MyOtherBlueLabel("导出到Excel");
		excel.setBounds(50, 620, 180, 40);
		excel.addMouseListener(listen);
		this.add(excel);

		delete = new MyOtherRedLabel("删除表格");
		delete.setBounds(320, 620, 180, 40);
		delete.addMouseListener(listen);
		this.add(delete);

		exit = new MyOtherOrangeLabel("返回菜单");
		exit.setBounds(590, 620, 180, 40);
		exit.addMouseListener(listen);
		this.add(exit);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

	}

	private void initPanelList() {
		OperateManagerBLService operate = new OperateStatistic();
		ArrayList<String> list = operate.getOperateFormListTitle();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				String title = list.get(i);
				initPanel(title, i);
			}
		}
	}

	private void initPanel(String title, int index) {

		getdocs(index);

		String[] head = { "收款日期", "收款金额", "付款日期", "付款金额", "付款条目" };

		DefaultTableModel tableModel = new DefaultTableModel(docs, head);
		JTable operatetable = new JTable(tableModel);

		operatetable.getTableHeader().setFont(f1);
		operatetable.getTableHeader().setBorder(
				BorderFactory.createMatteBorder(1, 1, 2, 1, Color.LIGHT_GRAY));
		operatetable.getTableHeader().setBackground(Color.WHITE);
		operatetable.getTableHeader().setPreferredSize(
				new Dimension(tablewidth - 10, 35));
		// 设置居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		operatetable.getTableHeader().setDefaultRenderer(r);
		operatetable.setDefaultRenderer(Object.class, r);

		operatetable.setRowHeight(35);
		operatetable.setFont(f);
		operatetable.setBounds(2, 0, tablewidth - 7, tableheight - 10);
		operatetable.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				1));

		JScrollPane scrollPane = new JScrollPane(operatetable);
		scrollPane.setFont(font);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY, 0));
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setBounds(2, 0, tablewidth - 5, tableheight - 10);
		tabpane.add(title, scrollPane);
	}

	private void getdocs(int index) {

		OperateManagerBLService operate = new OperateStatistic();

		OperateFormVO vo = operate.getOperateForm(index);
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
	}

	private void exportExcel(int index) {
		OperateManagerBLService operate = new OperateStatistic();

		OperateFormVO vo = operate.getOperateForm(index);

		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showSaveDialog(new JLabel());
		jfc.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}

			public String getDescription() {
				return ".xls";
			}
		});

		File file = jfc.getSelectedFile();

		String path = file.getAbsolutePath();
		boolean succ = operate.exportExcel(vo, path);
		if (succ) {
			TipBlock block = new TipBlock("导出成功");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			TipBlockError block = new TipBlockError("导出失败");
			tippane.add(block);
			block.show();
			block = null;

		}
	}

	private void deleteForm(int index) {
		OperateManagerBLService operate = new OperateStatistic();

		boolean succ = operate.removeOperateForm(index);
		if (succ) {
			TipBlock block = new TipBlock("删除成功");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			TipBlockError block = new TipBlockError("删除失败");
			tippane.add(block);
			block.show();
			block = null;

		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == excel) {
				int index = tabpane.getSelectedIndex();
				if (index < 0) {
					TipBlockEmpty block = new TipBlockEmpty("未选择表格");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					exportExcel(index);
				}
			} else if (e.getSource() == exit) {
				if (IDKeeper.getIsManager()) {
					m.jumpTomanagerMenuUI(IDKeeper.getID());
				} else {
					m.jumpToFinanceMenuUI(IDKeeper.getID(), IDKeeper.getHigh());
				}
			} else if (e.getSource() == delete) {
				int index = tabpane.getSelectedIndex();
				if (index < 0) {
					TipBlockEmpty block = new TipBlockEmpty("未选择表格");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					tabpane.remove(index);
					deleteForm(index);
				}
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
			if (e.getSource() == exit) {
				exit.whenPressed();
			} else if (e.getSource() == excel) {
				excel.whenPressed();
			} else if (e.getSource() == delete) {
				delete.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == exit) {
				exit.setMyColor();
			} else if (e.getSource() == excel) {
				excel.setMyColor();
			} else if (e.getSource() == delete) {
				delete.setMyColor();
			}
		}
	}
}
