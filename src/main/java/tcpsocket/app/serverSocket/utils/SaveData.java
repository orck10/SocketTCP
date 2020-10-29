package tcpsocket.app.serverSocket.utils;

import java.util.Date;
import java.util.List;

import tcpsocket.app.dao.imp.TextMessageDAO;
import tcpsocket.app.dao.imp.UserDAO;
import tcpsocket.app.dto.RequestDTO;
import tcpsocket.app.dto.TextMessageDTO;
import tcpsocket.app.dto.UserDTO;

public class SaveData {
	
	public void saveData(RequestDTO request, String ip) throws Exception{
		if(request.getIsConsistent()) {
			if(request.getFrame() == -95) {
				new TextMessageDAO().save(new TextMessageDTO(new String(request.getData()),  new Date(), ip));
			}
			else if(request.getFrame() == -94) {
				new UserDAO().save(new UserDTO(request.getData()));
			}
		}
	}
	
	public List<TextMessageDTO> getAll(){
		return new TextMessageDAO().getAll();
	}
}
