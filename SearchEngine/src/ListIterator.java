//Normally an iterrator would have more methods but these are only neccessary methods for my project
public class ListIterator<K, V> {
    private Node<K, V> currNode;

    ListIterator(Node<K, V> head) {
        this.currNode = head;
    }

    public Node<K, V> next() {
        if (currNode.getNext() != null)
            currNode = currNode.getNext();

        return currNode;
    }

    public boolean hasNext() {
        return currNode.getNext() != null;
    }

    public Node<K, V> getNext() {
        return currNode.getNext();
    }
}
