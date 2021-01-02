package Model;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{
    private final String name;

    public CustomTableModel(Object[][] lignes, Object[] colones,String name) {
        super(lignes,colones);
        this.name=name;
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if(name=="tableau" && getColumnClass(col)==Note.class){
            super.setValueAt(new Note((String) obj),row,col);
        }
        else{
            super.setValueAt(obj,row,col);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (name){
            case "tableau":
                switch (columnIndex){
                    case 0:
                        return Integer.class;
                    case 1: case 2: case 3:
                        return String.class;
                    case 4:
                        return Float.class;
                    default:
                        return Note.class;
                }
            default:
                switch (columnIndex){
                    case 0: case 1: case 2: case 3:
                        return String.class;
                    default:
                        return Float.class;
                }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return !(column==3 || column==4);
    }


}