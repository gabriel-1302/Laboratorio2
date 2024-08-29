import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;





public class RegistroDeuda extends UnicastRemoteObject implements IRegistroDeuda {
    private List<Deuda> deudas;


    protected RegistroDeuda() throws RemoteException {
        super();
        deudas = new ArrayList<>();

        // Simulación de deudas
        deudas.add(new Deuda("1234567", 2022, Impuesto.VEHICULO, 2451));
        deudas.add(new Deuda("1234567", 2022, Impuesto.INMUEBLE, 2500));
        deudas.add(new Deuda("555587", 2021, Impuesto.VEHICULO, 5000));
        deudas.add(new Deuda("333357", 2023, Impuesto.INMUEBLE, 24547));

        try {

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
          //Alcaldia = (Alcaldia) registry.lookup("Alcaldia");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public  ArrayList<Deuda> buscar(String CI) throws RemoteException {
        List<Deuda> result = new ArrayList<>();
        for (Deuda deuda : deudas) {
            if (String.valueOf(deuda.getCI()).equals(CI)) {
                result.add(deuda);
            }
        }
        return (ArrayList<Deuda>) result;
    }


    @Override
    public boolean pagar(Deuda deuda) throws RemoteException {

        boolean observacion = Alcaldia.hasObservations(deuda.getCI());
        if (observacion) {
            return false;
        }
        return deudas.remove(deuda);
    }



    public static void main(String[] args) {
        try {

            RegistroDeuda registroDeuda = new RegistroDeuda();


            java.rmi.registry.LocateRegistry.createRegistry(1099).rebind("RegistroDeuda", registroDeuda);

            System.out.println("Servidor de RegistroDeuda listo.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}