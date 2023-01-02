public class Soru1 {
    Soru1() {
        Stack s1 = new Stack(10);
        Stack s2 = new Stack(10);

        for (int i = 0; i < 10; i++) {
            int rnd = (int) (Math.random() * 20 + 1);

            while (!s1.isEmpty() && rnd > (int) s1.peek()) {
                s2.push(s1.pop());
            }

            s1.push(rnd);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        while (!s1.isEmpty()) {
            System.out.println(s1.peek());
            s2.push(s1.pop());
        }

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
}