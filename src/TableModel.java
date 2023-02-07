package src;

import javax.swing.table.AbstractTableModel;

class TableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public TableModel(Object[][] data, String[] columnNames){
        this.data = data;
        this.columnNames = columnNames;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    public boolean isCellEditable(int row, int col) {
        if (row < 1) {
            return false;
        } else {
            return true;
        }
    }


    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
