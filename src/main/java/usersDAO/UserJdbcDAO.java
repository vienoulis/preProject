package usersDAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDao {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        createTable();
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users");
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("age"),
                    set.getLong("passport"),
                    set.getString("password"),
                    set.getString("role")));
        }
        statement.close();
        return users;
    }

    // Изменить остальные методы
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table users \n" +
                "(id bigint not null auto_increment,\n" +
                " age integer, name varchar(255), \n" +
                " passport bigint, password varchar(255), \n" +
                "role varchar(255), primary key (id))");
        stmt.close();
    }

    private User getUserByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users where name like " + name);
        User user = new User();
        while (set.next()) {
            user.setName(set.getString("name"));
            user.setAge(set.getInt("age"));
            user.setPassport(set.getLong("passport"));
        }
        return user;
    }

    public boolean authUser(String name, String password) throws SQLException {
        User user = getUserByName(name);
        return name.equals(user.getName()) && password.equals(user.getPassword());
    }

    public boolean isAdmin(String name) throws SQLException {
        User user = getUserByName(name);
        return user.getRole().equals("admin");
    }

    @Override
    public void addUser(User user) throws SQLException {
        createTable();
        String update = "INSERT INTO users(name, age, passport) value (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setLong(3, user.getPassport());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void delete(long userId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from users where id like " + userId);
        statement.close();
    }

    @Override
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

    @Override
    public void update(long userId, String name, String age, String passport, String password, String role) throws SQLException {
        String update = "update users set name  = ?, age = ?, passport = ?, password = ?, role = ? where id = ?";
        User user = getUserById(userId);
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, name == null ? user.getName() : name);
        statement.setInt(2, age == null ? user.getAge() : Integer.parseInt(age));
        statement.setLong(3, passport == null ? user.getPassport() : Long.parseLong(passport));
        statement.setString(4, password == null ? user.getPassword() : password);
        statement.setString(5, role == null ? user.getRole() : role);
        statement.setLong(6, userId);
        statement.executeUpdate();
        statement.close();
    }


}
