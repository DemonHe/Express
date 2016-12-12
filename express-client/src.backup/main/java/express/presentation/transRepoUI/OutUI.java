package express.presentation.transRepoUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import express.businessLogic.IDKeeper;
import express.businessLogic.documentBL.InDoc;
import express.businessLogic.documentBL.OutDoc;
import express.businessLogic.documentBL.TransferDoc;
import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
import express.businesslogicService.transcenterRepoBLService.InDocblService;
import express.businesslogicService.transcenterRepoBLService.OutDocblService;
import express.businesslogicService.transcenterSaleBLService.TransCenterTransferDocblService;
import express.po.Area;
import express.presentation.mainUI.DateChooser;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyOtherOrangeLabel;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.InDocVO;
import express.vo.OutDocVO;

public class OutUI extends JPanel {

	// private JButton button_out;
	// private JButton button_return;
	private JPanel tippane;
	private MyOtherBlueLabel confirm;
	private MyOtherGreenLabel cancel;
	private MyOtherOrangeLabel exit;
	private JTextField textArea5, textArea4, datetf,dest;
	private MainUIService m;
	private DateChooser datechooser;
	private JComboBox<String> orderID, tranway;
	private JRadioButton tr, vr;
	private String orgID = IDKeeper.getOrgID();
	private JLabel trans, car;
	private boolean choose_t = true;

	public OutUI(MainUIService main) {

		int textlength = 200;
		int textwidth = 30;

		int labellength = 100;
		int labelwidth = 30;

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("隶书", Font.PLAIN, 20);

		setLayout(null);
		this.m = main;

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		AdjustRepoBLService adjust = new RepoController();

		String[] id = adjust.getAllInDoc(orgID);
		orderID = new JComboBox<String>(id);
		orderID.setBounds(230, 120, textlength, textwidth);
		orderID.setFont(font);
		orderID.setBackground(Color.WHITE);
		this.add(orderID);

		datetf = new JTextField();
		datetf.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		datetf.setBounds(230, 220, textlength, textwidth);
		datetf.setFont(f);
		datetf.setEditable(false);
		this.add(datetf);

		datechooser = new DateChooser("yyyy-MM-dd", datetf);
		datechooser.setBounds(440, 215, 40, 40);
		this.add(datechooser);

		dest = new JTextField();
		dest.setBounds(230, 320, textlength, textwidth);
		dest.setFont(font);
		dest.setEditable(false);
		dest.setBackground(Color.WHITE);
		this.add(dest);

		String[] tranways = { "汽车", "火车", "飞机" };
		tranway = new JComboBox<String>(tranways);
		tranway.setFont(f);
		tranway.setBackground(Color.WHITE);
		tranway.setBounds(230, 420, labellength, textwidth);
		this.add(tranway);

		textArea4 = new JTextField();
		textArea4.setBounds(230, 520, 200, textwidth);
		textArea4.setFont(f);
		textArea4.setEditable(false);
		textArea4.setBackground(Color.WHITE);
		textArea4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(textArea4);

		textArea5 = new JTextField();
		textArea5.setBounds(550, 520, 200, textwidth);
		textArea5.setFont(f);
		textArea5.setEditable(false);
		textArea5.setText("");
		this.add(textArea5);

		tr = new JRadioButton();
		tr.setSelected(choose_t);
		tr.setBounds(95, 505, 25, 60);
		tr.setBackground(Color.WHITE);
		this.add(tr);

		vr = new JRadioButton();
		vr.setSelected(!choose_t);
		vr.setBounds(455, 505, 25, 60);
		vr.setBackground(Color.WHITE);
		this.add(vr);

		JLabel title = new JLabel();
		title.setBounds(400, 50, labellength, labelwidth);
		title.setText("�? �? �?");
		title.setFont(new Font("楷体", Font.BOLD, 20));
		this.add(title);

		JLabel label1 = new JLabel("快�?�编�?");
		label1.setBounds(100, 120, labellength, labelwidth);
		label1.setFont(font);
		this.add(label1);

		JLabel label2 = new JLabel("出库日期");
		label2.setBounds(100, 220, labellength, labelwidth);
		label2.setFont(font);
		this.add(label2);

		JLabel label3 = new JLabel("目的�?");
		label3.setBounds(100, 320, labellength, labelwidth);
		label3.setFont(font);
		this.add(label3);

		JLabel label4 = new JLabel("装运形式");
		label4.setBounds(100, 420, labellength, labelwidth);
		label4.setFont(font);
		this.add(label4);

		trans = new JLabel("中转单编�?");
		trans.setBounds(120, 520, labellength, labelwidth);
		trans.setFont(font);
		this.add(trans);

		car = new JLabel("车次�?");
		car.setBounds(480, 520, 60, labelwidth);
		car.setFont(font);
		car.setForeground(Color.LIGHT_GRAY);
		this.add(car);

		confirm = new MyOtherBlueLabel("确定");
		confirm.setBounds(100, 600, 160, 40);

	
		this.add(confirm);

		cancel = new MyOtherGreenLabel("取消");
		cancel.setBounds(345, 600, 160, 40);
		this.add(cancel);

		exit = new MyOtherOrangeLabel("返回菜单");
		exit.setBounds(590, 600, 160, 40);
		this.add(exit);

		JListener listener = new JListener();
		confirm.addMouseListener(listener);
		cancel.addMouseListener(listener);
		exit.addMouseListener(listener);
		tr.addMouseListener(listener);
		vr.addMouseListener(listener);

		Item itemListener = new Item();
		tranway.addItemListener(itemListener);
		
		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		init();
	}

