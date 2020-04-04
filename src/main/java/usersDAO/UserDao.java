package usersDAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> getAllUsers() throws SQLException;
    public void addUser(User user) throws SQLException;
    public void delete(long userId) throws SQLException;
    public User getUserById(Long id) throws SQLException;
    public void update(long userId, String name, String age, String passport) throws SQLException;
}
