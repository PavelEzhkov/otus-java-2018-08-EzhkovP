package dbService;

import dataSet.AddressDataSet;
import dataSet.PhoneDataSet;
import dataSet.UserDataSet;

import java.sql.SQLException;
import java.util.List;


public interface DBService extends AutoCloseable{

    void save(UserDataSet dataSet) throws SQLException;

    UserDataSet read(long id, Class<UserDataSet> userDataSetClass) throws SQLException;

    UserDataSet read(long id);

    List<UserDataSet> readAllUsers();

    List<PhoneDataSet> readAllPhones();

    List<AddressDataSet> readAllAddresses();

    void shutdown();
}
