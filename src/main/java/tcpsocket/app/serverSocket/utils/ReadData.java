package tcpsocket.app.serverSocket.utils;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import tcpsocket.app.log.Log;

public class ReadData {
	private final Log log = new Log();
	
	public byte[] readStream(DataInputStream data, int timeWait){
		
		try {
			boolean isEnd = false;
			List<Byte> inData = new  ArrayList<Byte>();
			long start = System.currentTimeMillis();
			while(! isEnd) {
				byte b = data.readByte();
				inData.add(b);
				if(13 == (int) b) {
					int currentLentgh = inData.size();
					int lentgh = inData.size() >= 2 ? (int) inData.get(1) : 0;
					isEnd = currentLentgh >= lentgh ? true : false;
				}
				else if(start+timeWait <  System.currentTimeMillis()) {
					return null;
				}
			}
			byte[] message = new byte[inData.size()];
			int cout = 0;
			for(Byte b : inData) {
				message[cout] = b;
				cout++;
			}
			return message;
		}
		catch (Exception e) {
			this.log.errorLog("Erro na leitura do Stream", e);
		}
		return null;
	}
	
	
}
