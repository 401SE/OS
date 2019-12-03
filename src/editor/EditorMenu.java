package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Chandler Atchley
 * This class is a custom variant of a JMenuBar that holds all of the subcomponents needed for the editor.
 */
public class EditorMenu extends JMenuBar {
    private JMenu fileMenu;
    private JMenuItem newItem, openItem, saveItem, exitItem, aboutItem;
    private JMenu helpMenu;

    /**
     * Builds the menu bar and all of its subcomponents.
     */
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

    /**
     * Links up the menu bar with an event listener class capable of effecting each menu item's intended actions.
     * @param ml the menu listener to hook up with each of the menu bar's components
     */
    public void hookListener(MenuListener ml) {
        newItem.addActionListener(ml);
        openItem.addActionListener(ml);
        saveItem.addActionListener(ml);
        exitItem.addActionListener(ml);
        aboutItem.addActionListener(ml);
    }

}
