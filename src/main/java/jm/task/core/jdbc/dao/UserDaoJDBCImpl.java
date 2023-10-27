package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.getconnect();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id SERIAL primary key, name varchar(20), lastName varchar(20), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getconnect();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getconnect();
                PreparedStatement prs = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)")) {
            prs.setString(1, name);
            prs.setString(2, lastName);
            prs.setByte(3, age);
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getconnect();
                PreparedStatement prs = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            prs.setLong(1, id);
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Connection connection = Util.getconnect();
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getconnect();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
