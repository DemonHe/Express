package express.presentation.managerUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.examDocumentBL.ExamDocument;
import express.businesslogicService.managerBLService.ExamDocumentBLService;
import express.po.GoodsArrivalStatus;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.vo.ArrivalDocBusinessHallVO;

public class BusinessHallArrivalViewUI extends JDialog{
	private JTextField[] tf;
	private String id;
	private ArrivalDocBusinessHallVO vo;
	private DefaultTableModel tmodel;
	private ExamDocumentBLService examdoc;
	private MyOtherBlueLabel ok;
	private MyOtherGreenLabel exit;
	
	public BusinessHallArrivalViewUI(DefaultTableModel tablemodel,ArrivalDocBusinessHallVO vo){
		this.id = vo.getTransferDocID();
		this.setTitle(id+"营业厅到达单信息");
		this.setLayout(null);
		this.setSize(500, 280);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		
		
		this.vo = vo;
		tmodel = tablemodel;
		examdoc = new ExamDocument();

		int leftside1 = 10;
		int leftside2 = 110;
		int upside = 10;
		int width = 100;
		int height = 40;

		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变简体", Font.PLAIN, 18);
		JLabel[] label = new JLabel[5];
		tf = new JTextField[5];
		JListener lis = new JListener();

		String[] labelname = { "到达日期", "订单号", "中转单编号", "出发地", "货物到达状态" };

		for (int i = 0; i < 5; i++) {
			label[i] = new JLabel(labelname[i]);
			if(i==4)
				label[i].setBounds(leftside1, upside + 2 * (i / 2)
						* (height - 10), width+30, height);
			else if (i % 2 == 0)
				label[i].setBounds(leftside1, upside + 2 * (i / 2)
						* (height - 10), width, height);
			else
				label[i].setBounds(leftside2 + width + 30, upside + 2 * (i / 2)
						* (height - 10), width, height);
			label[i].setFont(font);
			this.add(label[i]);
		}

		for (int i = 0; i < 5; i++) {
			tf[i] = new JTextField();
			
			if(i==4)
				tf[i].setBounds(leftside2+30,
						upside + 2 * (i / 2) * (height - 10), width+20, height);
			else if (i % 2 == 0)
				tf[i].setBounds(leftside2,
						upside + 2 * (i / 2) * (height - 10), width+20, height);
			else
				tf[i].setBounds(leftside2 + width + 20 + width, upside + 2
						* (i / 2) * (height - 10), width+20, height);

			tf[i].setFont(f);
			tf[i].setEditable(false);
			this.add(tf[i]);
		}
		
		tf[0].setText(this.vo.getArriveTime());
		tf[1].setText(this.vo.getOrderID());
		tf[2].setText(this.vo.getTransferDocID());
		tf[3].setText(this.vo.getDeparture());
		
		GoodsArrivalStatus status = this.vo.getArrivalStatus();
		String state = "";
		if(status.equals(GoodsArrivalStatus.Complete))
			state = "完整";
		else if(status.equals(GoodsArrivalStatus.Damage))
			state = "损坏";
		else if(status.equals(GoodsArrivalStatus.Missing))
			state = "丢失";
		tf[4].setText(state);
		
		ok = new MyOtherBlueLabel("通过");
		ok.setBounds(leftside2, 190, 80, 30);
		ok.addMouseListener(lis);
		this.add(ok);

		exit = new MyOtherGreenLabel("取消");
		exit.setBounds(leftside2 + width + 30, 190, 80, 30);
		exit.addMouseListener(lis);
		this.add(exit);
	}
	
	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == ok) {
				vo.setState(true);
				examdoc.changeBusinessHallArrivalDoc(vo);
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
