package express.presentation.managerUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MySideLabel;
import express.vo.UserInfoSignVO;

public class managerMenuUI extends JPanel{

	private MainUIService m;
	private LogInBLService login;
	private String id;
	private CardLayout card;
//	private JFrame frame;
	private JPanel mainPanel,pane;
	private JLabel username, userid;
	private MySideLabel log;
	private MySideLabel statistic;
	private MySideLabel staff;
	private MySideLabel org;
	private MySideLabel examdoc,salary,cityprice;
	private MySideLabel exit;
	private JMenuItem viewprofits, viewoperate;
	private JPopupMenu viewstatisticpop;

	private boolean isclicked = false;
	
	public managerMenuUI(MainUIService main,String id){

		setLayout(null);
		this.m = main;
		pane = this;
		card = new CardLayout();	
		
		int base = 170;
		int width = 50;
		int height = 150;
		Font font = new Font("苹方 中等",Font.PLAIN,17);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(card);
		mainPanel.setOpaque(false);
		mainPanel.setBounds(150, 0, 850, 700);
		this.add(mainPanel);

		m.setcard1(card);
		m.setpane1(mainPanel);
		
		login = new User();
		this.id = id;		
		UserInfoSignVO vo = login.getUserInfo(id);
		String name = vo.getName();
		
		JLabel user = new JLabel();
		ImageIcon userimage = new ImageIcon("picture/headpro.png");
		user.setIcon(userimage);
		user.setBounds(0, 10, 150, 80);
		this.add(user);
		
		username = new JLabel();
		username.setBounds(0, 100, 150, 20);
		username.setText(name);
		username.setForeground(Color.WHITE);
		username.setFont(new Font("苹方 中等",Font.PLAIN,16));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(username);
		
		userid = new JLabel();
		userid.setBounds(0, 120, 150, 20);
		userid.setText(id);
		userid.setForeground(Color.WHITE);
		userid.setFont(new Font("苹方 中等",Font.PLAIN,16));
		userid.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(userid);
		
		log = new MySideLabel("查询日志");
		log.setBounds(0, base, height, width);
		
		this.add(log);
		
		staff = new MySideLabel("人员管理");
		staff.setBounds(0, base+width, height, width);
		
		this.add(staff);
		
		org = new MySideLabel("机构管理");
		org.setBounds(0, base+2*width, height, width);
		
		this.add(org);
		
		examdoc = new MySideLabel("审批单据");
		examdoc.setBounds(0, base+3*width, height, width);
		
		this.add(examdoc);
		
		statistic = new MySideLabel("查看统计分析");
		statistic.setBounds(0, base+4*width, height, width);
		this.add(statistic);
		
		salary = new MySideLabel("制定薪水策略");
		salary.setBounds(0, base+5*width, height, width);
		this.add(salary);
		
		cityprice = new MySideLabel("制定距离价格");
		cityprice.setBounds(0, base+6*width, height, width);
		this.add(cityprice);
		
		viewstatisticpop = new JPopupMenu();
		viewstatisticpop.setFont(font);
		viewprofits = new JMenuItem("查看成本收益表");
		viewprofits.setFont(font);
		viewprofits.setPreferredSize(new Dimension(170,50));
		viewprofits.setOpaque(true);
		viewprofits.setBackground(Color.WHITE);
		
		viewoperate = new JMenuItem("查看经营状况表");
		viewoperate.setFont(font);
		viewoperate.setPreferredSize(new Dimension(170,50));
		viewoperate.setOpaque(true);
		viewoperate.setBackground(Color.WHITE);
		
		viewstatisticpop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(135,206,250)));
		//viewstatisticpop.setSize(150,100);
		viewstatisticpop.setOpaque(true);
		viewstatisticpop.setBackground(Color.WHITE);
		viewstatisticpop.add(viewprofits);
		viewstatisticpop.add(viewoperate);
		
		exit = new MySideLabel("退出");
		exit.setBounds(0, 600, height, width);
		
		this.add(exit);
		
		this.setBounds(0, 30, 1200, 900);
		
		Listener listener = new Listener();
		ActListener actlis = new ActListener();
		
		log.addMouseListener(listener);		
		staff.addMouseListener(listener);	
		org.addMouseListener(listener);	
		examdoc.addMouseListener(listener);	
		statistic.addMouseListener(listener);	
        exit.addMouseListener(listener);
        salary.addMouseListener(listener);
        cityprice.addMouseListener(listener);
        viewprofits.addActionListener(actlis);
        viewoperate.addActionListener(actlis);
	}
	
	private class Listener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
			if(e.getSource()==exit){
				
				exit.whenClickHappend();
				login.SignOut(id);
				m.jumpToLogInUI();		
			}else if(e.getSource()==log){
				restoreAll();
				log.whenClickHappend();
				m.jumpToViewSysLogUI();	
			}else if(e.getSource()==staff){
				restoreAll();
				staff.whenClickHappend();
				m.jumpTomanagerMemberUI();
			}else if(e.getSource()==org){
				restoreAll();
				org.whenClickHappend();
				m.jumpTomanagerOrgUI();
			}else if(e.getSource()==examdoc){
				restoreAll();
				examdoc.whenClickHappend();
				m.jumpTomanagerExamDocUI();
			}else if (e.getSource() == statistic) {
				//statistic.whenClickHappend();
				if (!isclicked) {
					isclicked = true;
					viewstatisticpop.show(pane, 150, 370);
				} else {
					isclicked = false;
					viewstatisticpop.setVisible(false);
				}
			}else if(e.getSource()==salary){
				restoreAll();
				salary.whenClickHappend();
				m.jumpTomanagerSalaryUI();
			}else if(e.getSource()==cityprice){
				restoreAll();
				cityprice.whenClickHappend();
				m.jumpTomanagerCityPriceUI();
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==exit){
				exit.whenMouseOnIt();
					
			}else if(e.getSource()==log){
				log.whenMouseOnIt();
			
			}else if(e.getSource()==staff){
				staff.whenMouseOnIt();
				
			}else if(e.getSource()==org){
				org.whenMouseOnIt();
				
			}else if(e.getSource()==examdoc){
				examdoc.whenMouseOnIt();
				
			}else if (e.getSource() == statistic) {
				statistic.whenMouseOnIt();
				
			}else if(e.getSource()==salary){
				salary.whenMouseOnIt();
				
			}else if(e.getSource()==cityprice){
				cityprice.whenMouseOnIt();
				
			}
			
		}

		public void mouseExited(MouseEvent e) {
			if(e.getSource()==exit){
				exit.whenMouseleaveit();
					
			}else if(e.getSource()==log){
				log.whenMouseleaveit();
			
			}else if(e.getSource()==staff){
				staff.whenMouseleaveit();
				
			}else if(e.getSource()==org){
				org.whenMouseleaveit();
				
			}else if(e.getSource()==examdoc){
				examdoc.whenMouseleaveit();
				
			}else if (e.getSource() == statistic) {
				statistic.whenMouseleaveit();
				
			}else if(e.getSource()==salary){
				salary.whenMouseleaveit();
				
			}else if(e.getSource()==cityprice){
				cityprice.whenMouseleaveit();
				
			}
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}	
	
	private class ActListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			restoreAll();
			if (e.getSource().equals(viewprofits)) {
				statistic.whenClickHappend();
				m.jumpToViewProfitUI();
			 //   m.jumpToViewStatisticUI("成本收益表");
			} else if (e.getSource().equals(viewoperate)) {
				statistic.whenClickHappend();
				m.jumpToViewOperateUI();
			//	m.jumpToViewStatisticUI("经营状况表");
			}
			updateUI();
		}

	}
	
	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void restoreAll(){
		log.restore();
		statistic.restore();
		staff.restore();
		org.restore();
		examdoc.restore();
		salary.restore();
		cityprice.restore();
		exit.restore();
	}
	
}
