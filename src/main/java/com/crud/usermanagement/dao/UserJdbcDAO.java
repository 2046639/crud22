package com.crud.usermanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.usermanagement.model.User;
import com.crud.usermanagement.util.DBHelper;

public class UserJdbcDAO implements UserDAO<User> {

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, password, role) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id, name, password, role from users where id =?";

	private static final String SELECT_USER_BY_NAME_PASS = "select id, name, password, role from users where name =? and password =?";

	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,password= ?, role =? where id = ?;";

	private static Connection connection;

	//	private UserJdbcDAO() {
//		connection = getMysqlConnection();
//	}
	private UserJdbcDAO() {
		connection = DBHelper.getInstance().getConnection();
	}

	private static UserJdbcDAO INSTANCE;

	public static UserJdbcDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserJdbcDAO();
		}
		return INSTANCE;
	}

	@Override
	public void insertUser(User user) {
		System.out.println(INSERT_USERS_SQL);
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getRole());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}


	public User selectUser(int id) {
		User user = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String role = rs.getString("role");
				user = new User(id, name, password, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	@Override
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String role = rs.getString("role");
				users.add(new User(id, name, password, role));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}



	@Override
	public User selectUserByNamePassword(String name, String password) {
		User user = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME_PASS)) {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String role = rs.getString("role");
				user = new User(id, name, password, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try (PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		catch (SQLException e) {
			printSQLException(e);
		}

		return rowDeleted;
	}

	@Override
	public boolean updateUser(User user) {
		boolean rowUpdated = false;
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole());
			statement.setInt(4, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		catch (SQLException e) {
			printSQLException(e);
		}

		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
