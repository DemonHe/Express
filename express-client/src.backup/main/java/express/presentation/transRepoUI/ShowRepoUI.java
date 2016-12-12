package express.presentation.transRepoUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.po.Area;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;

public class ShowRepoUI extends JDialog {

	MyOtherBlueLabel ok;
	MyOtherGreenLabel exit;
	JButton[] buttonList = new JButton[12];
	JTextField rowtf, postf;
	String text = "";
	String orgID;

	public ShowRepoUI(String orgID, Area area, int row, JTextField rowtf,
			JTextField postf) {
		this.setTitle("仓库信息");
		this.setLayout(null);
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.white);
		this.setSize(400, 450);
		//this.setPreferredSize(new Dimension(400,500));
		this.setLocationRelativeTo(null);
		this.rowtf = rowtf;
		this.postf = postf;
		this.orgID = orgID;

		Color green = new Color(34, 139, 34);
		Font font = new Font("楷体", Font.PLAIN, 20);
		AdjustRepoBLService adjust = new RepoController();
		String[] list = adjust.getPosition(orgID, area, row);

		Action action = new Action();
		JLabel title = new JLabel();
		title.setText("�?" + row + "排仓库位置�?�择�?");
		title.setFont(font);
		title.setBounds(0, 20, 400, 30);
		this.add(title);

		for (int i = 0; i < 12; i++) {
			JButton button = new JButton();
			buttonList[i] = button;
			button.setBounds(100 * (i % 4), 60 + 100 * (i / 4), 100, 100);
			button.setBackground(Color.WHITE);
			String s = list[i];
			button.setActionCommand(s);
			if (s.startsWith("#")) {
				button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				s = s.substring(1);
			} else {
				button.setBorder(BorderFactory.createLineBorder(green, 3));
			}
			int index = s.indexOf("+");
			button.addActionListener(action);
			button.setText(s.substring(0, index) + " �?" + "\n" + s.substring(index+1) + " �?");
			button.setFont(font);
			this.add(button);
		}
		
		Listener listener = new Listener();
		
		ok = new MyOtherBlueLabel("确定");
		ok.setBounds(20, 390, 150, 40);
		this.add(ok);
		
		exit = new MyOtherGreenLabel("取消");
		
		exit.setBounds(200, 390, 150, 40);
		
		this.add(exit);
		
		ok.addMouseListener(listener);
		exit.addMouseListener(listener);
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == exit) {
				dispose();
			} else if (e.getSource() == ok) {
				
				if(text.equals("")){
					JOptionPane.showConfirmDialog(null, "请�?�择�?个仓库位�?", null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							null);
				}else{
					if(text.startsWith("#")){
						JOptionPane.showConfirmDialog(null, "该位置正在使�?", null,
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
								null);
					}else{
						int index = text.indexOf("+");
						rowtf.setText(text.substring(0, index));
						postf.setText(text.substring(index+1));
						dispose();
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
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

	private class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			JButton button = (JButton) e.getSource();
			text = s;
			for (JButton b : buttonList) {
				b.setBackground(Color.WHITE);
			}
			if (s.startsWith("#")) {
				button.setBackground(new Color(255, 218, 185));
			} else {
				button.setBackground(new Color(127, 255, 212));
			}
			repaint();
		}

	}
}
