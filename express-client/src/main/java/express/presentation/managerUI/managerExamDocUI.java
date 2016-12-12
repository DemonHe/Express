package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.GoodsArrivalStatus;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyCellRenderer;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.MyTableModel;
import express.vo.ArrivalDocBusinessHallVO;
import express.vo.ArrivalDocTransCenterVO;
import express.vo.DeliverDocVO;
import express.vo.DocumentVO;
import express.vo.InDocVO;
import express.vo.OrderVO;
import express.vo.OutDocVO;
import express.vo.PaymentDocVO;
import express.vo.ReceiveDocVO;
import express.vo.ShipmentDocBusinessHallVO;
import express.vo.ShipmentDocTransCenterVO;
import express.vo.TransferDocVO;

public class managerExamDocUI extends JPanel {

	private JPanel tippane;
	private JButton exam;
	private JComboBox<String> typebox;
	private MyOtherGreenLabel find;
	private JPanel currPanel;
	private JPanel title;
	//
	private MyTableModel[] tableModel;
	private MyCellRenderer[] headerren;
	private ExamDocumentBLService examdoc;
	private ArrayList<OrderVO> orderarr;
	private ArrayList<ShipmentDocBusinessHallVO> shipbusarr;
	private ArrayList<ArrivalDocTransCenterVO> arrivaltransarr;
	private ArrayList<InDocVO> indocarr;
	private ArrayList<TransferDocVO> transdocarr;
	private ArrayList<OutDocVO> outdocarr;
	private ArrayList<ShipmentDocTransCenterVO> shiptransarr;
	private ArrayList<ArrivalDocBusinessHallVO> arrivalbusarr;
	private ArrayList<DeliverDocVO> deliverdocarr;
	private ArrayList<ReceiveDocVO> receivearr;
	private ArrayList<PaymentDocVO> paymentarr;

