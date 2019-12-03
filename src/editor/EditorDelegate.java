package editor;

import javax.swing.*;
import java.awt.*;

public class EditorDelegate extends JFrame {
    private final int WINDOW_WIDTH = 480;
    private final int WINDOW_HEIGHT = 680;
    private final String STARTUP_TITLE = "Teddy";
    private EditorMenu em;
    private TextArea ta;
    private TextBuffer tb;

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

    public static void main(String[] args) {
        EditorDelegate ed = new EditorDelegate();
    }

}
