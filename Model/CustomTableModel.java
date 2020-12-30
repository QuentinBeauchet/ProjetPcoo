package Model;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
    private final Object[][] rowData;
    private final Object[] columnNames;

    /**
     * Classe qui permet de faire un AbstractTableModel personalisé.
     *
     * @param row Object[][]
     * @param col Object[]
     */

    public CustomTableModel(Object[][] row,Object[] col){
        rowData=row;
        columnNames=col;
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public String getColumnName(int column) {
        return (String)columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object value=this.getValueAt(0,columnIndex);
        return (value==null?Object.class:value.getClass());
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col!=3;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(rowData.length>row && columnNames.length>column){
            return rowData[row][column];
        }
        else{
            return null;
        }
    }
}
