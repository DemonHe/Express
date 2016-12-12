package express.presentation.transRepoUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

import express.businessLogic.IDKeeper;
import express.businessLogic.repoBL.RepoController;
import express.businesslogicService.transcenterRepoBLService.InventoryRepoBLService;
import express.po.Area;
import express.po.RepoPosition;
import express.presentation.mainUI.MainUIService;
import express.presentation.mainUI.MyOtherBlueLabel;
import express.presentation.mainUI.MyOtherGreenLabel;
import express.presentation.mainUI.MyScrollPane;
import express.presentation.mainUI.TipBlock;
import express.presentation.mainUI.TipBlockEmpty;
import express.presentation.mainUI.TipBlockError;
import express.vo.InDocVO;

public class InventoryUI extends JPanel {

	private MainUIService m;
	private JPanel tippane;
	private JPanel inventory;
	private MyOtherBlueLabel excel;
	private MyOtherGreenLabel exit;
	private String orgID = IDKeeper.getOrgID();
	private boolean isNull = false;

	public InventoryUI(MainUIService main) {

		Font font = new Font("楷体", Font.PLAIN, 20);
		// Font f = new Font("仿宋", Font.PLAIN, 18);
		Font f1 = new Font("隶书", Font.PLAIN, 20);

		setLayout(null);
		this.m = main;

		this.setBounds(0, 0, 850, 700);
		this.setBackground(Color.WHITE);

		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(d);
		JLabel title = new JLabel(date + " 库存盘点", JLabel.CENTER);
		// title.setText(date+" 库存盘点");
		title.setFont(new Font("仿宋", Font.BOLD, 20));
		title.setBounds(100, 30, 600, 30);
		this.add(title);

		inventory = new JPanel();
		inventory.setLocation(100, 95);
		inventory.setPreferredSize(new Dimension(640, 460));
		inventory.setOpaque(false);
		inventory.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(inventory);
		scrollPane.setFont(font);
		scrollPane.setBounds(100, 95, 650, 470);
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

		excel = new MyOtherBlueLabel("导出Excel");
		excel.setBounds(100, 600, 250, 40);
		this.add(excel);

		exit = new MyOtherGreenLabel("返回菜单");
		exit.setBounds(500, 600, 250, 40);
		this.add(exit);

		Listener listener = new Listener();
		excel.addMouseListener(listener);
		exit.addMouseListener(listener);

		tippane = new JPanel();
		tippane.setSize(850, 40);
		tippane.setLocation(0, 660);
		tippane.setBackground(Color.white);
		tippane.setLayout(null);
		this.add(tippane);

		init();
	}

	private void init() {
		InventoryRepoBLService view = new RepoController();
		view.endRepoInventory();

		ArrayList<InDocVO> list = view.inventoryRepo(orgID);

		if (list == null) {
			addNone();
			isNull = true;
			return;
		}
		if (list.size() == 0) {
			addNone();
			isNull = true;
			return;
		}
		addTitle();
		isNull = false;
		int index = 0;
		for (InDocVO vo : list) {
			addLine(vo, index);
			index++;
		}
		int height = 40 * list.size();
		inventory.setPreferredSize(new Dimension(640, height));
	}

	private void addLine(InDocVO vo, int i) {

		int y = i * 40;
		int height = 40;
		Font f = new Font("仿宋", Font.PLAIN, 18);

		JLabel title1 = new JLabel(vo.getdeliveryNumber(), JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(0, y, 100, height);
		title1.setOpaque(false);
		title1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title1);

		JLabel title2 = new JLabel(vo.getdate(), JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(100, y, 100, height);
		title2.setOpaque(false);
		title2.setBackground(new Color(250, 235, 215));
		title2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title2);

		JLabel title3 = new JLabel(vo.getarrival(), JLabel.CENTER);
		title3.setFont(f);
		title3.setBounds(200, y, 90, height);
		title3.setOpaque(false);
		title3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title3);

		RepoPosition rp = vo.getRepoPosition();
		Area a = rp.getblock();
		JLabel title4 = new JLabel(getArea(a), JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(290, y, 90, height);
		title4.setOpaque(false);
		title4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title4);

