package express.presentation.transRepoUI;

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

public class TranscenterRepoMenuUI extends JPanel {
	private CardLayout card = new CardLayout();
	private MainUIService main;
	private LogInBLService login;
	private String id;
	private JPanel repoPanel;

	// 组件�?
	private JLabel username, userid;
	private MySideLabel button_in = new MySideLabel("入库");
	private MySideLabel button_out = new MySideLabel("出库");
	private MySideLabel button_view = new MySideLabel("库存查看");
	private MySideLabel button_inventory = new MySideLabel("库存盘点");
	private MySideLabel button_adjust = new MySideLabel("仓库调整");
	private MySideLabel button_exit = new MySideLabel("�?�?");

	public TranscenterRepoMenuUI(MainUIService m, String id) {

		int base = 190;
		int width = 50;

		this.setLayout(null);
		this.main = m;

		repoPanel = new JPanel();
		repoPanel.setLayout(card);
		repoPanel.setBounds(150, 0, 850, 700);
		repoPanel.setOpaque(false);
		this.add(repoPanel);

		main.setcard1(card);
		main.setpane1(repoPanel);

		JListener listener = new JListener();
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

		button_in.setBounds(0, base, 150, width);

		button_in.addMouseListener(listener);// 加监�?

		button_out.setBounds(0, base + width, 150, width);

		button_out.addMouseListener(listener);// 加监�?

		button_view.setBounds(0, base + 2 * width, 150, width);

		button_view.addMouseListener(listener);// 加监�?

		button_inventory.setBounds(0, base + 3 * width, 150, width);

		button_inventory.addMouseListener(listener);// 加监�?

		button_adjust.setBounds(0, base + 4 * width, 150, width);

		button_adjust.addMouseListener(listener);

		button_exit.setBounds(0, 600, 150, 50);

		button_exit.addMouseListener(listener);

		add(button_in);
		add(button_out);
		add(button_view);
		add(button_inventory);
		add(button_adjust);
		add(button_exit);

		this.setBounds(0, 0, 1000, 700);
	}

	class JListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			restoreAll();
			if (arg0.getSource() == button_in) {
				button_in.whenClickHappend();
				main.jumpToinUI();
				System.out.println("应该跳转到�?�入库�?�界面的");

			} else if (arg0.getSource() == button_out) {
				button_out.whenClickHappend();
				main.jumpTooutUI();
				System.out.println("应该跳转到�?�出库�?�界面的");

			}

			else if (arg0.getSource() == button_view) {
				button_view.whenClickHappend();
				main.jumpToviewUI();
				System.out.println("应该跳转到�?�库存查看�?�界面的");

			} else if (arg0.getSource() == button_inventory) {
				button_inventory.whenClickHappend();
				main.jumpToinventoryUI();
				System.out.println("应该跳转到�?�库存盘点�?�界面的");

			} else if (arg0.getSource() == button_adjust) {
				button_adjust.whenClickHappend();
				main.jumpToadjustUI();
				System.out.println("应该跳转到�?�库存调整�?�界面的");

			} else if (arg0.getSource() == button_exit) {
				login.SignOut(id);
				main.jumpToLogInUI();
				System.out.println("应该回到登陆界面�?");

			}
			updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			if (arg0.getSource() == button_in) {
				button_in.whenMouseOnIt();

			} else if (arg0.getSource() == button_out) {
				button_out.whenMouseOnIt();

			}

			else if (arg0.getSource() == button_view) {
				button_view.whenMouseOnIt();

			} else if (arg0.getSource() == button_inventory) {
				button_inventory.whenMouseOnIt();

			} else if (arg0.getSource() == button_adjust) {
				button_adjust.whenMouseOnIt();

			} else if (arg0.getSource() == button_exit) {
				button_exit.whenMouseOnIt();
			}

		}

		public void mouseExited(MouseEvent arg0) {
			if (arg0.getSource() == button_in) {
				button_in.whenMouseleaveit();

			} else if (arg0.getSource() == button_out) {
				button_out.whenMouseleaveit();
			}

			else if (arg0.getSource() == button_view) {
				button_view.whenMouseleaveit();

			} else if (arg0.getSource() == button_inventory) {
				button_inventory.whenMouseleaveit();

			} else if (arg0.getSource() == button_adjust) {
				button_adjust.whenMouseleaveit();

			} else if (arg0.getSource() == button_exit) {
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
		g.drawImage(background.getImage(), 0, 0, this.getWidth(),
				this.getHeight(), this);
	}

	public void restoreAll() {
		button_in.restore();
		button_out.restore();
		button_view.restore();
		button_exit.restore();
		button_inventory.restore();
		button_adjust.restore();
	}

}
