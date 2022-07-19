import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MathServer extends UnicastRemoteObject implements MathService {

    private int clientCount = 0;

    public MathServer() throws RemoteException {
        super();
    }

    public synchronized int clientCount() throws  RemoteException{
        clientCount++;
        return clientCount;
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + "and" + b + " in the server");
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Subtracting " + a + "and" + b + " in the server");
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        System.out.println("Multiplying " + a + "and" + b + " in the server");
        return a * b;
    }

    @Override
    public int divide(int a, int b) throws RemoteException {
        System.out.println("Dividing " + a + "and" + b + " in the server");
        return a / b;
    }



    public int test(int a) {
        System.out.println("This is a test");
        return 0;
    }

    public static void main(String args[]) {
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
        try {
            MathServer server = new MathServer();
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("CalculatorService", server);

            System.out.println("Service Started");
        } catch (RemoteException remoteException) {
            System.err.println(remoteException.getMessage());
        } catch (AlreadyBoundException alreadyBoundException) {
            System.err.println(alreadyBoundException.getMessage());
        }
    }
}