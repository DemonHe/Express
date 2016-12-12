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
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.vo.OrderVO;

public class OrderChangeUI extends JDialog {
	private JTextField[] tf;
	private OrderVO vo;
	private String id;
	private JComboBox deliverytype, packtype;
	private DefaultTableModel tmodel;
	private ExamDocumentBLService examdoc;
	private MyOtherBlueLabel ok;
	private MyOtherGreenLabel exit;

	public OrderChangeUI(DefaultTableModel tablemodel, OrderVO vo) {
		this.setTitle("修改用户信息");
		this.setLayout(null);
		this.setSize(400, 330);
		this.setLocationRelativeTo(null);

		this.vo = vo;
		tmodel = tablemodel;
		examdoc = new ExamDocument();
		
		int leftside1 = 10;
		int leftside2 = 100;
		int upside = 10;
		int width = 90;
		int height = 40;

		Font font = new Font("楷体", Font.PLAIN, 18);
		Font f = new Font("仿宋", Font.PLAIN, 16);
		JLabel[] label = new JLabel[10];
		tf = new JTextField[8];
		JListener lis = new JListener();
		
		String[] labelname = { "出发地", "到达地", "原件数", "实际重量", "体积", "内件品名",
				"快递种类", "包装类型", "订单条形码", "费用合计" };
		String[] type1 = {"经济快递","标准快递","特快快递"};
		String[] type2 = {"纸箱","木箱","快递袋"};		

		for (int i = 0; i < 10; i++) {
			label[i] = new JLabel(labelname[i]);
			if(i%2==0)
			label[i].setBounds(leftside1, upside + 2 * (i/2) * (height - 10),
					width, height);
			else
				label[i].setBounds(leftside2+width+20, upside + 2 * (i/2) * (height - 10),
						width, height);
			label[i].setFont(font);
			this.add(label[i]);
		}

		for (int i = 0; i < 8; i++) {
			tf[i] = new JTextField();
			if (i < 6){
				if(i%2==0)
				tf[i].setBounds(leftside2, upside + 2 * (i/2) * (height - 10),
						width, height);
				else
					tf[i].setBounds(leftside2+width+20+width, upside + 2 * (i/2) * (height - 10),
							width, height);
			}
			else{
				if(i==6)
				tf[i].setBounds(leftside2, upside + 2 * 4 * (height - 10),
						width, height);
				else
					tf[i].setBounds(leftside2+width+20+width, upside + 2 * 4 * (height - 10),
							width, height);
			}
			tf[i].setFont(f);
			this.add(tf[i]);
		}

		id = vo.getOrderID();
		tf[0].setText(this.vo.getStartCity());
		tf[1].setText(this.vo.getEndCity());
		tf[2].setText(this.vo.getNumberOfGoods() + "");
		tf[3].setText(this.vo.getWeight() + "");
		tf[4].setText(this.vo.getVolume() + "");
		tf[5].setText(this.vo.getNameOfGoods());
		tf[6].setText(id);
		tf[7].setText(this.vo.getFee() + "");
		
		deliverytype = new JComboBox(type1);
		deliverytype.setBounds(leftside2, upside + 2 * 3 * (height - 10),
						width, height);
		deliverytype.setFont(f);
		this.add(deliverytype);
		
		packtype = new JComboBox(type2);
		packtype.setBounds(leftside2+width+20+width, upside + 2 * 3 * (height - 10),
						width, height);
		packtype.setFont(f);
		this.add(packtype);
		
		ok = new MyOtherBlueLabel("修改");
		ok.setBounds(70, 280, 70, 30);

		ok.addMouseListener(lis);
		this.add(ok);

		exit = new MyOtherGreenLabel("取消");
		exit.setBounds(210, 280, 70, 30);

		exit.addMouseListener(lis);
		this.add(exit);
	}
	
	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==ok){
				//添加修改vo信息
				examdoc.changeOrder(vo);
				for (int i = tmodel.getRowCount() - 1; i >= 0; i--) {
					if(tmodel.getValueAt(i, 2).equals(id)){
						tmodel.removeRow(i);
						JOptionPane.showMessageDialog(null, "修改成功"+"\n"+"该单据已通过审批", "提示",
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
