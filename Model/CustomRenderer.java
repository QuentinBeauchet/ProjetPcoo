package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class CustomRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
    private final static int NBR_COMPOSANTS_ETUDIANTS=5;

    /**
     * Classe qui permet de faire un TableCellRenderer personalisé.
     */

    public CustomRenderer(){
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

        setStyle(table,row,value);
        update(table,row);

        return this;
    }

    /**
     * Parametre le style pour chaque classe dans le tableau.
     *
     * @param table JTable
     * @param row int
     * @param value Object
     */

    private void setStyle(JTable table, int row, Object value){
        Class<?> currentClass=value.getClass();
        if(currentClass==String.class || currentClass==Integer.class){
            super.setBackground((row % 2 == 0 ? table.getTableHeader().getBackground() : Color.white));
            if(currentClass==Integer.class){
                super.setFont(new Font("Arial", Font.BOLD, 12));
            }
        }
        else if(currentClass==Float.class){
            super.setBackground(((float)value < 10 ? new Color(177, 96, 96,80) : new Color(96, 206, 96,80)));
        }
        else if(currentClass==Note.class){
            super.setBackground((row % 2 == 0 ? new Color(226,239,218) : Color.white));
            if(value.toString().equals("ABI")){
                super.setFont(new Font("Tahoma", Font.ITALIC + Font.BOLD, 12));
            }
        }
        super.setHorizontalAlignment(CENTER);
        super.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.darkGray));
    }

    /**
     * Actualise l'affiche des cours vide dynamiquement.
     *
     * @param table Jtable
     * @param row int
     */


    private void update(JTable table,int row){
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

