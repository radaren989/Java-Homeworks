
public class Entry<K, V> {
    private K key;
    private V value;

    Entry(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

}
