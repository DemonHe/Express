package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import express.businessLogic.infoManageBL.OrgForManager;
import express.businesslogicService.managerBLService.OrgManageBLService;
import express.po.OrgProperty;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.MyTableModel;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.OrganizationVO;

public class managerOrgUI extends JPanel {

	private JPanel tippane;
	private JTable table;
	private MyTableModel tableModel;
	private TableColumnModel tcm;
	private MyOtherBlueLabel add;
	private MyOtherGreenLabel change;
	private MyOtherRedLabel detele;
	private JTextField idtf;
	private JComboBox orgtypecb;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";
	private OrgManageBLService omg;
	private String orgid;

	public managerOrgUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		int leftside1 = 170;
		int leftside2 = 270;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);
		Listener listener = new Listener();
		omg = new OrgForManager();

		// 所属城市，机构全称，性质（选择营业厅、中转中心），机构代号
		Class[] typeArray = { Boolean.class, Object.class, Object.class,
				Object.class, Object.class, Object.class, Object.class };
		String[] headers = { "选择", "所属城市", "机构全称", "性质", "机构代号", "地址", "修改" };
		Object[][] datas = {};
		String[] orgtypes = { "营业厅", "中转中心", "总部" };

		ArrayList<OrganizationVO> orgarr = omg.getAllOrgInfo();
		if (orgarr != null) {
			datas = new Object[orgarr.size()][7];
			for (int i = 0; i < orgarr.size(); i++) {
				OrganizationVO temp = orgarr.get(i);
				datas[i][0] = false;
				datas[i][1] = temp.getCity();
				datas[i][2] = temp.getName();
				OrgProperty orgtemp = temp.getOrgProperty();
				datas[i][3] = temp.transtype(orgtemp);
				datas[i][4] = temp.getOrgID();
				datas[i][5] = temp.getAddress();
				datas[i][6] = changeunder;
			}
		}

		tableModel = new MyTableModel(datas, headers, typeArray);
		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.setFont(f);
		table.getTableHeader().setFont(font);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(listener);
		table.setBounds(50, 60, 750, 600);

		orgtypecb = new JComboBox(orgtypes);
		tcm = table.getColumnModel();
		tcm.getColumn(3).setCellEditor(new DefaultCellEditor(orgtypecb));

		JScrollPane scrollPanes = new JScrollPane(table);
		scrollPanes.setFont(font);
		scrollPanes.setBounds(50, 60, 750, 600);
		MyScrollPane render = new MyScrollPane();
		scrollPanes.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPanes.setOpaque(false);
		scrollPanes.getViewport().setOpaque(false);
		scrollPanes.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.add(scrollPanes);

		detele = new MyOtherRedLabel("删除");
		detele.setBounds(180, 10, 110, 40);
		detele.addMouseListener(listener);
		this.add(detele);

		add = new MyOtherBlueLabel("添加");
		add.setBounds(50, 10, 110, 40);
		add.addMouseListener(listener);
		this.add(add);

		change = new MyOtherGreenLabel("查找");
		change.setBounds(310, 10, 110, 40);
		change.addMouseListener(listener);
		this.add(change);

		JLabel idl = new JLabel("机构代号");
		idl.setBounds(450, 10, 80, 40);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField();
		idtf.setBounds(540, 10, 150, 40);
		idtf.setFont(f);
		this.add(idtf);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == detele) {
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) tableModel.getValueAt(i, 0)) {
						omg.removeOrgInfo((String) tableModel.getValueAt(i, 4));
						tableModel.removeRow(i);
					}
				}
				omg.endManage();
			} else if (e.getSource() == change) {
				orgid = idtf.getText();
				if (omg.isOrgIDAvailable(orgid)) {
					managerOrgChangeUI orgchange = new managerOrgChangeUI(
							tableModel, orgid);
					orgchange.setVisible(true);
				} else {
					TipBlockError block = new TipBlockError("不存在该机构代号");
					tippane.add(block);
					block.show();
					block = null;
				}
			} else if (e.getSource() == add) {
				managerOrgAddUI orgadd = new managerOrgAddUI(tableModel);
				orgadd.setVisible(true);
			} else if (e.getSource() == table) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (tableModel.getValueAt(row, col).equals(changeunder)) {
					tableModel.setrowedit();
					tableModel.setValueAt(confirmunder, row, col);
					// orgid = (String) tableModel.getValueAt(row, 4);
					// managerOrgChangeUI orgchange = new managerOrgChangeUI(
					// tableModel, orgid);
					// orgchange.setVisible(true);
					// }
				} else if (tableModel.getValueAt(row, col).equals(confirmunder)) {
					tableModel.setrowunedit();
					tableModel.setValueAt(changeunder, row, col);

					if (tableModel.getValueAt(row, 1).equals("")
							|| tableModel.getValueAt(row, 2).equals("")
							|| tableModel.getValueAt(row, 4).equals("")
							|| tableModel.getValueAt(row, 5).equals("")) {
						JOptionPane.showMessageDialog(null, "信息未填写完整", "提示",
								JOptionPane.ERROR_MESSAGE);
					} else {
						String city = (String) tableModel.getValueAt(row, 1);
						String orgname = (String) tableModel.getValueAt(row, 2);
						String orgtype = (String) tableModel.getValueAt(row, 3);
						String orgid = (String) tableModel.getValueAt(row, 4);
						String orgadd = (String) tableModel.getValueAt(row, 5);
						OrgProperty orgpro = OrgProperty.TRANSCENTER;
						// if (orgtype.equals("中转中心"))
						// orgpro = OrgProperty.TRANSCENTER;
						// if (orgtype.equals("营业厅"))
						// orgpro = OrgProperty.SALE;
						// if (orgtype.equals("总部"))
						// orgpro = OrgProperty.OTHER;
						OrganizationVO vo = new OrganizationVO(city, orgname,
								orgadd, orgpro, orgid);
						vo.setOrgProperty(vo.typetran(orgtype));
						omg.changeOrgInfo(vo, orgid);
						omg.endManage();
					}
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
			if (e.getSource() == add) {
				add.whenPressed();
			} else if (e.getSource() == detele) {
				detele.whenPressed();
			} else if (e.getSource() == change) {
				change.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == add) {
				add.setMyColor();
			} else if (e.getSource() == detele) {
				detele.setMyColor();
			} else if (e.getSource() == change) {
				change.setMyColor();
			}
		}
	}

}
