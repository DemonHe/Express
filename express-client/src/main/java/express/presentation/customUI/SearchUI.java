package express.presentation.customUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.channels.NonWritableChannelException;
import java.security.SecureRandom;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import express.rmi.RMIClient;

public class SearchUI extends JPanel {
	final JFrame searchframe = new JFrame();
	JPanel pane;
	final JLabel errortip = new JLabel("");
	ResultUI result;
	JButton exitbutton;
	JTextField orderid;

	public void init() {
		pane = new SearchUI();
		Font f = new Font("苹方 粗体", Font.PLAIN, 20);
		exitbutton = new JButton(new ImageIcon("picture/x.png"));

		JButton confirm = new JButton(new ImageIcon("picture/search1.png"));
		
		Foclistener focus = new Foclistener();
		orderid = new JTextField("请输入订单条形码号");
		orderid.setFont(f);
		orderid.addKeyListener(new Keylistener());
		orderid.setSize(583, 48);
		orderid.setLocation(130, 249);
		orderid.addFocusListener(focus);
		
		pane.add(orderid);

		searchframe.getContentPane().add(pane);
		searchframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 540*38 100*38
		pane.setLayout(null);
		pane.setBackground(Color.WHITE);
		searchframe.setSize(820, 550);

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(null); // 把背景设置为会
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
		buttonPanel.setSize(820, 550);
		//pane.add(buttonPanel);

		confirm.setSize(36, 36);
		confirm.setLocation(541, 6);
		confirm.setBorderPainted(false);
		// buttonPanel.add(confirm);
		orderid.add(confirm);

		searchframe.setLocationRelativeTo(null);
		searchframe.setUndecorated(true);
		searchframe.setVisible(true);

		exitbutton.setSize(30, 30);
		exitbutton.setContentAreaFilled(false);
		exitbutton.setBorderPainted(false);
		exitbutton.setLocation(790, 0);
		
		pane.add(exitbutton);

		errortip.setSize(300, 100);
		errortip.setFont(new Font("苹方 粗体", Font.PLAIN, 22));
		errortip.setForeground(Color.white);
		errortip.setLocation(350, 270);
		pane.add(errortip);

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

	}

	public void yesClicked() {
		if (orderid.getText().equals("")) {
			errortip.setForeground(Color.WHITE);
			errortip.setText("请输入订单条形码号");

		} else {
			result = new ResultUI();
			if (result.checkOrder(orderid.getText())) {
				errortip.setText("");
				pane.setVisible(false);
				searchframe.setContentPane(result.getResult());
			} else {
				errortip.setForeground(Color.RED);
				errortip.setText("订 单 不 存 在");
			}
		}
	}

	public class Keylistener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				yesClicked();
			}
			repaint();
		}
	}

	public static void main(String[] args) {
		try {
			RMIClient.init();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SearchUI ui = new SearchUI();
		ui.init();
	}

	public void paintComponent(Graphics g) {
		Image image = new ImageIcon("picture/bkbkbk1.png").getImage();
		g.drawImage(image, 0, 0, this);
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == orderid) {
				if (orderid.getText().equals("请输入订单条形码号")) {
					orderid.setText("");
				}
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == orderid) {
				if (orderid.getText().isEmpty()) {
					orderid.setText("请输入订单条形码号");
				}
			}
		}

	}

}
