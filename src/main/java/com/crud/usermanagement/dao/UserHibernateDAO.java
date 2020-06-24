package com.crud.usermanagement.dao;

import com.crud.usermanagement.model.User;
import com.crud.usermanagement.util.DBHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO<User> {

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
    public void insertUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> selectAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public User selectUser(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where id = :idParam");
        query.setParameter("idParam", id);
        query.setMaxResults(1);
//        session.close();
        return (User) query.uniqueResult();
    }

    @Override
    public User selectUserByNamePassword(String name, String password) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where name = :nameParam and password = :passwordParam");
        query.setParameter("nameParam", name);
        query.setParameter("passwordParam", password);
        query.setMaxResults(1);
        return (User) query.uniqueResult();
    }


    @Override
    public boolean updateUser(User user) {
        int id = user.getId();
        String name = user.getName();
        String password = user.getPassword();
        String role = user.getRole();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update User set name = :nameParam, password = :passwordParam, role = :roleParam where id = :idP";
        Query query = session.createQuery(hql);
        query.setParameter("nameParam", name);
        query.setParameter("passwordParam", password);
        query.setParameter("roleParam", role);
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
