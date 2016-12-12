package express.presentation.mainUI;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	private boolean edit = false;
	private Class[] typeArray; // 类型数组
	private String[] head;
	private Object[][] data;
	private String changeunder = "<HTML><U>修改</U></HTML>";
	private String confirmunder = "<HTML><U>确认</U></HTML>";

	public MyTableModel(Object[][] data, String[] head, Class[] typeArray) {
		super(data, head);
		this.typeArray = typeArray;
		this.head = head;
		this.data = data;
	}

	// 使表格具有可编辑性
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(data!=null)
		if (data.length > 0)
			if (data[0][head.length - 1].equals(changeunder)
					|| data[0][head.length - 1].equals(confirmunder)) {
				if (columnIndex == head.length - 1) {
					return false;
				}
			}

		if (edit) {
			if (this.getValueAt(rowIndex, head.length - 1).equals(confirmunder)) {
				return true;
			}
		}

		if (typeArray[0].equals(Boolean.class)) {
			if (columnIndex == 0) {
				return true;
			}
		}
		return false;
	}

	public void setrowedit() {
		edit = true;
	}

	public void setrowunedit() {
		edit = false;
	}

	public void selectAllOrNull(boolean value) {
		for (int i = 0; i < getRowCount(); i++) {
			this.setValueAt(value, i, 0);
		}
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		return typeArray[columnIndex];// 返回每一列的数据类型
	}
}
