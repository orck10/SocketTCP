package tcpsocket.app.dao.imp;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tcpsocket.app.Constants.DBConstants;
import tcpsocket.app.dao.MessageDAO;
import tcpsocket.app.dto.UserDTO;
import tcpsocket.app.log.Log;
import tcpsocket.app.repositores.utils.HibernateUtil;

public class UserDAO implements MessageDAO{
	
	Transaction transaction = null;
	Log log = new Log();
	String tableName = DBConstants.TABLEUSERDTO;
	
	@Override
	public List<UserDTO> getAll() {
		log.infoLog("Get All - UserDTO");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			TypedQuery<UserDTO> query = session.createQuery("from "+this.tableName, UserDTO.class);
			List <UserDTO> users = query.getResultList();
			session.close();
			log.infoLog("Get All sucess- UserDTO" + users.toString());
			return users;
        } catch (Exception e) {
	        log.errorLog("Get All fail - UserDTO", e);
        }
		return null;
	}

	@Override
	public <UserDTO> void save(UserDTO u) {
		log.infoLog("Save - UserDTO - " + u.toString());
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); 
            session.save(u);
            transaction.commit();
            session.close();
            log.infoLog("Sucess at save - UserDTO");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            log.errorLog("Save fail - UserDTO - "+ u.toString(), e);
        }
	}

}
