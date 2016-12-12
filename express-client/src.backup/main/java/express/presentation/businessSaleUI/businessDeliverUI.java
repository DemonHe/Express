package express.presentation.businessSaleUI;

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

import express.businessLogic.documentBL.DeliverDoc;
import express.businesslogicService.businessSaleBLService.BusinessSaleDeliverDocumentblService;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.DeliverDocVO;

public class businessDeliverUI extends JPanel {
	private JPanel tippane;
	private JTextField textArea1;
	private JTextField textArea2;
	private JTextField textArea3;
	private JLabel tip1;
	private DateChooser datechooser;
	private MyOtherBlueLabel button_confirm;
	private MyOtherRedLabel button_cancel;
	private String arriveDate, orderID, deliverManID;
	private DeliverDocVO vo;
	private Border border;
	private boolean complete = true;

	public businessDeliverUI() {
		int textlength = 150;
		int textwidth = 30;

		int labellength = 100;
		int labelwidth = 30;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		JListener listener = new JListener();
		Foclistener foclis = new Foclistener();

		textArea1 = new JTextField();
		textArea1.setBounds(300, 200, textlength, textwidth);
		textArea1.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		textArea1.setFont(f);
		textArea1.setEditable(false);
		textArea1.setBackground(Color.WHITE);
		this.add(textArea1);

		datechooser = new DateChooser("yyyy-MM-dd", textArea1);
		datechooser.setBounds(460, 195, 40, 40);
		this.add(datechooser);

		textArea2 = new JTextField();
		textArea2.setBounds(300, 200 + textwidth * 3, textlength+50, textwidth);
		textArea2.setFont(f);
		textArea2.addFocusListener(foclis);
		this.add(textArea2);
		border = textArea2.getBorder();
		
		tip1 = new JLabel(" * 填写错误");
		tip1.setBounds(510, 200 + textwidth * 3, 100, 30);
		tip1.setFont(font);
		tip1.setForeground(Color.RED);
		
		textArea3 = new JTextField();
		textArea3.setBounds(300, 200 + textwidth * 6, textlength+50, textwidth);
		textArea3.setFont(f);
		this.add(textArea3);
		
		JLabel label1 = new JLabel("到达日期");
		label1.setBounds(200, 200, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("派�?�员");
		label2.setBounds(200, 200 + labelwidth * 3, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("订单条形码号");
		label3.setBounds(200 - 30, 200 + labelwidth * 6, labellength + 30,
				labelwidth);// 字数太多，只能把label拉长�?�?
		label3.setFont(font);
		this.add(label3);

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(200, 580, 120, 35);
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new MyOtherRedLabel("取消");
		button_cancel.setBounds(380, 580, 120, 35);
		button_cancel.addMouseListener(listener);
		this.add(button_cancel);


		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
		
		this.addMouseListener(listener);
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == textArea2) {
				textArea2.setBorder(border);
			}
			updateUI();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == button_cancel) {
				
				tip1.setVisible(false);
				textArea1.setText(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
				textArea2.setText("");
				textArea3.setText("");
				textArea2.setBorder(border);
				
			} else if (e.getSource() == button_confirm) {
				
				arriveDate = textArea1.getText();
				deliverManID = textArea2.getText();
				orderID = textArea3.getText();
				
				if(deliverManID.isEmpty()){
					complete = false;
					textArea2.setBorder(new LineBorder(Color.RED));
				}
				
				if(orderID.isEmpty()){
					complete = false;
					textArea3.setBorder(new LineBorder(Color.RED));
				}
				
				if (!complete) {
					TipBlockEmpty block=new TipBlockEmpty("信息未填写完�?");
					tippane.add(block);
					block.show();
					block=null;
				} else {
					vo = new DeliverDocVO(arriveDate, orderID, deliverManID);
					BusinessSaleDeliverDocumentblService bsd = new DeliverDoc();
					
					if(!bsd.isOrderIDavailable(orderID)){
						TipBlockError block=new TipBlockError("订单条形码号错误");
						tippane.add(block);
						block.show();
						block=null;
					}else{
						if (!bsd.addDeliverDoc(vo)) {
							TipBlockError block=new TipBlockError("派件单生成失�?");
							tippane.add(block);
							block.show();
							block=null;
						} else {
							TipBlock block=new TipBlock("生成派件单成�?");
							tippane.add(block);
							block.show();
							block=null;
							bsd.endDeliverDoc();
						}
					}
					
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
			if (e.getSource() == button_cancel) {
				button_cancel.whenPressed();
			} else if (e.getSource() == button_confirm) {
				button_confirm.whenPressed();
			}	

		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == button_cancel) {
				button_cancel.setMyColor();
			} else if (e.getSource() == button_confirm) {
				button_cancel.setMyColor();
			}	


		}

	}

}
