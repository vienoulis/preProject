package usersDAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class UserHibernateDAO implements UserDao {
    private static final Configuration configuration = DBHelper.getConfiguration();
    private static final SessionFactory sessionFactory = createSessionFactory();

    public UserHibernateDAO() {
    }

    private static SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings( configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("From User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    @Override
    public void delete(long userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete  from User where id = :id")
                .setParameter("id", userId)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("from User where id = :id")
                .setParameter("id", id).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void update(long userId, String name, String age, String passport) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery("update User set name = :nm, age = :ag, passport = :p where  id = :i");
            q.setParameter("nm", name);
            q.setParameter("ag", Integer.parseInt(age));
            q.setParameter("p", Long.parseLong(passport));
            q.setParameter("i", userId);
            q.executeUpdate();
            transaction.commit();
        }
    }
}
