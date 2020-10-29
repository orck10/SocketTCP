package tcpsocket.app.serverSocket.utils;

import java.io.DataOutputStream;
import java.io.IOException;

import tcpsocket.app.log.Log;

public class WriteData {
	
	private final Log log = new Log();
	
	public void writeResponse(DataOutputStream dout, byte[] responce) {
		try {
			dout.write(responce);
		} catch (IOException e) {
			log.errorLog("Erro ao enviar mensagem", e);
		}
		
	}
}
