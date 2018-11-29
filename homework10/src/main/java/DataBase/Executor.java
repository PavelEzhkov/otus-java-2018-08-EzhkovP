package DataBase;


import DataSet.*;
import Handler.*;

import java.lang.reflect.Field;
import java.sql.*;

public class Executor {
    private static final String INSERT_USER = "insert into users (name, age) values (?,?)";
    private static final String UPDATE_USER = "update users set name = ?, age = ? where ID = ?";
    private static final String SELECT_USER = "SELECT * FROM users WHERE ID = ?";
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }


    public <T extends DataSet> void save(T user) throws SQLException {
        if (user.getId() <= 0) {
            create(INSERT_USER, user, "name", "age");
        } else {
            update(UPDATE_USER, user, "name", "age", "id");
        }
    }


    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        ResultSetHandler<T> handler = new Handler<T>(clazz);
        return query(SELECT_USER, handler, id);
    }

    private <T extends DataSet> T query(String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepare(statement, params);
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        }
    }


    private <T extends DataSet> void create(String sql, T user, String... names) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepare(statement, getParams(user, names));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("User ID not generated");
                }
            }
        }
    }

    private <T extends DataSet> void update(String sql, T user, String... names) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepare(statement, getParams(user, names));
            statement.executeUpdate();
        }
    }


    private Object[] getParams(Object object, String[] names) throws SQLException {
        Object[] parameters = new Object[names.length];
        try {
            for (int i = 0; i < names.length; i++) {
                Field field = ObjHelper.getField(object, names[i]);
                field.setAccessible(true);
                parameters[i] = field.get(object);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return parameters;
    }

    private void prepare(PreparedStatement statement, Object... params) throws SQLException {
        ParameterMetaData metadata = statement.getParameterMetaData();
        int count = metadata.getParameterCount();
        if (count != params.length) {
            throw new SQLException("Wrong number of parameters");
        }
        for (int i = 0; i < count; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}
