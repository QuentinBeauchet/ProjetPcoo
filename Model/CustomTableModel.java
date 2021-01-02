package Model;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel{
    private final Object[][] rowData;
    private final Object[] columnNames;
    private final Class<?>[] columnClass;

    /**
     * Classe qui permet de faire un AbstractTableModel personalisé.
     * ->Permet de forcer la classe des cellules.
     *
     * @param row Object[][]
     * @param col Object[]
     */

    public CustomTableModel(Object[][] row, Object[] col) {
        rowData = row;
        columnNames = col;
        columnClass=new Class[columnNames.length];
        if(rowData.length>0){
            for (int i = 0; i < columnNames.length; i++) {
                columnClass[i]=rowData[0][i].getClass();
            }
        }
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public String getColumnName(int column) {
        return (String) columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return !(col == 3 || col == 4);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (rowData.length > row && columnNames.length > column) {
            return rowData[row][column];
        } else {
            return null;
        }


        //setValueAt();
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        String txt=(String) obj;
        if(columnClass[col]==Note.class){
            rowData[row][col]=new Note(txt);
        }
        else if(columnClass[col]==Integer.class){
            rowData[row][col]=Integer.valueOf(txt);
        }
        else if(columnClass[col]==Float.class){
            rowData[row][col]=Float.valueOf(txt);
        }
        else{
            rowData[row][col]=txt;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }

}
