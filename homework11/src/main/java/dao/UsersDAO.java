package dao;


import dataSet.*;
import handler.*;
import helper.ReflectionHelper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class UsersDAO {
    private final Connection connection;


    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public <T extends DataSet> void save(T user) throws SQLException {
        if (user.getId() <= 0) {
            create(user);
        } else {
            update(user);
        }
    }

    private <T extends DataSet> void update(T user) throws SQLException {
        Field[] fields = ReflectionHelper.getObjectFields(user);
        ArrayList<String> params = new ArrayList<>();
        for (Field field : fields) {
            params.add(field.getName());
        }
        params.add("id");
        final String sql = ReflectionHelper.getSqlStringUpdate(user);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepare(statement, getParams(user, params));
            statement.executeUpdate();
        }
    }


    public <T extends DataSet> void create(T user) throws SQLException {
        Field[] fields = ReflectionHelper.getObjectFields(user);
        ArrayList<String> params = new ArrayList<>();
        for (Field field : fields) {
            params.add(field.getName());
        }
        final String sql = ReflectionHelper.getSqlStringCreate(user);
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepare(statement, getParams(user, params));
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

    private void prepare(PreparedStatement statement, Object... params) throws SQLException {
        ParameterMetaData metaData = statement.getParameterMetaData();
        int count = metaData.getParameterCount();
        if (count != params.length) {
            throw new SQLException("Wrong number of parameters");
        }
        for (int i = 0; i < count; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    private Object[] getParams(Object object, ArrayList<String> names) throws SQLException {
        Object[] parameters = new Object[names.size()];
        try {
            for (int i = 0; i < names.size(); i++) {
                Field field = ReflectionHelper.getField(object, names.get(i));
                field.setAccessible(true);
                parameters[i] = field.get(object);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return parameters;
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        String SELECT_USER = "SELECT * FROM " + clazz.getSimpleName() + " WHERE id=?";
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
}
