package express.presentation.financialUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MySideLabel;
import express.vo.UserInfoSignVO;

public class FinanceMenuUI extends JPanel {

	private MainUIService m;
	private LogInBLService login;
	private String id;
	private CardLayout card;
	// private JFrame frame;
	private JPanel mainPanel, pane;
	private JLabel username, userid;
	// private JMenuBar viewstatisticbar, createstatisticbar;
	// private JMenu viewstatisticm, createstatisticm;
	private JMenuItem viewprofits, viewoperate, createprofits, createoperate;
	private JPopupMenu viewstatisticpop, createstatisticpop;
	private MySideLabel log;
	private MySideLabel viewstatistic, createstatistic;
	private MySideLabel sumReceiveDoc, initAccount, payment, accountmanage;
	private MySideLabel exit;

	private boolean isclickedv = false;
	private boolean isclickedc = false;
	private boolean ishigh;

	public FinanceMenuUI(MainUIService main, String id, boolean high) {
		setLayout(null);
		this.m = main;
		pane = this;
		card = new CardLayout();
		ishigh = high;

		int base = 170;
		int width = 50;
		int height = 150;
		Font font = new Font("苹方 中等", Font.PLAIN, 17);

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
		username.setFont(new Font("苹方 中等", Font.PLAIN, 16));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(username);

		userid = new JLabel();
		userid.setBounds(0, 120, 150, 20);
		userid.setText(id);
		userid.setForeground(Color.WHITE);
		userid.setFont(new Font("苹方 中等", Font.PLAIN, 16));
		userid.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(userid);

		log = new MySideLabel("查询日志");
		log.setBounds(0, base, height, width);
		this.add(log);

		initAccount = new MySideLabel("期初建账");
		initAccount.setBounds(0, base + width, height, width);
		this.add(initAccount);

		payment = new MySideLabel("生成付款�?");
		payment.setBounds(0, base + 2 * width, height, width);
		this.add(payment);

		sumReceiveDoc = new MySideLabel("合计收款�?");
		sumReceiveDoc.setBounds(0, base + 3 * width, height, width);
		this.add(sumReceiveDoc);

		if (high) {
			accountmanage = new MySideLabel("账户管理");
			accountmanage.setBounds(0, base + 6 * width, height, width);
			this.add(accountmanage);
		}

		viewstatistic = new MySideLabel("查看统计分析");
		viewstatistic.setBounds(0, base + 4 * width, height, width);
		this.add(viewstatistic);

		// viewstatisticbar = new JMenuBar();
		// viewstatisticbar.setBounds(0, base + 5 * width, height, width);
		// viewstatisticm = new JMenu("查看统计分析");
		// viewstatisticm.setFont(font);
		viewstatisticpop = new JPopupMenu();
		viewstatisticpop.setFont(font);
		viewprofits = new JMenuItem("查看成本收益�?");
		viewprofits.setFont(font);
		viewprofits.setHorizontalAlignment(SwingConstants.CENTER);
		//viewprofits.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.LIGHT_GRAY));
		viewprofits.setPreferredSize(new Dimension(170,50));
		viewprofits.setOpaque(true);
		viewprofits.setBackground(Color.WHITE);
		
		viewoperate = new JMenuItem("查看经营状�?�表");
		viewoperate.setFont(font);
		viewoperate.setHorizontalAlignment(SwingConstants.CENTER);
		//viewoperate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
		viewoperate.setPreferredSize(new Dimension(170,50));
		viewoperate.setOpaque(true);
		viewoperate.setBackground(Color.WHITE);
		
		viewstatisticpop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(135,206,250)));
		//viewstatisticpop.setSize(150,100);
		viewstatisticpop.setOpaque(true);
		viewstatisticpop.setBackground(Color.WHITE);
		viewstatisticpop.add(viewprofits);
		viewstatisticpop.add(viewoperate);
		// viewstatisticm.add(viewprofits);
		// viewstatisticm.add(viewoperate);
		// viewstatisticbar.add(viewstatisticm);
		// this.add(viewstatisticbar);

		createstatistic = new MySideLabel("生成统计分析");
		createstatistic.setBounds(0, base + 5 * width, height, width);
		this.add(createstatistic);

		// createstatisticbar = new JMenuBar();
		// createstatisticbar.setBounds(0, base + 6 * width, height, width);
		// createstatisticm = new JMenu("生成统计分析");
		// createstatisticm.setFont(font);
		createstatisticpop = new JPopupMenu();
		createstatisticpop.setFont(font);
		
		createprofits = new JMenuItem("生成成本收益�?");
		createprofits.setFont(font);
		createprofits.setHorizontalAlignment(SwingConstants.CENTER);
		createprofits.setPreferredSize(new Dimension(170,50));
		createprofits.setOpaque(true);
		createprofits.setBackground(Color.WHITE);
		
		createoperate = new JMenuItem("生成经营状况�?");
		createoperate.setFont(font);
		createoperate.setHorizontalAlignment(SwingConstants.CENTER);
		createoperate.setPreferredSize(new Dimension(170,50));
		createoperate.setOpaque(true);
		createoperate.setBackground(Color.WHITE);
		
		createstatisticpop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(135,206,250)));
		createstatisticpop.setOpaque(true);
		createstatisticpop.setBackground(Color.WHITE);
		createstatisticpop.add(createprofits);
		createstatisticpop.add(createoperate);
		// createstatisticm.add(createprofits);
		// createstatisticm.add(createoperate);
		// createstatisticbar.add(createstatisticm);
		// this.add(createstatisticbar);

		exit = new MySideLabel("�?�?");
		exit.setBounds(0, 600, height, width);
		this.add(exit);

		this.setBounds(0, 0, 1200, 900);

		Listener listener = new Listener();
		ActListener actlis = new ActListener();

		log.addMouseListener(listener);
		sumReceiveDoc.addMouseListener(listener);
		initAccount.addMouseListener(listener);
		payment.addMouseListener(listener);
		if (high)
			accountmanage.addMouseListener(listener);
		// viewstatisticm.addMouseListener(listener);
		// createstatisticm.addMouseListener(listener);
		viewprofits.addActionListener(actlis);
		viewoperate.addActionListener(actlis);
		createprofits.addActionListener(actlis);
		createoperate.addActionListener(actlis);
		viewstatistic.addMouseListener(listener);
		createstatistic.addMouseListener(listener);
		exit.addMouseListener(listener);
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			isclickedv = false;
			viewstatisticpop.setVisible(false);
			createstatisticpop.setVisible(false);
			if (e.getSource() == exit) {
				login.SignOut(id);
				m.jumpToLogInUI();

			} else if (e.getSource() == log) {
				restoreAll();
				log.whenClickHappend();
				m.jumpToViewSysLogUI();

			} else if (e.getSource() == sumReceiveDoc) {
				restoreAll();
				sumReceiveDoc.whenClickHappend();
				m.jumpToFinanceSumReceiveDocUI();

			} else if (e.getSource() == initAccount) {
				restoreAll();
				initAccount.whenClickHappend();
				m.jumpToFinanceInitAccountUI();

			} else if (e.getSource() == payment) {
				restoreAll();
				payment.whenClickHappend();
				m.jumpToFinancePaymentUI();

			} else if (e.getSource() == viewstatistic) {
				if (!isclickedv) {
					isclickedv = true;
					viewstatisticpop.show(pane, 150, 370);
				} else {
					isclickedv = false;
					viewstatisticpop.setVisible(false);
				}
				
			} else if (e.getSource() == createstatistic) {				
				if (!isclickedc) {
					isclickedc = true;
					createstatisticpop.show(pane, 150, 420);
				} else {
					isclickedc = false;
					createstatisticpop.setVisible(false);
				}
				
			}
			if (ishigh) {
				if (e.getSource() == accountmanage) {
					restoreAll();
					accountmanage.whenClickHappend();
					m.jumpToFinanceManageBankAccountUI();
				}
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				exit.whenMouseOnIt();
			} else if (e.getSource() == log) {
				log.whenMouseOnIt();

			} else if (e.getSource() == sumReceiveDoc) {
				sumReceiveDoc.whenMouseOnIt();

			} else if (e.getSource() == initAccount) {
				initAccount.whenMouseOnIt();

			} else if (e.getSource() == payment) {
				payment.whenMouseOnIt();

			} else if (e.getSource() == viewstatistic) {
				viewstatistic.whenMouseOnIt();
				//viewstatisticpop.show(pane, 150, 370);

			} else if (e.getSource() == createstatistic) {
				createstatistic.whenMouseOnIt();
				//createstatisticpop.show(pane, 150, 420);

			}
			if (ishigh) {
				if (e.getSource() == accountmanage) {
					accountmanage.whenMouseOnIt();
				}
			}
			repaint();
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == exit) {
				exit.whenMouseleaveit();
			} else if (e.getSource() == log) {
				log.whenMouseleaveit();

			} else if (e.getSource() == sumReceiveDoc) {
				sumReceiveDoc.whenMouseleaveit();

			} else if (e.getSource() == initAccount) {
				initAccount.whenMouseleaveit();

			} else if (e.getSource() == payment) {
				payment.whenMouseleaveit();

			} else if (e.getSource() == viewstatistic) {
				viewstatistic.whenMouseleaveit();
				// viewstatisticpop.setVisible(false);

			} else if (e.getSource() == createstatistic) {
				createstatistic.whenMouseleaveit();
				// createstatisticpop.setVisible(false);

			}
			if (ishigh) {
				if (e.getSource() == accountmanage) {
					accountmanage.whenMouseleaveit();
				}
			}
			repaint();
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
			if (e.getSource().equals(createprofits)) {
				createstatistic.whenClickHappend();
				m.jumpToFinanceCreateProfitUI();

			} else if (e.getSource().equals(createoperate)) {
				createstatistic.whenClickHappend();
				m.jumpToFinanceCreateOperateUI();

			} else if (e.getSource().equals(viewprofits)) {
				viewstatistic.whenClickHappend();
				m.jumpToViewProfitUI();

			} else if (e.getSource().equals(viewoperate)) {
				viewstatistic.whenClickHappend();
				m.jumpToViewOperateUI();

			}
			updateUI();
		}

	}

	private void restoreAll() {
		log.restore();
		viewstatistic.restore();
		createstatistic.restore();
		sumReceiveDoc.restore();
		initAccount.restore();
		payment.restore();
		if (ishigh)
			accountmanage.restore();
		exit.restore();
	}

	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(),
				this.getHeight(), this);
	}
}
