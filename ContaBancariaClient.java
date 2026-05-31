import java.rmi.Naming;
import java.util.Scanner;

public class ContaBancariaClient {

    public static void main(String[] args) {
        try {
            // Realiza o lookup do objeto remoto no RMI Registry
            ContaBancaria conta = (ContaBancaria) Naming.lookup("//localhost:1099/ContaBancaria");
            System.out.println("[CLIENTE] Conectado ao objeto remoto 'ContaBancaria'.");

            Scanner scanner = new Scanner(System.in);
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n=== MENU ===");
                System.out.println("1 - Consultar saldo");
                System.out.println("2 - Depositar");
                System.out.println("3 - Retirar");
                System.out.println("0 - Sair");
                System.out.print("Opcao: ");

                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        double saldo = conta.saldo();
                        System.out.printf("[CLIENTE] Saldo atual: R$ %.2f%n", saldo);
                        break;

                    case 2:
                        System.out.print("Valor a depositar: R$ ");
                        double valorDeposito = scanner.nextDouble();
                        try {
                            conta.deposita(valorDeposito);
                            System.out.printf("[CLIENTE] Deposito de R$ %.2f realizado com sucesso.%n", valorDeposito);
                        } catch (IllegalArgumentException e) {
                            System.out.println("[CLIENTE] Erro: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Valor a retirar: R$ ");
                        double valorRetirada = scanner.nextDouble();
                        try {
                            conta.retira(valorRetirada);
                            System.out.printf("[CLIENTE] Retirada de R$ %.2f realizada com sucesso.%n", valorRetirada);
                        } catch (IllegalArgumentException e) {
                            System.out.println("[CLIENTE] Erro: " + e.getMessage());
                        } catch (SaldoInsuficienteException e) {
                            System.out.println("[CLIENTE] Erro: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("[CLIENTE] Encerrando conexao.");
                        break;

                    default:
                        System.out.println("[CLIENTE] Opcao invalida.");
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.err.println("[CLIENTE] Erro ao conectar ao servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
