public class s1 {
    public static void main(String[] args) {
        Stack s = new Stack(6);
        s.push("a");
        s.push("n");
        s.push("k");
        s.push("a");
        s.push("r");
        s.push("a");
        Stack s2 = new Stack(6);
        s2.push("n");
        s2.push("a");
        s2.push("a");
        s2.push("k");
        s2.push("r");
        s2.push("a");
        Stack temp = new Stack(6);
        int s1size = s.size();
        for (int i = 0; i < s1size; i++) {
            while (!s2.isEmpty()) {
                if (s.peek().equals(s2.peek())) {
                    s.pop();
                    s2.pop();
                } else {
                    temp.push(s2.pop());
                }
            }
            while (!temp.isEmpty()) {
                s2.push(temp.pop());
            }
        }
        if (s2.isEmpty() && s.isEmpty())
            System.out.println("anagram");
        else
            System.out.println("not");

    }
}
