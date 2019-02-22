package dbService;



import cache.CacheEngine;
import dao.AddressDataDAO;
import dao.PhoneDataDAO;
import dao.UsersDAOHibernate;
import dataSet.AddressDataSet;
import dataSet.PhoneDataSet;
import dataSet.UserDataSet;
import helper.ConfigHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    private CacheEngine<Long, UserDataSet> cache;


    public DBServiceHibernateImpl(CacheEngine<Long, UserDataSet> cache) {
        Configuration configuration = new Configuration();
        configuration = new ConfigHelper().config(configuration);
        sessionFactory = createSessionFactory(configuration);
        this.cache= cache;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    public void save(UserDataSet dataSet) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.save(dataSet);
            cache.put(dataSet.getId(), dataSet);
        }

    }

    @Override
    public UserDataSet read(long id, Class<UserDataSet> userDataSetClass) throws SQLException {
        UserDataSet user = cache.get(id);
        if (user==null) {
            return runInSession(session -> {
                UsersDAOHibernate dao = new UsersDAOHibernate(session);
                return dao.read(id);
            });
        }
        return user;
    }

    public UserDataSet read(long id) {
        return runInSession(session -> {
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            return dao.read(id);
        });
    }

    public List<UserDataSet> readAllUsers() {
        return runInSession(session -> {
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            return dao.readAll();
        });
    }

    public List<PhoneDataSet> readAllPhones() {
        return runInSession(session -> {
            PhoneDataDAO dao = new PhoneDataDAO(session);
            return dao.readAll();
        });
    }

    public List<AddressDataSet> readAllAddresses() {
        return runInSession(session -> {
            AddressDataDAO dao = new AddressDataDAO(session);
            return dao.readAll();
        });
    }

    public void shutdown() {
        sessionFactory.close();
    }

    @Override
    public long count() {
        return runInSession(session -> {
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            return dao.count();
        });
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;

        }
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