	public managerExamDocUI() {
		setLayout(null);
		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Font font = new Font("楷体", Font.PLAIN, 20);
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JLabel t = new JLabel("单据类型", JLabel.CENTER);
		t.setFont(new Font("楷体", Font.PLAIN, 21));
		t.setBounds(30, 50, 150, 30);
		this.add(t);

		String[] type = { "寄件单", "收款单", "营业厅到达单", "装车单", "派件单", "中转中心到达单",
				"入库单", "中转单", "出库单", "付款单" };
		typebox = new JComboBox<String>(type);
		typebox.setBounds(200, 50, 420, 30);
		typebox.setFont(font);
		typebox.setMaximumRowCount(10);
		typebox.setBackground(Color.WHITE);
		this.add(typebox);

		Listener listen = new Listener();

		find = new MyOtherGreenLabel("查找");
		find.setBounds(670, 50, 110, 30);
		find.addMouseListener(listen);
		this.add(find);

		title = new JPanel();
		title.setBounds(70, 115, 710, 35);
		title.setLayout(null);
		title.setOpaque(false);
		title.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
				Color.LIGHT_GRAY));
		this.add(title);

		currPanel = new JPanel();
		currPanel.setLocation(70, 150);
		currPanel.setPreferredSize(new Dimension(710, 500));
		currPanel.setOpaque(false);
		currPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(currPanel);
		scrollPane.setBounds(70, 150, 710, 500);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
				Color.LIGHT_GRAY));
		MyScrollPane render = new MyScrollPane();
		scrollPane.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		updateUI();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);
	}

	private void getList(int index) {
		title.removeAll();
		currPanel.removeAll();
		repaint();

		switch (index) {
		case 0:
			new ExamOrder(currPanel, title, tippane);
			break;
		case 1:
			new ExamReceive(currPanel, title, tippane);
			break;
		case 2:
			new ExamBusinessArrival(currPanel, title, tippane);
			break;
		case 3:
			new ExamShipment(currPanel, title, tippane);
			break;
		case 4:
			new ExamDeliver(currPanel, title, tippane);
			break;
		case 5:
			new ExamTransArrival(currPanel, title, tippane);
			break;
		case 6:
			new ExamIn(currPanel, title, tippane);
			break;
		case 7:
			new ExamTransfer(currPanel, title, tippane);
			break;
		case 8:
			new ExamOut(currPanel, title, tippane);
			break;
		case 9:
			new ExamPayment(currPanel, title, tippane);
			break;
		}
	}

	private class Listener implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			if (e.getSource() == find) {
				int index = typebox.getSelectedIndex();
				getList(index);
			}
			repaint();

			if (e.getSource() == exam) {
				// 通过审批，设置单据审批状态
				for (int i = 0; i < tableModel[0].getRowCount(); i++) {
					if ((boolean) tableModel[0].getValueAt(i, 0)) {
						OrderVO vo = orderarr.get(i);
						vo.setState(true);
						examdoc.changeOrder(vo);
						orderarr.remove(i);
						tableModel[0].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[1].getRowCount(); i++) {
					if ((boolean) tableModel[1].getValueAt(i, 0)) {
						ArrivalDocBusinessHallVO vo = arrivalbusarr.get(i);
						vo.setState(true);
						examdoc.changeBusinessHallArrivalDoc(vo);
						arrivalbusarr.remove(i);
						tableModel[1].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[2].getRowCount(); i++) {
					if ((boolean) tableModel[2].getValueAt(i, 0)) {
						ArrivalDocTransCenterVO vo = arrivaltransarr.get(i);
						vo.setState(true);
						examdoc.changeTransCenterArrivalDoc(vo);
						arrivaltransarr.remove(i);
						tableModel[2].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[3].getRowCount(); i++) {
					if ((boolean) tableModel[3].getValueAt(i, 0)) {
						DeliverDocVO vo = deliverdocarr.get(i);
						vo.setState(true);
						examdoc.changeDeliverDoc(vo);
						deliverdocarr.remove(i);
						tableModel[3].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[4].getRowCount(); i++) {
					if ((boolean) tableModel[4].getValueAt(i, 0)) {
						InDocVO vo = indocarr.get(i);
						vo.setState(true);
						examdoc.changeInDoc(vo);
						indocarr.remove(i);
						tableModel[4].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[5].getRowCount(); i++) {
					if ((boolean) tableModel[5].getValueAt(i, 0)) {
						OutDocVO vo = outdocarr.get(i);
						vo.setState(true);
						examdoc.changeOutDoc(vo);
						outdocarr.remove(i);
						tableModel[5].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[6].getRowCount(); i++) {
					if ((boolean) tableModel[6].getValueAt(i, 0)) {
						PaymentDocVO vo = paymentarr.get(i);
						vo.setState(true);
						examdoc.changePaymentDoc(vo);
						paymentarr.remove(i);
						tableModel[6].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[7].getRowCount(); i++) {
					if ((boolean) tableModel[7].getValueAt(i, 0)) {
						ReceiveDocVO vo = receivearr.get(i);
						vo.setState(true);
						examdoc.changeReceiveDoc(vo);
						receivearr.remove(i);
						tableModel[7].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[8].getRowCount(); i++) {
					if ((boolean) tableModel[8].getValueAt(i, 0)) {
						ShipmentDocBusinessHallVO vo = shipbusarr.get(i);
						vo.setState(true);
						examdoc.changeBusinessHallShipmentDoc(vo);
						shipbusarr.remove(i);
						tableModel[8].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[9].getRowCount(); i++) {
					if ((boolean) tableModel[9].getValueAt(i, 0)) {
						ShipmentDocTransCenterVO vo = shiptransarr.get(i);
						vo.setState(true);
						examdoc.changeTransCenterShipmentDoc(vo);
						shiptransarr.remove(i);
						tableModel[9].removeRow(i);
					}
				}

				for (int i = 0; i < tableModel[10].getRowCount(); i++) {
					if ((boolean) tableModel[10].getValueAt(i, 0)) {
						TransferDocVO vo = transdocarr.get(i);
						vo.setState(true);
						examdoc.changeTransferDoc(vo);
						transdocarr.remove(i);
						tableModel[10].removeRow(i);
					}
				}
			}

			for (int i = 0; i < 11; i++) {
				// if (e.getSource() == table[i]) {
				// int row = table[i].getSelectedRow();
				// int col = table[i].getSelectedColumn();
				//
				// if (tableModel[i].getValueAt(row, col).equals(changeunder)) {
				// if (i == 0) {
				// OrderViewUI orui = new OrderViewUI(tableModel[i],
				// orderarr.get(row));
				// orui.setVisible(true);
				// } else if (i == 1) {
				// BusinessHallArrivalViewUI baui = new
				// BusinessHallArrivalViewUI(
				// tableModel[i], arrivalbusarr.get(row));
				// baui.setVisible(true);
				// }
				//
				// }
				// }

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
			find.whenPressed();
		}

		public void mouseReleased(MouseEvent e) {
			find.setMyColor();
		}
	}
}
