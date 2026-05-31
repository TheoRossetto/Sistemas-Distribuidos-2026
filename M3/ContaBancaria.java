import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContaBancaria extends Remote {

    void deposita(double valor) throws RemoteException, IllegalArgumentException;

    void retira(double valor) throws RemoteException, IllegalArgumentException, SaldoInsuficienteException;

    double saldo() throws RemoteException;
}
