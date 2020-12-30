package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomRenderer extends JLabel implements TableCellRenderer {

    /**
     * Classe qui permet de faire un TableCellRenderer personalisé.
     */

    public CustomRenderer(){
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        float note=(float)value;
        super.setText(String.valueOf(note));
        super.setHorizontalAlignment(CENTER);
        super.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLACK));
        super.setFont(new Font("Tahoma", Font.PLAIN, 12));
        if(note<10.0){
            super.setBackground(new Color(177, 96, 96));
        }
        else{
            super.setBackground(new Color(96, 206, 96));
        }
        return this;
    }

}
