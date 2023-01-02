public class Soru2 {
    Soru2() {
        Queue q1 = new Queue(10);
        Queue q2 = new Queue(10);

        q1.enqueue(2);
        q1.enqueue(4);
        q1.enqueue(5);
        q1.enqueue(8);
        q1.enqueue(10);
        q1.enqueue(12);
        q1.enqueue(16);

        q2.enqueue(2);
        q2.enqueue(6);
        q2.enqueue(8);
        q2.enqueue(9);
        q2.enqueue(16);
        q2.enqueue(18);
        q2.enqueue(20);
        q2.enqueue(22);

        System.out.println("BEFORE MERGE");
        System.out.print("Q1:");
        for (int i = 0; i < q1.size(); i++) {
            System.out.print(q1.peek() + " ");
            q1.enqueue(q1.dequeue());
        }
        System.out.println();
        System.out.print("Q2:");
        for (int i = 0; i < q2.size(); i++) {
            System.out.print(q2.peek() + " ");
            q2.enqueue(q2.dequeue());
        }

        Queue q3 = merge(q1, q2);

        System.out.println();
        System.out.println("AFTER MERGE");
        System.out.print("Q1:");
        for (int i = 0; i < q1.size(); i++) {
            System.out.print(q1.peek() + " ");
            q1.enqueue(q1.dequeue());
        }
        System.out.println();
        System.out.print("Q2:");
        for (int i = 0; i < q2.size(); i++) {
            System.out.print(q2.peek() + " ");
            q2.enqueue(q2.dequeue());
        }
        System.out.println();
        System.out.print("Q2:");
        for (int i = 0; i < q3.size(); i++) {
            System.out.print(q3.peek() + " ");
            q3.enqueue(q3.dequeue());
        }

    }

    Queue merge(Queue q1, Queue q2) {
        Queue temp = new Queue(20);
        int counter1 = 0, counter2 = 0;

        while (counter1 != q1.size() && counter2 != q2.size()) {
            if ((int) q1.peek() < (int) q2.peek()) {
                temp.enqueue(q1.peek());
                q1.enqueue(q1.dequeue());
                counter1++;
            } else if ((int) q1.peek() > (int) q2.peek()) {
                temp.enqueue(q2.peek());
                q2.enqueue(q2.dequeue());
                counter2++;
            } else {
                temp.enqueue(q1.peek());
                q1.enqueue(q1.dequeue());
                q2.enqueue(q2.dequeue());
                counter1++;
                counter2++;
            }
        }
        while (counter2 != q2.size()) {
            temp.enqueue(q2.peek());
            q2.enqueue(q2.dequeue());
            counter2++;
        }

        while (counter1 != q1.size()) {
            temp.enqueue(q1.peek());
            q1.enqueue(q1.dequeue());
            counter1++;
        }

        Queue q3 = new Queue(temp.size());
        while (!temp.isEmpty()) {
            q3.enqueue(temp.dequeue());
        }

        return q3;
    }
}
