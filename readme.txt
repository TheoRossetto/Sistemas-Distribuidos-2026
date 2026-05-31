Aplicacao RMI - Conta Bancaria
Disciplina: Sistemas Distribuidos


DESCRICAO
---------
Aplicacao RMI que simula uma conta bancaria compartilhada.
Um servidor expoe um objeto remoto (ContaBancaria) com os metodos deposita, retira e saldo. Multiplos clientes podem se conectar concorrentemente e operar sobre a mesma conta.

ARQUIVOS
--------
ContaBancaria.java -> Interface remota (contrato RMI)
SaldoInsuficienteException.java -> Excecao customizada de negocio
ContaBancariaImpl.java -> Implementacao do objeto remoto
ContaBancariaServer.java -> Programa servidor (registra o objeto)
ContaBancariaClient.java -> Programa cliente (acessa o objeto)

PRE-REQUISITO
-------------
- JDK 8 ou superior instalado e configurado no PATH

COMPILACAO
----------
Abra um terminal na pasta onde estao os arquivos .java e execute:

javac ContaBancaria.java SaldoInsuficienteException.java ContaBancariaImpl.java ContaBancariaServer.java ContaBancariaClient.java

Todos os arquivos .class serao gerados na mesma pasta.

EXECUCAO
--------
1) Em um terminal, inicie o servidor: java ContaBancariaServer

O servidor cria automaticamente o RMI Registry na porta 1099, registra a conta com saldo inicial de R$ 1000,00 e fica aguardando.

2) Em um ou mais terminais separados, inicie cada cliente: java ContaBancariaClient

O cliente apresenta um menu interativo com as opcoes:
1 - Consultar saldo
2 - Depositar
3 - Retirar
0 - Sair

ARQUITETURA RMI
---------------
Interface remota (ContaBancaria)
- Estende java.rmi.Remote. Cada metodo declara throws RemoteException,
- requisito obrigatorio do RMI para lidar com falhas de comunicacao.

Excecao customizada (SaldoInsuficienteException)
- Excecao de negocio lancada quando a retirada excede o saldo.
- Serializable por ser propagada pela rede via RMI.

Implementacao (ContaBancariaImpl)
- Estende UnicastRemoteObject, que exporta o objeto automaticamente ao ser instanciado. Os metodos sao synchronized para garantir consistencia diante de acessos concorrentes de multiplos clientes.

Servidor (ContaBancariaServer)
- Cria o RMI Registry via LocateRegistry.createRegistry(1099) e  registra o objeto com Naming.rebind("//localhost:1099/ContaBancaria").

Cliente (ContaBancariaClient)
- Obtem a referencia remota via Naming.lookup("//localhost:1099/ContaBancaria") e invoca os metodos como se fossem chamadas locais. O stub gerado pelo RMI cuida do empacotamento dos parametros e da comunicacao com o servidor pela rede.

OBSERVACOES
-----------
- O servidor deve ser iniciado antes dos clientes.
- Para testar concorrencia, abra multiplos terminais de cliente.
- O saldo e compartilhado: operacoes de um cliente sao visiveis imediatamente para todos os outros.



