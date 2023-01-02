package project2;

import java.util.Random;

public class ClassNumber {
    int x, y, freeze;
    char c;

    ClassNumber(int x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.freeze = 0;
    }

    void move(char[][] map) {
        Random rnd = new Random();
        boolean flag = true;
        int counter = 0;
        while (freeze == 0 && flag) {
            if (counter == 10)
                break;
            int random = rnd.nextInt(4);
            switch (random) {
                case 0:
                    if (map[x + 1][y] == ' ') {
                        map[x][y] = ' ';
                        this.x++;
                        map[x][y] = this.c;
                        flag = false;
                    }
                    break;
                case 1:
                    if (map[x - 1][y] == ' ') {
                        map[x][y] = ' ';
                        this.x--;
                        map[x][y] = this.c;
                        flag = false;
                    }
                    break;
                case 2:
                    if (map[x][y + 1] == ' ') {
                        map[x][y] = ' ';
                        this.y++;
                        map[x][y] = this.c;
                        flag = false;
                    }
                    break;
                case 3:
                    if (map[x][y - 1] == ' ') {
                        map[x][y] = ' ';
                        this.y--;
                        map[x][y] = this.c;
                        flag = false;
                    }
                    break;
            }
            counter++;
        }
        if (freeze > 0)
            freeze--;
    }
}