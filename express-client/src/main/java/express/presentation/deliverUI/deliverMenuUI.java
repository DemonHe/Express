package express.presentation.deliverUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MySideLabel;
import express.vo.UserInfoSignVO;

public class deliverMenuUI extends JPanel{
	private CardLayout card;
	   
	private MainUIService main;
	private LogInBLService login;
	private String id;
	private JPanel actionPanel;	
	private JLabel username,userid;	 
	private MySideLabel button_order;
	private MySideLabel button_info;
    private MySideLabel button_exit;

	public deliverMenuUI(MainUIService m,String id){
		
		this.setLayout(null);
		this.main=m;
		card=new CardLayout();

		int base = 250;
		int width = 50;		
		Font font = new Font("隶书",Font.PLAIN,20);
		
		actionPanel=new JPanel();
		actionPanel.setLayout(card);
		actionPanel.setBounds(150, 0, 850, 700);
		actionPanel.setOpaque(false);
		this.add(actionPanel);
		
		main.setcard1(card);
		main.setpane1(actionPanel);
		
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
		
		JListener listener=new JListener();
		
		button_order = new MySideLabel("输入订单");
		//button_order.setFont(font);
		 button_order.setBounds(0, base, 150, width);
		 button_order.addMouseListener(listener);//加监听
		
		 button_info = new MySideLabel("输入收件信息");
		 //button_info.setFont(new Font("隶书",Font.PLAIN,18));
		 button_info.setBounds(0, base+width, 150, width);
		 button_info.addMouseListener(listener);//加监听
		
		 button_exit = new MySideLabel("退出");
		// button_exit.setFont(font);
		 button_exit.setBounds(0, 600, 150, 50); 
		 button_exit.addMouseListener(listener);
		
		 this.add(button_order);
		 this.add(button_info);
		 this.add(button_exit);
		 this.add(username);
		 this.add(userid);
		
		 this.setBounds(0,0,1000,700);	
	}
	class JListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			restoreAll();
			 if (arg0.getSource()==button_order){
				 button_order.whenClickHappend();
				 main.jumpTodeliverOrderUI();		
				 System.out.println("应该跳转到“输入订单”界面的");	
				 
			 }
			 else if (arg0.getSource()==button_info){
				 button_info.whenClickHappend();
				 main.jumpTodeliverReceiveUI(); 
				 System.out.println("应该跳转到“输入收件信息”界面的");
				 
			 }
			 else if (arg0.getSource()==button_exit){
				 login.SignOut(id);
				 main.jumpToLogInUI();
				 System.out.println("应该回到登陆界面的");	
				 
			 }
			 updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			 if (arg0.getSource()==button_order){
				 button_order.whenMouseOnIt();
				
				 
			 }
			 else if (arg0.getSource()==button_info){
				 button_info.whenMouseOnIt();
				
			 }
			 else if (arg0.getSource()==button_exit){
				button_exit.whenMouseOnIt();
				 
			 }
			
		}

		public void mouseExited(MouseEvent arg0) {
			if (arg0.getSource()==button_order){
				 button_order.whenMouseleaveit();
				
				 
			 }
			 else if (arg0.getSource()==button_info){
				 button_info.whenMouseleaveit();
				
			 }
			 else if (arg0.getSource()==button_exit){
				button_exit.whenMouseleaveit();
				 
			 }
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void restoreAll(){
		button_order.restore();
		button_exit.restore();
		button_info.restore();
	}
	
	
}
