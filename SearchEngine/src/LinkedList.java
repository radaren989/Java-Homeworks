public class LinkedList<K, V> {
    private int numberOfEntries;
    private Node<K, V> head;

    LinkedList() {
        this.head = null;
        this.numberOfEntries = 0;
    }

    // adds new elements to the front
    public void add(Node<K, V> newNode) {
        if (head == null)
            head = newNode;

        else {
            newNode.setNext(head);
            head = newNode;
        }
        numberOfEntries++;
    }

    public ListIterator<K, V> iterator() {
        Node<K, V> temp = new Node<>(null, null);
        temp.setNext(head);
        return new ListIterator<>(temp);
    }

    public boolean isEmpty() {
        return numberOfEntries < 1;
    }

    public int size() {
        return numberOfEntries;
    }

    public Node<K, V> getHead() {
        return head;
    }
}
