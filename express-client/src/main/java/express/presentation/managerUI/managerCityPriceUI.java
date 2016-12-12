package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import express.businessLogic.infoManageBL.DistanceManager;
import express.businessLogic.infoManageBL.PriceManager;
import express.businesslogicService.managerBLService.StaffManageBLService;
import express.po.CityDistancePO;
import express.po.DeliveryType;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.presentation.mainUI.MyTableModel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.CityDistanceVO;
import express.vo.PriceVO;
import express.vo.UserInfoVO;

public class managerCityPriceUI extends JPanel {

	private JPanel tippane;
	private MyOtherGreenLabel priceadd;
	private MyOtherBlueLabel disadd;
	private MyOtherGreenLabel dischange;
	private MyOtherRedLabel disdetele;
	private Vector<Vector<JTextField>> distf;
	private JTextField citytf;
	private JTextField[] pricetf;
	private Vector<JTextField> adddistance;
	private Vector<JLabel> cityhorlabel, cityverlabel;
	private JPanel pricepane, dispanecon, dispane;
	private ArrayList<String> allcity;
	private String city;
	private PriceManager priman;
	private DistanceManager disman;
	private Border border;
	private int citynum, changerow;
	private int base = 90;
	private int labelwidth = 100;
	private int height = 30;
	private int textwidth = 120;
	private Font font = new Font("幼圆", Font.PLAIN, 20);
	private Font f = new Font("方正隶变简体", Font.PLAIN, 18);
	private Foclistener foclis;

	public managerCityPriceUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font titlef = new Font("隶书", Font.BOLD, 26);
		Font buttonf = new Font("隶书", Font.BOLD, 18);
		priman = new PriceManager();
		disman = new DistanceManager();
		Listener lis = new Listener();
		foclis = new Foclistener();

