package express.presentation.deliverUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import express.businessLogic.receiveInfoBL.ReceiveInfo;
import express.businesslogicService.delivermanBLService.ReceiveInfoBLService;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.ReceiveInfoVO;

public class deliverReceiveUI extends JPanel {
	
	private JPanel tippane;
	private MyOtherBlueLabel button_return;
	private MyOtherGreenLabel button_cancel;
	private JTextField textArea1, textArea2, textArea3;
	private DateChooser datechooser;
	private String receiverName, receiveTime, OrderID;
	private boolean complete = true;
	private Border border;

	public deliverReceiveUI() {

		int textlength = 250;
		int textwidth = 40;

		int labellength = 100;
		int labelwidth = 40;

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);
		Foclistener foc = new Foclistener();

		JLabel label1 = new JLabel("收件人姓名");
		label1.setBounds(200, 100, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("签收时间");
		label2.setBounds(200, 140 + labelwidth * 2, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("订单编号");
		label3.setBounds(200, 180 + labelwidth * 4, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);

		textArea1 = new JTextField();
		textArea1.setBounds(300, 100, textlength, textwidth);
		textArea1.setFont(f);
		textArea1.addFocusListener(foc);
		this.add(textArea1);
		border = textArea1.getBorder();

		textArea2 = new JTextField(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		textArea2.setBounds(300, 140 + textwidth * 2, textlength-50, textwidth);
		textArea2.setFont(f);
		textArea2.setEditable(false);
		textArea2.setBackground(Color.WHITE);
		this.add(textArea2);

		datechooser = new DateChooser("yyyy-MM-dd", textArea2);
		datechooser.setBounds(510, 140 + textwidth * 2, 40, 40);
		this.add(datechooser);

		textArea3 = new JTextField();
		textArea3.setBounds(300, 180 + textwidth * 4, textlength, textwidth);
		textArea3.setFont(f);
		textArea3.addFocusListener(foc);
		this.add(textArea3);

		Listener lis = new Listener();

		button_return = new MyOtherBlueLabel("确认");
		button_return.setBounds(220, 550, 120, 35);
	
		button_return.addMouseListener(lis);
		this.add(button_return);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(430, 550, 120, 35);
	
		button_cancel.addMouseListener(lis);
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
			if (e.getSource() ==textArea1){
				textArea1.setBorder(border);
			}else if (e.getSource() ==textArea3){
				textArea3.setBorder(border);
			}
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == button_cancel) {
				
				textArea1.setText("");
				textArea1.setBorder(border);
				textArea2.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				textArea3.setText("");
				textArea3.setBorder(border);
				
			} else if (e.getSource() == button_return) {
				complete = true;
				Color yellow = new Color(255,215,0);
				
				receiverName = textArea1.getText();
				if (receiverName.equals("")) {
					complete = false;
					textArea1.setBorder(new LineBorder(yellow,2));
				}

				receiveTime = textArea2.getText();

				OrderID = textArea3.getText();
				if (OrderID.equals("")) {
					complete = false;
					textArea3.setBorder(new LineBorder(yellow,2));
				}

				if (complete) {
					textArea1.setBorder(new LineBorder(Color.GRAY));
					textArea3.setBorder(new LineBorder(Color.GRAY));
					ReceiveInfoVO vo = new ReceiveInfoVO(receiverName,
							receiveTime, OrderID);
					ReceiveInfoBLService rbs = new ReceiveInfo();
					if (rbs.addReceiveInfo(vo)) {
						TipBlock block=new TipBlock("生成收件信息成功");
						tippane.add(block);
						block.show();
						block=null;
						rbs.endReceiveInfo();
					} else {
						textArea3.setBorder(new LineBorder(Color.RED,2));
						TipBlockError block=new TipBlockError("订单条形码号错误");
						tippane.add(block);
						block.show();
						block=null;
					}
				}else{
					TipBlockEmpty block=new TipBlockEmpty("信息未输入完全");
					tippane.add(block);
					block.show();
					block=null;
				}
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			if(e.getSource()==button_return){
				button_return.whenPressed();
			}else if (e.getSource()==button_cancel) {
				button_cancel.whenPressed();
			}

		}

		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==button_return){
				button_return.setMyColor();
			}else if (e.getSource()==button_cancel) {
				button_cancel.setMyColor();
			}

		}
	}

}
