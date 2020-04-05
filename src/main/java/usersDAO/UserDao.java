package usersDAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    void delete(long userId) throws SQLException;

    User getUserById(Long id) throws SQLException;

    void update(long userId, String name, String age, String passport) throws SQLException;
}
