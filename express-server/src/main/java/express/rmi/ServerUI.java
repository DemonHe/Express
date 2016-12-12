package express.rmi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import express.po.IPPO;
import javafx.scene.layout.Pane;

public class ServerUI extends JPanel{//713 480
	public JButton refresh,stop,exit,minium;
	public JFrame  frame;
	public JLabel counter;
	public JLabel time;
	
	public String hour,minute,second;
	public int ihour,iminute,isecond;
	public JTextArea iparea,namearea;
	
	public void init(){
	
		iparea=new JTextArea();
		iparea.setSize(300,300);
		iparea.setOpaque(false);
		iparea.setForeground(Color.white);
		Font font=new Font("苹方粗体",Font.PLAIN, 18);
		iparea.setFont(font);
		iparea.setLocation(22,350);
		iparea.setEditable(false);
		
		namearea=new JTextArea();
		namearea.setSize(220,500);
		namearea.setOpaque(false);
		namearea.setForeground(Color.white);
		Font font2=new Font("苹方粗体",Font.PLAIN, 15);
		namearea.setFont(font2);
		namearea.setLocation(350,215);
		namearea.setEditable(false);
		
		
		
		
		frame=new JFrame();
		frame.setSize(725, 488);
		JPanel pane=new ServerUI();
		pane.setLayout(null);
		frame.getContentPane().add(pane);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setBackground(Color.white);
		
		refresh=new JButton();
		stop=new JButton();
		
		refresh.setLocation(560,160);
		refresh.setSize(160,160);
		refresh.setBorderPainted(false);
		refresh.setContentAreaFilled(false);
		pane.add(refresh);
		
		stop.setLocation(560,325);
		stop.setSize(160,160);
		stop.setBorderPainted(false);
		stop.setContentAreaFilled(false);
		pane.add(stop);
		
		
		minium=new JButton();
		exit=new JButton();
		minium.setSize(16, 16);
		minium.setContentAreaFilled(false);
		minium.setBorderPainted(false);
		exit.setSize(16,16);
		exit.setLocation(700,12);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		minium.setLocation(672,12);
		
		pane.add(exit);
		pane.add(minium);
		pane.add(namearea);
		
		Font fontforcounter=new Font("苹方粗体",Font.PLAIN, 70);
		counter=new JLabel();
		counter.setFont(fontforcounter);
		counter.setForeground(Color.white);
		counter.setText("0");
		counter.setSize(90,90);
		counter.setLocation(105,190);
		pane.add(counter);
		pane.add(iparea);
		
		Font fontfortimeer=new Font("苹方粗体",Font.PLAIN, 30);
		time=new JLabel();
		time.setFont(fontfortimeer);
		time.setForeground(Color.white);
		time.setText(" 0: 0: 0");
		time.setSize(150,30);
		time.setLocation(180,165);
		
		pane.add(time);
		frame.setVisible(true);	
		
		
		Listener listener=new Listener();
		exit.addMouseListener(listener);
		stop.addMouseListener(listener);
		minium.addMouseListener(listener);
		refresh.addMouseListener(listener);
		
		
		
		
		hour="000";
		minute="00";
		second="00";
		
		ihour=0;
		iminute=0;
		isecond=0;
		
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				isecond++;
				if(isecond==60){
					isecond=0;
					iminute++;
					if(iminute==60){
						ihour++;
					}
				}
				
				String result="";
				result+=ihour;
				result+=":";
				result+=iminute;
				result+=":";
				result+=isecond;
				time.setText(result);
			}
		},1000,1000);
	
		Server.start();
		//getNewdata();
	}
	
	public void getNewdata(){
		iparea.setText("");
		namearea.setText("");
		
		
		ArrayList<IPPO> list=IPmanager.iplist;
		int len=list.size();
		String ipstr="";
		String namestr="";
		
		counter.setText(""+len);
		for(int i=0;i<len;i++){
			IPPO po=list.get(i);
			ipstr+=po.getIP();
			ipstr+="\n";
			namestr+=po.getName();
			namestr+="\n";
		}
		iparea.setText(ipstr);
		namearea.setText(namestr);
	}
	
	
	private class Listener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==refresh){
				getNewdata();
			}else if (e.getSource()==stop) {
				System.exit(0);
			}else if (e.getSource()==minium) {
				 frame.setExtendedState(JFrame.ICONIFIED);
			}else if(e.getSource()==exit){
				System.exit(0);
			}
			

			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args){
		
		ServerUI ui=new ServerUI();
		ui.init();
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	public void paintComponent(Graphics g){
		Image image=new ImageIcon("picture/server.png").getImage();
		g.drawImage(image,0,0,this);
}
}
