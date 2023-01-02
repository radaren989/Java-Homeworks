package project2;

public class Warp {
    public int x, y, time;
    public boolean isActive, delete;

    Warp(int x, int y) {
        this.x = x;
        this.y = y;
        this.isActive = false;
        this.delete = false;
    }

    int[] checkSquare(char[][] map, Computer[] computers, ClassNumber[] numbers) {
        int[] ret = new int[2];
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                int counter = 0;
                if (i == Player.cursorX && j == Player.cursorY) {
                    if (!isActive) {
                        isActive = true;
                        time = 25;
                    }
                } else if (isActive && map[i][j] == '4' || map[i][j] == '5') {
                    counter = 0;
                    while (true) {
                        if (numbers[counter] != null && numbers[counter].x == i
                                && numbers[counter].y == j) {
                            if (map[i][j] == '4')
                                Player.score += 50;
                            else
                                Player.score += 150;
                            numbers[counter] = null;
                            map[i][j] = ' ';
                            ret[0] = i;
                            ret[1] = j;
                            break;
                        }
                        counter++;

                    }
                } else if (isActive && map[i][j] == '1' || map[i][j] == '2'
                        || map[i][j] == '3') {
                    if (map[i][j] == '1')
                        Player.score += 1;
                    else if (map[i][j] == '2')
                        Player.score += 5;
                    else
                        Player.score += 15;
                    map[i][j] = ' ';
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }
        if (isActive && time == 0)
            delete = true;
        if (ret[0] == 0)
            return null;
        else
            return ret;
    }
}
