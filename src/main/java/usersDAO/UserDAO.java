package usersDAO;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
