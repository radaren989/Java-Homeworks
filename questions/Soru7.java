public class Soru7 {
    Soru7() {
        Queue q = new Queue(6);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        double median = 0;
        if (q.size() % 2 == 0) {
            int size = (q.size() - 2) / 2;
            for (int i = 0; i < size; i++) {
                q.dequeue();
            }
            for (int i = 0; i < 2; i++) {
                median += (int) q.dequeue();
            }
            median /= 2;
        } else {
            int size = (q.size() - 1) / 2;
            for (int i = 0; i < size; i++) {
                q.dequeue();
            }
            median = (int) q.dequeue();
        }

        System.out.println(median);
    }
}
