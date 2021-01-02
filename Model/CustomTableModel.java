package Model;

import View.Home;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{
    private final String name;
    private final Home home;

    public CustomTableModel(Home home, Object[][] lignes, Object[] colones, String name) {
        super(lignes,colones);
        this.name=name;
        this.home=home;
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        updateXML(obj,row,col);

        if(name=="tableau" && getColumnClass(col)==Note.class){
            super.setValueAt(new Note((String) obj),row,col);
        }
        else{
            if(getColumnClass(col)==Integer.class){
                super.setValueAt(Integer.parseInt((String)obj),row,col);
                //TODO probleme affichage integer
            }
            if(getColumnClass(col)==Float.class){
                super.setValueAt(Float.parseFloat((String) obj),row,col);
            }
            else{
                super.setValueAt(obj,row,col);
            }
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

    private void updateXML(Object obj,int row,int col){
        XMLReader xmlReader=home.getXml();
        for (int i = 0; i < xmlReader.getStudentList().size(); i++) {
            Etudiant etudiant=xmlReader.getStudentList().get(i);
            String rowID= String.valueOf((Integer)getValueAt(row,0));
            if(rowID.equals(etudiant.getId())){
                switch (col){
                    case 0:
                        etudiant.setId((String)obj);
                        xmlReader.getStudentList().set(i,etudiant);
                        break;
                    case 1:
                        etudiant.setNom((String)obj);
                        break;
                    case 2:
                        etudiant.setPrenom((String)obj);
                    default:
                        //SET NOTE
                }
                break;
            }
        }
    }

}