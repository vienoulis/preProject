package usersDAO;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    private Properties properties = new Properties();

    public UserDao getDAO() throws IOException {
        properties.load(new FileReader(getClass().getClassLoader().getResource("dao.properties").getFile()));
        switch (properties.getProperty("daotype")) {
            case "JdbcDAO":
                return new UserJdbcDAO(DBHelper.getConnection());
            case "HibernateDAO":
                return new UserHibernateDAO(DBHelper.createSessionFactory());
            default:
                throw new RuntimeException("Неизвестная реализация");

        }
    }

}
