//  This following code was modified & copied from:
//      https://www.geeksforgeeks.org/introducing-threads-socket-programming-java/

//  401 Software Engineering
//  11/17/2019
//  Sam Portillo

package server;

import os.FileSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * To receive the socket connection from the SocketServer class.
 * Continue the socket connection.
 * Receive commands from the client and relay those commands to an instance of
 * the FileSystem class.
 * Forward the response of the FileSystem class to the client via the socket
 * connection.
 * @author Sam Portillo
 */
class Terminal extends Thread
{
    final Socket s;
    BufferedReader br;
    PrintWriter pw;
    FileSystem fs;


    /**
     * This constructor creates an instance of a Terminal for a multi
     * threaded environment.
     * It receives these parameters from the SocketServer class.
     * @param s  is the object that references the socket server connection.
     * @param br is the object of the BufferedReader class.
     * @param pw is the object of the PrintWriter class.
     * @author Sam Portillo
     */
    public Terminal(Socket s, BufferedReader br, PrintWriter pw)
    {
        this.s = s;
        this.br = br;
        this.pw = pw;
        this.fs = new FileSystem();
    }

    /**
     * This is the method to be overridden from the Thread class.
     * @author Sam Portillo
     */
    @Override
    public void run()
    {
        String received;
        String sss = "Connection to OS▼" + fs.socketTerminal("Date");
        boolean loop = true;

        try
        {
            while ( loop )
            {
                pw.println( sss );

                // receive the answer from client
                received = br.readLine();
                System.out.println( "> " + received );

                sss = fs.socketTerminal(received);

                //  11/26/2019  → I want a robust exit.
                if(received.equals("exit"))
                    loop = false;
            }

            System.out.print("Socket connection closed: " + s );
            s.close();
            br.close();
            pw.close();
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.print("Client rudely disconnected without saying bye ");
        }
        System.out.println("!");
    }
}