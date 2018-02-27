import java.rmi.*;

public interface Registrer extends Remote {

     boolean regNyttUtstyr(int startNr, String startBetegnelse,
                                 String startLeverandør, int startPåLager, int startNedreGrense)
             throws RemoteException;

     int endreLagerbeholdning(int nr, int mengde) throws RemoteException;

     String lagBestillingsliste() throws RemoteException;

     String lagDatabeskrivelse() throws RemoteException;
}
