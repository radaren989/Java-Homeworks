import java.util.Random;

public class Project {
    public static void main(String[] args) throws InterruptedException {
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        // To pass variables by reference
        int[] score1 = { 0 };
        int[] score2 = { 0 };

        SingleLinkedList scoreTableList = FileInteractions.readFile("HighscoreTable.txt");
        int counter = 1;
        while (counter != 11) {
            throwDice(list1);
            throwDice(list2);

            System.out.println("\nTURN " + counter);
            System.out.printf("Player1: %-40s  Score: %3s \n", list1.returnList(), score1[0]);
            System.out.printf("Player2: %-40s  Score: %3s \n", list2.returnList(), score2[0]);
            String beforeList1 = list1.returnList();
            String beforeList2 = list2.returnList();

            checkSame(list1, "0", score1);
            checkSame(list2, "0", score2);
            checkDiff(list1, score1);
            checkDiff(list2, score2);

            if (!beforeList1.equals(list1.returnList()) || !beforeList2.equals(list2.returnList())) {
                System.out.println("-------------------------------------------------------------");
                System.out.printf("Player1: %-40s  Score: %3s \n", list1.returnList(), score1[0]);
                System.out.printf("Player2: %-40s  Score: %3s \n", list2.returnList(), score2[0]);
            }

            counter++;
            Thread.sleep(1000);
        }
        scoreTableList.sortedInsert("player " + Math.max(score1[0], score2[0]));
        String[] scoreStr = scoreTableList.returnList().split(" ");

        System.out.println("\nHigh Score Table");

        for (int i = 0; i < 20; i += 2) {
            System.out.printf("%-6s %-10s\n", scoreStr[i], scoreStr[i + 1]);
        }

        FileInteractions.writeFile("new.txt", scoreTableList);

    }

    static void throwDice(SingleLinkedList list) {
        Random rnd = new Random();
        for (int i = 0; i < 3; i++) {
            int random = rnd.nextInt(1, 7);
            list.insert(random);
        }
    }

    static void checkSame(SingleLinkedList list, String num, int[] score) {
        String listStr = list.returnList();
        int amount = listStr.length() - listStr.replaceAll(num, "").length();
        if (amount >= 4) {
            for (int i = 0; i < 4; i++) {
                list.remove(Integer.parseInt(num));
            }
            score[0] += 10;
        }
        if (Integer.parseInt(num) != 6) {
            String nextNum = String.valueOf(Integer.parseInt(num) + 1);
            checkSame(list, nextNum, score);
        }
    }

    static void checkDiff(SingleLinkedList list, int[] score) {
        String str = list.returnList();
        for (int i = 1; i < 7; i++) {
            if (!str.contains(String.valueOf(i)))
                return;
        }

        for (int i = 1; i < 7; i++) {
            list.remove(i);
        }

        score[0] += 30;
    }

}
