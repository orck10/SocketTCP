package tcpsocket.testesDTO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tcpsocket.app.dto.RequestDTO;

public class TestRequestDTO {
	
	@Test
	public void testeContrutor() {
		///Array com Usurario TesteRR - 0a 10 a2 1f 64 aa 07 54 65 73 74 65 52 52 16 0d 
		byte[] array = { (byte)10, (byte)16, (byte)162, (byte)31, (byte)100, (byte)170, (byte)7, (byte)84, (byte)101, (byte)115, (byte)116, (byte)101, (byte)82, (byte)82, (byte)22, (byte)13 };
		
		///Teste do contrutor
		RequestDTO req = new RequestDTO(array, 007);
		Byte crc = (byte) 22;
		Integer size = 16;
		Integer frame = -94;
		byte[] data = {(byte) 31, (byte) 100, (byte) 170, (byte) 7, (byte) 84, (byte) 101, (byte) 115, (byte) 116, (byte) 101, (byte) 82,(byte) 82}; 
		assertEquals(true, req.getCrc().equals(crc));
		assertEquals(true, req.getFrame().equals(frame));
		assertEquals(true, req.getSize().equals(size));
		int count = 0;
		for(byte b : req.getData()) {
			assertEquals(true, b==data[count]);
			count++;
		}
		assertEquals(true, req.getIsConsistent());
		
	}
	
	@Test
	public void testeContrutor2() {
		///Array com Usurario TesteRR co crc errado - 0a 10 a2 1f 64 aa 07 54 65 73 74 65 52 52 01 0d 
		byte[] array = { (byte)10, (byte)16, (byte)162, (byte)31, (byte)100, (byte)170, (byte)7, (byte)84, (byte)101, (byte)115, (byte)116, (byte)101, (byte)82, (byte)82, (byte)1, (byte)13 };
		
		
		///Teste do contrutor
		RequestDTO req = new RequestDTO(array, 007);
		
		
		assertEquals(false, req.getIsConsistent());
	}
}
