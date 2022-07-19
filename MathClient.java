import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class MathClient {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        MathService service = null;

        try {
            service = (MathService) Naming.lookup("//localhost/CalculatorService");

            System.out.println("Number of clients Connected: " + service.clientCount());
            System.out.println("Add: " + service.add(2, 2));
            System.out.println("Subtract: " + service.subtract(5, 2));
            System.out.println("Multiply: " + service.multiply(2, 8));
            System.out.println("Divide: " + service.divide(20, 2));
        } catch (NotBoundException exception) {
            System.err.println(exception.getMessage());
        } catch (MalformedURLException exception) {
            System.err.println(exception.getMessage());
        } catch (RemoteException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
