package tcpsocket.app.serverSocket.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import tcpsocket.app.log.Log;

public class GetDate {
	private final static Log log = new Log();
	
	
	public byte[] getCurrentDate(byte[] timeZone) {
		this.log.infoLog("Recuperando hora corrente");
		try {
			Calendar currentDate = Calendar.getInstance(TimeZone.getTimeZone(new String(timeZone)));
			
			List<Byte> date = new ArrayList<Byte>();
			date.add((byte) currentDate.get(Calendar.DAY_OF_MONTH));
			int month = currentDate.get(Calendar.MONTH) + 1;
			date.add((byte) month);
			Integer y = currentDate.get(Calendar.YEAR);
			String fullYear = y.toString();
			int digitsYear = Integer.parseInt(fullYear.substring(2, 4));
			date.add((byte) digitsYear);
			date.add((byte) currentDate.get(Calendar.HOUR_OF_DAY));
			date.add((byte) currentDate.get(Calendar.MINUTE));
			date.add((byte) currentDate.get(Calendar.SECOND));
			
			byte[] out = new byte[date.size()];
			
			int count = 0;
			for(Byte b : date) {
				out[count] = b;
				count++;
			}
			return out;
			
		}catch (Exception e) {
			log.errorLog("Erro ao recuperar hora corrente", e);
		}
		return null;
	}
}
