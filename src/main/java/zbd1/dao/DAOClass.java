package zbd1.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zbd1.util.HibernateUtil;

import java.util.List;

public abstract class DAOClass {
    
	protected Session session;
    protected static final Logger log = LoggerFactory.getLogger(DAOClass.class);
    
    public DAOClass() {
    
    }

    protected void beginSession(){
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
        } catch (HibernateException e) {
            log.warn(e.getMessage());
        }
	}
    
	protected void endSession(){
		session.getTransaction().commit();
	}
    
    protected void save(Object obj) {
        try {
            beginSession();
            session.saveOrUpdate(obj);
            endSession();
        } catch (HibernateException e) {
            log.warn("error while save/update transaction :\n" + e.getMessage());
            try {
        		session.close();
        	} catch (HibernateException e2) {
        		log.warn("error while closing session\n" + e2.getMessage());
        	}
        }
    }

    protected void delete(Object obj) {
        try {
            beginSession();
            session.delete(obj);
            endSession();
        } catch (HibernateException e) {
        	log.warn("error while delete transaction :\n" + e.getMessage());
        	try {
        		session.close();
        	} catch (HibernateException e2) {
        		log.warn("error while closing session\n" + e2.getMessage());
        	}
        }
    }

    protected Object find(@SuppressWarnings("rawtypes") Class c, Long id) {
        Object obj = null;
        try {
            beginSession();
            obj = session.get(c, id);
            endSession();
        } catch (HibernateException e) {
        	log.warn("error while find transaction :\n" + e.getMessage());
        	try {
        		session.close();
        	} catch (HibernateException e2) {
        		log.warn("error while closing session\n" + e2.getMessage());
        	}
        }
        return obj;
    }

    @SuppressWarnings("rawtypes")
	protected List findAll( Class c) {
        List objects = null;
        try {
            beginSession();
            Query query = session.createQuery("from " + c.getName());
            objects = query.list();
            endSession();
        } catch (HibernateException e) {
        	log.error("error while findAll transaction :\n" + e.getMessage());
        	try {
        		session.close();
        	} catch (HibernateException e2) {
        		log.error("error while closing session :\n" + e2.getMessage());
        	}
        }
        return objects;
    }
    
}