		pricepane = new JPanel();
		pricepane.setLayout(null);
		pricepane.setBackground(Color.WHITE);
		pricepane.setBounds(0, 0, 300, 700);
		pricepane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5,
				Color.GRAY));
		this.add(pricepane);

		JLabel pricetitle = new JLabel("价格：");
		pricetitle.setBounds(30, 30, 100, 40);
		pricetitle.setFont(titlef);
		pricepane.add(pricetitle);

		JLabel pricesubtitle = new JLabel("(单位：元/每公斤每公里）");
		pricesubtitle.setBounds(30, 90, 300, 40);
		pricesubtitle.setFont(buttonf);
		pricepane.add(pricesubtitle);

		String[] type = { "经济快递", "标准快递", "特快快递" };
		JLabel[] pricel = new JLabel[3];
		pricetf = new JTextField[3];
		double[] pricebefore = {
				priman.getPricePerKMPerKilo(DeliveryType.Slow),
				priman.getPricePerKMPerKilo(DeliveryType.Standard),
				priman.getPricePerKMPerKilo(DeliveryType.Fast) };

		for (int i = 0; i < 3; i++) {
			pricel[i] = new JLabel(type[i]);
			pricel[i].setBounds(10, 220 + 2 * i * 30, 100, 30);
			pricel[i].setFont(font);
			pricepane.add(pricel[i]);

			pricetf[i] = new JTextField(pricebefore[i] + "");
			pricetf[i].setBounds(120, 220 + 2 * i * 30, 150, 30);
			pricetf[i].setFont(f);
			pricetf[i].addFocusListener(foclis);
			pricepane.add(pricetf[i]);
		}

		border = pricetf[0].getBorder();
		priceadd = new MyOtherGreenLabel("修改");
		priceadd.setBounds(100, 520, 100, 30);
		priceadd.addMouseListener(lis);
		pricepane.add(priceadd);

		// allcity = new ArrayList<String>();
		// allcity.add("广州");
		// // allcity.add("北京");
		// allcity.add("上海");
		allcity = disman.getAllCity();
		citynum = allcity.size();
		// ArrayList<CityDistancePO> alldis = disman.getDistanceList();

		dispanecon = new JPanel();
		// dispane.setLayout(null);
		dispanecon.setPreferredSize(new Dimension(10 + (textwidth + 10)
				* (citynum + 1) + 10, 2 * (citynum + 1) * height + 10));
		// this.add(dispane);

		JScrollPane scrollPane = new JScrollPane(dispanecon);
		scrollPane.setBounds(300, base, 550, 510);
		this.add(scrollPane);

		dispane = new JPanel();
		dispane.setLayout(null);
		dispane.setPreferredSize(new Dimension(10 + (textwidth + 10)
				* (citynum + 1) + 10, 2 * (citynum + 1) * height + 10));
		dispanecon.add(dispane);

		JLabel distitle = new JLabel("价格：");
		distitle.setBounds(330, 30, 100, 40);
		distitle.setFont(titlef);
		this.add(distitle);

		cityhorlabel = new Vector<JLabel>();
		cityverlabel = new Vector<JLabel>();
		distf = new Vector<Vector<JTextField>>();

		for (int i = 0; i < citynum; i++) {
			JLabel horl = new JLabel(allcity.get(i));
			horl.setBounds(10 + labelwidth + 20 + (textwidth + 10) * i, 5,
					labelwidth, height);
			horl.setFont(font);
			cityhorlabel.add(horl);
			dispane.add(horl);

			JLabel verl = new JLabel(allcity.get(i));
			verl.setBounds(10, 5 + 2 * (i + 1) * height, labelwidth, height);
			verl.setFont(font);
			cityverlabel.add(verl);
			dispane.add(verl);
		}

		for (int i = 0; i < citynum; i++) {
			Vector<JTextField> vectortf = new Vector<JTextField>();
			for (int j = 0; j < citynum; j++) {
				JTextField temptf = new JTextField();
				temptf.setBounds(10 + labelwidth + 10 + (textwidth + 10) * j, 5
						+ 2 * (i + 1) * height, textwidth, height);
				temptf.setFont(f);
				temptf.addFocusListener(foclis);
				dispane.add(temptf);
				vectortf.add(temptf);

				Double distance = disman.getTwoCityDistance(allcity.get(i),
						allcity.get(j));
				temptf.setText(distance + "");
				temptf.setEditable(false);
			}
			distf.add(vectortf);
		}

		citytf = new JTextField();
		citytf.setBounds(300 + 20, 630, 100, 30);
		citytf.addFocusListener(foclis);
		citytf.setFont(f);
		this.add(citytf);

		disadd = new MyOtherBlueLabel("添加");
		disadd.setBounds(300 + 150, 630, 120, 30);
		disadd.addMouseListener(lis);
		this.add(disadd);

		// disok = new JButton("确认");
		// disok.setBounds(300 + 270, 630, 80, 30);
		// disok.addMouseListener(lis);
		// disok.setFont(buttonf);
		// this.add(disok);

		dischange = new MyOtherGreenLabel("修改");
		dischange.setBounds(300 + 300, 630, 120, 30);
		dischange.addMouseListener(lis);
		this.add(dischange);

		disdetele = new MyOtherRedLabel("删除");
		disdetele.setBounds(300 + 450, 630, 80, 30);
		disdetele.addMouseListener(lis);
		this.add(disdetele);
		
		tippane=new JPanel();
		 tippane.setSize(850,40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
		
		
		
	}

	private void resizepanel() {
		dispanecon.setPreferredSize(new Dimension(10 + (textwidth + 10)
				* (citynum + 1) + 10, 2 * (citynum + 1) * height + 10));
		dispane.setPreferredSize(new Dimension(10 + (textwidth + 10)
				* (citynum + 1) + 10, 2 * (citynum + 1) * height + 10));
	}

	private boolean checkDouble(String str) {
		boolean right = true;

		for (int i = 0; i < str.length(); i++) {
			if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str
					.charAt(i) == '.')) {
				right = false;
				break;
			}
		}
		return right;

	}

	private boolean checkDataFull(Vector<JTextField> vectf) {
		boolean complete = true;

		for (int i = 0; i < vectf.size(); i++) {
			if (vectf.get(i).getText().isEmpty()) {
				complete = false;
				vectf.get(i).setBorder(new LineBorder(Color.RED));
			}
		}

		if (!complete) {
			TipBlockEmpty block=new TipBlockEmpty("信息未填写完整");
			tippane.add(block);
			block.show();
			block=null;
		}

		return complete;
	}

	private boolean checkVectorData(Vector<JTextField> vectf) {
		boolean right = true;

		for (int i = 0; i < vectf.size(); i++) {
			if (!checkDouble(vectf.get(i).getText())) {
				right = false;
				vectf.get(i).setBorder(new LineBorder(Color.RED));
			}
		}

		if (!right) {
			TipBlockEmpty block=new TipBlockEmpty("距离应填写小数");
			tippane.add(block);
			block.show();
			block=null;
		}
		return right;
	}

	private boolean checkTextFieldFull(JTextField tf) {
		boolean full = true;

		if (tf.getText().isEmpty()) {
			full = false;
			tf.setBorder(new LineBorder(Color.RED));
			TipBlockEmpty block=new TipBlockEmpty("城市名未填写");
			tippane.add(block);
			block.show();
			block=null;
		}

		return full;
	}

	private boolean isCityExist(String city, boolean add) {
		boolean exist = true;
		int row = allcity.indexOf(city);

		if (add) {
			if (row != -1) {
				TipBlockError block=new TipBlockError("该城市已存在");
				tippane.add(block);
				block.show();
				block=null;
			}else{
				exist = false;
			}
		} else {
			if (row == -1) {
				exist = false;
				TipBlockError block=new TipBlockError("该城市不存在");
				tippane.add(block);
				block.show();
				block=null;
			} else {
				exist = true;
				changerow = row;
			}
		}

		return exist;
	}

	private class Foclistener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 3; i++) {
				if (e.getSource() == pricetf[i])
					pricetf[i].setBorder(border);
			}

			if (adddistance != null) {
				for (int i = 0; i < citynum; i++) {
					if (e.getSource() == adddistance.get(i))
						adddistance.get(i).setBorder(border);
				}
			}
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocus();
			if (e.getSource() == priceadd) {
				boolean complete = true;
				boolean right = true;

				for (int i = 0; i < 3; i++) {
					if (pricetf[i].getText().isEmpty()) {
						complete = false;
						pricetf[i].setBorder(new LineBorder(Color.RED));
					}
				}

				if (complete) {
					for (int i = 0; i < 3; i++) {
						if (!checkDouble(pricetf[i].getText())) {
							right = false;
							pricetf[i].setBorder(new LineBorder(Color.RED));
						}
					}

					if (right) {
						double[] price = new double[3];
						DeliveryType[] type = { DeliveryType.Slow,
								DeliveryType.Standard, DeliveryType.Fast };
						for (int i = 0; i < 3; i++) {
							price[i] = Double.parseDouble(pricetf[i].getText());
							PriceVO vo = new PriceVO(price[i], type[i]);
							priman.setPricePerKMPerKilo(vo);
						}
						TipBlock block=new TipBlock("设置成功");
						tippane.add(block);
						block.show();

					} else {
						TipBlockEmpty block=new TipBlockEmpty("价格应为小数");
						tippane.add(block);
						block.show();
						block=null;
					}
				} else {
					TipBlockEmpty block=new TipBlockEmpty("价格未全部填写");
					tippane.add(block);
					block.show();
					block=null;
				}

			} else if (e.getSource() == disadd) {
				if (disadd.getText().equals("添加")) {
					if (checkTextFieldFull(citytf)) {

						city = citytf.getText();

						if (!isCityExist(city, true)) {
							disadd.setText("确认添加");
							++citynum;
							allcity.add(city);
							resizepanel();

							JLabel cityhoraddl = new JLabel();
							cityhoraddl = new JLabel(city);
							cityhoraddl.setBounds(10 + labelwidth + 20
									+ (textwidth + 20) * (citynum - 1), 5,
									labelwidth, height);
							cityhoraddl.setFont(font);
							dispane.add(cityhoraddl);
							cityhorlabel.add(cityhoraddl);

							JLabel cityveraddl = new JLabel();
							cityveraddl = new JLabel(city);
							cityveraddl.setBounds(10, 5 + 2 * citynum * height,
									labelwidth, height);
							cityveraddl.setFont(font);
							dispane.add(cityveraddl);
							cityverlabel.add(cityveraddl);

							adddistance = new Vector<JTextField>();

							for (int i = 0; i < citynum; i++) {
								JTextField temp = new JTextField();
								temp.setBounds(10 + labelwidth + 10
										+ (textwidth + 10) * i, 5 + 2 * citynum
										* height, textwidth, height);
								temp.setFont(f);
								temp.addFocusListener(foclis);
								dispane.add(temp);
								adddistance.add(temp);

								if (i == citynum - 1) {
									temp.setEditable(false);
									temp.setText("30.0");
								} else
									temp.setEditable(true);
							}

							distf.add(adddistance);
						}
					}
				} else if (disadd.getText().equals("确认添加")) {

					if (checkDataFull(adddistance)) {

						if (checkVectorData(adddistance)) {
							ArrayList<CityDistanceVO> voarr = new ArrayList<CityDistanceVO>();

							for (int i = 0; i < citynum - 1; i++) {
								String str = adddistance.get(i).getText();
								double dist = Double.parseDouble(str);
								CityDistanceVO vo = new CityDistanceVO(city,
										allcity.get(i), dist);
								voarr.add(vo);

								JTextField temp = new JTextField(str);
								temp.setBounds(10 + labelwidth + 10
										+ (textwidth + 10) * (citynum - 1), 5
										+ 2 * (i + 1) * height, textwidth,
										height);
								temp.setFont(f);
								temp.addFocusListener(foclis);
								temp.setEditable(false);
								dispane.add(temp);
								distf.get(i).add(temp);

								adddistance.get(i).setEditable(false);
							}

							disman.addDistanceStrategy(voarr);
							disadd.setText("添加");
							disman.endDistance();
							
							
							TipBlock block=new TipBlock("添加成功");
							tippane.add(block);
							block.show();
							block=null;
						}
					}
				}
			} else if (e.getSource() == disdetele) {
				if (checkTextFieldFull(citytf)) {
					city = citytf.getText();
					if (isCityExist(city, false)) {
						Vector<JTextField> deterow = distf.get(changerow);
						for (int i = 0; i < citynum; i++) {
							deterow.get(i).setVisible(false);
							Vector<JTextField> detecol = distf.get(i);
							detecol.get(changerow).setVisible(false);
							detecol.remove(changerow);
						}
						distf.remove(deterow);
						--citynum;
						allcity.remove(city);
						resizepanel();
						disman.deleteDistanceStrategy(city);
						disman.endDistance();
						TipBlock block=new TipBlock("删除成功");
						tippane.add(block);
						block.show();
					}

				}
			} else if (e.getSource() == dischange) {
				if (dischange.getText().equals("修改")) {
					if (checkTextFieldFull(citytf)) {
						city = citytf.getText();

						if (isCityExist(city, false)) {
							Vector<JTextField> changerowtf = distf
									.get(changerow);
							for (int i = 0; i < citynum; i++) {
								if (i != changerow)
									changerowtf.get(i).setEditable(true);
							}
							dischange.setText("确认修改");
						}
					}
				} else if (dischange.getText().equals("确认修改")) {

					Vector<JTextField> changerowvec = distf.get(changerow);

					if (checkDataFull(changerowvec)) {
						if (checkVectorData(changerowvec)) {
							for (int i = 0; i < citynum; i++) {
								if (i != changerow) {
									changerowvec.get(i).setEditable(false);
									Double distemp = Double
											.parseDouble(changerowvec.get(i)
													.getText());
									String city2 = allcity.get(i);
									CityDistanceVO vo = new CityDistanceVO(
											city, city2, distemp);
									disman.setTwoCityDistance(vo);

									distf.get(i).get(changerow)
											.setText(distemp + "");
								}
							}
							dischange.setText("修改");
							TipBlock block=new TipBlock("修改成功");
							tippane.add(block);
							block.show();
							block=null;
						}
					}
				}
			}
			updateUI();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==priceadd){
				priceadd.whenPressed();
			}else if (e.getSource()==disadd) {
				disadd.whenPressed();
			}else if (e.getSource()==dischange) {
				dischange.whenPressed();
			}else if (e.getSource()==disdetele) {
				disdetele.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==priceadd){
				priceadd.setMyColor();
			}else if (e.getSource()==disadd) {
				disadd.setMyColor();
			}else if (e.getSource()==dischange) {
				dischange.setMyColor();
			}else if (e.getSource()==disdetele) {
				disdetele.setMyColor();
			}

		}

	}
}
