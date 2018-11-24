package DataBase;


import DataSet.*;
import java.lang.reflect.Field;
import java.sql.*;

public class Executor {
    private static final String INSERT_USER = "insert into users (name, age) values (?,?)";
    private static final String UPDATE_USER = "update users set name = ?, age = ? where id = ?";
    private static final String SELECT_USER = "SELECT * FROM users WHERE id = ?";
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }


    public <T extends DataSet> void save(T user) throws SQLException {
        if (user.getId() <= 0) {
            create(user);
        } else {
            update(user);
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        T type = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                final String name = set.getString(2);
                final int age = set.getInt(3);

                type = clazz.getConstructor(long.class, String.class, int.class)
                        .newInstance(id, name, age);
            }
        } catch (Exception e) {
            new SQLException(e);
        }
        return type;
    }


    private <T extends DataSet> void create(T user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, (String) getParams(user, "name"));
            statement.setInt(2, (Integer) getParams(user, "age"));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("User ID not generated");
                }
            }
            System.out.println("User " + getParams(user, "name") + " saved!");
        }
    }

    private <T extends DataSet> void update(T user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, (String) getParams(user, "name"));
            statement.setInt(2, (Integer) getParams(user, "age"));
            statement.setLong(3, (Long) getParams(user, "id"));
            statement.executeUpdate();
            System.out.println("User " + getParams(user, "name") + " overwrite!");
        }
    }

    private Object getParams(Object object, String name) throws SQLException {
        Object parameter;
        try {
            Field field = ObjHelper.getField(object, name);
            field.setAccessible(true);
            parameter = field.get(object);
        } catch (Exception e) {
            throw new SQLException();
        }
        return parameter;
    }
}
