package tcpsocket.app.serverSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tcpsocket.app.log.Log;

public class SeverSocket {
	
	private final Log log = new Log();
	
	public void newSocket(String[] args) {
		Integer port = Integer.parseInt(args[0]);
		Integer crcDigit = Integer.parseInt(args[1]);
		Integer timeWait = Integer.parseInt(args[2]);
		
		
		ServerSocket s = this.start(port);
		this.log.infoLog("Socket Criado");
		Boolean run = true;
		while(run) {
			try {
				Socket socket = s.accept();
				this.log.infoLog("Conexão Esrtabelecida");
				ThreadSocket thread= new ThreadSocket(socket, crcDigit, timeWait);
				thread.join(timeWait);
				thread.start();
			} catch (Exception e) {
				new Log().errorLog("Erro na execuçao do socket", e);
			}
		}
		closeSocket(s);
	}
	
	private ServerSocket start(int port) {
		ServerSocket s = null;
		try {
			s = new ServerSocket(port);
		}
		catch (Exception e) {
			this.log.errorLog("Erro ao iniciar Socket", e);
			System.exit(-1);
		}
		return s;
	}
	
	private void closeSocket(ServerSocket s) {
		try {
			s.close();
		} catch (IOException e) {
			this.log.errorLog("Erro ao encerrar Socket", e);
		}
	}
	
	 
}
