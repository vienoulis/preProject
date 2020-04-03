package usersDAO;

import model.User;


import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    List<User> getAllSortUsers(String name) throws SQLException;

    List<User> getAllSortUsers(int parseInt) throws SQLException;

    List<User> getAllSortUsers(long parseLong) throws SQLException;

    String update(int age, String name, long passport, long newPassport) throws SQLException;

    String update(String name, int age, long passport, int newAge) throws SQLException;

    void delete(User user) throws SQLException;
}

