package oppgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        final int PORT = 9001;
        Scanner scanner = new Scanner(System.in);
        String ip = "127.0.0.1";
        System.out.println("Kobler til: " + ip);

        try {
            Socket s = new Socket(ip, PORT);

            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            String firstLine = br.readLine();
            System.out.println(firstLine);

            String linje = scanner.nextLine();
            while (!linje.equals("")) {
                pw.println(linje);
                String response = br.readLine();
                System.out.println("Fra server: " + response);
                linje = scanner.nextLine();
            }
            br.close();
            pw.close();
            s.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}