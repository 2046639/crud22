package com.crud.usermanagement.dao_old;

import com.crud.usermanagement.model.User_old;
import com.crud.usermanagement.util.DBHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO<User_old> {

    private final SessionFactory sessionFactory;

    private static UserHibernateDAO INSTANCE;

    private UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserHibernateDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserHibernateDAO(DBHelper.getSessionFactory());
        }
        return INSTANCE;
    }

    @Override
    public void insertUser(User_old user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User_old> selectAllUsers() {
        Session session = sessionFactory.openSession();
        List<User_old> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public User_old selectUser(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where id = :idParam");
        query.setParameter("idParam", id);
        query.setMaxResults(1);
//        session.close();
        return (User_old) query.uniqueResult();
    }

    @Override
    public boolean updateUser(User_old user) {
        int id = user.getId();
        String name = user.getName();
        String email = user.getEmail();
        String country = user.getCountry();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update User set name = :nameParam, email = :emailParam, country = :countryParam where id = :idP";
        Query query = session.createQuery(hql);
        query.setParameter("nameParam", name);
        query.setParameter("emailParam", email);
        query.setParameter("countryParam", country);
        query.setParameter("idP", id);
        int result = query.executeUpdate();
        transaction.commit();
//        session.close();
        return result > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete User where id = :paramId");
        query.setParameter("paramId", id);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result > 0;
    }
}
