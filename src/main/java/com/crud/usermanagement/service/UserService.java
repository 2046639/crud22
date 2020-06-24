package com.crud.usermanagement.service;

import com.crud.usermanagement.dao.UserDAO;
import com.crud.usermanagement.dao.UserDaoFactory;
import com.crud.usermanagement.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

//    private final UserJdbcDAO dao = UserJdbcDAO.getInstance();
//    private final UserHibernateDAO dao = UserHibernateDAO.getInstance();
    private final UserDAO<User> dao = UserDaoFactory.createUserDAO();

    private UserService() {
    }

    public static UserService INSTANCE;

    public static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }


    public List<User> selectAllUsers() {
        return dao.selectAllUsers();
    }

    public User selectUser(int id) {
        return dao.selectUser(id);
    }

    public User selectUserByNamePassword(String name, String password) {
        return dao.selectUserByNamePassword(name, password);
    }

    public String getRoleByNamePassword(String name, String password) {
        String role = null;
        User user = selectUserByNamePassword(name, password);
        if (user != null) {
            role = user.getRole();
        }
        return role;
    }

    public boolean isValidUser(User user) {
        boolean result = true;
//        if (user.getRole().equals("user") || user.getRole().equals("admin")) {
//            result = true;
//        }
        List<User> list = selectAllUsers();
        for (User u: list) {
            if (user.getName().equals(u.getName())) {
                result = false;
                return result;
            }
        }
        return result;
    }

    public void insertUser(User user) {
        dao.insertUser(user);
    }

    public boolean updateUser(User user) {
        return dao.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return dao.deleteUser(id);
    }
}
