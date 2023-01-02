package project2;

public class Trap {
    public int x, y, time;
    public boolean isActive, delete;

    Trap(int x, int y) {
        this.x = x;
        this.y = y;
        this.isActive = false;
        this.delete = false;
    }

    void checkSquare(char[][] map, Computer[] computers, ClassNumber[] numbers) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                int counter = 0;
                if (i == Player.cursorX && j == Player.cursorY) {
                    if (!isActive) {
                        isActive = true;
                        time = 25;
                    }
                } else if (isActive && map[i][j] == 'C') {
                    while (true) {
                        if (computers[counter] != null && computers[counter].x == i && computers[counter].y == j) {
                            if (computers[counter].freeze == 0)
                                computers[counter].freeze = 25;
                            break;
                        }
                        counter++;
                    }
                } else if (isActive && (map[i][j] == '4' || map[i][j] == '5')) {
                    while (true) {
                        if (numbers[counter] != null && numbers[counter].x == i && numbers[counter].y == j) {
                            if (numbers[counter].freeze == 0)
                                numbers[counter].freeze = 25;
                            break;
                        }
                        counter++;

                    }
                }
            }
        }
        if (isActive && time == 0)
            delete = true;
    }
}
