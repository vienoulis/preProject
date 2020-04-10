package usersDAO;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDao {
    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("From User").list();
            transaction.commit();
            return users;
        }
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void delete(long userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete  from User where id = :id")
                    .setParameter("id", userId)
                    .executeUpdate();
            transaction.commit();
        }

    }

    @Override
    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = (User) session.createQuery("from User where id = :id")
                    .setParameter("id", id).uniqueResult();
            session.close();
            return user;
        }

    }

    @Override
    public void update(long userId, String name, String age, String passport, String password, String role) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery("update User set name = :nm, age = :ag, passport = :pp, password = :pw, role = :rl where  id = :i");
            q.setParameter("nm", name);
            q.setParameter("ag", Integer.parseInt(age));
            q.setParameter("pp", Long.parseLong(passport));
            q.setParameter("pw", password);
            q.setParameter("rl", role);
            q.setParameter("i", userId);
            q.executeUpdate();
            transaction.commit();
        }
    }
}
