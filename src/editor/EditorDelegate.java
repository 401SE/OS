package editor;

/**
 * @author Chandler Atchley
 * @version 1.0
 * @since 1.0
 */

import javax.swing.*;
import java.awt.*;

public class EditorDelegate extends JFrame {
    private final int WINDOW_WIDTH = 480;
    private final int WINDOW_HEIGHT = 680;
    private final String STARTUP_TITLE = "Teddy";
    private EditorMenu em;
    private TextArea ta;
    private TextBuffer tb;

    /**
     * Establishes the main application for the text editor.
     */
    public EditorDelegate() {
        setTitle(STARTUP_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        em = new EditorMenu();
        tb = new TextBuffer();
        ta = new TextArea(tb);
        em.hookListener(new MenuListener(ta));


        getContentPane().add(ta, BorderLayout.CENTER);

        setJMenuBar(em);
        setVisible(true);
    }

    /**
     * Initializes the EditorDelegate when run as a program.
     *
     */
    public static void main(String[] args) {
        EditorDelegate ed = new EditorDelegate();
    }

}
