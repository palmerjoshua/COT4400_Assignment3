package HW3;
import javax.swing.table.AbstractTableModel;

public class SorterTableModel extends AbstractTableModel {
    public int getRowCount() {
        return data.length;
    }
    public int getColumnCount() {
        return data[0].length;
    }

    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    public void addData(Object[][] rowData) {
        data = rowData.clone();
    }

    private final String[] columnNames = {"Array Size", "Theoretical RT (microseconds)", "Simulated RT (microseconds)", "Hidden Cost"};
    private Object[][] data = new Object[20][4];
}
