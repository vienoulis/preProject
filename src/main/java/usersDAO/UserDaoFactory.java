package usersDAO;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    private Properties properties = new Properties();

    public UserDao getDAO() throws IOException {
        properties.load(new FileReader("C:\\Java\\JavaMentorLessons\\Lessons\\preProject\\src\\main\\resources\\dao.properties"));
        switch (properties.getProperty("daotype")) {
            case "JdbcDAO":
                return new UserJdbcDAO(DBHelper.getConnection());
            case "HibernateDAO":
                return new UserHibernateDAO();
            default:
                throw new RuntimeException("Неизвестная реализация");

        }
    }

}
