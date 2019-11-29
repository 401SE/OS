package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The Client class is run from a remote (local) client pc that will
 * connect via a socket to the SocketServer class.
 */
public class Client
{
    static void printMessage(String message)
    {
        String a[] = message.split("▼");
        for (int i = 0; i < a.length - 1; i++)
            System.out.println(a[i]);

        System.out.print(a[a.length - 1]);      // "> " & leave cursor on same line.
    }


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("10.0.0.227");
        //Socket s = new Socket(ip, 8070);
        Socket s = new Socket(ip, 7000);
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        StringBuilder sb = new StringBuilder();

        boolean loop = true;

        while (loop)
        {
            printMessage(br.readLine());
            String command = scan.nextLine();
            pw.println( command );

            if (command.equals("exit"))
                loop = false;
        }

        System.out.print("Socket connection closed: " + s);
        s.close();
        scan.close();
        in.close();
        br.close();
        pw.close();
        System.out.println("!");
    }
}