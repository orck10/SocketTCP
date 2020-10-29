package tcpsocket.app.dto;

import java.util.Arrays;

import tcpsocket.app.serverSocket.utils.CRC8Check;

public class RequestDTO {
	private Integer size;
	private Integer frame;
	private byte[] data;
	private Byte crc;
	private byte[] origem;
	private Boolean isConsistent;
	
	public RequestDTO(byte[] obj, int crcDigit) {
		if(obj != null) {
			this.origem = Arrays.copyOfRange(obj, 1, obj.length-2);
			this.crc = obj[obj.length - 2];
			this.isConsistent = this.crcVerify(this.origem, crcDigit);
			if(this.isConsistent) {
				this.size = (int) this.origem[0];
				this.frame = (int) this.origem[1];
				this.data =   Arrays.copyOfRange(origem,2, origem.length);
			}
			else {
				this.size = 0;
				this.frame = -1;
				this.data = new byte[] {};
			}
		}else {
			this.isConsistent = false;
		}
	}

	private Boolean crcVerify(byte[] obj, int crcDigit) {
		if(this.origem != null && this.crc != null) {
			byte[] array = obj;
			CRC8Check check = new CRC8Check(crcDigit);
			if(check.getCRC(array) == this.crc) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean getIsConsistent() {
		return this.isConsistent;
	}
	public Integer getSize() {
		return this.size;
	}
	
	public Integer getFrame() {
		return this.frame;
	}
	
	public byte[] getData() {
		return this.data;
	}
	
	public Byte getCrc() {
		return this.crc;
	}

	@Override
	public String toString() {
		return "RequestDTO [size=" + size + ", frame=" + frame + ", data=" + data + ", crc=" + crc + ", origem="
				+ origem + ", isConsistent=" + isConsistent + "]";
	}
}
