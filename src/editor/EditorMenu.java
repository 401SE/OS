package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditorMenu extends JMenuBar {
    private JMenu fileMenu;
    private JMenuItem newItem, openItem, saveItem, exitItem, aboutItem;
    private JMenu helpMenu;

    public EditorMenu() {
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open...");
        saveItem = new JMenuItem("Save as...");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        add(fileMenu);
        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About...");
        helpMenu.add(aboutItem);
        add(helpMenu);
    }


    public void hookListener(MenuListener ml) {
        newItem.addActionListener(ml);
        openItem.addActionListener(ml);
        saveItem.addActionListener(ml);
        exitItem.addActionListener(ml);
        aboutItem.addActionListener(ml);
    }

}
