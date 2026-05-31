import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ContaBancariaServer {

    public static void main(String[] args) {
        try {
            // Inicia o rmiregistry na porta padrao 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("[SERVIDOR] RMI Registry iniciado na porta 1099.");

            // Cria o objeto remoto com saldo inicial de R$ 1000,00
            ContaBancariaImpl conta = new ContaBancariaImpl(1000.0);

            // Registra o objeto no RMI Registry com o nome "ContaBancaria"
            Naming.rebind("//localhost:1099/ContaBancaria", conta);

            System.out.println("[SERVIDOR] Objeto 'ContaBancaria' registrado com sucesso.");
            System.out.println("[SERVIDOR] Aguardando conexoes de clientes...");

        } catch (Exception e) {
            System.err.println("[SERVIDOR] Erro ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
