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
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users");
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("age"),
                    set.getLong("passport")));
        }
        statement.close();
        return users;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users " +
                "(id bigint auto_increment, name varchar(256), age int, passport bigint, primary key (id))");
        stmt.close();
    }

    public void addUser(User user) throws SQLException {
        createTable();
        String update = "INSERT INTO users(name, age, passport) value (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(update);
        String s = "asdadsas";
        s = "adsadsad";
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setLong(3, user.getPassport());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(int userId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from users where id like " + userId);
        statement.close();
    }

    public User getUserById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users where id like " + id);
        User user = new User();
        while (set.next()) {
            user.setName(set.getString("name"));
            user.setAge(set.getInt("age"));
            user.setPassport(set.getLong("passport"));
        }
        return user;
    }

    public void update(long userId, String name, String age, String passport) throws SQLException {
        String update = "update users set name  = ?, age = ?, passport = ? where id like ?";
        User user = getUserById(userId);
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, name == null ? user.getName() : name);
        statement.setInt(2, age == null ? user.getAge() : Integer.parseInt(age));
        statement.setLong(3, passport == null ? user.getPassport() : Long.parseLong(passport));
        statement.setLong(4, userId);
        statement.executeUpdate();
        statement.close();
    }
}
