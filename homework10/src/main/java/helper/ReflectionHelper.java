package helper;

import dataSet.DataSet;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReflectionHelper {
    public static <T> Field getField(T object, String name) throws SQLException {
        Field field = null;
        try {
            try {
                field = object.getClass().getDeclaredField(name);
            } catch (Exception e) {
                field = object.getClass().getSuperclass().getDeclaredField(name);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return field;
    }

    public static <T> Field[] getObjectFields(T o) {
        Field[] fields = o.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        return fields;
    }

    public static <T extends DataSet> String getSqlStringCreate(T o) {
        Class<?> oClass = o.getClass();
        Field[] fields = oClass.getDeclaredFields();
        final String className = oClass.getSimpleName();

        String fieldNames = Arrays.stream(fields)
                .map(s -> s.getName())
                .collect(Collectors.joining(", ", "(", ")"));
        String params = Arrays.stream(fields)
                .map(s -> "?")
                .collect(Collectors.joining(", ", "(", ")"));
        return "insert into " + className + fieldNames + " values " + params;
    }

    public static <T extends DataSet> String getSqlStringUpdate(T o) {
        Class<?> oClass = o.getClass();
        Field[] fields = oClass.getDeclaredFields();
        final String className = oClass.getSimpleName();

        String fieldNames = Arrays.stream(fields)
                .map(s -> s.getName() + " = ?")
                .collect(Collectors.joining(", "));

        return "update " + className + " set " + fieldNames + " where ID = ?";
    }
}
