package express.presentation.transSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import express.businessLogic.IDKeeper;
import express.businesslogicService.transcenterSaleBLService.TransCenterTransferDocblService;
import express.businessLogic.documentBL.TransferDoc;
import express.po.TransWay;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.TransferDocVO;

public class transSaleTransferDocUI extends JPanel {
	
	private JPanel tippane;
	private MyOtherBlueLabel button_confirm;
	private MyOtherGreenLabel button_cancel;
	private JTextArea textArea7;
	private JTextField[] tf;
	private JLabel label2;
	private DateChooser datechooser;
	private JComboBox tranway;
	private String transmode, date, transcenterNumber, flightNumber, begin,
			arrival, containerNumber, checkMan;
	private TransWay transWay;
	private ArrayList<String> orderlist;
	private double money;
	private TransCenterTransferDocblService ttd;
	private Border border, border1;

	public transSaleTransferDocUI() {

		int base = 40;
		int textlength = 200;
		int textwidth = 40;

		int labellength = 100;
		int labelwidth = 30;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		tf = new JTextField[8];
		ttd = new TransferDoc();
		transcenterNumber = ttd.getTransferDocID();
		JListener listener = new JListener();
		FocListener foclis = new FocListener();

		for (int i = 0; i < 8; i++) {
			tf[i] = new JTextField();
			if (i < 2) {
				tf[i].setBounds(300, base + labelwidth * 2 * i, textlength,
						textwidth);
				if (i == 0)
					tf[i].setText(transcenterNumber);
				else if (i == 1)
					tf[i].setText(new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date()));
				tf[i].setEditable(false);
			} else if (i == 2) {
				tf[i].setBounds(520, base + labelwidth * 4, textlength,
						textwidth);
				border = tf[i].getBorder();
			} else if (i > 2 && i < 7) {
				tf[i].setBounds(300, base + labelwidth * 2 * i, textlength,
						textwidth);
			} else if (i == 7) {
				tf[i].setBounds(300, base + labelwidth * 17, textlength,
						textwidth);
				tf[i].setEditable(false);
			}
			tf[i].setFont(f);
			tf[i].addFocusListener(foclis);
			this.add(tf[i]);
		}

		datechooser = new DateChooser("yyyy-MM-dd", tf[1]);
		datechooser.setBounds(510, base + labelwidth * 2 - 5, 40, 40);
		this.add(datechooser);

		String[] tranways = { "汽车", "火车", "飞机" };
		tranway = new JComboBox(tranways);
		tranway.setFont(f);
		tranway.setBounds(300, base + labelwidth * 4, labellength, textwidth);
		tranway.addFocusListener(foclis);
		this.add(tranway);

		textArea7 = new JTextArea();
		textArea7.setBounds(300, base + labelwidth * 14, textlength + 50,
				textwidth * 2);// 字太长了，所以放宽了70
		textArea7.setFont(f);
		textArea7.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		textArea7.setLineWrap(true);
		textArea7.setWrapStyleWord(true);
		textArea7.addFocusListener(foclis);
		this.add(textArea7);
		border1 = textArea7.getBorder();

		JScrollPane scrollPane = new JScrollPane(textArea7);
		scrollPane.setFont(font);
		scrollPane.setBounds(300, base + labelwidth * 14, textlength + 50,
				textwidth * 2);
		this.add(scrollPane);
		
