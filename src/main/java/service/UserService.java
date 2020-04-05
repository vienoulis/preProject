package service;

import model.User;
import servlet.UsersServlet;
import sun.usagetracker.UsageTrackerClient;
import usersDAO.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDao userDao = new UserDaoFactory().getDAO();

    private UserService() throws IOException {
    }
    public static UserService getInstance() throws IOException {
        if (userService == null){
            return new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUserById(long id) {
        try {
            return userDao.getUserById(id);
        } catch (SQLException e) {

        }
        return new User();
    }

    public void addUser(String name, String age, String passport) {
        User user = new User(name,
                age == null ? 0 : Integer.parseInt(age),
                passport == null ? 0 : Long.parseLong(passport));
        if (!getAllUsers().contains(user)) {
            try {
                userDao.addUser(user);
            } catch (SQLException e) {

            }
        }
    }

    public void delete(int userId) {
        try {
            userDao.delete(userId);
        } catch (SQLException e) {

        }
    }

    public void update(long userId, String name, String age, String passport) {
        try {
            userDao.update(userId, name, age, passport);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
