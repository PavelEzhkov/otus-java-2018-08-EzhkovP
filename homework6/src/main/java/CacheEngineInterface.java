public interface CacheEngineInterface<K, V> {
    void put(K key, V value);
    V get(K key);
}