		JLabel title5 = new JLabel(rp.getrow() + "", JLabel.CENTER);
		title5.setFont(f);
		title5.setBounds(380, y, 90, height);
		title5.setOpaque(false);
		title5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title5);

		JLabel title6 = new JLabel(rp.getshelf() + "", JLabel.CENTER);
		title6.setFont(f);
		title6.setBounds(470, y, 90, height);
		title6.setOpaque(false);
		title6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title6);

		JLabel title7 = new JLabel(rp.getposition() + "", JLabel.CENTER);
		title7.setFont(f);
		title7.setBounds(560, y, 90, height);
		title7.setOpaque(false);
		title7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1,
				Color.LIGHT_GRAY));
		inventory.add(title7);
	}

	private void addTitle() {
		Font f = new Font("仿宋", Font.PLAIN, 18);
		JLabel title1 = new JLabel("快�?�编�?", JLabel.CENTER);
		title1.setFont(f);
		title1.setBounds(100, 60, 100, 35);
		title1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title1);

		JLabel title2 = new JLabel("入库日期", JLabel.CENTER);
		title2.setFont(f);
		title2.setBounds(200, 60, 100, 35);
		title2.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title2);

		JLabel title3 = new JLabel("目的�?", JLabel.CENTER);
		title3.setFont(f);
		title3.setBounds(300, 60, 90, 35);
		title3.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title3);

		JLabel title4 = new JLabel("区号", JLabel.CENTER);
		title4.setFont(f);
		title4.setBounds(390, 60, 90, 35);
		title4.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title4);

		JLabel title5 = new JLabel("排号", JLabel.CENTER);
		title5.setFont(f);
		title5.setBounds(480, 60, 90, 35);
		title5.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title5);

		JLabel title6 = new JLabel("架号", JLabel.CENTER);
		title6.setFont(f);
		title6.setBounds(570, 60, 90, 35);
		title6.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title6);

		JLabel title7 = new JLabel("位号", JLabel.CENTER);
		title7.setFont(f);
		title7.setBounds(660, 60, 90, 35);
		title7.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 1,
				Color.LIGHT_GRAY));
		this.add(title7);
	}

	private void addNone() {
		JLabel title = new JLabel();
		title.setBounds(235, 60, 330, 35);
		title.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
				Color.LIGHT_GRAY));
		this.add(title);

		JLabel tip = new JLabel("�? �? �? �? �? �?", JLabel.CENTER);
		tip.setBounds(0, 100, 640, 200);
		tip.setFont(new Font("仿宋", Font.BOLD, 49));
		tip.setOpaque(false);
		tip.setForeground(Color.LIGHT_GRAY);
		inventory.add(tip);
	}

	private String getArea(Area a) {
		switch (a) {
		case AIR:
			return "航运�?";
		case TRAIN:
			return "铁运�?";
		case CAR:
			return "汽运�?";
		default:
			return "机动�?";
		}
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource() == excel) {
				if (isNull) {
					TipBlockEmpty block = new TipBlockEmpty("没有入库信息");
					tippane.add(block);
					block.show();
					block = null;
				} else {
					InventoryRepoBLService view = new RepoController();

					JFileChooser jfc = new JFileChooser();
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.showSaveDialog(new JLabel());
					jfc.addChoosableFileFilter(new FileFilter() {
						public boolean accept(File file) {
							return true;
						}

						public String getDescription() {
							return ".xls";
						}
					});

					File file = jfc.getSelectedFile();

					String path = file.getAbsolutePath();
					boolean succ = view.exportExcel(orgID, path);
					if (succ) {
						TipBlock block = new TipBlock("导出成功");
						tippane.add(block);
						block.show();
						block = null;
					} else {
						TipBlockError block = new TipBlockError("导出失败");
						tippane.add(block);
						block.show();
						block = null;

					}
				}
			} else if (e.getSource() == exit) {
				m.jumpTotranscenterRepoMenuUI(IDKeeper.getID());
			}
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
			if (e.getSource() == excel) {
				excel.whenPressed();
			} else if (e.getSource() == exit) {
				exit.whenPressed();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == excel) {
				excel.setMyColor();
			} else if (e.getSource() == exit) {
				exit.setMyColor();
			}

		}

	}

}
