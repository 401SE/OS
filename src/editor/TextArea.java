package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chandler Atchley
 * This is a custom variant of the JTextArea with methods to manage the text buffer that the user will manipulate.
 */
public class TextArea extends JTextArea {
    private TextBuffer tBuf;

    /**
     * Creates a JTextArea element and hooks it up with the underlying text buffer.
     * @param tb The text buffer this text area will represent
     */
    public TextArea(TextBuffer tb) {
        tBuf = tb;
    }

    /**
     * Grants access to the underlying text buffer.
     * @return the text buffer associated with this area
     */
    public TextBuffer getTextBuffer() {
        return tBuf;
    }

    /**
     * @author Chandler Atchley
     * A private listener class that responds to changes in the text area to update the text buffer.
     */
    private class TextAreaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tBuf.setBuffer(getText());
        }
    }
}