	private void init() {
		String id = (String) orderID.getSelectedItem();

		if (id.charAt(0) >= '0' && id.charAt(0) <= '9') {
			TransCenterTransferDocblService transfer = new TransferDoc();
			String transID = transfer.getTransferDocID();
			textArea4.setText(transID);
			
			InDocblService inDoc = new InDoc();
			InDocVO in = inDoc.getInDoc(id);
			dest.setText(in.getarrival());
		} else {
			textArea4.setText("无中转单编号");
			dest.setText("�?");
		}
	}

	private boolean check() {
		boolean fill = true;
		if (!choose_t) {
			String text = textArea5.getText();
			String type = car.getText();
			if (text.equals("")) {
				fill = false;
				textArea5.setBorder(BorderFactory.createLineBorder(new Color(
						255, 215, 0), 2));
				JOptionPane.showConfirmDialog(null, "请填�?" + type + "\n"
						+ "或�?�择中转单编号生成单�?", null, JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null);
			}
		}
		return fill;
	}

	private boolean checkDate() {
		String date = datetf.getText();
		String t = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (t.compareTo(date) >= 0)
			return true;
		else {
			JOptionPane.showConfirmDialog(null, "出库时间应该早于或等于当前时�?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null);
			return false;
		}
	}

	private void outRepo() {
		String id = (String) orderID.getSelectedItem();
		int index = orderID.getSelectedIndex();
		orderID.removeItemAt(index);
		if(orderID.getItemCount() == 0){
			orderID.addItem("仓库没有快�?�入�?");
		}
		updateUI();
		
		String date = datetf.getText();
		String destination = dest.getText();
		String way = (String) tranway.getSelectedItem();
		String type;
		if (choose_t) {
			type = textArea4.getText();
		} else {
			type = textArea5.getText();
		}

		OutDocVO vo = new OutDocVO(id, date, destination, way, type, orgID);
		OutDocblService outDoc = new OutDoc();
		
		AdjustRepoBLService adjust = new RepoController();
		
		InDocblService inDoc = new InDoc();
		InDocVO in = inDoc.getInDoc(id);
		
		adjust.freeRepoBlock(orgID, in.getRepoPosition());
		adjust.recordRepo();
		
		boolean succ = outDoc.addOutDoc(vo);
		outDoc.endOutDoc();
		if (succ) {
			TipBlock block = new TipBlock("出库成功");
			tippane.add(block);
			block.show();
			block = null;
		} else {
			TipBlockError block = new TipBlockError("出库失败");
			tippane.add(block);
			block.show();
			block = null;
		}
		datetf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textArea4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	private class JListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == confirm) {
				String id = (String)orderID.getSelectedItem();
				if (id == null||(id.charAt(0) < '0' || id.charAt(0) >'9')) {
					TipBlockEmpty block = new TipBlockEmpty("仓库没有库存");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					if (check()) {
						if (checkDate()) {
							outRepo();
						}
					}
				}
			} else if (e.getSource() == cancel) {
				datetf.setText(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				datetf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				textArea5.setText("");
				textArea5.setBorder(BorderFactory.createLineBorder(Color.GRAY,
						1));
				choose_t = true;
				tr.setSelected(choose_t);
				vr.setSelected(!choose_t);
				textArea4.setBackground(Color.WHITE);
				textArea4.setForeground(Color.BLACK);
				textArea4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				trans.setForeground(Color.BLACK);
				textArea5.setEditable(false);
				car.setForeground(Color.LIGHT_GRAY);
			} else if (e.getSource() == exit) {

				m.jumpTotransSaleMenuUI(orgID);

			} else if (e.getSource() == tr) {
				choose_t = true;
				tr.setSelected(choose_t);
				vr.setSelected(!choose_t);
				textArea4.setBackground(Color.WHITE);
				textArea4.setForeground(Color.BLACK);
				textArea4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				trans.setForeground(Color.BLACK);
				textArea5.setEditable(false);
				textArea5.setForeground(Color.LIGHT_GRAY);
				car.setForeground(Color.LIGHT_GRAY);
			} else if (e.getSource() == vr) {
				choose_t = false;
				tr.setSelected(choose_t);
				vr.setSelected(!choose_t);
				// textArea4.setBackground(Color.LIGHT_GRAY);
				textArea4.setForeground(Color.LIGHT_GRAY);
				textArea4.setBorder(BorderFactory
						.createLineBorder(Color.LIGHT_GRAY));
				trans.setForeground(Color.LIGHT_GRAY);
				textArea5.setEditable(true);
				textArea5.setForeground(Color.BLACK);
				car.setForeground(Color.BLACK);
			}
			repaint();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			if(arg0.getSource()==confirm){
				confirm.whenPressed();
			}else if (arg0.getSource()==cancel) {
				cancel.whenPressed();
			}else if (arg0.getSource()==exit) {
				exit.whenPressed();
			}
			

		}

		public void mouseReleased(MouseEvent arg0) {
			if(arg0.getSource()==confirm){
				confirm.setMyColor();
			}else if (arg0.getSource()==cancel) {
				cancel.setMyColor();
			}else if (arg0.getSource()==exit) {
				exit.setMyColor();
			}

		}

	}

	private class Item implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getSource() == tranway) {
					if (tranway.getSelectedIndex() == 2) {
						car.setText("航班�?");
					} else {
						car.setText("车次�?");
					}
				} else if (e.getSource() == orderID) {
					init();
				}
				updateUI();
			}

		}

	}

}