		JLabel label1 = new JLabel("中转单编号");
		label1.setBounds(200 - 10, base + 5, labellength + 10, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label9 = new JLabel("装运日期");
		label9.setBounds(200, base + 5 + labelwidth * 2, labellength,
				labelwidth);
		label9.setFont(font);
		this.add(label9);

		JLabel label2_0 = new JLabel("运输方式");
		label2_0.setBounds(200, base + 5 + labelwidth * 4, labellength,
				labelwidth);
		label2_0.setFont(font);
		this.add(label2_0);

		label2 = new JLabel("车次号");
		label2.setBounds(440, base + 5 + labelwidth * 4, labellength,
				labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("出发地");
		label3.setBounds(200, base + 5 + labelwidth * 6, labellength,
				labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label4 = new JLabel("到达地");
		label4.setBounds(200, base + 5 + labelwidth * 8, labellength,
				labelwidth);
		label4.setFont(font);
		this.add(label4);

		JLabel label5 = new JLabel("货柜号");
		label5.setBounds(200, base + 5 + labelwidth * 10, labellength,
				labelwidth);
		label5.setFont(font);
		this.add(label5);

		JLabel label6 = new JLabel("监装员");
		label6.setBounds(200, base + 5 + labelwidth * 12, labellength + 100,
				labelwidth);
		label6.setFont(font);
		this.add(label6);

		JLabel label7 = new JLabel("本次装箱所有订单条形号码");
		label7.setBounds(200 - 130, base + 5 + labelwidth * 14,
				labellength + 170, labelwidth);
		label7.setFont(font);
		this.add(label7);

		JLabel label8 = new JLabel("运费");
		label8.setBounds(200, base + 5 + labelwidth * 17, labellength,
				labelwidth);
		label8.setFont(font);
		this.add(label8);

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(230, 610, 80, 30);
		
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(400, 610, 80, 30);
	
		button_cancel.addMouseListener(listener);
		
		
		
		
		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
		
		
		
		
		this.add(button_cancel);

		this.addMouseListener(listener);
	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			requestFocus();
			if (e.getSource() == button_confirm) {
				boolean complete = true;

				for (int i = 2; i < 7; i++) {
					if (tf[i].getText().isEmpty()) {
						tf[i].setBorder(new LineBorder(Color.RED));
						complete = false;
					}
				}
				if (textArea7.getText().isEmpty()) {
					textArea7.setBorder(new LineBorder(Color.RED));
					complete = false;
				}

				if (complete) {
					date = tf[1].getText();
					flightNumber = tf[2].getText();
					begin = tf[3].getText();
					arrival = tf[4].getText();
					containerNumber = tf[5].getText();
					checkMan = tf[6].getText();
					transWay = TransWay.values()[tranway.getSelectedIndex()];
					String[] temp = textArea7.getText().split("\n");
					orderlist = new ArrayList<String>();
					for (int i = 0; i < temp.length; i++) {
						orderlist.add(temp[i]);
					}

					TransferDocVO vo = new TransferDocVO(date,
							transcenterNumber, flightNumber, begin, arrival,
							containerNumber, checkMan, money, transWay,
							orderlist);

					money = ttd.getTransferFee(vo);
					vo.setmoney(money);
					tf[7].setText(money + "");

					if (ttd.addTransferDoc(vo)) {
						TipBlock block=new TipBlock("生成中转单成功");
						tippane.add(block);
						block.show();
						block=null;
						ttd.endTransferDoc();
					} else {
						TipBlockError block=new TipBlockError("订单条形码号错误");
						tippane.add(block);
						block.show();
						block=null;
					}
				} else {
					TipBlockEmpty block=new TipBlockEmpty("信息未填写完整");
					tippane.add(block);
					block.show();
					block=null;
				}
			} else if (e.getSource() == button_cancel) {
				tf[1].setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				transcenterNumber = ttd.getTransferDocID();
				tf[0].setText(transcenterNumber);
				for (int i = 2; i < 8; i++) {
					tf[i].setText("");
					if (i != 7)
						tf[i].setBorder(border);
				}
				textArea7.setText("");
				textArea7.setBorder(border1);
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			if(arg0.getSource()==button_confirm){
				button_confirm.whenPressed();
			}else if (arg0.getSource()==button_cancel) {
				button_cancel.whenPressed();
			}


		}

		public void mouseReleased(MouseEvent arg0) {
			if(arg0.getSource()==button_confirm){
				button_confirm.setMyColor();
			}else if (arg0.getSource()==button_cancel) {
				button_cancel.setMyColor();
			}

		}

	}

	private class FocListener implements FocusListener {

		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			for (int i = 2; i <7; i++) {
				if (e.getSource() == tf[i])
					tf[i].setBorder(border);
			}
			if (e.getSource() == textArea7) {
				textArea7.setBorder(border1);
			}
			
			updateUI();
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == tranway) {
				if (tranway.getSelectedIndex() == 2) {
					transmode = "航班号";
				} else {
					transmode = "车次号";
				}
				label2.setText(transmode);	
			}	
			updateUI();
		}
	}
}
