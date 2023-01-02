import java.io.FileWriter;
import java.io.IOException;

public class DoubleLinkedList {
    class Node {
        private Player data;
        private Node next;
        private Node prev;

        public Node(Player data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        public Object getData() {
            return this.data;
        }

        public void setData(Player data) {
            this.data = data;
        }

        public Node getPrev() {
            return this.prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;
    private Node tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addSorted(Player data) {
        Node newNode = new Node(data);
        float score = data.getScore();

        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            if (score > head.data.getScore()) {
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            } else if (tail.data.getScore() > score) {
                tail.setNext(newNode);
                newNode.setPrev(tail);
                tail = newNode;
            } else {
                Node temp = head;
                Node prev = head;

                while (score < temp.data.getScore()) {
                    prev = temp;
                    temp = temp.getNext();
                }

                prev.setNext(newNode);
                newNode.setPrev(prev);
                newNode.setNext(temp);
                temp.setPrev(newNode);
            }
        }
    }

    public String returnStr() {
        Node temp = head;
        String str = "";

        while (temp != null) {
            str += temp.data.toString() + " ";

            temp = temp.next;
        }

        return str;
    }

    public int size() {
        int counter = 0;

        Node temp = head;
        Node tempTail = tail;

        if (head != null && tail != null) {
            while (temp != tempTail) {
                counter += 2;
                temp = temp.next;
                tempTail = tempTail.prev;
            }
        }
        return counter;
    }

    public void displayHighScores() throws IOException { // displays the top10 highscores and also writes into txt
        Node temp = head; // pointer
        int counter = 1;
        FileWriter myWriter = new FileWriter("new.txt");
        
        System.out.println("----------------------------------------------");  // 46 - 
        System.out.printf("%10s %5s %10s ", "Name","Surname", "Score");
        System.out.println();
        System.out.println("----------------------------------------------");
        while (temp != null) {
            System.out.printf( "%10s %-8s %10s " ,((Player) (temp.getData())).getName(),((Player) (temp.getData())).getSurname()
                            ,((Player) (temp.getData())).getScore() );
            System.out.println();
            
            
            myWriter.write(((Player) (temp.getData())).getName() + " " + ((Player) (temp.getData())).getSurname() + " "
                    + ((Player) (temp.getData())).getScore() + "\n");

            temp = temp.getNext();

            if (counter == 10)
                break;
            counter++;
        }
        System.out.println("----------------------------------------------");
        

        myWriter.close();

    }

}
