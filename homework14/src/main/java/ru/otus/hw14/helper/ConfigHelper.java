package ru.otus.hw14.helper;

import org.hibernate.cfg.Configuration;
import ru.otus.hw14.dataSet.PhoneDataSet;
import ru.otus.hw14.dataSet.UserDataSet;
import ru.otus.hw14.dataSet.AddressDataSet;

public class ConfigHelper {
    Configuration configuration;
    public Configuration config(Configuration configuration) {
        this.configuration = configuration;
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        return configuration;
    }
}
