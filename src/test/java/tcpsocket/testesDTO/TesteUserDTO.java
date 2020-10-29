package tcpsocket.testesDTO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tcpsocket.app.dto.UserDTO;

public class TesteUserDTO {
	@Test
	public void testeContrutor() {
		//Dados do usuario - 1F 64 AA 07 54 65 73 74 65 52 52
		byte[] data = {(byte) 31, (byte) 100, (byte) 170, (byte) 7, (byte) 84, (byte) 101, (byte) 115, (byte) 116, (byte) 101, (byte) 82,(byte) 82}; 
		
		UserDTO user = new UserDTO(data);
		
		String nome = "TesteRR";
		
		assertEquals(true, user.getAge().equals(31));
		assertEquals(true, user.getSize().equals((float) 1.7));
		assertEquals(true, user.getWeight().equals(100));
		assertEquals(true, user.getName().equals(nome));
	}
}
