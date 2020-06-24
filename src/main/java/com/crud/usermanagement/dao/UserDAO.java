package com.crud.usermanagement.dao;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO<T> {
    void insertUser(T t);

    List<T> selectAllUsers();

    boolean updateUser(T t);

    boolean deleteUser(int t);

    T selectUser(int t);

    T selectUserByNamePassword(String name, String password);
}
