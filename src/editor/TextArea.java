package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextArea extends JTextArea {
    private TextBuffer tBuf;
    public TextArea(TextBuffer tb) {
        tBuf = tb;
    }

    public TextBuffer getTextBuffer() {
        return tBuf;
    }

    private class TextAreaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tBuf.setBuffer(getText());
        }
    }
}
