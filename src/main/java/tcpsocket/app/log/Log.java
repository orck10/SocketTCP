package tcpsocket.app.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Log {
	private void log(String message, String type) {
		File arquivo = new File("socket.log");
        
        if( !arquivo.exists()){
            try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        List<String> lista = new ArrayList<String>();
        lista.add(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " - " + type + " : " + message);
        System.out.println(lista.toString());
        try {
			Files.write(Paths.get(arquivo.getPath()), lista, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void infoLog(String message){
		this.log(message, "INFO");
		
	}
	
	public void errorLog(String message, Exception e){
		e.printStackTrace();
		this.log(message, "ERROR");
		this.log(e.getMessage(), "ERROR");
	}
}
