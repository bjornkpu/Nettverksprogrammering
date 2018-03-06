package oppgave1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        final int PORT = 9001;
        Socket s = null;
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Starter server...");
            while (true) {
                s = ss.accept();
                Thread tcm = new ThreadClientManager(s);
                tcm.start();
            }
        } catch(IOException ioe) {
        } finally {
            try { s.close(); }
            catch (IOException e) {e.printStackTrace();}
        }
    }
}