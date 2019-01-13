package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionHelper {
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
