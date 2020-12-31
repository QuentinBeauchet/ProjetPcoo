package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class CustomStringRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
    private final static int NBR_COMPOSANTS_ETUDIANTS=5;

    /**
     * Classe qui permet de faire un TableCellRenderer personalisé.
     */

    public CustomStringRenderer(){
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        setColor(table,row,column,value);
        setString((String)value,column);
        update(table,row);

        return this;
    }

    /**
     * Couleur des lignes personalisés.
     *
     * @param table JTable
     * @param row int
     * @param column int
     * @param value Object
     */

    private void setColor(JTable table, int row, int column, Object value){
        Color impair;
        if(column<NBR_COMPOSANTS_ETUDIANTS-1){
            impair = table.getTableHeader().getBackground();
        }
        else{
            impair=new Color(226,239,218);
        }
        if(column==NBR_COMPOSANTS_ETUDIANTS-1){
            super.setBackground((Float.parseFloat((String)value) <10 ? new Color(177, 96, 96,80) : new Color(96, 206, 96,80)));
        }
        else{
            super.setBackground((row % 2 == 0 ? impair : Color.white));
        }
    }

    /**
     * Personalisation des string.
     *
     * @param value String
     * @param column int
     */

    private void setString(String value,int column){
        if(column==0){
            super.setFont(new Font("Arial", Font.BOLD, 12));
        }
        if(value.equals("ABI")){
            super.setFont(new Font("Tahoma", Font.ITALIC + Font.BOLD, 12));
            super.setHorizontalAlignment(CENTER);
        }
    }

    /**
     * Enleve dynamiquement les cours inutiles quand il n'y a qu'un seul eleve.
     *
     * @param table JTable
     * @param row int
     */

    private void update(JTable table,int row){
        super.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.darkGray));
        if(row==table.getRowCount()-1){
            super.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.darkGray));
            TableColumnModel columnModel = table.getColumnModel();
            if(table.getRowCount()==1){
                int lastRow=table.convertRowIndexToModel(row);
                for (int i = NBR_COMPOSANTS_ETUDIANTS+1; i < table.getModel().getColumnCount(); i++) {
                    if(table.getModel().getValueAt(lastRow,i).equals("")){
                        columnModel.getColumn(i).setMinWidth(0);
                        columnModel.getColumn(i).setMaxWidth(0);
                        columnModel.getColumn(i).setWidth(0);
                    }
                }
            }
            else{
                int preferredWidth=columnModel.getColumn(0).getPreferredWidth();
                for (int i = NBR_COMPOSANTS_ETUDIANTS+1; i < table.getModel().getColumnCount(); i++) {
                    columnModel.getColumn(i).setMinWidth(preferredWidth);
                    columnModel.getColumn(i).setMaxWidth(preferredWidth);
                    columnModel.getColumn(i).setWidth(preferredWidth);
                }
            }
        }
    }
}

