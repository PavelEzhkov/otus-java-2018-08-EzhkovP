package handler;



import helper.ReflectionHelper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Handler<T> implements ResultSetHandler<T> {

    Class<? extends T> type;

    public Handler(Class<? extends T> type) {
        this.type = type;
    }

    public T handle(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            T instance = newInstance(type);
            return populateObject(rs, instance);
        }
        return null;
    }

    private T newInstance(Class<? extends T> type) throws SQLException {
        try {
            return type.getConstructor().newInstance();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private T populateObject(final ResultSet rs, T instance) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        try {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Field field = ReflectionHelper.getField(instance, metaData.getColumnName(i).toLowerCase());
                field.setAccessible(true);
                field.set(instance, rs.getObject(i));
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return instance;
    }

}
