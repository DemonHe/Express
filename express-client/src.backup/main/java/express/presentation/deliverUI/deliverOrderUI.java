package express.presentation.deliverUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import express.businessLogic.documentBL.OrderController;
import express.businessLogic.infoManageBL.DistanceManager;
import express.businesslogicService.delivermanBLService.DeliverCreateOrderBLService;
import express.po.DeliveryType;
import express.po.PackageType;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.OrderVO;

public class deliverOrderUI extends JPanel {
	private JPanel tippane;
	private JTextField[] tf;
	private JPanel[] p;
	private JComboBox deliverytype, packtype;
	private JComboBox<String> start, end;
	private MyOtherGreenLabel button_cancel;
	private MyOtherBlueLabel button_confirm;
	private String sendername, senderaddress, senderworkplace, sendertelpnum,
			sendermobilepnm;
	private String recipientname, recipientaddress, recipientworkplace,
			recipienttelpnum, recipientmobilepnm;
	private int count;
	private double weight, volume, money;
	private String nameOfGoods, orderID, startCity, endCity, predictTime;
	private DeliveryType type;
	private PackageType packagetype;
	private Border border;
	private boolean complete = true;

	public deliverOrderUI() {
		setLayout(null);

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font0 = new Font("幼圆", Font.BOLD, 20);
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		Font buttonfont = new Font("隶书", Font.PLAIN, 18);

		int width = 100;
		int height = 30;
		int width2 = 120;
		int upside = 60;
		int leftside1 = 5;
		int leftside2 = 250;
		int leftside3 = 500;

		JPanel linep = new JPanel();
		linep.setLocation(30, 30);
		linep.setPreferredSize(new Dimension(790, 950));
		linep.setOpaque(false);

		Foclistener foc = new Foclistener();
		p = new JPanel[5];
		JLabel[] label = new JLabel[21];
		tf = new JTextField[19];
		String[] labelname = { "寄件人姓�?", "住址", "单位", "电话", "手机�?", "收件人姓�?", "住址",
				"单位", "电话", "手机�?", "出发�?", "到达�?", "原件�?", "实际重量", "体积", "内件品名",
				"快�?�种�?", "包装类型", "订单条形码号", "费用合计", "预计送达时间" };
		String[] type1 = { "经济快�??", "标准快�??", "特快快�??" };
		String[] type2 = { "纸箱", "木箱", "快�?�袋" };

		for (int i = 0; i < 5; i++) {
			p[i] = new JPanel();
			p[i].setBackground(Color.white);
			p[i].setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0,
					Color.GRAY));
			p[i].setLayout(null);
			if (i < 2)
				p[i].setPreferredSize(new Dimension(790, 200));
			if (i == 2)
				p[i].setPreferredSize(new Dimension(790, 110));
			if (i == 3)
				p[i].setPreferredSize(new Dimension(790, 250));
			if (i == 4)
				p[i].setPreferredSize(new Dimension(790, 140));
			linep.add(p[i]);
		}

		JLabel label0 = new JLabel("寄件人信息：");
		label0.setBounds(0, 5, 150, 30);
		label0.setFont(font0);
		p[0].add(label0);

		JLabel label_1 = new JLabel("收件人信息：");
		label_1.setBounds(0, 5, 150, 30);
		label_1.setFont(font0);
		p[1].add(label_1);

		JLabel label_2 = new JLabel("地址信息:");
		label_2.setBounds(5, 5, 150, 30);
		label_2.setFont(font0);
		p[2].add(label_2);

		JLabel lblNewLabel_2 = new JLabel("托运货物信息�?");
		lblNewLabel_2.setBounds(5, 5, 200, 30);
		lblNewLabel_2.setFont(font0);
		p[3].add(lblNewLabel_2);

		for (int i = 0; i < 21; i++) {
			label[i] = new JLabel(labelname[i]);
			label[i].setFont(font);
			label[i].setSize(width, height);
			if (i < 12) {
				if (i % 5 == 0)
					label[i].setLocation(leftside1, upside);
				if (i % 5 == 1)
					label[i].setLocation(leftside2, upside);
				if (i % 5 == 2)
					label[i].setLocation(leftside1, upside + 2 * height);
				if (i % 5 == 3)
					label[i].setLocation(leftside2, upside + 2 * height);
				if (i % 5 == 4)
					label[i].setLocation(leftside3, upside + 2 * height);
				p[i / 5].add(label[i]);
			} else if (i > 11 && i < 18) {
				if (i % 2 == 0)
					label[i].setLocation(leftside1, upside + 2 * ((i - 12) / 2)
							* height);
				if (i % 2 == 1)
					label[i].setLocation(leftside2, upside + 2 * ((i - 12) / 2)
							* height);
				p[3].add(label[i]);
			} else {
				if (i == 18 || i == 19)
					label[i].setBounds(leftside1, 20 + 2 * (i - 18) * height,
							width2, height);
				if (i == 20)
					label[i].setBounds(leftside2 + 20, 20 + 2 * height, width2,
							height);
				p[4].add(label[i]);
			}
		}

		for (int i = 0; i < 19; i++) {
			tf[i] = new JTextField();
			tf[i].setFont(f);
			tf[i].setSize(width2, height);
			tf[i].addFocusListener(foc);
			if (i < 10) {
				if (i % 5 == 0)
					tf[i].setLocation(leftside1 + width, upside);
				else if (i % 5 == 1)
					tf[i].setLocation(leftside2 + width, upside);
				else if (i % 5 == 2)
					tf[i].setLocation(leftside1 + width, upside + 2 * height);
				else if (i % 5 == 3)
					tf[i].setLocation(leftside2 + width, upside + 2 * height);
				else if (i % 5 == 4)
					tf[i].setLocation(leftside3 + width, upside + 2 * height);
				p[i / 5].add(tf[i]);
			} else if (i > 11 && i < 16) {
				if (i % 2 == 0)
					tf[i].setLocation(leftside1 + width, upside + 2
							* ((i - 12) / 2) * height);
				if (i % 2 == 1)
					tf[i].setLocation(leftside2 + width, upside + 2
							* ((i - 12) / 2) * height);
				p[3].add(tf[i]);
			} else if (i > 15) {
				if (i == 16 || i == 17)
					tf[i].setLocation(leftside1 + width2, 20 + 2 * (i - 16)
							* height);
				if (i == 18)
					tf[i].setLocation(leftside2 + 20 + width2, 20 + 2 * height);
				tf[i].setEditable(false);
				p[4].add(tf[i]);
			}
		}
		
		start = new JComboBox<String>();
		start.setLocation(leftside1 + width, upside);
		start.setSize(width2+10, height);
		start.setFont(f);
		start.setOpaque(false);
		p[2].add(start);

		end = new JComboBox<String>();
		end.setLocation(leftside2 + width, upside);
		end.setSize(width2+10, height);
		end.setFont(f);
		end.setOpaque(false);
		p[2].add(end);

		border = tf[1].getBorder();
		deliverytype = new JComboBox(type1);
		deliverytype.setBounds(leftside1 + width, upside + 2 * 2 * height,
				width + 20, height);
		deliverytype.setFont(f);
		p[3].add(deliverytype);

		packtype = new JComboBox(type2);
		packtype.setBounds(leftside2 + width, upside + 2 * 2 * height, width,
				height);
		packtype.setFont(f);
		p[3].add(packtype);

		JScrollPane scrollPane = new JScrollPane(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(linep);
		scrollPane.setBounds(30, 30, 790, 600);
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		this.add(scrollPane);

		Listener lis = new Listener();

		button_confirm = new MyOtherBlueLabel("确定");
		button_confirm.setBounds(250, 630, 100, 30);

		button_confirm.addMouseListener(lis);
		this.add(button_confirm);

		button_cancel = new MyOtherGreenLabel("取消");
		button_cancel.setBounds(400, 630, 100, 30);

		button_cancel.addMouseListener(lis);
		this.add(button_cancel);
		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		init();
	}
	
	private void init(){
		DistanceManager distance = new DistanceManager();
		ArrayList<String> cityList = distance.getAllCity();
		
		if(cityList!=null){
			for(int i=0;i<cityList.size();i++){
				String city = cityList.get(i);
				start.addItem(city);
				end.addItem(city);
			}
		}
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 16; i++) {
				if (e.getSource() == tf[i])
					tf[i].setBorder(border);
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
			requestFocus();
			if (e.getSource() == button_cancel) {
				for (int i = 0; i < 19; i++) {
					tf[i].setText("");
					tf[i].setBorder(border);
				}
			} else if (e.getSource() == button_confirm) {
				for (int i = 0; i < 16; i++) {
					if (tf[i].getText().isEmpty()) {
						complete = false;
						tf[i].setBorder(new LineBorder(Color.RED));
					}
				}

				if (complete) {
					sendername = tf[0].getText();
					senderaddress = tf[1].getText();
					senderworkplace = tf[2].getText();
					sendertelpnum = tf[2].getText();
					sendermobilepnm = tf[4].getText();
					recipientname = tf[5].getText();
					recipientaddress = tf[6].getText();
					recipientworkplace = tf[7].getText();
					recipienttelpnum = tf[8].getText();
					recipientmobilepnm = tf[9].getText();
					count = Integer.parseInt(tf[12].getText());
					weight = Double.parseDouble(tf[13].getText());
					volume = Double.parseDouble(tf[14].getText());
					nameOfGoods = tf[15].getText();
					startCity = start.getSelectedItem().toString();
					endCity = end.getSelectedItem().toString();
					type = DeliveryType.values()[deliverytype
							.getSelectedIndex()];
					packagetype = packagetype.values()[packtype
							.getSelectedIndex()];

					OrderVO vo = new OrderVO();
					vo.setSenderInfo(sendername, senderaddress,
							senderworkplace, sendermobilepnm, sendertelpnum,
							startCity);
					vo.setReceiverInfo(recipientname, recipientaddress,
							recipientworkplace, recipientmobilepnm,
							recipienttelpnum, endCity);
					vo.setGoodsInfo(count, weight, volume, nameOfGoods, type,
							packagetype);

					DeliverCreateOrderBLService dcob = new OrderController();
					money = dcob.getTotal(vo);
					predictTime = dcob
							.getPredictArrivalTime(startCity, endCity)
							.getTime();
					vo.setFee(money);
					vo.setPredictTime(predictTime);
					tf[18].setText(predictTime);
					tf[17].setText(money + "");

					String result = dcob.addOrder(vo);
					boolean success = true;
					for (int i = 0; i < result.length(); i++) {
						if (!(result.charAt(i) >= '0' && result.charAt(i) <= '9')) {
							success = false;
							break;
						}
					}

					if (success) {
						orderID = result;
						tf[16].setText(orderID);
						TipBlock block = new TipBlock("生成订单成功");
						tippane.add(block);
						block.show();
						block = null;
						dcob.endOrder();
					} else {
						TipBlockError block = new TipBlockError(result);
						tippane.add(block);
						block.show();
						block = null;
					}
				}else{
					TipBlockEmpty block=new TipBlockEmpty("信息未输入完�?");
					tippane.add(block);
					block.show();
					block=null;
				}
			}
			complete = true;
			updateUI();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
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
