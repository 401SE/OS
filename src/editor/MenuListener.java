package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuListener implements ActionListener {
    private TextArea tArea;
    public MenuListener(TextArea ta) {
        tArea = ta;

    }
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        switch (e.getActionCommand()) {
            case "New":
                tArea.getTextBuffer().newFile();
                break;
            case "Open...":
                fileChooser.showOpenDialog(null);
                try {
                    TextBuffer tBuf = tArea.getTextBuffer();
                    tBuf.setCurrentFile(fileChooser.getSelectedFile());
                    tBuf.loadFile();
                    tArea.setText(tBuf.getBuffer());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to open file.");
                    System.exit(1);
                }
                break;
            case "Save as...":
                tArea.getTextBuffer().setBuffer(tArea.getText());
                fileChooser.showOpenDialog(null);
                try {
                    TextBuffer tBuf = tArea.getTextBuffer();
                    tBuf.setCurrentFile(fileChooser.getSelectedFile());
                    tBuf.saveToFile();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to save file.");
                    System.exit(1);
                }
                break;
            case "Exit":
                System.exit(0);
                break;
            case "About...":
                JOptionPane.showMessageDialog(null, "Teddy © Chandler Atchley 2019");
            default:
                break;

        }
    }
}