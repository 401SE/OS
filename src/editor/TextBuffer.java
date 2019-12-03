package editor;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author Chandler Atchley
 * This class is responsible for the underlying text that this editor can manipulate, as well as the saving to and loading from
 * text files on the hard drive.
 */
public class TextBuffer {
    private String buf;
    private File currentFile;
    private FileReader input;

    public TextBuffer() {
       newFile();
    }

    /**
     * Flushes the current text buffer and associates a new, temporary file for it
     */
    public void newFile() {
        buf = new String();
        try {
            currentFile = File.createTempFile("temp", ".txt");
            input = new FileReader(currentFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Text buffer initialization failed.");
            System.exit(1);
        }

    }

    /**
     * Associates the text buffer with a designated file
     * @param f the file to set the text buffer to
     * @throws IOException
     */
    public void setCurrentFile(File f) throws IOException {
        input.close();
        currentFile = f;
        input =  new FileReader(f);
    }

    /**
     * Returns the currently associated file.
     * @return the text buffer's file
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Takes the currently associated file and loads it into the buffer.
     * @throws FileNotFoundException
     */
    public void loadFile() throws FileNotFoundException {
        Scanner sc = new Scanner(currentFile);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
            sb.append('\r');
            sb.append('\n');
        }
        buf = sb.toString();
    }

    /**
     * Returns the text of the buffer.
     * @return the buffer's text as a string
     */
    public String getBuffer() {
        return buf;
    }

    /**
     * Sets the text of the buffer to a new string.
     * @param nBuf the new text of the buffer
     */
    public void setBuffer(String nBuf) {
        buf = nBuf;
    }

    /**
     * Saves the text of the buffer with its currently associated file.
     */
    public void saveToFile() {
        PrintWriter output;
        try {
            output = new PrintWriter(currentFile);
            output.write(buf);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
