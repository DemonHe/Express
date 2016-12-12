package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
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
import express.businessLogic.documentBL.ReceiveDoc;
import express.businesslogicService.businessSaleBLService.BusinessSaleReceiveDocumentblService;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.ReceiveDocVO;

public class businessReceiveUI extends JPanel {

	
	
	private JPanel tippane;
	private JTextField textArea0;
	private JTextField textArea1;
	private JTextField textArea2;
	private JTextField textArea3;
	private JTextArea textArea4;
	private DateChooser datechooser;
	
	private MyOtherBlueLabel button_confirm;
	private MyOtherRedLabel button_cancel;
	
	private String orgID, receiveDate, deliverManID;
	private ArrayList<String> allOrderIDs;
	private double receivePrice;
	private Border border,border1;
	private boolean complete = true;

	public businessReceiveUI() {
		int textlength = 250;
		int textwidth = 40;

		int labellength = 100;
		int labelwidth = 40;
		int base = 50;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		JListener listener = new JListener();
		Foclistener foclis = new Foclistener();

		orgID = IDKeeper.getOrgID();
		textArea0 = new JTextField();
		textArea0.setBounds(300, base, textlength, textwidth);
		textArea0.setFont(f);
		textArea0.setText(orgID);
		textArea0.setEditable(false);
		textArea0.setBackground(Color.WHITE);
		this.add(textArea0);

		textArea1 = new JTextField(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		textArea1.setBounds(300, base + labelwidth * 2, textlength-50, textwidth);
		textArea1.setFont(f);
		textArea1.setEditable(false);
		textArea1.setBackground(Color.WHITE);
		this.add(textArea1);

		datechooser = new DateChooser("yyyy-MM-dd", textArea1);
		datechooser.setBounds(300 + textlength -40, base + labelwidth * 2, 40,
				40);
		this.add(datechooser);

		textArea2 = new JTextField();
		textArea2.setBounds(300, base + labelwidth * 4, textlength, textwidth);
		textArea2.setFont(f);
		textArea2.addFocusListener(foclis);
		this.add(textArea2);

		textArea3 = new JTextField();
		textArea3.setBounds(300, base + labelwidth * 6, textlength, textwidth);
		textArea3.setFont(f);
		textArea3.addFocusListener(foclis);
		this.add(textArea3);
		border = textArea3.getBorder();

		textArea4 = new JTextArea();
		textArea4.setBounds(300, base + labelwidth * 8, textlength,
				textwidth * 3);
		textArea4.setFont(f);
		textArea4.setColumns(10);
		textArea4.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		textArea4.setLineWrap(true);// �?活自动换行功�?
		textArea4.setWrapStyleWord(true);// �?活断行不断字功能
		textArea4.addFocusListener(foclis);
		border1 = textArea4.getBorder();

		JScrollPane scrollPane = new JScrollPane(textArea4);
		scrollPane.setFont(font);
		scrollPane.setBounds(300, base + labelwidth * 8, textlength,
				textwidth * 4);
		scrollPane.setBorder(null);
		this.add(scrollPane);

		JLabel label0 = new JLabel("本营业厅编号");
		label0.setBounds(200 - 30, base, labellength + 30, labelwidth);
		label0.setFont(font);
		this.add(label0);

		JLabel label1 = new JLabel("收款日期");
		label1.setBounds(200, base + labelwidth * 2, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("收款金额");
		label2.setBounds(200, base + labelwidth * 4, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("收款快�?�员");
		label3.setBounds(200 - 15, base + labelwidth * 6, labellength,
				labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label4 = new JLabel("订单条形号码");
		label4.setBounds(200 - 30, base + labelwidth * 8, labellength + 30,
				labelwidth);
		label4.setFont(font);
		this.add(label4);

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(200, 580, 120, 35);
		button_confirm.addMouseListener(listener);
		
		this.add(button_confirm);

		button_cancel = new MyOtherRedLabel("取消");
		button_cancel.setBounds(430, 580, 120, 35);
		
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
			if (e.getSource() == textArea2) {
				textArea2.setBorder(border);
			}else if (e.getSource() == textArea3) {
				textArea3.setBorder(border);
			}else if (e.getSource() == textArea4) {
				textArea4.setBorder(border1);
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
				
				textArea1.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				textArea2.setText("");
				textArea3.setText("");
				textArea4.setText("");
				textArea2.setBorder(border);
				textArea3.setBorder(border);
				textArea4.setBorder(border1);
				
			} else if (e.getSource() == button_confirm) {
				
				receiveDate = textArea1.getText();
				if (textArea2.getText().isEmpty()) {
					textArea2.setBorder(new LineBorder(Color.RED));
					complete = false;
				} else {
					receivePrice = Double.parseDouble(textArea2.getText());
				}
				
				deliverManID = textArea3.getText();
				String[] temp = textArea4.getText().split("\n");
				allOrderIDs = new ArrayList<String>();
				for (int i = 0; i < temp.length; i++) {
					allOrderIDs.add(temp[i]);
				}

				if (deliverManID.isEmpty()) {
					textArea3.setBorder(new LineBorder(Color.RED));
					complete = false;
				} 
				
				if (textArea4.getText().isEmpty()) {
					textArea4.setBorder(new LineBorder(Color.RED));
					complete = false;
				} 
				
				if (!complete) {
					TipBlockEmpty block=new TipBlockEmpty("信息未填写完�?");
					tippane.add(block);
					block.show();
					block=null;
				} else {
					ReceiveDocVO vo = new ReceiveDocVO(receiveDate,
							receivePrice, deliverManID, allOrderIDs, orgID);
					BusinessSaleReceiveDocumentblService bsrd = new ReceiveDoc();
					if (bsrd.addReceiveDoc(vo)) {
						TipBlock block=new TipBlock("生成收款单成�?");
						tippane.add(block);
						block.show();
						block=null;
						bsrd.endReceiveDoc();
					} else {
						TipBlockError block=new TipBlockError("今日已建立收款单");
						tippane.add(block);
						block.show();
						block=null;
					}
				}
				complete = true;
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			if(e.getSource()==button_cancel){
				button_cancel.whenPressed();
			}else if (e.getSource()==button_confirm) {
				button_confirm.whenPressed();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==button_cancel){
				button_cancel.setMyColor();
			}else if (e.getSource()==button_confirm) {
				button_confirm.setMyColor();
			}

		}

	}

}
