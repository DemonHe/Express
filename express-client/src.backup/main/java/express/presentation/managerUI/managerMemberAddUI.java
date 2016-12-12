package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.OrgForManager;
import express.businessLogic.infoManageBL.StaffForManager;
import express.businesslogicService.managerBLService.OrgInfoManageService;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.UserRole;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.UserInfoVO;

public class managerMemberAddUI extends JDialog {

	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel exit;
	private JTextField nametf, idtf, phonetf, datetf;
	private JLabel tip1, tip2, tip3;
	private String tipstr1 = "* 未填�?";
	private String tipstr2 = "* 格式错误";
	private JComboBox gendercb, positioncb, citycb;
	private String name, id, city, phone, date, position, gender;
	private UserRole posit;
	private boolean sex;
	private DateChooser datechooser;
	private DefaultTableModel tmodel;
	private OrgInfoManageService orginfo;
	private Border border;
	private boolean complete = true;
	private boolean right = true;

	public managerMemberAddUI(DefaultTableModel tablemodel) {

		this.setTitle("添加用户信息");
		this.setLayout(null);
		this.setSize(420, 400);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setUndecorated(true);

		int leftside1 = 10;
		int leftside2 = 110;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);

		tmodel = tablemodel;
		JListener lis = new JListener();
		Foclistener foc = new Foclistener();

		JLabel namel = new JLabel("姓名");
		namel.setFont(font);
		namel.setBounds(leftside1, 5, 50, 30);
		this.add(namel);

		nametf = new JTextField();
		nametf.setBounds(leftside2, 5, 200, 30);
		nametf.setFont(f);
		nametf.addFocusListener(foc);
		this.add(nametf);
		border = nametf.getBorder();

		tip1 = new JLabel();
		tip1.setBounds(leftside2 + 210, 5, 100, 30);
		tip1.setFont(font);
		tip1.setForeground(Color.RED);

		JLabel genderl = new JLabel("性别");
		genderl.setFont(font);
		genderl.setBounds(leftside1, 55, 50, 30);
		this.add(genderl);

		String[] genders = { "�?", "�?" };
		gendercb = new JComboBox(genders);
		gendercb.setBounds(leftside2, 55, 200, 30);
		gendercb.setFont(f);
		gendercb.setOpaque(false);
		this.add(gendercb);

		JLabel idl = new JLabel("工号");
		idl.setBounds(leftside1, 105, 100, 30);
		idl.setFont(font);
		this.add(idl);

		idtf = new JTextField();
		idtf.setBounds(leftside2, 105, 200, 30);
		idtf.setFont(f);
		idtf.addFocusListener(foc);
		this.add(idtf);

