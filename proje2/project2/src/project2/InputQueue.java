package project2;

import java.util.Random;

public class InputQueue {
    private static char[] charToSelect = new char[] { '1', '2', '3', '4', '5', '=', '*', 'C' };

    static void fillQueue(Queue queue) {
        for (int i = 0; i < 15; i++) {
            queue.enqueue(selectChar());
        }
    }

    static void putOne(Queue queue) {
        queue.enqueue(selectChar());
    }

    static char selectChar() {
        Random rnd = new Random();
        int random = rnd.nextInt(1, 41);
        if (random < 13) {
            return charToSelect[0];
        } else if (random >= 13 && random < 21) {
            return charToSelect[1];
        } else if (random >= 21 && random < 27) {
            return charToSelect[2];
        } else if (random >= 27 && random < 32) {
            return charToSelect[3];
        } else if (random >= 32 && random < 36) {
            return charToSelect[4];
        } else if (random >= 36 && random < 38) {
            return charToSelect[5];
        } else if (random >= 38 && random < 39) {
            return charToSelect[6];
        } else if (random <= 40) {
            return charToSelect[7];
        }
        return ' ';

    }
}
