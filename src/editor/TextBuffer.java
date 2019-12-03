package editor;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class TextBuffer {
    private String buf;
    private File currentFile;
    private FileReader input;

    public TextBuffer() {
       newFile();
    }

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

    public void setCurrentFile(File f) throws IOException {
        input.close();
        currentFile = f;
        input =  new FileReader(f);
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void loadFile() throws FileNotFoundException {
        Scanner sc = new Scanner(currentFile);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
            sb.append('\n');
        }
        buf = sb.toString();
    }

    public String getBuffer() {
        return buf;
    }

    public void setBuffer(String nBuf) {
        buf = nBuf;
    }

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
