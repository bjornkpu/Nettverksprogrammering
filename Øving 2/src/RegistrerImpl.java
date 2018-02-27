import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/*
 *
 * Et register holder orden på en mengde Utstyrsobjekter. En klient kan legge inn nye
 * Utstyr-objekter i registeret, og også endre varebeholdningen for et
 * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
 */
class RegistrerImpl extends UnicastRemoteObject implements Registrer {
    public static final int ok = -1;
    public static final int ugyldigNr = -2;
    public static final int ikkeNokPåLager = -3;

    private ArrayList<Utstyr> registeret = new ArrayList<Utstyr>();

    public RegistrerImpl() throws RemoteException {}

    public synchronized  boolean regNyttUtstyr(int startNr, String startBetegnelse,
                                 String startLeverandør, int startPåLager, int startNedreGrense)
            throws RemoteException {
        if (finnUtstyrindeks(startNr) < 0) { // fins ikke fra får
            Utstyr nytt = new Utstyr(startNr, startBetegnelse, startLeverandør,
                    startPåLager, startNedreGrense);
            registeret.add(nytt);
            return true;
        } else return false;
    }

    public synchronized  int endreLagerbeholdning(int nr, int mengde) throws RemoteException {
        int indeks = finnUtstyrindeks(nr);
        if (indeks < 0) return ugyldigNr;
        else {
            if (!(registeret.get(indeks)).endreLagerbeholdning(mengde)) {
                return ikkeNokPåLager;
            } else return ok;
        }
    }

    private  int finnUtstyrindeks(int nr) {
        for (int i = 0; i < registeret.size(); i++) {
            int funnetNr = (registeret.get(i)).finnNr();
            if (funnetNr == nr) return i;
        }
        return -1;
    }

    public synchronized  String lagBestillingsliste() throws RemoteException {
        String resultat = "\n\nBestillingsliste:\n";
        for (int i = 0; i < registeret.size(); i++) {
            Utstyr u = registeret.get(i);
            resultat += u.finnNr() + ", " + u.finnBetegnelse() + ": " +
                    u.finnBestKvantum() + "\n";
        }
        return resultat;
    }

    public synchronized  String lagDatabeskrivelse() throws RemoteException {
        String resultat = "Alle data:\n";
        for (int i = 0; i < registeret.size(); i++) {
            resultat += (registeret.get(i)).toString() + "\n";
        }
        return resultat;
    }
}