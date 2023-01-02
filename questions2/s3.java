public class s3 {
    public static void main(String[] args) {
        Stack s1 = new Stack(11);
        s1.push(7);
        s1.push(8);
        s1.push(2);
        s1.push(8);
        s1.push(5);
        s1.push(2);
        s1.push(6);
        s1.push(7);
        s1.push(1);
        s1.push(4);
        s1.push(9);
        Stack temp1 = new Stack(11);
        Stack temp2 = new Stack(11);

        while (!s1.isEmpty()) {
            temp1.push(s1.pop());
            if (s1.peek() != null)
                temp2.push(s1.pop());
        }
        while (!temp1.isEmpty()) {
            if (temp2.peek() != null)
                s1.push(temp2.pop());
            s1.push(temp1.pop());
        }
        int size = s1.size();
        for (int i = 0; i < size; i++) {
            System.out.print(s1.pop());
        }

    }
}
