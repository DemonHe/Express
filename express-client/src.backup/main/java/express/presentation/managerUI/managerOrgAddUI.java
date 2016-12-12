package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import express.businesslogicService.managerBLService.OrgManageBLService;
import express.po.OrgProperty;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockError;
import express.vo.OrganizationVO;

public class managerOrgAddUI extends JDialog {

	private JPanel tippane;
	private MyOtherBlueLabel ok;
	private MyOtherOrangeLabel exit;
	private JTextField orgnametf, orgidtf, citytf, orgaddtf;
	private JComboBox orgtypecb;
	private DefaultTableModel tmodel;
	private OrgManageBLService omg;
	private Border border;
	private boolean complete = true;
	private boolean right = true;

	public managerOrgAddUI(DefaultTableModel tablemodel) {
		this.setTitle("添加机构信息");
		this.setLayout(null);
		this.setSize(320, 350);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		this.setUndecorated(true);
		
		int leftside1 = 10;
		int leftside2 = 110;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);
		tmodel = tablemodel;
		omg = new OrgForManager();

		JListener lis = new JListener();
		Foclistener foc = new Foclistener();
		String[] butype = { "营业�?", "中转中心", "总部" };

		JLabel cityl = new JLabel("�?属城�?");
		cityl.setBounds(leftside1, 50, 100, 30);
		cityl.setFont(font);
		this.add(cityl);

		citytf = new JTextField();
		citytf.setBounds(leftside2, 50, 200, 30);
		citytf.setFont(f);
		this.add(citytf);
		border = citytf.getBorder();

		JLabel orgnamel = new JLabel("机构全称");
		orgnamel.setBounds(leftside1, 100, 100, 30);
		orgnamel.setFont(font);
		this.add(orgnamel);

		orgnametf = new JTextField();
		orgnametf.setBounds(leftside2, 100, 200, 30);
		orgnametf.setFont(f);
		this.add(orgnametf);

		JLabel butypel = new JLabel("机构性质");
		butypel.setBounds(leftside1, 150, 100, 30);
		butypel.setFont(font);
		this.add(butypel);

		orgtypecb = new JComboBox(butype);
		orgtypecb.setBounds(leftside2, 150, 200, 30);
		orgtypecb.setFont(f);
		this.add(orgtypecb);

		JLabel orgaddl = new JLabel("机构地址");
		orgaddl.setBounds(leftside1, 200, 100, 30);
		orgaddl.setFont(font);
		this.add(orgaddl);

		orgaddtf = new JTextField();
		orgaddtf.setBounds(leftside2, 200, 200, 30);
		orgaddtf.setFont(f);
		this.add(orgaddtf);

		JLabel orgidl = new JLabel("机构代号");
		orgidl.setBounds(leftside1, 250, 100, 30);
		orgidl.setFont(font);
		this.add(orgidl);

		orgidtf = new JTextField();
		orgidtf.setBounds(leftside2, 250, 200, 30);
		orgidtf.setFont(f);
		this.add(orgidtf);

		ok = new MyOtherBlueLabel("确认");
		ok.setBounds(leftside1, 300, 120, 35);
		ok.addMouseListener(lis);
		
		this.add(ok);

		exit = new MyOtherOrangeLabel("取消");
		exit.setBounds(190, 300, 120, 35);
		exit.addMouseListener(lis);
	
		this.add(exit);
		
		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
		
		
		
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == orgnametf ){
				 orgnametf.setBorder(border);
			}
			if(e.getSource() == orgidtf ){
				orgidtf.setBorder(border);
			}
			if(e.getSource() == citytf ){
				citytf.setBorder(border);
			}
			if(e.getSource() == orgaddtf ){
				orgaddtf.setBorder(border);
			}
			repaint();
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == ok) {
				String city = citytf.getText();
				if (city.isEmpty()) {
					complete = false;
					citytf.setBorder(new LineBorder(Color.RED));
				}

				String orgname = orgnametf.getText();
				if (orgname.isEmpty()) {
					complete = false;
					orgnametf.setBorder(new LineBorder(Color.RED));
				}

				String orgtype = orgtypecb.getSelectedItem().toString();

				String orgid = orgidtf.getText();
				if (orgid.isEmpty()) {
					complete = false;
					orgidtf.setBorder(new LineBorder(Color.RED));
				}

				String orgadd = orgaddtf.getText();
				if (orgadd.isEmpty()) {
					complete = false;
					orgaddtf.setBorder(new LineBorder(Color.RED));
				}

				if (complete) {
					String result = "";
					if (omg.isOrgIDAvailable(orgid)) {
						right = false;
						result += "机构代号重复";
					}
					if (omg.isOrgNameAvailable(orgname)) {
						right = false;
						if(!result.isEmpty())
							result += "\n";
						result = "机构名称重复";
					}

					if (right) {
						Object[] values = { false, city, orgname, orgtype,
								orgid, orgadd, "<HTML><U>修改</U></HTML>" };
						tmodel.addRow(values);
						OrgProperty orgpro = OrgProperty.TRANSCENTER;
						OrganizationVO vo = new OrganizationVO(city, orgname,
								orgadd, orgpro, orgid);
						vo.setOrgProperty(vo.typetran(orgtype));
						omg.addOrgInfo(vo);
						TipBlock block=new TipBlock("添加成功");
						tippane.add(block);
						block.show();
						block=null;
						
						
						omg.endManage();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "信息错误", "提示",
								JOptionPane.ERROR_MESSAGE);
						// TipBlockError block=new TipBlockError("Error");
						// tippane.add(block);
						// block.show();
						// block=null;
					}
				}else{
					JOptionPane.showMessageDialog(null, "信息未填写完�?", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
				complete = true;
				right = true;
				repaint();	
			}else if (e.getSource() == exit) {
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
			if(arg0.getSource()==ok){
				ok.whenPressed();
			}else if (arg0.getSource()==exit) {
				exit.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(arg0.getSource()==ok){
				ok.setMyColor();
			}else if (arg0.getSource()==exit) {
				exit.setMyColor();
			}
		}

	}
}
