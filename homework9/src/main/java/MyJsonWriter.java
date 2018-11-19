import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyJsonWriter {
    public static String toJsonString(Object object){
        return convertToJsonObject(object).build().toString();
    }
    private static JsonObjectBuilder convertToJsonObject(Object object){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        List<Field>  fields = getFields(object.getClass());
        for (Field field:fields){
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Class type = field.getType();
            if (value == null){
                builder.addNull(field.getName());
            } else if (type.isPrimitive()) {
                if (value instanceof Byte) {
                    builder.add(field.getName(), (Byte) value);
                } else if (value instanceof Integer) {
                    builder.add(field.getName(), (Integer) value);
                } else if (value instanceof Short) {
                    builder.add(field.getName(), (Short) value);
                } else if (value instanceof Long) {
                    builder.add(field.getName(), (Long) value);
                } else if (value instanceof Float) {
                    builder.add(field.getName(), (Float) value);
                } else if (value instanceof Double) {
                    builder.add(field.getName(), (Double) value);
                } else if (value instanceof Boolean) {
                    builder.add(field.getName(), (Boolean) value);
                } else if (value instanceof Character) {
                    builder.add(field.getName(), (Character) value);
                }
            } else if (value instanceof String){
                builder.add(field.getName(), (String) value);
            } else if (type.isArray()){
                JsonArrayBuilder arrayBuilder = convertArray((Object []) value);
                builder.add(field.getName(),arrayBuilder);
            } else if (Collection.class.isAssignableFrom(type)){
                value = ((Collection) value).toArray();
                JsonArrayBuilder arrayBuilder = convertArray((Object[]) value);
                builder.add(field.getName(), arrayBuilder);
            } else if (value instanceof Object){
                builder.add(field.getName(), convertToJsonObject(value));
            }
        }
        return builder;
    }

    private static JsonArrayBuilder convertArray(Object[] value) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object o : value){
            arrayBuilder.add(convertToJsonObject(o));
        }
        return  arrayBuilder;
    }

    private static List<Field> getFields(Class<?> aClass) {
        return new ArrayList<Field>(Arrays.asList(aClass.getDeclaredFields()));
    }
}
