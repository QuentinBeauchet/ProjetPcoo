package Model;

import javax.swing.*;

public class CustomListModel extends AbstractListModel<Object> {
    private Object[] list;

    public CustomListModel(Object[] list) {
        this.list=list;
    }

    @Override
    public int getSize() {
        return list.length;
    }

    @Override
    public Object getElementAt(int index) {
        return list[index];
    }
}
