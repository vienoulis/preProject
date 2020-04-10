package service;

import com.google.gson.Gson;
import model.User;
import usersDAO.*;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDao userDao = new UserDaoFactory().getDAO();

    private UserService() throws IOException {
    }

    public static UserService getInstance() throws IOException {
        if (userService == null) {
            return new UserService();
        }
        return userService;
    }

    public List<String> getAllUsersToGSON() {
        List<String> strings = new ArrayList<>();
        Gson gson = new Gson();
        for (User user : getAllUsers()) {
            strings.add(gson.toJson(user));
        }
        return strings;
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

    public void addUser(String name, String age, String passport, String password, String role) {
        User user = new User(name,
                age.length() == 0 ? 0 : Integer.parseInt(age),
                passport.length() == 0 ? 0 : Long.parseLong(passport),
                password.length() == 0 ? "1234" : password,
                role == null ? "user" : "admin");
        if (!getAllUsers().contains(user)) {
            try {
                userDao.addUser(user);
            } catch (SQLException e) {

            }
        }
    }
    // исправить др мтоды.

    public void delete(int userId) {
        try {
            userDao.delete(userId);
        } catch (SQLException e) {

        }
    }

    public void update(long userId, String name, String age, String passport, String password, String role) {
        try {
            role = role == null ? "user" : "admin";
            userDao.update(userId, name, age, passport, password, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
