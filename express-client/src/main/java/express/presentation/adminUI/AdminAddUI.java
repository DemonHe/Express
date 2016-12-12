package express.presentation.adminUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.Admin;
import express.businesslogicService.adminBLService.AdminBLService;
import express.po.UserRole;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.vo.UserInfoAdminVO;
import express.vo.UserInfoVO;

public class AdminAddUI extends JDialog {

	private JTextField nametf, keytf,posotiontf;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel exit;
	private JComboBox<String> idcb;
	private DefaultTableModel tmodel;
	private ArrayList<UserInfoVO> userarr;
	private AdminBLService abs;

	public AdminAddUI(DefaultTableModel tablemodel) {
		this.setTitle("添加用户信息");
		this.setLayout(null);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setUndecorated(true);

		abs = new Admin();
		
		
		tmodel = tablemodel;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);

		JListener lis = new JListener();

		JLabel idl = new JLabel("工号");
		idl.setBounds(10, 50, 50, 30);
		idl.setFont(font);
		this.add(idl);
		
		Item item = new Item();
		idcb = new JComboBox<String>();
		idcb.setBounds(70, 50, 200, 30);
		idcb.setFont(f);
		idcb.addItemListener(item);
		this.add(idcb);
		
		JLabel namel = new JLabel("姓名");
		namel.setBounds(10, 100, 50, 30);
		namel.setFont(font);
		this.add(namel);

		nametf = new JTextField();
		nametf.setBounds(70, 100, 200, 30);
		nametf.setFont(f);
		nametf.setEditable(false);
		nametf.setBackground(Color.WHITE);
		this.add(nametf);

		JLabel positionl = new JLabel("职位");
		positionl.setBounds(10, 150, 50, 30);
		positionl.setFont(font);
		this.add(positionl);

		posotiontf = new JTextField();
		posotiontf.setBounds(70, 150, 200, 30);
		posotiontf.setFont(f);
		posotiontf.setEditable(false);
		posotiontf.setBackground(Color.WHITE);
		this.add(posotiontf);

		JLabel keyl = new JLabel("密码");
		keyl.setBounds(10, 200, 50, 30);
		keyl.setFont(font);
		this.add(keyl);

		keytf = new JTextField();
		keytf.setBounds(70, 200, 200, 30);
		keytf.setFont(f);
		this.add(keytf);

		ok = new MyOtherBlueLabel("确认");
		ok.setBounds(10, 250, 120, 35);
		ok.addMouseListener(lis);
		this.add(ok);

		exit = new MyOtherOrangeLabel("取消");
		exit.setBounds(150, 250, 120, 35);
		exit.addMouseListener(lis);
		this.add(exit);
		
		init();
	}
	
	private void init(){
		userarr = abs.getUnregistered();
		if(userarr!=null){
			if(userarr.size()>0){
				for(int i = 0;i<userarr.size();i++){
					UserInfoVO vo = userarr.get(i);
					idcb.addItem(vo.getID());
				}
				UserInfoVO vo = userarr.get(0);
				nametf.setText(vo.getName());
				String position = getPosition(vo.getPosition());
				posotiontf.setText(position);
			}
		}
		
	}
	
	private String getPosition(UserRole role){
		switch(role){
		case DeliverMan: return "快递员";
		case Admin:return "管理员";
		case Manager:return "总经理";
		case Financial:return "普通财务人员";
		case Financial_highest:return "最高权限财务人员";
		case TransCenterRepo:return "中转中心仓库管理人员";
		case TransCenterSale:return "中转中心业务员";
		default:return "营业厅业务员";
		}
	}
	
	private UserRole getPosition(String s){
		switch(s){
		case "快递员": return UserRole.DeliverMan;
		case "管理员":return UserRole.Admin;
		case "总经理":return UserRole.Manager;
		case "普通财务人员":return UserRole.Financial;
		case "最高权限财务人员":return UserRole.Financial_highest;
		case "中转中心仓库管理人员":return UserRole.TransCenterRepo;
		case "中转中心业务员":return UserRole.TransCenterSale;
		default:return UserRole.BusinessSale;
		}
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ok) {
				String name = nametf.getText();
				String id = idcb.getSelectedItem().toString();
				String position = posotiontf.getText();
				String key = keytf.getText();
				Object[] temp = { false, name, position, id, key };
				Object[] values;
				values = temp;

				if (key.equals("")) {
					JOptionPane.showMessageDialog(null, "信息未填写完整", "提示",
							JOptionPane.ERROR_MESSAGE);
				} else {
					
					UserRole posit = getPosition(position);

					UserInfoAdminVO vo = new UserInfoAdminVO(name, id, posit,
							key);

					if (abs.addUser(vo)) {
						tmodel.addRow(values);
						// JOptionPane.showMessageDialog(null, "添加成功", "提示",
						// JOptionPane.INFORMATION_MESSAGE);
						abs.endManage();

					} else {
						JOptionPane.showMessageDialog(null, "添加失败", "提示",
								JOptionPane.WARNING_MESSAGE);
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
		public void mousePressed(MouseEvent arg0) {
			if (arg0.getSource() == exit) {
				exit.whenPressed();
			} else if (arg0.getSource() == ok) {
				ok.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (arg0.getSource() == exit) {
				exit.setMyColor();
			} else if (arg0.getSource() == ok) {
				ok.setMyColor();
			}

		}
	}
	
	private class Item implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				int index = idcb.getSelectedIndex();
				UserInfoVO vo = userarr.get(index);
				nametf.setText(vo.getName());
				String position = getPosition(vo.getPosition());
				posotiontf.setText(position);
			}
			repaint();
		}
	}
}
