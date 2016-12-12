package express.rmi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Server {
	//private static JButton button;
	
	public static void start(){
		try{
	        System.out.println("Try start server...");
	        RMIServer.init();
	        System.out.println("Server is now running!");
	        
//	        Font font = new Font("幼圆", Font.PLAIN, 20);
//	        
//	        JFrame frame=new JFrame("服务器");
//	        frame.setSize(500, 300);
//	        frame.setLayout(null);
//	        
//	        
//	        JLabel label=new JLabel("服务器开启成功!");
//	        label.setBounds(150,50,200,100);
//	        label.setFont(font);
//	        label.setForeground(Color.GREEN);
//	        
//	        button =new JButton("关闭服务器");
//	        button.setBounds(130, 150, 200, 50);
//	        button.setFont(font);
//	        button.setBackground(Color.RED);
//	        JListener listener=new JListener();
//	        button.addMouseListener(listener);
//	        
//	        frame.add(label);
//	        frame.add(button);
//	        
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setLocationRelativeTo(null);
//			frame.setVisible(true);
//	        
	    }catch (ServerException e) {
	    	e.printStackTrace();
	        System.out.println("Server starts fail!"); 
	        JOptionPane.showMessageDialog(null, "服务器开启失败", "提示",
					JOptionPane.ERROR_MESSAGE);
	    }
		
	}
	private static class JListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
//			 if (e.getSource()==button){
//				 System.exit(0);
//			 }
			
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
