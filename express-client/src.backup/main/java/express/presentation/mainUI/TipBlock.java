package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TipBlock extends JLabel {
	private int x, y;

	public TipBlock(String contents) {
		int widt = 300;
		int heig = 20;

		x = 0;
		y = 30;

		Font font = new Font("苹方 常规", Font.BOLD, 22);
		this.setLayout(null);
		JLabel text = new JLabel(contents);
		text.setFont(font);
		text.setSize(400, 40);
		text.setLocation(373, 0);
		this.setSize(x, y);
		this.setOpaque(true);
		this.setBounds(x, y, 850, 40);
		this.setFont(font);
		text.setForeground(Color.white);
		this.add(text);
		this.setPicture();
		this.setText(contents);
	}
	
	public void setPicture(){
		this.setIcon(new ImageIcon("picture/Apply.png"));
	}
	
	

	public void show() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int nowy = 0;

				for (int i = 0; i <= 30; i += 2) {
					try {
						TipBlock.this.setLocation(x, y - i);
						// System.out.println("running...");
						nowy = y - i;
						Thread.sleep(15);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(1800);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				for (int i = 0; i <= 40; i += 5) {
					try {
						TipBlock.this.setLocation(x, nowy + i);
						// System.out.println("running...");
						Thread.sleep(8);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		};

		new Thread(runnable).start();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1000, 700);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JPanel tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 640);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		frame.add(tippane);

		TipBlock bloc = new TipBlock("添加成功");

		tippane.add(bloc);
		bloc.show();

	}

}
