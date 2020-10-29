package tcpsocket.app.dto;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tcpsocket.app.Constants.DBConstants;

@Entity(name = DBConstants.TABLEUSERDTO)
@Table(name = DBConstants.TABLEUSERDTO)
public class UserDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "age")
	private Integer age;
	@Column(name = "weight")
	private Integer weight;
	@Column(name = "size")
	private Float size;
	@Column(name = "name")
	private String name;
	
	public UserDTO(Integer age, Integer weight, Float size, String name) {
		this.age = age;
		this.weight = weight;
		this.size = size;
		this.name = name;
	}
	
	public UserDTO(byte[] data) {
		if(data != null && data.length > 5) {
			this.age = (int) data[0];
			this.weight = (int) data[1];
			int sizeTemp = (int) data[2] > 0 ? ((int) data[2]) : ((int) data[2] + 256);
			this.size = ((float) sizeTemp)/100;
			this.name = new String(Arrays.copyOfRange(data,4, (4+ (int) data[3])));
		}
	}
	public UserDTO() {
		super();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO [age=" + age + ", weight=" + weight + ", size=" + size + ", name=" + name + "]";
	}
	
	
	
}
