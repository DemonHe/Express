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
import express.businessLogic.documentBL.ShipmentDocController;
import express.businesslogicService.transcenterSaleBLService.TransCenterSaleShipmentDocblService;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.ShipmentDocTransCenterVO;

public class transSaleShipmentDocUI extends JPanel {

	
	
	private JPanel tippane;
	private MyOtherBlueLabel button_confirm;
	private MyOtherGreenLabel button_cancel;
	private DateChooser datechooser;
	private JTextField[] tf;
	private JTextArea textArea6;
	private String date, transcenterNumber, arrivalplace, startplace, vanID,
			checkMan, transMan, shipmentID;
	private ArrayList<String> orderID;
	private double money;
	private TransCenterSaleShipmentDocblService tsd;
	private Border border, border1;

	public transSaleShipmentDocUI() {

		int base = 30;
		int textlength = 200;
		int textwidth = 40;

		int labellength = 150;
		int labelwidth = 30;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		tf = new JTextField[9];
		tsd = new ShipmentDocController();
		JListener listener = new JListener();
		Foclistener foclis = new Foclistener();
		transcenterNumber = tsd.getShipmentDocID();
		shipmentID = tsd.getShipmentDocID();

		for (int i = 0; i < 9; i++) {
			tf[i] = new JTextField();
			if (i < 3) {
				tf[i].setBounds(360, base + labelwidth * 2 * i, textlength,
						textwidth);
				if (i == 1)
					tf[i].setText(new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date()));
				else
					tf[i].setText(shipmentID);
				tf[i].setEditable(false);
				border = tf[i].getBorder();
			}
			else if (i == 3) {
				tf[i].setBounds(190, base + labelwidth * 6, textlength,
						textwidth);
			}
			else if (i == 4)
				tf[i].setBounds(530, base + labelwidth * 6, textlength,
						textwidth);
			else if (i > 4 && i < 8)
				tf[i].setBounds(360, base + labelwidth * (2 * i - 2),
						textlength, textwidth);
			else if (i == 8) {
				tf[i].setBounds(360, base + labelwidth * 17 + 10, textlength,
						textwidth);
				tf[i].setEditable(false);
			}
			tf[i].setFont(f);
			tf[i].addFocusListener(foclis);
			this.add(tf[i]);
		}

		datechooser = new DateChooser("yyyy-MM-dd", tf[1]);
		datechooser.setBounds(570, base + labelwidth * 2 - 5, 40, 40);
		this.add(datechooser);

		textArea6 = new JTextArea();
		textArea6.setBounds(360, base + labelwidth * 14, textlength,
				textwidth * 2);
		textArea6.setFont(f);
		textArea6.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		textArea6.setLineWrap(true);
		border1 = textArea6.getBorder();
		textArea6.setWrapStyleWord(true);
		textArea6.addFocusListener(foclis);

		JScrollPane scrollPane = new JScrollPane(textArea6);
		scrollPane.setFont(font);
		scrollPane.setBounds(360, base + labelwidth * 14, textlength,
				textwidth * 2);
		this.add(scrollPane);

		JLabel label8 = new JLabel("装车单编号");
		label8.setBounds(200, base + 5, labellength + 50, labelwidth);
		label8.setFont(font);
		this.add(label8);

		JLabel label9 = new JLabel("装车日期");
		label9.setBounds(200, base + 5 + labelwidth * 2, labellength + 50,
				labelwidth);
		label9.setFont(font);
		this.add(label9);

		JLabel label1 = new JLabel("本中转中心汽运编号");
		label1.setBounds(200 - 50, base + 5 + labelwidth * 4, labellength + 50,
				labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("到达地");
		label2.setBounds(100, base + 5 + labelwidth * 6, labellength,
				labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label2_1 = new JLabel("出发地");
		label2_1.setBounds(440, base + 5 + labelwidth * 6, labellength,
				labelwidth);
		label2_1.setFont(font);
		this.add(label2_1);

		JLabel label3 = new JLabel("车辆代号");
		label3.setBounds(200, base + 5 + labelwidth * 8, labellength,
				labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label4 = new JLabel("监装员");
		label4.setBounds(200, base + 5 + labelwidth * 10, labellength,
				labelwidth);
		label4.setFont(font);
		this.add(label4);

		JLabel label5 = new JLabel("押运员");
		label5.setBounds(200, base + 5 + labelwidth * 12, labellength,
				labelwidth);
		label5.setFont(font);
		this.add(label5);

		JLabel label6 = new JLabel("本次装箱所有订单条形号码");
		label6.setBounds(200 - 70, base + 5 + labelwidth * 14,
				labellength + 100, labelwidth);
		label6.setFont(font);
		this.add(label6);

		JLabel label7 = new JLabel("运费");
		label7.setBounds(200, base + 15 + labelwidth * 17, labellength,
				labelwidth);
		label7.setFont(font);
		this.add(label7);

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(250, 605, 100, 30);
		
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(400, 605, 100, 30);
		button_cancel.addMouseListener(listener);
		
		this.add(button_cancel);
	
		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
	
	
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			for (int i = 3; i <8; i++) {
				if (e.getSource() == tf[i])
					tf[i].setBorder(border);
			}
			if (e.getSource() == textArea6) {
				textArea6.setBorder(border1);
			}
			updateUI();
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			requestFocus();
			if (e.getSource() == button_confirm) {
				boolean complete = true;

				for (int i = 3; i < 8; i++) {
					if (tf[i].getText().isEmpty()) {
						tf[i].setBorder(new LineBorder(Color.RED));
						complete = false;
					}
				}
				if (textArea6.getText().isEmpty()) {
					textArea6.setBorder(new LineBorder(Color.RED));
					complete = false;
				}
				if (complete) {
					date = tf[1].getText();
					arrivalplace = tf[3].getText();
					startplace = tf[4].getText();
					vanID = tf[5].getText();
					checkMan = tf[6].getText();
					transMan = tf[7].getText();
					String[] temp = textArea6.getText().split("\n");
					orderID = new ArrayList<String>();
					for (int i = 0; i < temp.length; i++) {
						orderID.add(temp[i]);
					}

					ShipmentDocTransCenterVO vo = new ShipmentDocTransCenterVO(
							date, transcenterNumber, arrivalplace, vanID,
							checkMan, transMan, orderID, money, shipmentID,
							startplace);

					money = tsd.getShipmentfee(vo);
					vo.setMoney(money);
					tf[8].setText(money + "");

					if (tsd.addShipmentDoc(vo)) {
						
						
						TipBlock block=new TipBlock("生成装车单成功");
						tippane.add(block);
						block.show();
						block=null;
						tsd.endShipmentDoc();
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
				shipmentID = tsd.getShipmentDocID();
				tf[0].setText(shipmentID);
				tf[2].setText(shipmentID);
				for(int i=3;i<8;i++){
					tf[i].setText("");
					tf[i].setBorder(border);
				}
				textArea6.setText("");
				textArea6.setBorder(border1);
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

}
