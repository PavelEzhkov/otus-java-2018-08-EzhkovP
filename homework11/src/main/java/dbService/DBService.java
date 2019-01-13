package dbService;

import dataSet.UserDataSet;

import java.sql.SQLException;


public interface DBService {

    void save(UserDataSet dataSet) throws SQLException;

    UserDataSet read(long id, Class<UserDataSet> userDataSetClass) throws SQLException;
}
