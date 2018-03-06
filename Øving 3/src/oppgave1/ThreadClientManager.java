package oppgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClientManager extends Thread {

    private Socket socket;

    public ThreadClientManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);

            pr.println("Hei, du er nå tilkoblet serveren.");

            String linje = br.readLine();
            while (linje != null) {
                System.out.println("Klienten skrev: " + linje);

                int sum = -1;
                if (linje.contains("+")) {
                    sum = Integer.parseInt(linje.split("\\+")[0].trim()) + Integer.parseInt(linje.split("\\+")[1].trim());
                } else if (linje.contains("-")) {
                    sum = Integer.parseInt(linje.split("-")[0].trim()) - Integer.parseInt(linje.split("-")[1].trim());
                }
                pr.println("= " + sum);
                linje = br.readLine();
            }
            br.close();
            pr.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}