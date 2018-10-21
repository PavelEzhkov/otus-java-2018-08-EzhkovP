import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class CacheEngine<K, V> implements CacheEngineInterface<K,V> {

    private Map<K, SoftReference<V>> elements = new HashMap<>();

    @Override
    public void put(K key, V value) {
        elements.keySet().removeIf(keys -> elements.get(key) == null);
        if (value == null){
            elements.remove(key);}
            else elements.put(key, new SoftReference<>(value));
    }

    @Override
    public V get(K key) {
        SoftReference<V> reference = elements.get(key);
        if (reference == null){
            return null;
        }
        System.out.println("Value is: " + reference.get());
        return reference.get();
    }

}
