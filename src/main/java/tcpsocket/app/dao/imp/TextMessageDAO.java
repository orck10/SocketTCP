package tcpsocket.app.dao.imp;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tcpsocket.app.Constants.DBConstants;
import tcpsocket.app.dao.MessageDAO;
import tcpsocket.app.dto.TextMessageDTO;
import tcpsocket.app.log.Log;
import tcpsocket.app.repositores.utils.HibernateUtil;

public class TextMessageDAO implements MessageDAO{
		
	Transaction transaction = null;
	Log log = new Log();
	String tableName = DBConstants.TABLETEXTMESSAGEDTO;
	
	
	
	public TextMessageDAO() {
	}

	@Override
	public List<TextMessageDTO> getAll() {
		log.infoLog("Get All - TextMessageDTO");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			TypedQuery<TextMessageDTO> query = session.createQuery("from "+this.tableName, TextMessageDTO.class);
			List <TextMessageDTO> messages = query.getResultList();
			session.close();
			log.infoLog("Get All sucess- TextMessageDTO" + messages.toString());
			return messages;
        } catch (Exception e) {
	        log.errorLog("Get All fail - TextMessageDTO", e);
        }
		return null;
	}
	
	@Override
	public <TextMessageDTO> void save(TextMessageDTO m) {
		log.infoLog("Save - TextMessageDTO - " + m.toString());
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); 
            session.save(m);
            transaction.commit();
            session.close();
            log.infoLog("Sucess at save - TextMessageDTO");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            log.errorLog("Save fail - TextMessageDTO - "+ m.toString(), e);
        }
	}
}
