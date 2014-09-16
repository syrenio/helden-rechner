package at.syrenio.helden.rechner;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RechnerTableModel extends AbstractTableModel {
	private String[] columnNames = { "Talent", "Probe", "Wert", "Wuerfeln", "Endwert" };
	private List<RechnerRowData> data;

	public void setData(List<RechnerRowData> data) {
		this.data = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if (data == null)
			return 0;
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return data.get(row).Talent;
		case 1:
			return data.get(row).Probe;
		case 2:
			return data.get(row).Wert;
		case 3:
			return data.get(row).Action;
		case 4:
			return data.get(row).Endwert;
		case 5:
			return data.get(row).Talent;
		}
		return null;
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public void setValueAt(int wert, int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 4:
			data.get(rowIndex).Endwert = wert;
			break;

		default:
			break;
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public RechnerRowData getRow(int row) {
		return data.get(row);
	}

	public void fireChange(int row, int col) {
		fireTableCellUpdated(row, col);
		fireTableDataChanged();
	}

}
