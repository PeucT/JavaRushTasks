package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by ArchMage on 07.04.17.
 */
public class TextEditMenuListener implements MenuListener {
    private View view;

    public TextEditMenuListener(View view){
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu editObject = (JMenu) e.getSource();
        Component[] menuList = editObject.getMenuComponents();
        boolean isEnable = view.isHtmlTabSelected();
        for (Component entry : menuList){
            entry.setEnabled(isEnable);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
