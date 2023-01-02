public class SingleLinkedList {

    class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head = null;

    public void insert(Object data) {
        Node newNode = new Node(data);

        if (head == null)
            head = newNode;

        else {
            Node temp = head;

            while (temp.next != null)
                temp = temp.next;

            temp.next = newNode;
        }
    }

    public void sortedInsert(Object data) {
        Node newNode = new Node(data);

        if (head == null)
            head = newNode;

        else {
            Node temp = head;
            Node prev = null;

            if (Integer.parseInt(newNode.data.toString().split(" ")[1]) > Integer
                    .parseInt(head.data.toString().split(" ")[1])) {
                newNode.next = head;
                head = newNode;
                return;
            }
            while (temp != null && Integer.parseInt(temp.data.toString().split(" ")[1]) > Integer
                    .parseInt(newNode.data.toString().split(" ")[1])) {
                prev = temp;
                temp = temp.next;
            }

            if (temp == null) {
                prev.next = newNode;
            } else {
                newNode.next = temp;
                prev.next = newNode;
            }
        }
    }

    public void remove(Object dataDelete) {

        if (head == null)
            System.out.println("Listis empty!");

        else {
            if (head.data.equals(dataDelete)) {
                head = head.next;
                return;
            }

            Node temp = head;
            Node prev = null;
            while (temp != null) {

                if (temp.data.equals(dataDelete)) {
                    prev.next = temp.next;
                    temp = prev;
                    return;
                }

                prev = temp;
                temp = temp.next;
            }

        }

    }

    public String returnList() {
        Node temp = head;
        String str = "";

        while (temp != null) {
            str += temp.data.toString() + " ";

            temp = temp.next;
        }

        return str;
    }

    public int size() {
        Node temp = head;
        int counter = 0;

        while (temp != null) {
            counter++;
            temp = temp.next;
        }

        return counter;
    }

    public void print() {
        int counter = 1, longest = 0, conNum = 0;
        Node temp = head.next;

        while (temp != null) {
            while (temp.next != null && (int) temp.data == (int) temp.next.data) {
                counter++;
                temp = temp.next;
            }
            if (counter > longest) {
                longest = counter;
                conNum = (int) temp.data;
            }
            temp = temp.next;
            counter = 1;
        }

        System.out.println("Number: " + conNum);
        System.out.println("Number of con: " + longest);

    }

    public boolean isCons() {
        boolean flag = true;
        Node temp = head;
        while (temp != null) {
            while (temp.next != null && temp.data != temp.next.data) {
                flag = false;
                break;
            }
            temp = temp.next;
        }
        return flag;
    }

    public void change(Object x, Object y) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == x)
                temp.data = y;
            if (temp.data == y)
                temp.data = x;
            temp = temp.next;
        }
    }
}
