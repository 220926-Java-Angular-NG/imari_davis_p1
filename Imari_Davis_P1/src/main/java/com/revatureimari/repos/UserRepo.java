package com.revatureimari.repos;

import com.revatureimari.models.User;
import com.revatureimari.models.UserStatus;
import com.revatureimari.utils.CRUDDaoInterface;
import com.revatureimari.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements CRUDDaoInterface<User> {
    public Connection connection;

    public UserRepo() {
        try {
            connection = ConnectionManager.getConnection();
            System.out.println(connection.getSchema());

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public int create(User user) {
        try {
            String sql = "INSERT INTO users (user_id, first_name, last_name, email, user_name, pass_word, user_status) " +
                    "values (default,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getUserStatus().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            return resultSet.getInt("user_id");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();

        try {
            String sql = "SELECT * FROM users";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("pass_word"));
                user.setUserStatus(UserStatus.valueOf(resultSet.getString("user_status")));
            }

            return users;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public User getById(int userId) {
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();

            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("pass_word"));
                user.setUserStatus(UserStatus.valueOf(resultSet.getString("user_status")));
            }

            return user;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public User update(User user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, user_name = ?, pass_word = ? " +
                    "WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getUserId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("pass_word"));
            }

            return user;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public boolean delete(User user) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getUserId());

            preparedStatement.execute();
            return true;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteById(int userId) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);

            preparedStatement.execute();
            return true;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    public User loginUser(User user) {
        try {
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getString("pass_word").equals(user.getPassword())) {
                return new User(resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("user_name"),
                        resultSet.getString("pass_word"),
                        UserStatus.valueOf(resultSet.getString("user_status")));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public User loginUserByEmail(User user) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getString("pass_word").equals(user.getPassword())) {
                return new User(resultSet.getInt("user_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("email"),
                                resultSet.getString("user_name"),
                                resultSet.getString("pass_word"),
                                UserStatus.valueOf(resultSet.getString("user_status")));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }
}
