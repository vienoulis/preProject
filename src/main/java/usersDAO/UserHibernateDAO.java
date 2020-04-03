package usersDAO;

import com.google.gson.Gson;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("From User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        session.save(user);
        session.close();
    }

    @Override
    public List<User> getAllSortUsers(String name) {
        List<User> users = session.createQuery("from User where name = :name")
                .setParameter("name", name).list();
        session.close();
        return users;
    }

    @Override
    public List<User> getAllSortUsers(int age) {
        List<User> users = session.createQuery("from  User  where age = :age")
                .setParameter("age", age).list();
        session.close();
        return users;
    }

    @Override
    public List<User> getAllSortUsers(long passport) {
        List<User> users = session.createQuery("from  User  where passport = :pss")
                .setParameter("pss", passport).list();
        session.close();
        return users;
    }

    @Override
    public String update(int age, String name, long passport, long newPassport) {
        int i = session.createQuery("update from User set passport = :np where name = :nm and " +
                "age = :a and  passport = :pss").setParameter("np", newPassport)
                .setParameter("nm", name).setParameter("a", age)
                .setParameter("pss", passport).executeUpdate();
        session.close();
        return i > 0 ? new Gson().toJson(new User(name, age, newPassport)) : "This user was't found";
    }

    @Override
    public String update(String name, int age, long passport, int newAge) {
        int i = session.createQuery("update from User set age = :np where name = :nm and " +
                "age = :a and  passport = :pss").setParameter("np", newAge)
                .setParameter("nm", name).setParameter("a", age)
                .setParameter("pss", passport).executeUpdate();
        session.close();
        return i > 0 ? new Gson().toJson(new User(name, newAge, passport)) : "This user was't found";
    }

    @Override
    public void delete(User user) {
        session.createQuery("delete from User where name = :nm and  age = :ag and passport = :pss")
                .setParameter("nm", user.getName()).setParameter("ag", user.getAge())
                .setParameter("pss", user.getPassport()).executeUpdate();
        session.close();
    }
}
