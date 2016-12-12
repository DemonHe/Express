package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.IDKeeper;
import express.businessLogic.statisticBL.ProfitStatistic;
import express.businesslogicService.financialBLService.ProfitManagerBLService;
import express.vo.ProfitFormVO;

public class ViewProfitUI extends JPanel {

	private MainUIService m;
	private JPanel tippane;
	private MyOtherBlueLabel excel;
	private MyOtherOrangeLabel exit;
	private MyOtherRedLabel delete;
	private JTable profittable;
	private String[] tableheader = { "统计时间", "总收入", "总支出", "总利润" };
	private String[][] data = null;
	private DefaultTableModel tableModel;

	public ViewProfitUI(MainUIService main) {
		m = main;
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("仿宋", Font.PLAIN, 22);

		JLabel title = new JLabel("成 本 收 益 表", JLabel.CENTER);
		title.setBounds(70, 70, 710, 35);
		title.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
				Color.LIGHT_GRAY));
		title.setFont(font);
		this.add(title);

		Listener listen = new Listener();

		getProfitForm();

		tableModel = new DefaultTableModel(data, tableheader);
		profittable = new JTable(tableModel);
		profittable.getTableHeader().setFont(f1);
		profittable.getTableHeader().setBorder(
				BorderFactory.createMatteBorder(1, 1, 2, 1, Color.LIGHT_GRAY));
		profittable.getTableHeader().setBackground(Color.WHITE);
		profittable.getTableHeader().setPreferredSize(new Dimension(650, 35));

		// 设置居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		profittable.getTableHeader().setDefaultRenderer(r);
		profittable.setFont(f);

		profittable.setDefaultRenderer(Object.class, r);
		profittable.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				1));
		profittable.setRowHeight(35);
		profittable.addMouseListener(listen);
		this.add(profittable);

		JScrollPane scrollPane = new JScrollPane(profittable);
		scrollPane.setFont(font);
		// scrollPane.setViewportView(logtable);

		// 设置背景透明
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(70, 100, 710, 500);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY, 0));
		this.add(scrollPane);

		excel = new MyOtherBlueLabel("导出到Excel");
		excel.setBounds(70, 590, 180, 40);
		excel.addMouseListener(listen);
		this.add(excel);

		delete = new MyOtherRedLabel("删除表格");
		delete.setBounds(335, 590, 180, 40);
		delete.addMouseListener(listen);
		this.add(delete);

		exit = new MyOtherOrangeLabel("返回菜单");
		exit.setBounds(600, 590, 180, 40);
		exit.addMouseListener(listen);
		this.add(exit);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
	}

	private void getProfitForm() {
		ProfitManagerBLService profit = new ProfitStatistic();

		ArrayList<ProfitFormVO> list = profit.getProfitFormList();
		if (list != null) {
			data = new String[list.size()][4];

			for (int i = 0; i < list.size(); i++) {
				ProfitFormVO vo = list.get(i);
				data[i][0] = vo.getTitle();
				data[i][1] = vo.getIncome() + "";
				data[i][2] = vo.getOutCome() + "";
				data[i][3] = vo.getProfit() + "";
			}
		}
	}

	private void exportExcel(ProfitFormVO p) {
		ProfitManagerBLService profit = new ProfitStatistic();
		if (p == null) {
			TipBlockEmpty block = new TipBlockEmpty("未选择表格");
			tippane.add(block);
			block.show();
			block = null;
		} else {
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

			boolean succ = profit.exportExcel(p, path);
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
	}

	private void deleteForm(int index) {
		ProfitManagerBLService profit = new ProfitStatistic();

		boolean succ = profit.removeProfitForm(index);
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
				int row = profittable.getSelectedRow();
				if (row < 0) {
					TipBlockEmpty block = new TipBlockEmpty("未选择表格");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					String title = profittable.getValueAt(row, 0).toString();
					String in = profittable.getValueAt(row, 1).toString();
					String out = profittable.getValueAt(row, 2).toString();
					String pro = profittable.getValueAt(row, 3).toString();

					double income = Double.parseDouble(in);
					double outcome = Double.parseDouble(out);
					double profit = Double.parseDouble(pro);

					ProfitFormVO p = new ProfitFormVO(title, income, outcome,
							profit);
					exportExcel(p);
				}
			} else if (e.getSource() == exit) {
				if (IDKeeper.getIsManager()) {
					m.jumpTomanagerMenuUI(IDKeeper.getID());
				} else {
					m.jumpToFinanceMenuUI(IDKeeper.getID(), IDKeeper.getHigh());
				}
			} else if (e.getSource() == delete) {
				int index = profittable.getSelectedRow();
				if (index < 0) {
					TipBlockEmpty block = new TipBlockEmpty("未选择表格");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					tableModel.removeRow(index);
					deleteForm(index);
				}
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
