
import com.sun.org.apache.xpath.internal.SourceTree;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

class RegistrerKlient {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/AS_Register"; // localhost kan byttes ut
        Registrer register = (Registrer) Naming.lookup(url);

        /* Sender meldinger til objektet på tjenermaskinen */

        System.out.println("To quit type exit or q\n" +
                "Adding utstyr:     add nr betegnelse leverandør påLager nedreGrense\n" +
                "Endre utstyr:      endre nr mengde\n" +
                register.lagDatabeskrivelse());

        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            input = scanner.nextLine();
            if(input.equals("exit") || input.equals("q")) {break;}

            String[] a = input.split(" ");
            if(a[0].equals("add")){
                int nr = Integer.parseInt(a[1]);
                String betegnelse = a[2];
                String leverandør = a[3];
                int påLager = Integer.parseInt(a[4]);
                int nedreGrense = Integer.parseInt(a[5]);
                register.regNyttUtstyr(nr,betegnelse,leverandør, påLager, nedreGrense);
            }else if (a[0].equals("endre")){
                int nr = Integer.parseInt(a[1]);
                int mengde = Integer.parseInt(a[2]);
                register.endreLagerbeholdning(nr,mengde);
            } else {
                System.out.println("Invalid input. Try again.");
            }
            System.out.println(register.lagDatabeskrivelse());
        }
        System.out.println(register.lagBestillingsliste());
    }
}