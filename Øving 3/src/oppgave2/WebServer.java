package oppgave2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WebServer {
    public static void main(String[] args) {
        final int PORT = 80;

        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Starter server på: http://127.0.0.1:80 http://localhost:80");
            Socket s = ss.accept();

            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            ArrayList<String> header = new ArrayList<String>();
            String linje = br.readLine();
            while (linje != null) {
                System.out.println("Klient skrev: " + linje);

                if (linje.equals("")) {
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html");
                    pw.println();
                    pw.println("<html><body>");
                    pw.println("<h1>Velkommen</h1>");
                    pw.println("Header fra klienten: ");
                    pw.println("<ul>");

                    for (int i = 0; i < header.size(); i++) {
                        pw.println("<li>" + header.get(i) + "</li>");
                    }
                    pw.println("</ul>");
                    pw.println("</body></html>");

                    br.close();
                    pw.close();
                    s.close();
                    return;
                } else { header.add(linje); }
                linje = br.readLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}