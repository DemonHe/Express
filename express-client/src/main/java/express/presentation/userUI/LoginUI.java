package express.presentation.userUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Desktop.Action;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import express.Client.Client;
import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.dataService.ipandname.IPDataService;
import express.po.IPPO;
import express.rmi.AboutSystem;
import express.rmi.RMIClient;
import express.vo.SignInVO;

public class LoginUI extends JFrame {

	private JButton exitbutton, yesbutton;
	private JTextField usertext;
	private JPasswordField passtext;
	private LogInBLService login;

	public LoginUI() {
		this.setLayout(null);
		this.setSize(300, 350);

		login = new User();
		Listener listener = new Listener();
		Font f = new Font("苹方 中等", Font.PLAIN, 17);

		ImageIcon image = new ImageIcon("picture/login22.png");
		JLabel background = new JLabel(image);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		Container cp = this.getContentPane();
		cp.setLayout(null); // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
		((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了

		usertext = new JTextField("[用 户 名]");
		usertext.setSize(190, 46);
		usertext.setLocation(77, 97);
		usertext.addFocusListener(new Foclistener());
		usertext.setFont(f);
		this.add(usertext);

		passtext = new JPasswordField();
		passtext.setSize(190, 46);
		passtext.setLocation(77, 157);
		passtext.setFont(f);
		passtext.addKeyListener(new Keylistener());
		this.add(passtext);

		exitbutton = new JButton(new ImageIcon("picture/quitlogin.png"));
		exitbutton.setSize(28, 28);
		exitbutton.setBorderPainted(false);
		exitbutton.setContentAreaFilled(false);
		exitbutton.setLocation(270, 0);
		exitbutton.addMouseListener(listener);
		this.add(exitbutton);

		yesbutton = new JButton(new ImageIcon("picture/yesbutton.png"));
		yesbutton.setSize(252, 46);
		yesbutton.setBorderPainted(false);
		yesbutton.setContentAreaFilled(false);
		yesbutton.setLocation(25, 260);
		yesbutton.addMouseListener(listener);
		this.add(yesbutton);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	private void yesClicked() {
		String id = usertext.getText();
		String key = String.valueOf(passtext.getPassword());
		SignInVO vo = login.signIn(id, key);
		if (vo.equals(SignInVO.PERMIT)) {
			dispose();
			new SignInUI(id);
		} else if (vo.equals(SignInVO.ID_ERROR)) {
			JOptionPane.showMessageDialog(null, "用户名填写错误", "提示",
					JOptionPane.ERROR_MESSAGE);
		} else if (vo.equals(SignInVO.PASSWORD_ERROR)) {
			JOptionPane.showMessageDialog(null, "密码输入错误", "提示",
					JOptionPane.ERROR_MESSAGE);
		} else if (vo.equals(SignInVO.SIGNED)) {
			JOptionPane.showMessageDialog(null, "您已登录", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == yesbutton) {
				yesClicked();
			} else if (e.getSource() == exitbutton) {
				IPDataService iprmi = RMIClient.getIPObject();
				IPPO ip = new IPPO(AboutSystem.getMyIp(), AboutSystem.getMyName());
				try {
					iprmi.minusIP(ip);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
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
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class Keylistener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				yesClicked();
			}
		}
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == usertext) {
				if (usertext.getText().equals("[用 户 名]")) {
					usertext.setText("");
				}
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == usertext) {
				if (usertext.getText().isEmpty()) {
					usertext.setText("[用 户 名]");
				}
			}
		}

	}

}
