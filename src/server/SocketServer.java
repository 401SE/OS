//  This following code references:
//      https://www.geeksforgeeks.org/introducing-threads-socket-programming-java/

//  401 Software Engineering
//  11/17/2019
//  Sam Portillo


package server;

// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

/**
 * Serve multi users via its multi threaded socket server over the LAN or WAN.
 * Waits for a request for a new socket connection, to create and start a terminal
 * instance on a separate thread.
 * @author Sam Portillo
 */
public class SocketServer
{

    /**
     * This is the starting point of execution when the OS
     * is in a multi user mode.
     * Waits for a request for a new socket connection,
     * to create and start a terminal instance on a separate thread.
     * @param args not used.
     * @throws IOException is required in case the socket throws an exception.
     * @author Sam Portillo
     */
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(7000);
        System.out.println("Waiting for a socket connection on port 7000");
        while (true)
        {
            Socket s = null;

            try
            {
                s = ss.accept();
                System.out.println("Start Client connection : " + s);
                InputStreamReader in = new InputStreamReader( s.getInputStream());
                BufferedReader br = new BufferedReader(in);
                PrintWriter pw = new PrintWriter( s.getOutputStream(), true );
                System.out.println("Start Terminal on new thread.");
                Thread t = new Terminal(s, br, pw);
                t.start();
            }
            catch (Exception e)
            {
                s.close();
                e.printStackTrace();
            }
        }
    }
}