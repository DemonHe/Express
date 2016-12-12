package express.presentation.managerUI;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.DeliveryType;
import express.po.PackageType;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.vo.OrderVO;

public class OrderViewUI extends JDialog {
	private JTextField[] tf;
	private OrderVO vo;
	private String id;
	private DefaultTableModel tmodel;
	private ExamDocumentBLService examdoc;
	private MyOtherBlueLabel ok;
	private MyOtherGreenLabel exit;

	public OrderViewUI(DefaultTableModel tablemodel, OrderVO vo) {
		id = vo.getOrderID();
		this.setTitle(id+"收款单信�?");
		this.setLayout(null);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);

		this.vo = vo;
		tmodel = tablemodel;
		examdoc = new ExamDocument();

		int leftside1 = 10;
		int leftside2 = 110;
		int upside = 10;
		int width = 100;
		int height = 40;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		JLabel[] label = new JLabel[10];
		tf = new JTextField[10];
		JListener lis = new JListener();

		String[] labelname = { "出发�?", "到达�?", "原件�?", "实际重量", "体积", "内件品名",
				"快�?�种�?", "包装类型", "订单条形�?", "费用合计" };

		for (int i = 0; i < 10; i++) {
			label[i] = new JLabel(labelname[i]);
			if (i % 2 == 0)
				label[i].setBounds(leftside1, upside + 2 * (i / 2)
						* (height - 10), width, height);
			else
				label[i].setBounds(leftside2 + width + 30, upside + 2 * (i / 2)
						* (height - 10), width, height);
			label[i].setFont(font);
			this.add(label[i]);
		}

		for (int i = 0; i < 10; i++) {
			tf[i] = new JTextField();

			if (i % 2 == 0)
				tf[i].setBounds(leftside2,
						upside + 2 * (i / 2) * (height - 10), width+20, height);
			else
				tf[i].setBounds(leftside2 + width + 20 + width, upside + 2
						* (i / 2) * (height - 10), width+20, height);

			tf[i].setFont(f);
			tf[i].setEditable(false);
			this.add(tf[i]);
		}

		tf[0].setText(this.vo.getStartCity());
		tf[1].setText(this.vo.getEndCity());
		tf[2].setText(this.vo.getNumberOfGoods() + "");
		tf[3].setText(this.vo.getWeight() + "");
		tf[4].setText(this.vo.getVolume() + "");
		tf[5].setText(this.vo.getNameOfGoods());
		tf[8].setText(id);
		tf[9].setText(this.vo.getFee() + "");
		
		String delitype = "";
		DeliveryType dtype = this.vo.getType();
		if(dtype.equals(DeliveryType.Slow))
			delitype = "经济快�??";
		else if(dtype.equals(DeliveryType.Standard))
			delitype = "标准快�??";
		else if(dtype.equals(DeliveryType.Fast))
			delitype = "特快快�??";		
		tf[6].setText(delitype);
		
		String pactype = "";
		PackageType ptype = this.vo.getPackageType();
		if(ptype.equals(PackageType.CardBox))
			pactype = "纸箱";
		else 	if(ptype.equals(PackageType.WoodBox))
			pactype = "木箱";
		else 	if(ptype.equals(PackageType.DeliverBag))
			pactype = "快�?�袋";
		tf[7].setText(pactype);

		ok = new MyOtherBlueLabel("通过");
		ok.setBounds(leftside2, 310, 80, 30);

		ok.addMouseListener(lis);
		this.add(ok);

		exit = new MyOtherGreenLabel("取消");
		exit.setBounds(leftside2 + width + 30, 310, 80, 30);

		exit.addMouseListener(lis);
		this.add(exit);
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ok) {
				vo.setState(true);
				examdoc.changeOrder(vo);
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if (tmodel.getValueAt(i, 2).equals(id)) {
						tmodel.removeRow(i);
						JOptionPane.showMessageDialog(null,"该单据已通过审批", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			}
			dispose();
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
		public void mousePressed(MouseEvent arg0) {
			if(arg0.getSource()==ok){
				ok.whenPressed();
			}else if (arg0.getSource()==exit) {
				exit.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(arg0.getSource()==ok){
				ok.setMyColor();
			}else if (arg0.getSource()==exit) {
				exit.setMyColor();
			}
			

		}

	}
}
