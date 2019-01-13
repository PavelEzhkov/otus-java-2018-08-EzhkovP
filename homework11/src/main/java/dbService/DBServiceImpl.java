package dbService;

import dao.UsersDAO;
import dataSet.UserDataSet;
import helper.H2ConnectionHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBServiceImpl implements DBService,AutoCloseable {
    private static final String CREATE_TABLE = "create table if not exists UserDataSet (ID bigint(20) NOT NULL auto_increment, name varchar(256), age int(3))";
    private final Connection connection;
    private UsersDAO dao;

    public DBServiceImpl() {
        this.connection = H2ConnectionHelper.getConnection();
        dao = new UsersDAO(H2ConnectionHelper.getConnection());
    }

    public void prepareTables() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println("Table doesn't create");
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed. Bye!");
    }

    @Override
    public void save(UserDataSet user1) throws SQLException {
        dao.save(user1);
    }

    @Override
    public UserDataSet read(long id, Class<UserDataSet> userDataSetClass) throws SQLException {
        return dao.load(id, userDataSetClass);
    }

}