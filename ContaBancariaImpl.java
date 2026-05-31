import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ContaBancariaImpl extends UnicastRemoteObject implements ContaBancaria {

    private double saldo;

    public ContaBancariaImpl(double saldoInicial) throws RemoteException {
        super();
        this.saldo = saldoInicial;
    }

    @Override
    public synchronized void deposita(double valor) throws RemoteException, IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do deposito deve ser positivo.");
        }
        saldo += valor;
        System.out.printf("[SERVIDOR] Deposito de R$ %.2f realizado. Novo saldo: R$ %.2f%n", valor, saldo);
    }

    @Override
    public synchronized void retira(double valor)
            throws RemoteException, IllegalArgumentException, SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da retirada deve ser positivo.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException(saldo, valor);
        }
        saldo -= valor;
        System.out.printf("[SERVIDOR] Retirada de R$ %.2f realizada. Novo saldo: R$ %.2f%n", valor, saldo);
    }

    @Override
    public synchronized double saldo() throws RemoteException {
        System.out.printf("[SERVIDOR] Consulta de saldo: R$ %.2f%n", saldo);
        return saldo;
    }
}
