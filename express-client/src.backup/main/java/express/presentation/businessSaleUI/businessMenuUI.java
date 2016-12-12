package express.presentation.businessSaleUI;

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

public class businessMenuUI extends JPanel{
	
	private MainUIService main;
	private LogInBLService login;
	private String id;
	private CardLayout card=new CardLayout();	
	private JPanel businessPanel;	
	private JLabel username, userid;

	private MySideLabel button_shipment = new MySideLabel("车辆装车管理");
	private MySideLabel button_vehicle = new MySideLabel("车辆信息管理");
	private MySideLabel button_driver = new MySideLabel("司机信息管理");
	private MySideLabel button_recieve = new MySideLabel("建立收款�?");
	private MySideLabel button_deliver = new MySideLabel("生成派件�?");
	private MySideLabel button_arrival = new MySideLabel("生成到达�?");
	private MySideLabel button_exit=new MySideLabel("�?�?");
	
	public businessMenuUI(MainUIService m,String id){
		int base = 170;
		int width = 50;
		//Font font = new Font("隶书",Font.PLAIN,19);
		
		this.main=m;
		setLayout(null);
	
		businessPanel=new JPanel();
		businessPanel.setLayout(card);
		businessPanel.setBounds(150, 0, 850, 700);
		businessPanel.setOpaque(false);
		this.add(businessPanel);
		
		main.setcard1(card);
		main.setpane1(businessPanel);
		
		JListener listener=new JListener();
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
			
		button_shipment.setBounds(0, base, 150, width);
		//button_shipment.setFont(font);
        button_shipment.addMouseListener(listener);//加监�?
	
		button_vehicle.setBounds(0, base+width, 150, width);
		//button_vehicle.setFont(font);
		button_vehicle.addMouseListener(listener);//加监�?		
		
		button_driver.setBounds(0, base+2*width, 150, width);
		//button_driver.setFont(font);
		button_driver.addMouseListener(listener);//加监�?
		
		button_recieve.setBounds(0, base+3*width, 150, width);
		//button_recieve.setFont(font);
		button_recieve.addMouseListener(listener);//加监�?
		
		button_deliver.setBounds(0, base+4*width, 150, width);
		//button_deliver.setFont(font);
		button_deliver.addMouseListener(listener);
		
		button_arrival.setBounds(0, base+5*width, 150, width);
		//button_arrival.setFont(font);
		button_arrival.addMouseListener(listener);
		
		
		button_exit.setBounds(0, 600, 150, 50); 
		//button_exit.setFont(font);
		button_exit.addMouseListener(listener);
		
		
		this.add(button_arrival);
		this.add(button_deliver);
		this.add(button_driver);
		this.add(button_recieve);
		this.add(button_shipment);
		this.add(button_vehicle);
		this.add(button_exit);
	}
	
	private class JListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
		 
			restoreAll();
			if (arg0.getSource()==button_shipment){
				button_shipment.whenClickHappend();
				main.jumpTobusinessShipmentUI();
				System.out.println("应该跳转到车辆装车管理界面的");			 
				
			}
			else if (arg0.getSource()==button_vehicle){
				button_vehicle.whenClickHappend();
				main.jumpTobusinessVehicleUI();
				System.out.println("应该跳转到车辆信息管理界面的");
				
			}
			else if (arg0.getSource()==button_driver){
				button_driver.whenClickHappend();
				main.jumpTobusinessDriverUI();
				System.out.println("应该跳转到司机信息管理界面的");
		
			}
			else if (arg0.getSource()==button_recieve){
				button_recieve.whenClickHappend();
				main.jumpTobusinessReceiveUI();
				System.out.println("应该跳转到建立收款单界面�?");				
				
			}
			else if (arg0.getSource()==button_deliver){
				button_deliver.whenClickHappend();
				main.jumpTobusinessDeliverUI();
				System.out.println("应该跳转到生成派件单界面�?");			
				
			}
			else if (arg0.getSource()==button_arrival){
				button_arrival.whenClickHappend();
				main.jumpTobusinessArrivalUI();
				System.out.println("应该跳转到生成到达单界面�?");

			}
			else if (arg0.getSource()==button_exit){
				login.SignOut(id);
				main.jumpToLogInUI();
				 System.out.println("应该回到登陆界面�?");
				
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			if (arg0.getSource()==button_shipment){
				button_shipment.whenMouseOnIt();			 
			}
			else if (arg0.getSource()==button_vehicle){
				button_vehicle.whenMouseOnIt();
				
			}
			else if (arg0.getSource()==button_driver){
				button_driver.whenMouseOnIt();
		
			}
			else if (arg0.getSource()==button_recieve){
				button_recieve.whenMouseOnIt();			
				
			}
			else if (arg0.getSource()==button_deliver){
				button_deliver.whenMouseOnIt();
			}
			else if (arg0.getSource()==button_arrival){
				button_arrival.whenMouseOnIt();
			}
			else if (arg0.getSource()==button_exit){
				button_exit.whenMouseOnIt();
			}	
		}

		public void mouseExited(MouseEvent arg0) {
			if (arg0.getSource()==button_shipment){
				button_shipment.whenMouseleaveit();			 
			}
			else if (arg0.getSource()==button_vehicle){
				button_vehicle.whenMouseleaveit();
				
			}
			else if (arg0.getSource()==button_driver){
				button_driver.whenMouseleaveit();
		
			}
			else if (arg0.getSource()==button_recieve){
				button_recieve.whenMouseleaveit();		
				
			}
			else if (arg0.getSource()==button_deliver){
				button_deliver.whenMouseleaveit();
			}
			else if (arg0.getSource()==button_arrival){
				button_arrival.whenMouseleaveit();
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
			button_shipment.restore();
			button_vehicle.restore();
			button_driver.restore(); 
			button_recieve .restore();
			button_deliver .restore();
			button_arrival.restore(); 
			button_exit.restore();
	}
	
	

}
