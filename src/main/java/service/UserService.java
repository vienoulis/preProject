package service;

import model.User;
import usersDAO.DBHelper;
import usersDAO.UserDao;
import usersDAO.UserHibernateDAO;
import usersDAO.UserJdbcDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

public class UserService {

    private UserDao userDao = getUserJDBCDAO();
//    private UserDao userDao = new UserHibernateDAO(DBHelper.getSessionFactory());

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

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=1234").
                    append("&serverTimezone=UTC");       //password

            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
            System.out.printf(e.getMessage());
            throw new IllegalStateException();
        }
    }

    private UserJdbcDAO getUserJDBCDAO() {
        if (userDao == null){
            return new UserJdbcDAO(getMysqlConnection());
        } else {
            return (UserJdbcDAO) userDao;
        }
    }

    private UserHibernateDAO getUserHibernateDAO() {
        return new UserHibernateDAO(DBHelper.getSessionFactory());
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
