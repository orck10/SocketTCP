package tcpsocket.app;

import tcpsocket.app.log.Log;
import tcpsocket.app.repositores.utils.H2SeverStarter;
import tcpsocket.app.serverSocket.SeverSocket;

public class App {
	
	private static final Log log = new Log();
	
	public static void main( String[] args )
    {
		if(args.length >= 3) {
			try {
				new H2SeverStarter().startDB();
			} catch (Exception e) {
				log.errorLog("Erro ao subir banco de dados", e);
			}
			new SeverSocket().newSocket(args);
		}
		else {
			Exception e = new Exception("Os Argumento devem ser - {porta} {Digito Crc} {Tempo de Espera}");
			for(String s : args) {
				log.infoLog(s);
			}
			log.errorLog("Argumentos invalidos", e);
			System.exit(-1);
		}
    }
}

