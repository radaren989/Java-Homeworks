public class Soru5 {
    Soru5() {
        Queue q1 = new Queue(8);
        Queue q1temp = new Queue(8);
        Queue q2 = new Queue(8);
        Queue q2temp = new Queue(8);
        Queue q3 = new Queue(8);
        Queue q3temp = new Queue(8);

        for (int i = 0; i < 8; i++) {
            int a = (int) (Math.random() * 15 + 1);
            int b = (int) (Math.random() * 15 + 1);

            q1.enqueue(a);
            q2.enqueue(b);
        }

        while (!q1.isEmpty()) {
            q3.enqueue((int) q1.peek() - (int) q2.peek());
            q1temp.enqueue(q1.dequeue());
            q2temp.enqueue(q2.dequeue());
        }

        while (!q1temp.isEmpty()) {
            q1.enqueue(q1temp.dequeue());
            q2.enqueue(q2temp.dequeue());
        }

        while (!q3.isEmpty()) {
            System.out.print(q3.peek() + " ");
            q3temp.enqueue(q3.dequeue());
        }

        while (!q3temp.isEmpty()) {
            q3.enqueue(q3temp.dequeue());
        }
    }
}
