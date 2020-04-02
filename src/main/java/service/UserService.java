package service;

import model.User;
import usersDAO.UserDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public List<User> getAllUsers(){
        try {
            return new UserDAO(getMysqlConnection()).getAllUsers();
        } catch (SQLException e){
            return null;
        }
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

    private static UserDAO getBankClientDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
