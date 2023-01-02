public class s2 {
    public static void main(String[] args) {
        Queue q = new Queue(8);
        q.enqueue('E');
        q.enqueue('C');
        q.enqueue('E');
        q.enqueue('V');
        q.enqueue('E');
        q.enqueue('E');
        q.enqueue('G');
        q.enqueue('a');

        int maj = q.size() / 2, counter = 0;
        for (int i = 0; i < q.size(); i++) {
            Object maxC = q.dequeue();
            counter = 1;
            for (int j = 0; j < q.size(); j++) {
                Object a = q.dequeue();
                if (maxC.equals(a)) {
                    counter++;
                }
                q.enqueue(a);
            }
            q.enqueue(maxC);
            if (counter > maj) {
                System.out.println(maxC.toString());
                break;
            }
        }
    }
}