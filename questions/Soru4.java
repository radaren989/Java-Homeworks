public class Soru4 {
    Soru4() {
        Stack s1 = new Stack(5);
        Stack s2 = new Stack(5);
        int output = 0;
        s1.push(1);
        s1.push(2);
        s1.push(8);
        s1.push(5);
        s1.push(3);

        while (!s1.isEmpty()) {
            int num = (int) s1.pop();

            while (!s1.isEmpty()) {
                output += Math.abs(num - (int) s1.peek());
                s2.push(s1.pop());
            }
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        System.out.println(output);
    }

}