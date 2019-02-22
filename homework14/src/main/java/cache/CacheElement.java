package cache;

class CacheElement<K, V> {

    private final K key;
    private final V value;
    private final long creationTime;
    private long lastAccessTime;


    CacheElement(K key, V value) {
        this.key = key;
        this.value = value;
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    long getCreationTime() {
        return creationTime;
    }

    long getLastAccessTime() {
        return lastAccessTime;
    }

    void setAccessed() {
        lastAccessTime = getCurrentTime();
    }
}