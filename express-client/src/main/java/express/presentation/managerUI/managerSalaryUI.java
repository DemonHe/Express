package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import express.businessLogic.infoManageBL.SalaryManager;
import express.businesslogicService.managerBLService.SalaryManagerBLService;
import express.po.Strategy;
import express.po.UserRole;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.SalaryStrategyVO;

public class managerSalaryUI extends JPanel {

	private JPanel tippane;
	private JComboBox positioncb;
	private JPanel panel;
	private JPanel[] p;
	private JTextField[] tf;
	private MyOtherBlueLabel ok;
	private MyOtherGreenLabel exit;
	private Border border;
	private boolean complete = true;
	private SalaryManagerBLService smb;

	public managerSalaryUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);
		;
		Font font0 = new Font("楷体", Font.BOLD, 20);
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);
		int width = 220;
		int height = 30;

		String[] pos = { "司机", "快递员", "管理员", "总经理", "财务人员", "中转中心仓库管理人员",
				"中转中心业务员", "营业厅业务员" };
		String[] way = { "计次", "提成", "按月" };
		String[] values = new String[8];

		p = new JPanel[8];
		JLabel[] l = new JLabel[8];
		JLabel[] label = new JLabel[8];
		JLabel[] lab = new JLabel[8];
		tf = new JTextField[8];
		Listener lis = new Listener();
		Foclistener foc = new Foclistener();

		smb = new SalaryManager();
		ArrayList<SalaryStrategyVO> strarr = smb.getSalaryStrategyList();
		if (strarr != null) {
			for (int i = 0; i < 8; i++) {
				SalaryStrategyVO temp = strarr.get(i);
				UserRole posit = temp.getPosition();
				if (posit.equals(UserRole.Admin)) {
					values[2] = temp.getValue() + "";
				} else if (posit.equals(UserRole.BusinessSale)) {
					values[7] = temp.getValue() + "";
				} else if (posit.equals(UserRole.DeliverMan)) {
					values[1] = temp.getValue() + "";
				} else if (posit.equals(UserRole.Driver)) {
					values[0] = temp.getValue() + "";
				} else if (posit.equals(UserRole.Financial)
						|| posit.equals(UserRole.Financial_highest)) {
					values[4] = temp.getValue() + "";
				} else if (posit.equals(UserRole.Manager)) {
					values[3] = temp.getValue() + "";
				} else if (posit.equals(UserRole.TransCenterRepo)) {
					values[5] = temp.getValue() + "";
				} else if (posit.equals(UserRole.TransCenterSale)) {
					values[6] = temp.getValue() + "";
				}
			}
		}

		panel = new JPanel();
		panel.setBounds(30, 30, 790, 560);
		panel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.GRAY));
		this.add(panel);

		for (int i = 0; i < 8; i++) {
			p[i] = new JPanel();
			p[i].setLayout(null);
			p[i].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0,
					Color.GRAY));
			p[i].setPreferredSize(new Dimension(790, 64));

			l[i] = new JLabel(pos[i]);
			l[i].setBounds(10, 16, width, height);
			l[i].setFont(font0);
			p[i].add(l[i]);

			lab[i] = new JLabel();
			lab[i].setBounds(580, 16, 100, height);
			lab[i].setFont(font);
			label[i] = new JLabel();
			label[i].setBounds(240, 16, 100, height);
			label[i].setFont(font);

			if (i == 0) {
				label[i].setText(way[0]);
				lab[i].setText("元/次");
			} else if (i == 1) {
				label[i].setText(way[1]);
				lab[i].setText("%");
			} else {
				label[i].setText(way[2]);
				lab[i].setText("元/月");
			}
			p[i].add(label[i]);
			p[i].add(lab[i]);

			tf[i] = new JTextField();
			tf[i].setBounds(340, 16, width, height);
			tf[i].setFont(f);
			tf[i].setText(values[i]);
			tf[i].addFocusListener(foc);
			p[i].add(tf[i]);

			panel.add(p[i]);

		}

		ok = new MyOtherBlueLabel("确认");
		ok.setBounds(230, 620, 100, 30);
		ok.addMouseListener(lis);

		this.add(ok);

		exit = new MyOtherGreenLabel("取消");
		exit.setBounds(400, 620, 100, 30);
		exit.addMouseListener(lis);

		this.add(exit);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

	}

	private boolean checkNum1(String num) {
		double n = 0;
		try {
			n = Double.parseDouble(num);
		} catch (Exception e) {
			return false;
		}
		if (n < 0) {
			return false;
		}
		return true;
	}

	private boolean checkNum2(String num) {
		double n = 0;
		try {
			n = Double.parseDouble(num);
		} catch (Exception e) {
			return false;
		}
		if (n < 0 || n > 99) {
			return false;
		}
		return true;
	}
	
	private void addSalary(){
		UserRole[] role = { UserRole.Driver, UserRole.DeliverMan,
				UserRole.Admin, UserRole.Manager,
				UserRole.Financial, UserRole.TransCenterRepo,
				UserRole.TransCenterSale, UserRole.BusinessSale };
		SalaryStrategyVO[] vo = new SalaryStrategyVO[8];
		for (int i = 0; i < 8; i++) {
			tf[i].setBorder(new LineBorder(Color.GRAY,1));
			if (i == 0)
				vo[i] = new SalaryStrategyVO(role[i],
						Strategy.COUNT_BY_TIME,
						Double.parseDouble(tf[i].getText()));
			if (i == 1)
				vo[i] = new SalaryStrategyVO(role[i],
						Strategy.PERCENT, Double.parseDouble(tf[i]
								.getText()));
			else
				vo[i] = new SalaryStrategyVO(role[i],
						Strategy.FIXED, Double.parseDouble(tf[i]
								.getText()));
			smb.changeSalaryStrategy(vo[i]);
		}
		smb.recordSalaryStrategy();
		TipBlock block = new TipBlock("薪水策略保存成功");
		tippane.add(block);
		block.show();
		block = null;
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 8; i++) {
				if (e.getSource() == tf[i])
					tf[i].setBorder(border);
			}
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == exit) {
				for (int i = 0; i < 8; i++) {
					// tf[i].setText("");
					tf[i].setBorder(border);
				}
			} else if (e.getSource() == ok) {
				for (int i = 0; i < 8; i++) {
					if (tf[i].getText().equals("")) {
						complete = false;
						Color yellow = new Color(255,215,0);
						tf[i].setBorder(new LineBorder(yellow,2));
					}
				}
				if (complete) {
					boolean correct = true;
					boolean t;
					for (int i = 0; i < 8; i++) {
						String money = tf[i].getText();
						if(i == 1)
							t = checkNum2(money);
						else
							t = checkNum1(money);
						if (!t) {
							correct = false;
							tf[i].setBorder(new LineBorder(Color.RED,2));
						}
					}
					if(correct){
						addSalary();
					}else{
						TipBlockError block = new TipBlockError("薪水输入错误");
						tippane.add(block);
						block.show();
						block = null;
					}
					
				} else {
					TipBlockEmpty block = new TipBlockEmpty("信息未填写完整");
					tippane.add(block);
					block.show();
					block = null;
				}
			}
			complete = true;
			updateUI();
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
