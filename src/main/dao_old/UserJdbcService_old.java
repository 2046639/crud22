package com.crud.usermanagement.service;

import com.crud.usermanagement.dao_old.UserDAO;
import com.crud.usermanagement.dao_old.UserDaoFactory;
import com.crud.usermanagement.model.User_old;

import java.sql.SQLException;
import java.util.List;

public class UserJdbcService_old {

//    private final UserJdbcDAO dao = UserJdbcDAO.getInstance();
//    private final UserHibernateDAO dao = UserHibernateDAO.getInstance();
    private final UserDAO<User_old> dao = UserDaoFactory.createUserDAO();

    private UserJdbcService_old() {
    }

    public static UserJdbcService_old INSTANCE;

    public static UserJdbcService_old getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJdbcService_old();
        }
        return INSTANCE;
    }


    public List<User_old> selectAllUsers() throws SQLException {
        return dao.selectAllUsers();
    }

    public User_old selectUser(int id) throws SQLException {
        return dao.selectUser(id);
    }

    public void insertUser(User_old user) throws SQLException {
        dao.insertUser(user);
    }

    public boolean updateUser(User_old user) throws SQLException {
        return dao.updateUser(user);
    }

    public boolean deleteUser(int id) throws SQLException {
        return dao.deleteUser(id);
    }
}
