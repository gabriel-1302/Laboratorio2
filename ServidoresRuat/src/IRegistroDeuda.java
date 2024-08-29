
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRegistroDeuda extends Remote{

        ArrayList<Deuda> buscar(String CI) throws RemoteException;
        boolean pagar(Deuda deuda) throws RemoteException;
}