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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import express.businessLogic.IDKeeper;
import express.businesslogicService.transcenterSaleBLService.TransCenterArrivalDocblService;
import express.businesslogicService.transcenterSaleBLService.TransCenterTransferDocblService;
import express.businessLogic.documentBL.ArrivalDocTransCenter;
import express.businessLogic.documentBL.TransferDoc;
import express.businessLogic.infoManageBL.DistanceManager;
import express.po.GoodsArrivalStatus;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.ArrivalDocTransCenterVO;

public class transSaleArrivalDocUI extends JPanel {

	private JPanel tippane;
	private MyOtherBlueLabel button_confirm;
	private MyOtherGreenLabel button_cancel;
	private JComboBox<String> start;
	private ButtonGroup bg1, bg2;
	private JRadioButton radioButton, radioButton_1, radioButton_2,
			radioButton_3, radioButton_4;
	private JLabel tip;
	private JTextField ordertf, datetf, tranNumtf, transDocNumtf;
	private DateChooser datechooser;
	private String date, transDocNum, tranNum, order, startplace;
	private GoodsArrivalStatus arrivalStatus;
	private Border border;

	public transSaleArrivalDocUI() {

		int textlength = 200;
		int textwidth = 30;

		int labellength = 100;
		int labelwidth = 30;

		int base = 80;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);
		Foclistener foclis = new Foclistener();

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		JLabel label1 = new JLabel("到达日期");
		label1.setBounds(200, base, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		datetf = new JTextField();
		datetf.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(300, base, textlength - 50, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		datetf.setBackground(Color.WHITE);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(460, base - 5, 40, 40);
		this.add(datechooser);

		JLabel label2 = new JLabel("订单�?");
		label2.setBounds(200 + 10, base + labelwidth * 2, labellength,
				labelwidth);
		label2.setFont(font);
		this.add(label2);

		ordertf = new JTextField();
		ordertf.setBounds(300, base + textwidth * 2, textlength, textwidth);
		ordertf.setFont(f);
		ordertf.addFocusListener(foclis);
		this.add(ordertf);
		border = ordertf.getBorder();

		JLabel label3 = new JLabel("中转中心编号");
		label3.setBounds(200 - 30, base + labelwidth * 4, labellength + 50,
				labelwidth);
		label3.setFont(font);
		this.add(label3);

		tranNum = IDKeeper.getOrgID();
		tranNumtf = new JTextField();
		tranNumtf.setEditable(false);
		tranNumtf.setEditable(false);
		tranNumtf.setText(tranNum);
		tranNumtf.setBounds(300, base + textwidth * 4, textlength, textwidth);
		tranNumtf.setFont(f);
		this.add(tranNumtf);

		JLabel label4 = new JLabel("中转单编�?");
		label4.setBounds(200 - 10, base + labelwidth * 8, labellength,
				labelwidth);
		label4.setFont(font);
		this.add(label4);

		transDocNumtf = new JTextField();
		transDocNumtf.setBounds(300, base + textwidth * 8, textlength,
				textwidth);
		transDocNumtf.setFont(f);
		transDocNumtf.addFocusListener(foclis);
		this.add(transDocNumtf);

		bg2 = new ButtonGroup();

		radioButton_3 = new JRadioButton("寄件人中转中�?");
		radioButton_3.setBounds(200, base + labelwidth * 6, textlength,
				labelwidth);
		radioButton_3.setFont(font);
		radioButton_3.setSelected(true);
		bg2.add(radioButton_3);
		radioButton_3.addFocusListener(foclis);
		this.add(radioButton_3);

		radioButton_4 = new JRadioButton("收件人中转中�?");
		radioButton_4.setBounds(200, base + labelwidth * 6, textlength,
				labelwidth);
		radioButton_4.setFont(font);
		radioButton_4.addFocusListener(foclis);
		bg2.add(radioButton_4);
		this.add(radioButton_4);

		JLabel label5 = new JLabel("出发�?");
		label5.setBounds(200 + 10, base + labelwidth * 10, labellength,
				labelwidth);
		label5.setFont(font);
		this.add(label5);

		start = new JComboBox<String>();
		start.setBounds(300, base + textwidth * 10, textlength, textwidth);
		start.setFont(f);
		start.setOpaque(false);
		this.add(start);

		JLabel label6 = new JLabel("货物到达状�??");
		label6.setBounds(200 - 30, base + labelwidth * 12, labellength + 50,
				labelwidth);
		label6.setFont(font);
		this.add(label6);

		bg1 = new ButtonGroup();

		radioButton = new JRadioButton("完整");
		radioButton.setBounds(300, base + labelwidth * 12, labellength,
				labelwidth);
		radioButton.setFont(font);
		radioButton.setOpaque(false);
		radioButton.addFocusListener(foclis);
		radioButton.setSelected(true);

		bg1.add(radioButton);
		this.add(radioButton);

		radioButton_1 = new JRadioButton("损坏");
		radioButton_1.setBounds(300, base + labelwidth * 13, labellength,
				labelwidth);
		radioButton_1.setFont(font);
		radioButton_1.setOpaque(false);
		radioButton_1.addFocusListener(foclis);
		bg1.add(radioButton_1);
		this.add(radioButton_1);

		radioButton_2 = new JRadioButton("丢失");
		radioButton_2.setBounds(300, base + labelwidth * 14, labellength,
				labelwidth);
		radioButton_2.setFont(font);
		radioButton_2.setOpaque(false);
		radioButton_2.addFocusListener(foclis);
		bg1.add(radioButton_2);
		this.add(radioButton_2);

		tip = new JLabel("*未�?�择");
		tip.setBounds(420, base + labelwidth * 12, 100, 30);
		tip.setFont(font);
		tip.setForeground(Color.RED);

		JListener listener = new JListener();

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(210, 580, 120, 40);
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(380, 580, 120, 40);
		button_cancel.addMouseListener(listener);
		this.add(button_cancel);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		init();
	}

	private void init() {
		DistanceManager distance = new DistanceManager();
		ArrayList<String> cityList = distance.getAllCity();

		if (cityList != null) {
			for (int i = 0; i < cityList.size(); i++) {
				String city = cityList.get(i);
				start.addItem(city);
			}
		}
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == radioButton_3) {
				transDocNum = "-1";
				transDocNumtf.setEditable(false);
				transDocNumtf.setText("");
			} else if (e.getSource() == radioButton_4) {
				transDocNumtf.setEditable(true);
				if (transDocNumtf.getText().equals("-1"))
					transDocNumtf.setText("");
			} else if (e.getSource() == ordertf) {
				ordertf.setBorder(border);
			} else if (e.getSource() == transDocNumtf) {
				if (transDocNumtf.isEditable())
					transDocNumtf.setBorder(border);
			} else if (e.getSource() == radioButton
					|| e.getSource() == radioButton_1
					|| e.getSource() == radioButton_2) {
				tip.setVisible(false);
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
				boolean right = true;

				date = datetf.getText();
				startplace = start.getSelectedItem().toString();
				order = ordertf.getText();

				Color yellow = new Color(255, 215, 0);
				
				if (!transDocNum.equals("-1")) {
					transDocNum = transDocNumtf.getText();
					if (transDocNum.equals("")) {
						complete = false;
						transDocNumtf.setBorder(new LineBorder(yellow, 2));
					}
				}

				if (startplace.isEmpty()) {
					complete = false;
				}

				if (order.equals("")) {
					complete = false;
					ordertf.setBorder(new LineBorder(yellow, 2));
				}

				if (bg1.getSelection() == null) {
					complete = false;
					add(tip);
				}

				if (radioButton.isSelected()) {
					arrivalStatus = GoodsArrivalStatus.Complete;
				} else if (radioButton_1.isSelected()) {
					arrivalStatus = GoodsArrivalStatus.Damage;
				} else if (radioButton_2.isSelected()) {
					arrivalStatus = GoodsArrivalStatus.Missing;
				}
				if (!complete) {
					TipBlockEmpty block = new TipBlockEmpty("信息未填写完�?");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					if (transDocNumtf.isEditable()) {
						TransCenterTransferDocblService ttd = new TransferDoc();
						if (ttd.isTransIDavailable(transDocNum))
							right = false;
					}
					if (right) {
						ArrivalDocTransCenterVO vo = new ArrivalDocTransCenterVO(
								order, date, tranNum, transDocNum, startplace,
								arrivalStatus);
						TransCenterArrivalDocblService tadb = new ArrivalDocTransCenter();
						if (tadb.addArrivalDoc(vo)) {
							TipBlock block = new TipBlock("生成到达单成�?");
							tippane.add(block);
							block.show();
							block = null;
							tadb.endArrivalDoc();
						} else {
							TipBlockError block = new TipBlockError("订单号错�?");
							tippane.add(block);
							block.show();
							block = null;
						}
					} else {
						TipBlockError block = new TipBlockError("中转单编号号错误");
						tippane.add(block);
						block.show();
						block = null;
					}
				}

			} else if (e.getSource() == button_cancel) {
				tip.setVisible(false);
				ordertf.setText("");
				ordertf.setBorder(border);
				datetf.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				bg1.clearSelection();
				bg2.clearSelection();
				transDocNumtf.setText("");
				transDocNumtf.setBorder(border);
				transDocNumtf.setEditable(true);
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
			if (arg0.getSource() == button_confirm) {
				button_confirm.whenPressed();
			} else if (arg0.getSource() == button_cancel) {
				button_cancel.whenPressed();
			}

		}

		public void mouseReleased(MouseEvent arg0) {
			if (arg0.getSource() == button_confirm) {
				button_confirm.setMyColor();
			} else if (arg0.getSource() == button_cancel) {
				button_cancel.setMyColor();
			}

		}

	}
}
