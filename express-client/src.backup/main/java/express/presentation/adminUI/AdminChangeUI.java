package express.presentation.adminUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.Admin;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.adminBLService.AdminBLService;
import express.businesslogicService.adminBLService.RemoveUserBLService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.vo.UserInfoAdminVO;
import express.vo.UserInfoVO;

public class AdminChangeUI extends JDialog {

	private JTextField nametf, idtf, keytf, positiontf;
	private MyOtherBlueLabel ok;
	private MyOtherGreenLabel exit;
	private MyOtherRedLabel detele;
	private String name, position, id, key = "";
	private UserRole posit;
	private DefaultTableModel tmodel;
	private AdminBLService abs;

	public AdminChangeUI(DefaultTableModel tablemodel, String id) {
		this.setTitle("修改用户信息");
		this.setLayout(null);
		this.setSize(320, 300);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		this.setUndecorated(true);

		this.id = id;
		abs = new Admin();

		tmodel = tablemodel;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);

		JListener lis = new JListener();

		JLabel namel = new JLabel("姓名");
		namel.setBounds(10, 50, 50, 30);
		namel.setFont(font);
		this.add(namel);

		nametf = new JTextField();
		nametf.setBounds(70, 50, 200, 30);
		nametf.setFont(f);
		nametf.setEditable(false);
		nametf.setBackground(Color.WHITE);
		this.add(nametf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(10, 100, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

		// String[] pos = { "快�?�员", "管理�?", "总经�?", "普�?�财务人�?", "�?高权限财务人�?",
		// "中转中心仓库管理人员", "中转中心业务�?", "营业厅业务员" };

		positiontf = new JTextField(position);
		positiontf.setBounds(70, 100, 200, 30);
		positiontf.setFont(f);
		positiontf.setEditable(false);
		positiontf.setBackground(Color.WHITE);
		this.add(positiontf);

		JLabel idl = new JLabel("工号");
		idl.setBounds(10, 150, 50, 30);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField(id);
		idtf.setBounds(70, 150, 200, 30);
		idtf.setFont(f);
		idtf.setEditable(false);
		idtf.setBackground(Color.WHITE);
		this.add(idtf);

		JLabel keyl = new JLabel("密码");
		keyl.setBounds(10, 200, 50, 30);
		keyl.setFont(font);
		this.add(keyl);

		keytf = new JTextField(key);
		keytf.setBounds(70, 200, 200, 30);
		keytf.setFont(f);
		this.add(keytf);

		ok = new MyOtherBlueLabel("修改");
		ok.setBounds(10, 250, 90, 30);
		ok.addMouseListener(lis);
		this.add(ok);

		detele = new MyOtherRedLabel("删除");
		detele.setBounds(110, 250, 90, 30);
		detele.addMouseListener(lis);
		this.add(detele);

		exit = new MyOtherGreenLabel("取消");
		exit.setBounds(210, 250, 90, 30);
		exit.addMouseListener(lis);
		this.add(exit);

		init();
	}

	private void init() {

		UserInfoAdminVO vo = abs.getUser(id);
		if (vo == null) {
			StaffManageBLService staff = new StaffForManager();
			UserInfoVO user = staff.getUser(id);
			name = user.getName();
			posit = user.getPosition();
			position = new UserInfoVO().transposition(posit);
		} else {
			name = vo.getName();
			posit = vo.getPosition();
			position = new UserInfoVO().transposition(posit);
			key = vo.getPassword();
			keytf.setText(key);
		}
		nametf.setText(name);
		positiontf.setText(position);
		idtf.setText(id);
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ok) {

				key = keytf.getText();
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if (tmodel.getValueAt(i, 3).equals(id)) {
						tmodel.setValueAt(key, i, 4);
						abs.changeUserPassword(id, key);
						abs.endManage();
						JOptionPane.showMessageDialog(null, "修改密码成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			} else if (e.getSource() == detele) {
				RemoveUserBLService rub = new Admin();
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if (tmodel.getValueAt(i, 3).equals(id)) {
						tmodel.removeRow(i);
						rub.removeUser(id);
						abs.endManage();
						// JOptionPane.showMessageDialog(null, "删除成功", "提示",
						// JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			}
			dispose();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == ok) {
				ok.whenPressed();
			} else if (e.getSource() == exit) {
				exit.whenPressed();
			} else if (e.getSource() == detele) {
				detele.whenPressed();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == ok) {
				ok.setMyColor();
			} else if (e.getSource() == exit) {
				exit.setMyColor();
			} else if (e.getSource() == detele) {
				detele.setMyColor();
			}

		}

	}

}
