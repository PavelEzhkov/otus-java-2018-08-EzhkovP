package DataSet;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class ObjHelper {
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
}
