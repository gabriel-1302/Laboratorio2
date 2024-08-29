import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author armando
 */
public class ServidorRuat
{
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); 
            RegistroDeuda ruat = new RegistroDeuda();
            Naming.rebind("//localhost/RuatService", ruat);
            System.out.println("Servidor RUAT está listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}