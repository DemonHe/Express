package express.presentation.mainUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MyCellRenderer extends DefaultTableCellRenderer {

	private MyTableModel tModel;
	private JTableHeader tableHeader;
	private JCheckBox selectBox;

	public MyCellRenderer(JTable table,MyTableModel tableModel) {
		this.tModel = tableModel;
		this.tableHeader = table.getTableHeader();
		selectBox = new JCheckBox(tModel.getColumnName(0));
		selectBox.setSelected(false);

		tableHeader.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					// 获得选中�?
					int selectColumn = tableHeader.columnAtPoint(e
							.getPoint());
					if (selectColumn == 0) {
						boolean value = !selectBox.isSelected();
						selectBox.setSelected(value);
						tModel.selectAllOrNull(value);
						tableHeader.repaint();
					}
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
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		// TODO Auto-generated method stub

		String valueStr = (String) value;
		JLabel label = new JLabel(valueStr);
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER); // 表头标签剧中
		selectBox.setHorizontalAlignment(SwingConstants.CENTER);// 表头标签剧中
		selectBox.setBorderPainted(true);

		JComponent component = (column == 0) ? selectBox : label;
		component.setForeground(tableHeader.getForeground());
//		component.setBackground(Color.WHITE);
		component.setFont(new Font("幼圆", Font.PLAIN, 18));
		component.setBorder(UIManager.getBorder("Table.cellBorder"));
		return component;

	}

}
