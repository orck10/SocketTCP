package tcpsocket.app.dao;

import java.util.List;

public interface MessageDAO {
	public List<?> getAll();

	public <T> void save(T u);
}
