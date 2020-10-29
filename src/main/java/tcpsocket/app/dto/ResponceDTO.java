package tcpsocket.app.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import tcpsocket.app.serverSocket.utils.CRC8Check;
import tcpsocket.app.serverSocket.utils.GetDate;

public class ResponceDTO {
	private String message;
	private int frame;
	private byte[] responce;
	
	public ResponceDTO(int tipo, Boolean isConssitent, byte[] data) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		if(isConssitent == true) {
			if(tipo == -95 || tipo == -94) {
				this.message = "";
				this.frame = 160;
			}
			else if(tipo == -93) {
				this.responce = new GetDate().getCurrentDate(data);
				this.message = new String(this.responce); 
				this.frame = 160;
			}
			else {
				this.message = "Operação invalida - " + dtf.format(now);
				this.frame = 160;
			}
		}else {
			this.message = "Mensagem recebida é inconsistente tente novamente - " + dtf.format(now);
			this.frame = 160;
		}
	}
	
	public byte[] responseGenerate(int crcType){
		byte[] resp = new byte[this.message.length() + 5];
		resp[0] = (byte) 10;
		int length = this.message.length() + 5;
		resp[1] = (byte) length;
		resp[2] = (byte) this.frame;
		int count = 3;
		for(char c : this.message.toCharArray()) {
			resp[count] = (byte) c;
			count++;
		}
		byte[] obj = Arrays.copyOfRange(resp, 1, length-2);
		int crc = new CRC8Check(crcType).getCRC(obj);
		resp[length - 2] = (byte) crc;
		resp[length - 1] = (byte) 13;
		
		return resp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getFrame() {
		return this.frame;
	}
	
	public byte[] getResponce() {
		return this.responce;
	}
}
