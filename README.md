# TCP Socket

## Aplicação Teste

### Para executar, copie a pasta target no destino e rode o seguinte comando:


'''

	- java -jar tcpsocket-0.0.1-SNAPSHOT.jar {porta do servidor} {digito crc em inteiro} {tempo de execução da thread em milesegundos}
'''

#### A aplição pede para executar 3 parametros de entrada, na seguinte ordem;

##### - Porta da maquina destinada aplicação.

##### - Digito polinômio CRC de verificação.

##### - Tempo em mile segundo que a thread será executada sem ser interrompida.


### Para verificar o salvamento no banco de dados cesse no browser a no IP da maquina onde a aplicação esstá rodando na porta 8082.

#### Utilize para o login no gerenciador do banco de dados os egintes parametros:
'''

	driver class: org.h2.Driver
	
	JDBC URL: jdbc:h2:~/test
	
	user name: sa 
'''

##### OBS: O campo Password deve estar vazio.
