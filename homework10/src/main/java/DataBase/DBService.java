package DataBase;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService implements AutoCloseable {
    private static final String CREATE_TABLE = "create table if not exists users (id bigint(20) NOT NULL auto_increment, name varchar(256), age int(3))";
    private final Connection connection;

    public DBService() {
        this.connection = H2ConnectionHelper.getConnection();
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
}
