package tcpsocket.testesDTO;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import tcpsocket.app.dto.TextMessageDTO;

public class TestTextMessageDTO {
	@Test
	public void testeContrutor() {
		
		Date date = new Date();
		
		TextMessageDTO mensagem = new TextMessageDTO("Teste", date, "localhost");
		
		assertEquals(true , mensagem.getDate().equals(date));
		assertEquals(true , mensagem.getMessage().equals("Teste"));
		assertEquals(true , mensagem.getIp().equals("localhost"));
		
	}
}
