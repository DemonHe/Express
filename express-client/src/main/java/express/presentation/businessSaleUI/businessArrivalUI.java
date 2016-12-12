package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
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
import express.businessLogic.documentBL.ArrivalDocBusinessHall;
import express.businesslogicService.businessSaleBLService.BusinessSaleArrivalDocumentblService;
import express.po.GoodsArrivalStatus;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.ArrivalDocBusinessHallVO;

public class businessArrivalUI extends JPanel {

	private JPanel tippane;
	private JTextField ordertf, startplacetf, datetf, transDocNumtf;
	private JLabel tip0, tip1, tip2;
	private MyOtherBlueLabel button_confirm;
	private MyOtherRedLabel button_cancel;
	private ButtonGroup bg1;
	private JRadioButton radioButton, radioButton_1, radioButton_2;
	private DateChooser datechooser;
	private String date, transDocNum, order, startplace;
	private BusinessSaleArrivalDocumentblService bsad;
	private Border border;
	private boolean complete = true;

	public businessArrivalUI() {

		int textlength = 150;
		int textwidth = 30;
		int labellength = 100;
		int labelwidth = 30;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		JListener listener = new JListener();
		Foclistener foclis = new Foclistener();
		bsad = new ArrivalDocBusinessHall();

		datetf = new JTextField();
		datetf.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(300, 100, textlength, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		datetf.setBackground(Color.WHITE);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(460, 95, 40, 40);
		this.add(datechooser);

		ordertf = new JTextField();
		ordertf.setBounds(300, 120 + textwidth * 2, textlength + 50, textwidth);
		ordertf.setFont(f);
		ordertf.addFocusListener(foclis);
		this.add(ordertf);
		border = ordertf.getBorder();

		tip0 = new JLabel(" * 订单号不能为空");
		tip0.setBounds(460, 120 + textwidth * 2, 200, 30);
		tip0.setFont(font);
		tip0.setForeground(Color.RED);

		tip1 = new JLabel(" * 填写错误");
		tip1.setBounds(460, 120 + textwidth * 2, 100, 30);
		tip1.setFont(font);
		tip1.setForeground(Color.RED);

		transDocNumtf = new JTextField();
		transDocNumtf.setBounds(300, 140 + textwidth * 4, textlength + 50,
				textwidth);
		transDocNumtf.setFont(f);
		transDocNumtf.setEditable(false);
		this.add(transDocNumtf);

		startplacetf = new JTextField();
		startplacetf.setBounds(300, 160 + textwidth * 6, textlength + 50,
				textwidth);
		startplacetf.setFont(f);
		startplacetf.setEditable(false);
		this.add(startplacetf);

		JLabel label1 = new JLabel("到达日期");
		label1.setBounds(200, 100, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("订单号");
		label2.setBounds(200, 120 + labelwidth * 2, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("中转单编号");
		label3.setBounds(200-10, 140 + labelwidth * 4, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label4 = new JLabel("出发地");
		label4.setBounds(200, 160 + labelwidth * 6, labellength + 30,
				labelwidth);
		label4.setFont(font);
		this.add(label4);

		JLabel label5 = new JLabel("货物到达状态");
		label5.setBounds(200 - 30, 180 + labelwidth * 8, labellength + 30,
				labelwidth);
		label5.setFont(font);
		this.add(label5);

		bg1 = new ButtonGroup();

		radioButton = new JRadioButton("损坏");
		radioButton.setBounds(300, 180 + labelwidth * 8, 100, 30);
		radioButton.setFont(font);
		radioButton.setOpaque(false);
		bg1.add(radioButton);
		this.add(radioButton);

		radioButton_1 = new JRadioButton("完整");
		radioButton_1.setBounds(300, 180 + labelwidth * 9, 100, 30);
		radioButton_1.setFont(font);
		radioButton_1.setSelected(true);
		radioButton_1.setOpaque(false);
		bg1.add(radioButton_1);
		this.add(radioButton_1);

		radioButton_2 = new JRadioButton("丢失");
		radioButton_2.setBounds(300, 180 + labelwidth * 10, 100, 30);
		radioButton_2.setFont(font);
		radioButton_2.setOpaque(false);
		bg1.add(radioButton_2);
		this.add(radioButton_2);

		tip2 = new JLabel("*未选择");
		tip2.setBounds(460, 180 + labelwidth * 9, 100, 30);
		tip2.setFont(font);
		tip2.setForeground(Color.RED);

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(200, 580, 120, 35);
		button_confirm.addMouseListener(listener);
		this.add(button_confirm);

		button_cancel = new MyOtherRedLabel("取消");
		button_cancel.setBounds(380, 580, 120, 35);
		button_cancel.addMouseListener(listener);
		this.add(button_cancel);

		tippane = new JPanel();
		tippane.setSize(850, 40);
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
			if (e.getSource() == ordertf) {
				ordertf.setBorder(border);
			}
			updateUI();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ordertf) {
				order = ordertf.getText();
				if (order.isEmpty()) {
					ordertf.setBorder(new LineBorder(Color.RED));
					add(tip0);
				} else {
					tip0.setVisible(false);
					transDocNumtf.setText(bsad.getTransferDocID(order));
					startplacetf.setText(bsad.getDeparture(order));
				}
			}
			updateUI();
		}

	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == button_cancel) {
				tip1.setVisible(false);
				tip2.setVisible(false);
				ordertf.setText("");
				ordertf.setBorder(border);
				datetf.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				bg1.clearSelection();
				complete = true;
			} else if (e.getSource() == button_confirm) {
				date = datetf.getText();
				startplace = startplacetf.getText();
				order = ordertf.getText();
				transDocNum = startplacetf.getText();
				if (order.isEmpty()) {
					ordertf.setBorder(new LineBorder(Color.RED));
					complete = false;
				}
				GoodsArrivalStatus status = null;

				if (radioButton_1.isSelected()) {
					status = GoodsArrivalStatus.Complete;
					tip2.setVisible(false);
				} else if (radioButton_2.isSelected()) {
					status = GoodsArrivalStatus.Missing;
					tip2.setVisible(false);
				} else if (radioButton.isSelected()) {
					status = GoodsArrivalStatus.Damage;
					tip2.setVisible(false);
				} else {
					add(tip2);
					complete = false;
				}
				if (complete) {
					ArrivalDocBusinessHallVO vo = new ArrivalDocBusinessHallVO(
							date, transDocNum, startplace, status, order);
					if (bsad.addArrivalDoc(vo)) {
						TipBlock block = new TipBlock("生成到达单成功");
						tippane.add(block);
						block.show();
						block = null;
						bsad.endArrivalDoc();
					} else {
						add(tip1);
						TipBlockError block = new TipBlockError("订单条形码号错误");
						tippane.add(block);
						block.show();
						block = null;
					}
				} else {
					TipBlockEmpty block = new TipBlockEmpty("信息未填写完整");
					tippane.add(block);
					block.show();
					block = null;
				}
				complete = true;
			}
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == button_confirm) {
				button_confirm.whenPressed();
			} else if (e.getSource() == button_cancel) {
				button_cancel.whenPressed();
			}

		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == button_confirm) {
				button_confirm.setMyColor();
			} else if (e.getSource() == button_cancel) {
				button_cancel.setMyColor();
			}

		}

	}

}
