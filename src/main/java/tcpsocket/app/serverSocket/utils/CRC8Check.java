package tcpsocket.app.serverSocket.utils;

import java.util.zip.Checksum;

public class CRC8Check implements Checksum{
	
	private int poly;
	private int crc;
	
	
	
	public CRC8Check(int poly) {
		super();
		this.poly = poly;
		this.crc = 0;
	}

	public void update(int b) {
		update((byte) b);
	}

	public void update(final byte[] input, final int offset, final int len) {
		for (int i = 0; i < len; i++) {
			update(input[offset + i]);
		}
	}
	
	public void update(final byte[] input) {
		update(input, 0, input.length);
	}
	
	public void update(final Byte[] input) {
		byte[] array = new byte[input.length];
		int count = 0;
		for(Byte b : input) {
			array[count] = b;
			count++;
		}
		update(array, 0, array.length);
	}
	
	private final void update(final byte b) {
		crc ^= b;
		for (int j = 0; j < 8; j++) {
			if ((crc & 0x80) != 0) {
				crc = ((crc << 1) ^ poly);
			} else {
				crc <<= 1;
			}
		}
		crc &= 0xFF;
	}

	public long getValue() {
		return (crc & 0xFF);
	}

	public void reset() {
		crc = 0;
	}
	
	public int getCRC(final byte[] input) {
		reset();
		update(input);
		byte teste = (byte) crc;
		return (int) teste;
	}
}
