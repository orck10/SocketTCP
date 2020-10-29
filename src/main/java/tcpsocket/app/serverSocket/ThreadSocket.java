package tcpsocket.app.serverSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import tcpsocket.app.dto.RequestDTO;
import tcpsocket.app.dto.ResponceDTO;
import tcpsocket.app.log.Log;
import tcpsocket.app.serverSocket.utils.ReadData;
import tcpsocket.app.serverSocket.utils.SaveData;
import tcpsocket.app.serverSocket.utils.WriteData;

public class ThreadSocket extends Thread{
	private Socket socket;
	private int crcDigit;
	private int timeWait;
	
	private final Log log = new Log();
	
	
	
	
	public ThreadSocket(Socket socket, int crcDigit, int timeWait) {
		super();
		this.socket = socket;
		this.crcDigit = crcDigit;
		this.timeWait = timeWait;
	}

	@Override
	public void run() {
		this.log.infoLog("Inicia Thread");
		try {
			
			DataOutputStream dout = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream din = new DataInputStream(socket.getInputStream());
			WriteData writer = new WriteData();
			byte[] message = new ReadData().readStream(din, this.timeWait);
			this.log.infoLog("Request :"+new String(message));
			
			RequestDTO request =  new RequestDTO(message, this.crcDigit);
			
			byte[] responce = null;
			try {
				new SaveData().saveData(request, socket.getInetAddress().getHostAddress());
				responce = new ResponceDTO(request.getFrame(), request.getIsConsistent(), request.getData()).responseGenerate(this.crcDigit);
				
			}
			catch (Exception e) {
				log.errorLog("Erro ao Salvar mensagem", e);
				responce = new ResponceDTO(-1, request.getIsConsistent(), request.getData()).responseGenerate(this.crcDigit);
			}
			
			this.log.infoLog("Responce :"+new String(responce));
			dout.flush();
			writer.writeResponse(dout, responce);
			
			
			dout.close();
			din.close();
		}
		catch (Exception e) {
			log.errorLog("Erro ao ler dados no socket", e);
		}
		finally {
			try {
				this.socket.close();
			} catch (IOException e) {
				log.errorLog("Erro ao encerrar Socket", e);
			}
		}
		this.log.infoLog("Finaliza Thread");
	}
}