		tip2 = new JLabel();
		tip2.setBounds(leftside2 + 210, 105, 100, 30);
		tip2.setFont(font);
		tip2.setForeground(Color.RED);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(leftside1, 155, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

		String[] pos = { "快�?�员", "管理�?", "总经�?", "普�?�财务人�?", "�?高权限财务人�?",
				"中转中心仓库管理人员", "中转中心业务�?", "营业厅业务员" };
		positioncb = new JComboBox(pos);
		positioncb.setBounds(leftside2, 155, 200, 30);
		positioncb.setFont(f);
		this.add(positioncb);

		JLabel cityl = new JLabel("�?在单�?");
		cityl.setBounds(leftside1, 205, 80, 30);
		cityl.setFont(font);
		this.add(cityl);

		String[] cities = {};
		orginfo = new OrgForManager();
		ArrayList<String> orgname = orginfo.getAllOrgName();
		if (orgname != null) {
			int num = orgname.size();
			cities = new String[num];
			for (int x = 0; x < num; x++) {
				cities[x] = orgname.get(x);
			}
		}

		citycb = new JComboBox(cities);
		citycb.setBounds(leftside2, 205, 200, 30);
		citycb.setFont(f);
		this.add(citycb);

		JLabel phonel = new JLabel("联系方式");
		phonel.setBounds(leftside1, 255, 80, 30);
		phonel.setFont(font);
		this.add(phonel);

		phonetf = new JTextField();
		phonetf.setBounds(leftside2, 255, 200, 30);
		phonetf.setFont(f);
		phonetf.addFocusListener(foc);
		this.add(phonetf);

		tip3 = new JLabel();
		tip3.setBounds(leftside2 + 210, 255, 100, 30);
		tip3.setFont(font);
		tip3.setForeground(Color.RED);

		JLabel datel = new JLabel("入职日期");
		datel.setBounds(leftside1, 305, 80, 30);
		datel.setFont(font);
		this.add(datel);

		datetf = new JTextField(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(leftside2, 305, 140, 30);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(leftside2 + 160, 305, 40, 40);
		this.add(datechooser);

		ok = new MyOtherBlueLabel("确认");
		ok.setBounds(10, 350, 120, 30);
		ok.addMouseListener(lis);
		this.add(ok);

		exit = new MyOtherOrangeLabel("取消");
		exit.setBounds(190, 350, 120, 30);
		exit.addMouseListener(lis);
		this.add(exit);

		// tippane = new JPanel();
		// tippane.setSize(850, 40);
		// tippane.setLocation(0, 660);
		// tippane.setBackground(Color.white);
		// tippane.setLayout(null);
		// this.add(tippane);

	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == nametf) {
				nametf.setBorder(border);
				tip1.setText("");
			}
			if (e.getSource() == idtf) {
				idtf.setBorder(border);
				tip2.setText("");
			}
			if (e.getSource() == phonetf) {
				phonetf.setBorder(border);
				tip3.setText("");
			}
			repaint();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == ok) {
				name = nametf.getText();
				position = positioncb.getSelectedItem().toString();
				id = idtf.getText();
				String cityname = citycb.getSelectedItem().toString();
				city = orginfo.getOrgID(cityname);
				phone = phonetf.getText();
				date = datetf.getText();
				gender = gendercb.getSelectedItem().toString();
				Object[] values = { false, name, gender, id, position, city,
						phone, date, "<HTML><U>修改</U></HTML>" };

				if (name.isEmpty()) {
					complete = false;
					nametf.setBorder(new LineBorder(Color.RED));
					tip1.setText(tipstr1);
					add(tip1);
				}

				if (id.isEmpty()) {
					complete = false;
					idtf.setBorder(new LineBorder(Color.RED));
					tip2.setText(tipstr1);
					add(tip2);
				}

				if (phone.isEmpty()) {
					complete = false;
					phonetf.setBorder(new LineBorder(Color.RED));
					tip3.setText(tipstr1);
					add(tip3);
				}

				if (!complete) {
					JOptionPane.showMessageDialog(null, "信息未填写完�?", "提示",
							JOptionPane.WARNING_MESSAGE);
				} else {
					posit = UserRole.values()[positioncb.getSelectedIndex() + 1];
					System.out.println(posit);
					if (gender.equals("�?")) {
						sex = true;
					} else {
						sex = false;
					}

					StaffManageBLService smb = new StaffForManager();
					if (!smb.isUserIDAvailable(id)) {
						right = false;
						tip2.setText(tipstr2);
						add(tip2);
					}

					if (!smb.isCellPhoneAvailable(phone)) {
						right = false;
						tip3.setText(tipstr2);
						add(tip3);
					}

					if (right) {
						tip1.setText("");
						tip2.setText("");
						tip3.setText("");
						UserInfoVO vo = new UserInfoVO(name, sex, id, phone,
								posit, city, date);

						if (smb.addUserFromManager(vo)) {
							tmodel.addRow(values);
							JOptionPane.showMessageDialog(null, "添加成功", "提示",
									JOptionPane.INFORMATION_MESSAGE);

							smb.endManage();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "信息未填写完�?", "提示",
									JOptionPane.WARNING_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "信息填写错误", "提示",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				complete = true;
				right = true;
				repaint();
			} else if (e.getSource() == exit) {
				dispose();
			}

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
		public void mousePressed(MouseEvent arg0) {
			if (arg0.getSource() == ok) {
				ok.whenPressed();
			} else if (arg0.getSource() == exit) {
				exit.whenPressed();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (arg0.getSource() == ok) {
				ok.setMyColor();
			} else if (arg0.getSource() == exit) {
				exit.setMyColor();
			}
		}

	}
}
