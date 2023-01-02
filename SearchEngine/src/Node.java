public class Node<K, V> {
    private K key;
    private V value;
    private Node<K, V> nextNode;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.nextNode = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setNext(Node<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<K, V> getNext() {
        return this.nextNode;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
