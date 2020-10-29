package tcpsocket.app.repositores.utils;

import java.sql.SQLException;

import org.h2.tools.Server;

import tcpsocket.app.log.Log;

public class H2SeverStarter {
	private Server s;
	private final static Log log = new Log();
	public void startDB() throws SQLException {
		log.infoLog("Iniciando Banco H2......");
		this.s = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
		this.s = Server.createWebServer();
		s.start();
		log.infoLog("Banco H2 iniciado!");
	}
	
}