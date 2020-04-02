package usersDAO;

import com.google.gson.Gson;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        createTable();
        List<User> users = new ArrayList<>();
        ResultSet set = connection.createStatement().executeQuery("select * from users");
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("age"),
                    set.getLong("passport")));
        }
        connection.close();
        return users;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users " +
                "(id bigint auto_increment, name varchar(256), age int, passport bigint, primary key (id))");
        stmt.close();
    }

    public void addUser(User user) throws SQLException {
        String update = "INSERT INTO users(name, age, passport) value (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setLong(3, user.getPassport());
        statement.executeUpdate();
        connection.close();
    }

    public List<User> getAllSortUsers(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users where name  = '" + name + "'");
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("age"),
                    set.getLong("passport")));
        }
        connection.close();
        return users;
    }

    public List<User> getAllSortUsers(int parseInt) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users where age = " + parseInt);
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("age"),
                    set.getLong("passport")));
        }
        connection.close();
        return users;
    }

    public List<User> getAllSortUsers(long parseLong) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users where passport = " + parseLong);
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("age"),
                    set.getLong("passport")));
        }
        connection.close();
        return users;
    }

    public String update(int age, String name, long passport, long newPassport) throws SQLException {
        String result;
        String update = "update users set passport = ? where name like ? and  age like ? and passport like ?";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setLong(1, newPassport);
        statement.setString(2, name);
        statement.setInt(3, age);
        statement.setLong(4, passport);

        if (statement.executeUpdate() > 0) {
            result = new Gson().toJson(new User(name, age, newPassport));
        } else {
            result = "This user was't found";
        }
        statement.close();
        return result;
    }

    public String update(String name, int age, long passport, int newAge) throws SQLException {
        String result;
        String update = "update users set age = ? where name like ? and  age like ? and passport like ?";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setInt(1, newAge);
        statement.setString(2, name);
        statement.setInt(3, age);
        statement.setLong(4, passport);

        if (statement.executeUpdate() > 0) {
            result = new Gson().toJson(new User(name, newAge, passport));
        } else {
            result = "This user was't found";
        }
        statement.close();
        return result;
    }
}
