package tcpsocket.testesDTO;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

import tcpsocket.app.dto.ResponceDTO;

public class TestResponceDTO {
	@Test
	public void testeContrutorRespostaSalvarUsuario() {
		byte[] data = {(byte) 31, (byte) 100, (byte) 170, (byte) 7, (byte) 84, (byte) 101, (byte) 115, (byte) 116, (byte) 101, (byte) 82, (byte) 82};
		byte[] resposta = {(byte)10, (byte) 5, (byte) 160, (byte) 40, (byte) 13};
		
		///Array com Usurario TesteRR - 0a 10 a2 1f 64 aa 07 54 65 73 74 65 52 52 16 0d 
		ResponceDTO resp = new ResponceDTO(-94, new Boolean(true), data);
		
		
		int frame = 160;
		
		assertEquals(true, resp.getFrame() == frame);
		assertEquals(true, resp.getMessage().equals(""));
		int count = 0;
		for(byte b : resp.responseGenerate(007)) {
			assertEquals(true, b == resposta[count]);
			count++;
		}
	}
	
	@Test
	public void testeContrutorRespostaSalvarMensagem() {
		byte[] data = {(byte) 31, (byte) 100, (byte) 170, (byte) 7, (byte) 84, (byte) 101, (byte) 115, (byte) 116, (byte) 101, (byte) 82, (byte) 82};
		byte[] resposta = {(byte)10, (byte) 5, (byte) 160, (byte) 40, (byte) 13};
		
		///Array com Usurario TesteRR - 0a 10 a2 1f 64 aa 07 54 65 73 74 65 52 52 16 0d 
		ResponceDTO resp = new ResponceDTO(-95, new Boolean(true), data);
		
		
		int frame = 160;
		
		assertEquals(true, resp.getFrame() == frame);
		assertEquals(true, resp.getMessage().equals(""));
		int count = 0;
		for(byte b : resp.responseGenerate(007)) {
			assertEquals(true, b == resposta[count]);
			count++;
		}
	}
	
	
	@Test
	public void testeContrutorRespostaHoraCorrente() {
		byte[] data = {(byte)65, (byte)109, (byte)101, (byte)114, (byte)105, (byte)99, (byte)97, (byte)47, (byte)83, (byte)97, (byte)111, (byte)95, (byte)80, (byte)97, (byte)117, (byte)108, (byte)111};
		Calendar currentDate = Calendar.getInstance(TimeZone.getTimeZone(new String("America/Sao_Paulo")));
		
		byte[] date = new byte[3];
		date[0] = (byte) currentDate.get(Calendar.DAY_OF_MONTH);
		int month = currentDate.get(Calendar.MONTH) + 1;
		date[1] =  (byte) month;
		Integer y = currentDate.get(Calendar.YEAR);
		String fullYear = y.toString();
		int digitsYear = Integer.parseInt(fullYear.substring(2, 4));
		date[2] = (byte) digitsYear;
		
		///Array com requisição de data America/Sao_Paulo - 0a 16 a3 41 6d 65 72 69 63 61 2f 53 61 6f 5f 50 61 75 6c 6f cd 0d 
		ResponceDTO resp = new ResponceDTO(-93, new Boolean(true), data);
		
		
		int frame = 160;
		
		assertEquals(true, resp.getFrame() == frame);
		int count = 0;
		int count2 = 3;
		for(byte b : date) {
			assertEquals(true, resp.getResponce()[count] == b);
			assertEquals(true, resp.responseGenerate(007)[count2] == b);
			count++;
			count2++;
		}
	}
	
	@Test
	public void testeContrutorComFrameByteInvalido() {
		byte[] data = {(byte) 31, (byte) 100, (byte) 170, (byte) 7, (byte) 84, (byte) 101, (byte) 115, (byte) 116, (byte) 101, (byte) 82, (byte) 82};
		
		///Array com Usurario TesteRR - 0a 10 a2 1f 64 aa 07 54 65 73 74 65 52 52 16 0d 
		ResponceDTO resp = new ResponceDTO(85, new Boolean(true), data);
		
		
		int frame = 160;
		
		assertEquals(true, resp.getFrame() == frame);
		assertEquals(true, resp.getMessage().contains("Operação invalida - "));
	}
	
	@Test
	public void testeContrutorComMensagemInconssitente() {
		byte[] data = {(byte) 31, (byte) 100, (byte) 170, (byte) 7, (byte) 84, (byte) 101, (byte) 115, (byte) 116, (byte) 101, (byte) 82, (byte) 82};
		
		///Array com Usurario TesteRR - 0a 10 a2 1f 64 aa 07 54 65 73 74 65 52 52 16 0d 
		ResponceDTO resp = new ResponceDTO(-94, new Boolean(false), data);
		
		
		int frame = 160;
		
		assertEquals(true, resp.getFrame() == frame);
		assertEquals(true, resp.getMessage().contains("Mensagem recebida é inconsistente tente novamente - "));
	}
}
