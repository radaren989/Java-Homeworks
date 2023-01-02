public class SingleLinkedList {
    private Node head;

    public void add(Object data) {

        Node newNode = new Node(data);
        if (head == null)
            head = newNode;
        else {
            Node temp = head;
            while (temp.getLink() != null) {
                temp = temp.getLink();
            }
            temp.setLink(newNode);
        }
    }

    public int size() {
        int counter = 0;
        if (head == null)
            return 0;
        else {
            Node temp = head;

            while (temp != null) {
                temp = temp.getLink();
                counter++;
            }
        }
        return counter;
    }

    public void revert() {
        Node temp = head;
        Node prev = null;

        while (temp == null) {
            prev = temp;
            temp = temp.getLink();

            if(temp.getLink() == null)
            head = temp;
            else{
                temp.setLink(prev);
            }
        }
    }
}