package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.vo.UserInfoVO;

public class managerMemberChangeUI extends JDialog {

	private JTextField nametf, idtf, phonetf, datetf;
	private JLabel tipphone;
	private JComboBox gendercb, positioncb, citycb;
	private String name, id, city, phone, date, position, gender;
	private UserRole posit;
	private boolean sex;
	private DateChooser datechooser;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel exit;
	private MyOtherRedLabel detele;
	private DefaultTableModel tmodel;
	private StaffManageBLService smb;
	private OrgInfoManageService orginfo;
	private UserInfoVO vo;

	public managerMemberChangeUI(DefaultTableModel tablemodel, String id) {
		this.setTitle("修改用户信息");
		this.setLayout(null);
		this.setSize(420, 400);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setUndecorated(true);

		this.id = id;
		tmodel = tablemodel;
		int leftside1 = 10;
		int leftside2 = 110;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		smb = new StaffForManager();
		vo = smb.getUser(id);
		JListener lis = new JListener();

		JLabel namel = new JLabel("姓名");
		namel.setFont(font);
		namel.setBounds(leftside1, 5, 50, 30);
		this.add(namel);

		nametf = new JTextField(vo.getName());
		nametf.setBounds(leftside2, 5, 200, 30);
		nametf.setFont(f);
		this.add(nametf);

		JLabel genderl = new JLabel("性别");
		genderl.setFont(font);
		genderl.setBounds(leftside1, 55, 50, 30);
		this.add(genderl);

		String[] genders = { "男", "女" };
		gendercb = new JComboBox(genders);
		gendercb.setBounds(leftside2, 55, 200, 30);
		gendercb.setFont(f);
		gendercb.setOpaque(false);
		if (vo.getGender())
			gendercb.setSelectedIndex(0);
		else
			gendercb.setSelectedIndex(1);
		this.add(gendercb);

		JLabel idl = new JLabel("工号");
		idl.setBounds(leftside1, 105, 100, 30);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField(id);
		idtf.setBounds(leftside2, 105, 200, 30);
		idtf.setFont(f);
		idtf.setEditable(false);
		this.add(idtf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(leftside1, 155, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

		String[] pos = { "快递员", "管理员", "总经理", "普通财务人员", "最高权限财务人员",
				"中转中心仓库管理人员", "中转中心业务员", "营业厅业务员" };
		posit = vo.getPosition();
		position = posit.toString();
		positioncb = new JComboBox(pos);
		positioncb.setBounds(leftside2, 155, 200, 30);
		positioncb.setFont(f);
		positioncb.setSelectedItem(position);
		this.add(positioncb);

		JLabel cityl = new JLabel("所在城市");
		cityl.setBounds(leftside1, 205, 80, 30);
		cityl.setFont(font);
		this.add(cityl);

		city = vo.getCity();
		String temp = "";
		String[] cities = {};
		orginfo = new OrgForManager();
		ArrayList<String> orgname = orginfo.getAllOrgName();
		if (orgname != null) {
			int num = orgname.size();
			cities = new String[num];
			for (int x = 0; x < num; x++) {
				cities[x] = orgname.get(x);
				if (orginfo.getOrgID(cities[x]).equals(city)) {
					temp = cities[x];
				}
			}
		}

		citycb = new JComboBox(cities);
		citycb.setBounds(leftside2, 205, 200, 30);
		citycb.setFont(f);
		citycb.setSelectedItem(temp);
		this.add(citycb);

		JLabel phonel = new JLabel("联系方式");
		phonel.setBounds(leftside1, 255, 80, 30);
		phonel.setFont(font);
		this.add(phonel);

		phonetf = new JTextField(vo.getPhoneNum());
		phonetf.setBounds(leftside2, 255, 200, 30);
		phonetf.setFont(f);
		this.add(phonetf);

		tipphone = new JLabel();
		tipphone.setFont(font);
		tipphone.setForeground(Color.RED);
		tipphone.setBounds(leftside2 + 110, 255, 100, 30);
		this.add(tipphone);

		JLabel datel = new JLabel("入职日期");
		datel.setBounds(leftside1, 305, 80, 30);
		datel.setFont(font);
		this.add(datel);

		datetf = new JTextField(vo.getDate());
		datetf.setBounds(leftside2, 305, 100, 30);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(leftside2 + 110, 305, 40, 40);
		this.add(datechooser);

		ok = new MyOtherBlueLabel("修改");
		ok.setBounds(10, 355, 90, 30);
		ok.setFont(buttonfont);
		ok.addMouseListener(lis);
		this.add(ok);

		detele = new MyOtherRedLabel("删除");
		detele.setBounds(115, 355, 90, 30);
		detele.setFont(buttonfont);
		detele.addMouseListener(lis);
		this.add(detele);

		exit = new MyOtherOrangeLabel("取消");
		exit.setBounds(220, 355, 90, 30);
		exit.setFont(buttonfont);
		exit.addMouseListener(lis);
		this.add(exit);

		// tippane = new JPanel();
		// tippane.setSize(850, 40);
		// tippane.setLocation(0, 660);
		// tippane.setBackground(Color.white);
		// tippane.setLayout(null);
		// this.add(tippane);

	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ok) {
				name = nametf.getText();
				position = positioncb.getSelectedItem().toString();
				id = idtf.getText();
				String cityname = citycb.getSelectedItem().toString();
				city = orginfo.getOrgID(cityname);
				phone = phonetf.getText();
				date = datetf.getText();
				gender = gendercb.getSelectedItem().toString();

				posit = UserRole.values()[positioncb.getSelectedIndex() + 1];
				if (gender.equals("男")) {
					sex = true;
				} else {
					sex = false;
				}

				boolean complete = true;
				boolean correct = true;

				if (!smb.isCellPhoneAvailable(phone)) {
					tipphone.setText(" * 手机号格式错误");
					correct = false;
				} else {
					tipphone.setText("");
					correct = true;
				}

				if (name.equals("") || phone.equals("") || date.equals("")) {
					complete = false;
				}

				if (complete && correct) {
					vo = new UserInfoVO(name, sex, id, phone, posit, city, date);
					smb.changeUser(vo, id);
					smb.endManage();
					for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
						if (tmodel.getValueAt(i, 3).equals(id)) {
							tmodel.setValueAt(name, i, 1);
							tmodel.setValueAt(gender, i, 2);
							tmodel.setValueAt(id, i, 3);
							tmodel.setValueAt(position, i, 4);
							tmodel.setValueAt(city, i, 5);
							tmodel.setValueAt(phone, i, 6);
							tmodel.setValueAt(date, i, 7);

							JOptionPane.showMessageDialog(null, "修改成功", "提示",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "信息未填写完整", "提示",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (e.getSource() == detele) {

				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if (tmodel.getValueAt(i, 3).equals(id)) {
						tmodel.removeRow(i);
						smb.removeUser(id);
						smb.endManage();

						JOptionPane.showMessageDialog(null, "修改成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				dispose();
			} else if (e.getSource() == exit) {
				dispose();
			}
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
		public void mousePressed(MouseEvent arg0) {
			if (arg0.getSource() == ok) {
				ok.whenPressed();
			} else if (arg0.getSource() == exit) {
				exit.whenPressed();
			} else if (arg0.getSource() == detele) {
				detele.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (arg0.getSource() == ok) {
				ok.setMyColor();
			} else if (arg0.getSource() == exit) {
				exit.setMyColor();
			} else if (arg0.getSource() == detele) {
				detele.setMyColor();
			}

		}

	}
}
