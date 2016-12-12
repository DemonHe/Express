package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.MyTableModel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.vo.UserInfoVO;

public class managerMemberUI extends JPanel {

	private JPanel tippane;
	private JTable table;
	private MyTableModel tableModel;
	private TableColumnModel tcm;
	private MyOtherBlueLabel add;
	private MyOtherGreenLabel change;
	private MyOtherRedLabel detele;
	private JTextField idtf;
	private JComboBox positioncb, gendercb, citycb;
	private StaffManageBLService smb;
	private UserInfoVO vo;
	private ArrayList<UserInfoVO> userarr;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";
	private String id;
	private OrgInfoManageService orginfo;
	private Object[][] data;
	private String[] header = { "选择", "姓名", "性别", "工号", "职位", "所在单位", "联系方式",
			"入职日期", "修改" };

	public managerMemberUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);
		smb = new StaffForManager();
		JListener listener = new JListener();

		String[] pos = { "快递员", "管理员", "总经理", "普通财务人员", "最高权限财务人员",
				"中转中心仓库管理人员", "中转中心业务员", "营业厅业务员" };
		String[] genders = { "男", "女" };
		String[] cities = {};
		Class[] typeArray = { Boolean.class, Object.class, JComboBox.class,
				Object.class, JComboBox.class, JComboBox.class, Object.class,
				Object.class, Object.class };

		orginfo = new OrgForManager();
		ArrayList<String> orgname = orginfo.getAllOrgName();
		if (orgname != null) {
			int num = orgname.size();
			cities = new String[num];
			for (int x = 0; x < num; x++) {
				cities[x] = orgname.get(x);
			}
		}

		userarr = smb.getAllUser();
		if (userarr != null) {
			data = new Object[userarr.size()][9];
			for (int i = 0; i < userarr.size(); i++) {
				UserInfoVO temp = userarr.get(i);
				data[i][0] = false;
				data[i][1] = temp.getName();
				data[i][2] = temp.getGender() ? "男" : "女";
				data[i][3] = temp.getID();
				UserRole posit = temp.getPosition();
				data[i][4] = temp.transposition(posit);
				data[i][5] = temp.getCity();
				data[i][6] = temp.getPhoneNum();
				data[i][7] = temp.getDate();
				data[i][8] = changeunder;
			}
		}

		// Object[] user1 = { true, "lhl", "man", "10001", "110", " ", " ", " ",
		// changeunder };
		// Object[] user2 = { false, "hmt", "woman", "10086", "120", " ", " ",
		// " ", changeunder };
		// Object user[][] = { user1, user2 };
		// data = user;

		tableModel = new MyTableModel(data, header, typeArray);
		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setFont(font);
		table.setBounds(50, 60, 750, 600);
		table.setFont(f);
		table.addMouseListener(listener);

		tcm = table.getColumnModel();
		positioncb = new JComboBox(pos);
		gendercb = new JComboBox(genders);
		citycb = new JComboBox(cities);
		tcm.getColumn(4).setCellEditor(new DefaultCellEditor(positioncb));
		tcm.getColumn(2).setCellEditor(new DefaultCellEditor(gendercb));
		tcm.getColumn(5).setCellEditor(new DefaultCellEditor(citycb));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 60, 750, 600);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.add(scrollPane);

		detele = new MyOtherRedLabel("删除");
		detele.setBounds(190, 10, 120, 40);
		detele.addMouseListener(listener);
		this.add(detele);

		add = new MyOtherBlueLabel("添加");
		add.setBounds(50, 10, 120, 40);
		add.addMouseListener(listener);
		this.add(add);

		change = new MyOtherGreenLabel("查找");
		change.setBounds(330, 10, 120, 40);
		change.addMouseListener(listener);
		this.add(change);

		JLabel idl = new JLabel("工号");
		idl.setBounds(480, 10, 50, 40);
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

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == detele) {
				// m.jumpTomanagerMenuUI();
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					if ((boolean) tableModel.getValueAt(i, 0)) {
						smb.removeUser((String) tableModel.getValueAt(i, 3));
						tableModel.removeRow(i);
					}
				}
				smb.endManage();
				// JOptionPane.showMessageDialog(null, "删除成功", "提示",
				// JOptionPane.INFORMATION_MESSAGE);
				TipBlock block = new TipBlock("删除成功");
				tippane.add(block);
				block.show();
				block = null;

			} else if (e.getSource() == add) {

				managerMemberAddUI mmaui = new managerMemberAddUI(tableModel);
				mmaui.setVisible(true);

			} else if (e.getSource() == change) {

				id = idtf.getText();
				if (!smb.isUserIDAvailable(id)) {
					managerMemberChangeUI mmcui = new managerMemberChangeUI(
							tableModel, id);
					mmcui.setVisible(true);
				} else {
					TipBlockEmpty block = new TipBlockEmpty("工号不存在");
					tippane.add(block);
					block.show();
					block = null;
				}

			} else if (e.getSource() == table) {

				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (col == 8) {
					if (tableModel.getValueAt(row, col).equals(changeunder)) {
						tableModel.setrowedit();
						tableModel.setValueAt(confirmunder, row, col);
						// id = (String) tableModel.getValueAt(row, 3);
						// managerMemberChangeUI mmcui = new
						// managerMemberChangeUI(
						// tableModel, id);
						// mmcui.setVisible(true);
						// }
					} else if (tableModel.getValueAt(row, col).equals(
							confirmunder)) {
						if (tableModel.getValueAt(row, 1).equals("")
								|| tableModel.getValueAt(row, 3).equals("")
								|| tableModel.getValueAt(row, 6).equals("")
								|| tableModel.getValueAt(row, 7).equals("")) {
							TipBlockEmpty block = new TipBlockEmpty("信息未填写完整");
							tippane.add(block);
							block.show();
							block = null;
						} else {
							tableModel.setrowunedit();
							tableModel.setValueAt(changeunder, row, col);

							String name = (String) tableModel
									.getValueAt(row, 1);
							String gender = (String) tableModel.getValueAt(row,
									2);
							String id = (String) tableModel.getValueAt(row, 3);
							String city = (String) tableModel
									.getValueAt(row, 5);
							String orgID = orginfo.getOrgID(city);
							String phone = (String) tableModel.getValueAt(row,
									6);
							String date = (String) tableModel
									.getValueAt(row, 7);
							UserRole posit = UserRole.values()[positioncb
									.getSelectedIndex() + 1];

							boolean sex = gender.equals("男");
							vo = new UserInfoVO(name, sex, id, phone, posit,
									orgID, date);
							tableModel.setValueAt(orgID, row, 5);
							smb.changeUser(vo, id);
							TipBlock block = new TipBlock("信息修改成功");
							tippane.add(block);
							block.show();
							block = null;
							smb.endManage();
						}
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
