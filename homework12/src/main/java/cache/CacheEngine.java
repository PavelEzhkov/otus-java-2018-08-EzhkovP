package cash;

public interface CacheEngine<K, V> {

    void put(CacheElement<K, V> element);

    void put(K key, V value);

    V get(K key);

}