package dbService;

import dao.AddressDataDAO;
import dao.PhoneDataDAO;
import dao.UsersDAOHibernate;
import dataSet.AddressDataSet;
import dataSet.PhoneDataSet;
import dataSet.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService,AutoCloseable {

    private final SessionFactory sessionFactory;

    public DBServiceHibernateImpl(){
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:file:./h2db/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    public DBServiceHibernateImpl(Configuration configuration){
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    public void save(UserDataSet dataSet) throws SQLException {
        try (Session session = sessionFactory.openSession()){
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            dao.save(dataSet);
            }

    }

    @Override
    public UserDataSet read(long id, Class<UserDataSet> userDataSetClass) throws SQLException {
        return runInSession(session -> {
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            return dao.read(id);
        });
    }

    public UserDataSet read(long id)  {
        return runInSession(session -> {
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            return dao.read(id);
        });
    }

    public List<UserDataSet> readAllUsers(){
        return runInSession(session -> {
            UsersDAOHibernate dao = new UsersDAOHibernate(session);
            return dao.readAll();
        });
    }

    public List<PhoneDataSet> readAllPhones(){
        return runInSession(session -> {
            PhoneDataDAO dao = new PhoneDataDAO(session);
            return dao.readAll();
        });
    }

    public List<AddressDataSet> readAllAddresses(){
        return runInSession(session -> {
            AddressDataDAO dao = new AddressDataDAO(session);
            return dao.readAll();
        });
    }

    public void shutdown() {
        sessionFactory.close();
    }

    private <R> R runInSession(Function<Session, R> function){
        try (Session session = sessionFactory.openSession()){
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
