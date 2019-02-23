package ru.otus.hw14.dbService;

import org.springframework.stereotype.Component;
import ru.otus.hw14.dataSet.PhoneDataSet;
import ru.otus.hw14.dataSet.UserDataSet;
import ru.otus.hw14.dataSet.AddressDataSet;

import java.sql.SQLException;
import java.util.List;

@Component
public interface DBService extends AutoCloseable {

    void save(UserDataSet dataSet) throws SQLException;

    UserDataSet read(long id, Class<UserDataSet> userDataSetClass) throws SQLException;

    UserDataSet read(long id);

    List<UserDataSet> readAllUsers();

    List<PhoneDataSet> readAllPhones();

    List<AddressDataSet> readAllAddresses();

    void shutdown();

    long count();
}
