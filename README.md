# TCP Socket
##Aplica��o Teste

###Para executar, copie a pasta target no destino e rode o seguinte comando:


'''
	- java -jar tcpsocket-0.0.1-SNAPSHOT.jar {porta do servidor} {digito crc em inteiro} {tempo de execu��o da thread em milesegundos}
'''

#### A apli��o pede para executar 3 parametros de entrada, na seguinte ordem;

#####- Porta da maquina destinada aplica��o.
#####- Digito de decodifica��o do crc em valor interiro .
#####- Tempo em mile segundo que a thread ser� executada sem ser interrompida.


### Para verificar o salvamento no banco de dados cesse no browser a no IP da maquina onde a aplica��o esst� rodando na porta 8082.

####Utilize para o login no gerenciador do banco de dados os egintes parametros:
'''
	driver class: org.h2.Driver
	JDBC URL: jdbc:h2:~/test
	user name: sa 
'''

#####OBS: O campo Password deve estar vazio.