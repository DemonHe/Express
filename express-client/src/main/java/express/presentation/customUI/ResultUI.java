package express.presentation.customUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import express.businessLogic.searchBL.Search;
import express.businesslogicService.customerBLService.SearchBLService;
import express.vo.GoodTransStatusVO;

public class ResultUI {
	private SearchBLService sbs;
	private final JLabel errortip=new JLabel("");
	JButton confirm=new JButton(new ImageIcon("picture/search1.png"));
	JButton exitbutton= new JButton(new ImageIcon("picture/x.png"));
	String orderID;
	JLabel id=new JLabel("");
	JTextField orderid; 
	
	JLabel status1=new JLabel("");
	JLabel status2=new JLabel("");
	JLabel status3=new JLabel("");
	JLabel status4=new JLabel("");
	JLabel status5=new JLabel("");
	JLabel status6=new JLabel("");
	JLabel status7=new JLabel("");
	
	
	JLabel time1=new JLabel("");
	JLabel time2=new JLabel("");
	JLabel time3=new JLabel("");
	JLabel time4=new JLabel("");
	JLabel time5=new JLabel("");
	JLabel time6=new JLabel("");
	JLabel time7=new JLabel("");
	
	ArrayList<JLabel> statuslist=new ArrayList<JLabel>();
	ArrayList<JLabel> timelist=new ArrayList<JLabel>();
	
	
	public ResultUI(){
		statuslist.add(status1);
		statuslist.add(status2);
		statuslist.add(status3);
		statuslist.add(status4);
		statuslist.add(status5);
		statuslist.add(status6);
		statuslist.add(status7);
		
		
		timelist.add(time1);
		timelist.add(time2);
		timelist.add(time3);
		timelist.add(time4);
		timelist.add(time5);
		timelist.add(time6);
		timelist.add(time7);
	}
	
	
	
	
	public JPanel getResult(){
		Font f=new Font("苹方 粗体",Font.PLAIN,20);
		MyDrawPanel2 pane=new MyDrawPanel2();
		pane.setLayout(null);
		pane.setBackground(Color.WHITE);
		
		
		final JPanel buttonPanel = new JPanel();  
		buttonPanel.setBackground(null);                      // 把背景设置为会  
		buttonPanel.setOpaque(false); 
		buttonPanel.setLayout(null);
		buttonPanel.setSize(820,550);
		//pane.add(buttonPanel);
			
		orderid= new JTextField();
		orderid.setSize(570,48);
		orderid.setLocation(10,23);
		orderid.setFont(f);
		orderid.addKeyListener(new Keylistener());
		pane.add(orderid);
		
		confirm.setSize(36,36);
		confirm.setLocation(537, 29);
		confirm.setBorderPainted(false);
		pane.add(confirm);	

		exitbutton.setSize(30,30);
		exitbutton.setContentAreaFilled(false);
		exitbutton.setBorderPainted(false);
		exitbutton.setLocation(790,0);
		pane.add(exitbutton);
		
		status1.setSize(300,35);
		status1.setFont(f);
		status1.setLocation(50,193);
		pane.add(status1);
		
		status2.setSize(300,35);
		status2.setFont(f);
		status2.setLocation(50,235);
		pane.add(status2);
		
		status3.setSize(300,35);
		status3.setFont(f);
		status3.setLocation(50,279);
		pane.add(status3);
		
		status4.setSize(300,35);
		status4.setFont(f);
		status4.setLocation(50,321);
		pane.add(status4);
		
		status5.setSize(300,35);
		status5.setFont(f);
		status5.setLocation(50,367);
		pane.add(status5);
		
		status6.setSize(300,35);
		status6.setFont(f);
		status6.setLocation(50,413);
		pane.add(status6);
		
		status7.setSize(300,35);
		status7.setFont(f);
		status7.setLocation(50,455);
		pane.add(status7);
		
		time1.setSize(150,30);
		time1.setFont(f);
		time1.setLocation(640, 198);
		pane.add(time1);
		
		time2.setSize(150,30);
		time2.setFont(f);
		time2.setLocation(640, 240);
		pane.add(time2);
		
		time3.setSize(150,30);
		time3.setFont(f);
		time3.setLocation(640, 284);
		pane.add(time3);
		
		time4.setSize(150,30);
		time4.setFont(f);
		time4.setLocation(640, 326);
		pane.add(time4);
		
		time5.setSize(150,30);
		time5.setFont(f);
		time5.setLocation(640, 372);
		pane.add(time5);
		
		time6.setSize(150,30);
		time6.setFont(f);
		time6.setLocation(640, 418);
		pane.add(time6);
		
		time7.setSize(150,30);
		time7.setFont(f);
		time7.setLocation(640, 460);
		pane.add(time7);
		
		
		errortip.setSize(300,30);
		errortip.setForeground(Color.RED);
		errortip.setLocation(580, 30);
		pane.add(errortip);
		
		id.setSize(350,30);
		id.setFont(f);
		id.setLocation(130,97);
		pane.add(id);
		
		
		exitbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				yesClicked();
			}
		});
		return pane;
	}
	
	
	
	
	public void yesClicked(){
		if(orderid.getText().equals("")){
			errortip.setText("请输入订单条形码号");

		}
		else{
			if(checkOrder(orderid.getText())){
				
			}
			else{
				errortip.setText("订单不存在");
			}
		}
	}
	
	
	private class Keylistener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				yesClicked();
			}
		}
	}
	
	
	
	
	
	
	public boolean checkOrder(String orderid){
		sbs=new Search();
		
		GoodTransStatusVO vo=new GoodTransStatusVO();
		vo=sbs.getOrderIDStatus(orderid);
		if(vo==null||vo.getOrderID().equals("-1")){
			return false;
		}
		else {
			
			id.setText(orderid);
			
			ArrayList<String> stat=vo.getstatusList();
			ArrayList<String> time=vo.getTime();
			int len=stat.size();
			
			
			for(int i=0;i<7;i++){
				statuslist.get(i).setText("");
				timelist.get(i).setText("");
			}
			
			for(int i=0;i<len;i++){
				statuslist.get(i).setText(stat.get(i));
				timelist.get(i).setText(time.get(i));
			}
			errortip.setText("");
			return true;
		}
		
		
	}
	
}
class MyDrawPanel2 extends JPanel{
	public void paintComponent(Graphics g){
		Image image=new ImageIcon("picture/result.png").getImage();
		g.drawImage(image,0,0,this);
}
}