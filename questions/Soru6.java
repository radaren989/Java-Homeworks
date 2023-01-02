public class Soru6 {
    Soru6() {
        Stack s1 = new Stack(10);
        Stack s2 = new Stack(10);
        Stack temp = new Stack(10);
        Stack s3 = new Stack(10);

        while (!s1.isFull()) {
            int a = (int) (Math.random() * 15 + 1);
            int b = (int) (Math.random() * 15 + 1);
            s1.push(a);
            s2.push(b);
        }

        while (!s1.isEmpty()) {
            int num = (int) s1.pop();
            while (!s2.isEmpty()) {
                temp.push(s2.pop());
                if (num == (int) temp.peek()) {
                    s3.push(temp.peek());
                    break;
                }
            }
            while (!temp.isEmpty()) {
                s2.push(temp.pop());
            }
        }

        while (!s3.isEmpty()) {
            System.out.println(s3.pop());
        }
    }
}
