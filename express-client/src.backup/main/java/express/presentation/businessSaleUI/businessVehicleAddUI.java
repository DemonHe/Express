package express.presentation.businessSaleUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.Vehicle;
import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
import express.data.vehicleAndDriverData.VehicleIO;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherRedLabel;
import express.vo.VehicleInfoVO;

public class businessVehicleAddUI extends JDialog {

	private DefaultTableModel model;
	private JTextField nametf, numbertf, orgIDtf, useYeartf;
	private JComboBox isUsingcb;
	private MyOtherBlueLabel ok;
	private MyOtherRedLabel exit;

	private String name, number, orgID, isUsing;
	private int useYear;
	private boolean used;

	// private String orgID;//机构名称
	// private int useYear;//服役时间
	// private boolean isUsing;//是否在使�?

	public businessVehicleAddUI(DefaultTableModel tablemodel) {
		this.setTitle("添加车辆");
		this.setLayout(null);
		this.setSize(350, 310);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		this.setUndecorated(true);
		
		int leftside1 = 10;
		int leftside2 = 130;
		Font font = new Font("幼圆", Font.PLAIN, 20);
		Font f = new Font("方正隶变�?�?", Font.PLAIN, 18);
		this.model = tablemodel;

		JListener listener = new JListener();

		JLabel namelabel = new JLabel("车辆代号");
		namelabel.setFont(font);
		namelabel.setBounds(leftside1, 5, 80, 30);
		this.add(namelabel);

		nametf = new JTextField();
		nametf.setBounds(leftside2, 5, 100, 30);
		nametf.setFont(f);
		this.add(nametf);

		JLabel numberlabel = new JLabel("车牌�?");
		numberlabel.setFont(font);
		numberlabel.setBounds(leftside1, 45, 80, 30);
		this.add(numberlabel);

		numbertf = new JTextField();
		numbertf.setBounds(leftside2, 45, 100, 30);
		numbertf.setFont(f);
		this.add(numbertf);

		JLabel orgIDlabel = new JLabel("机构名称");
		orgIDlabel.setFont(font);
		orgIDlabel.setBounds(leftside1, 85, 80, 30);
		this.add(orgIDlabel);

		orgIDtf = new JTextField();
		orgIDtf.setBounds(leftside2, 85, 100, 30);
		orgIDtf.setFont(f);
		this.add(orgIDtf);

		JLabel useYearlabel = new JLabel("服役时间");
		useYearlabel.setFont(font);
		useYearlabel.setBounds(leftside1, 125, 80, 30);
		this.add(useYearlabel);

		useYeartf = new JTextField();
		useYeartf.setBounds(leftside2, 125, 100, 30);
		useYeartf.setFont(f);
		this.add(useYeartf);

		JLabel isUsinglabel = new JLabel("是否在使�?");
		isUsinglabel.setFont(font);
		isUsinglabel.setBounds(leftside1, 165, 120, 30);
		this.add(isUsinglabel);

		isUsingcb = new JComboBox(new String[] { "�?", "�?" });
		isUsingcb.setBounds(leftside2, 165, 100, 30);
		isUsingcb.setFont(f);
		this.add(isUsingcb);

		ok = new MyOtherBlueLabel("确认");
		ok.setBounds(30, 225, 100, 30);
		ok.addMouseListener(listener);

		this.add(ok);

		exit = new MyOtherRedLabel("取消");
		exit.setBounds(180, 225, 100, 30);
		exit.addMouseListener(listener);
		
		this.add(exit);
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == ok) {
				name = nametf.getText();
				number = numbertf.getText();
				orgID = orgIDtf.getText();
				useYear = Integer.parseInt(useYeartf.getText());
				isUsing = isUsingcb.getSelectedItem().toString();
				Object[] values = { false, name, number, orgID, useYear,
						isUsing, "<HTML><U>修改</U></HTML>" };

				if (name.isEmpty() || number.isEmpty()) {
					JOptionPane.showMessageDialog(null, "信息未填写完�?", "提示",
							JOptionPane.WARNING_MESSAGE);
				} else {

					if (isUsing.equals("�?")) {
						used = true;
					} else {
						used = false;
					}

					VehicleInfoVO vo = new VehicleInfoVO(name, number, orgID,
							useYear, used);
					VehicleBusinessSaleblService vds = new Vehicle();

					if (!vds.isCarIDAvailable(name)
							&& !vds.isCarLicenseAvailable(number)) {
						vds.addVehicleInfo(vo);
						model.addRow(values);
						JOptionPane.showMessageDialog(null, "添加成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						vds.endManage();
					} else {
						JOptionPane.showMessageDialog(null, "信息有误，添加失�?", "提示",
								JOptionPane.WARNING_MESSAGE);
					}

				}
				repaint();
			} else if (e.getSource() == exit) {

			}
			dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==ok){
				ok.whenPressed();
			}else if (e.getSource()==exit) {
				exit.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==ok){
				ok.setMyColor();
			}else if (e.getSource()==exit) {
				ok.setMyColor();
			}

		}

	}

}
