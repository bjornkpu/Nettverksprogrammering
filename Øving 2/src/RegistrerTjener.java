import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class RegistrerTjener {
    public static void main(String[] args) throws Exception {
        String objektnavn = "AS_Register";
        System.out.println("Skal lage et tjenerobjekt");
        Registrer register = new RegistrerImpl();


        register.regNyttUtstyr(1,"Kniv","BestikkAS", 80, 10);
        register.regNyttUtstyr(2,"Skje","BestikkAS", 60, 10);
        register.regNyttUtstyr(3,"Gaffel","BestikkAS", 40, 10);
        register.regNyttUtstyr(4,"Porselensfat","ServiseAS", 20, 10);
        register.regNyttUtstyr(5,"Glass","ServiseAS", 20, 10);


        System.out.println("Nå er det laget!");


        LocateRegistry.createRegistry(1099);
        Registry registry = LocateRegistry.getRegistry(1099);
        registry.rebind(objektnavn, register);


        System.out.println("Nå venter vi bare på at noen skal bruke oss...");
        javax.swing.JOptionPane.showMessageDialog(null,
                "Trykk Ok for å stoppe tjeneren.");
        Naming.unbind(objektnavn);
        System.exit(0);
    }
}

