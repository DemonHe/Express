package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import express.businessLogic.infoManageBL.Driver;
import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.MyTableModel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.DriverInfoVO;
import express.vo.VehicleInfoVO;

public class businessDriverUI extends JPanel {
	
	
	private JPanel tippane;
	private DriverBusinessSaleblService dbs;
	private DriverInfoVO vo;
	private ArrayList<DriverInfoVO> list;

	private MyTableModel tableModel;
	private TableColumnModel tcm;
	private JTable table;

	private MyOtherRedLabel delete;
	private MyOtherGreenLabel change;
	private MyOtherBlueLabel add;
	private JTextField idtf;
	private JComboBox gendercb;

	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";
	private Object[][] data;
	private String[] header = { "选择", "司机编号", "营业厅编号", "姓名", "出生日期", "身份证号",
			"联系方式", "性别", "行驶证期限", "修改" };

	private String[] genders = { "男", "女" };

	private String id;

	public businessDriverUI() {
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		dbs = new Driver();
		JListener listener = new JListener();

		Class[] typeArray = { Boolean.class, Object.class, Object.class,
				Object.class, Object.class, Object.class, Object.class,
				JComboBox.class, Object.class, Object.class };
		// Object[] driver1 = {true,"001", "A01",
		// "未加入底层","2015-06-07","11","11","男" ,"5",changeunder};
		// Object[] driver2 = { false,"002", "B01","未加入底层","2015-06-07","22"
		// ,"22","女","6",changeunder };
		// Object vehicle[][] = { driver1, driver2 };
		// data = vehicle;
		list = dbs.getDriverInfoList();
		if (list != null) {
			data = new Object[list.size()][10];
			for (int i = 0; i < list.size(); i++) {
				DriverInfoVO temp = list.get(i);
				data[i][0] = false;
				data[i][1] = temp.getdriverNumber();
				data[i][2] = temp.getbusinesshallNumber();
				data[i][3] = temp.getname();
				data[i][4] = temp.getdate();
				data[i][5] = temp.getID();
				data[i][6] = temp.getcellPhone();
				data[i][7] = temp.getsex() ? "男" : "女";
				data[i][8] = temp.getdeadline();
				data[i][9] = changeunder;
			}
		}

		tableModel = new MyTableModel(data, header, typeArray);
		table = new JTable(tableModel);
		table.setBounds(25, 60, 800, 600);
		table.setFont(f);
		table.setRowHeight(40);
		table.getTableHeader().setFont(new Font("幼圆", Font.PLAIN, 18));
		table.getTableHeader().setReorderingAllowed(false);

		gendercb = new JComboBox(genders);
		tcm = table.getColumnModel();
		tcm.getColumn(7).setCellEditor(new DefaultCellEditor(gendercb));

		table.addMouseListener(listener);
		
		int[] widths = { 50,90, 105,65, 90, 100, 95,50, 110, 55 };
		TableColumnModel columns = table.getColumnModel();
		for (int i = 0; i < 10; i++) {
			TableColumn column = columns.getColumn(i);
			column.setPreferredWidth(widths[i]);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 60, 800, 600);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		this.add(scrollPane);

		delete = new MyOtherRedLabel("删除");
		delete.setBounds(50, 10, 100, 40);
		delete.addMouseListener(listener);
		this.add(delete);

		add = new MyOtherBlueLabel("添加");
		add.setBounds(190, 10, 100, 40);
		add.addMouseListener(listener);
		this.add(add);

		change = new MyOtherGreenLabel("查找");
		change.setBounds(320, 10, 100, 40);
		change.addMouseListener(listener);
		this.add(change);

		JLabel id = new JLabel("司机编号");
		id.setBounds(450, 10, 100, 40);
		id.setFont(font);
		this.add(id);

		idtf = new JTextField();
		idtf.setBounds(560, 10, 150, 40);
		idtf.setFont(f);
		this.add(idtf);
		
		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
		
		
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == delete) {
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) tableModel.getValueAt(i, 0)) {

						dbs.removeDriverInfo((String) (table.getValueAt(i, 1)));// 逻辑层删除这条记录
						dbs.endManage();// 删除之后，逻辑层会调用数据层的writeall，更新所有记录
						tableModel.removeRow(i);
					}
				}
				TipBlock block=new TipBlock("删除成功");
				tippane.add(block);
				block.show();
				block=null;
			} else if (e.getSource() == add) {
				businessDriverAddUI DriverAddpanel = new businessDriverAddUI(
						tableModel);
				DriverAddpanel.setVisible(true);
			} else if (e.getSource() == change) {
				id = idtf.getText();
				if (dbs.isDriverIDAvailable(id)) {
					businessDriverChangeUI driverChange = new businessDriverChangeUI(
							tableModel, id);
					driverChange.setVisible(true);
				} else {
					TipBlockError block=new TipBlockError("司机编号不存在");
					tippane.add(block);
					block.show();
					block=null;
				}

			} else if (e.getSource() == table) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if (col == 9) {
					if (tableModel.getValueAt(row, col).equals(changeunder)) {
						tableModel.setrowedit();
						tableModel.setValueAt(confirmunder, row, col);
					} else if (tableModel.getValueAt(row, col).equals(
							confirmunder)) {

						tableModel.setrowunedit();
						tableModel.setValueAt(changeunder, row, col);

						String driverNumber = (String) tableModel.getValueAt(
								row, 1);
						String orgID = (String) tableModel.getValueAt(row, 2);
						String name = (String) tableModel.getValueAt(row, 3);
						String date = (String) tableModel.getValueAt(row, 4);
						String ID = (String) tableModel.getValueAt(row, 5);
						String cellPhone = (String) tableModel.getValueAt(row,
								6);
						String gender = (String) tableModel.getValueAt(row, 7);
						String ddl = (String) tableModel.getValueAt(row, 8);
						int deadline = Integer.parseInt(ddl);

						// int deadline=special.intValue();
						boolean sex;
						if (gender == "男") {
							sex = true;
						} else {
							sex = false;
						}

						vo = new DriverInfoVO(driverNumber, orgID, name, date,
								ID, cellPhone, sex, deadline);
						dbs.changeDriverInfo(vo, driverNumber);
						TipBlock block=new TipBlock("信息修改成功");
						tippane.add(block);
						block.show();
						block=null;
						dbs.endManage();
					}
				}
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
			if (e.getSource()==add) {
				add.whenPressed();
			}else if (e.getSource()==delete) {
				delete.whenPressed();
			}else if (e.getSource()==change) {
				change.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource()==add) {
				add.setMyColor();
			}else if (e.getSource()==delete) {
				delete.setMyColor();
			}else if (e.getSource()==change) {
				change.setMyColor();
			}

		}

	}
}
