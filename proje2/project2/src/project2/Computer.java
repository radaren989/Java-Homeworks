package project2;

import java.util.Random;

import enigma.core.Enigma;

public class Computer {
	public static int score = 0;
	public static enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
	public int x, y, freeze;

	Computer(int x, int y) {
		this.x = x;
		this.y = y;
		this.freeze = 0;
	}

	void setScore(char eaten, int x, int y, ClassNumber[] numbers, Trap[] traps, char[][] map) {
		int counter = 0;
		switch (eaten) {
		case '1':
			score += 2;
			break;
		case '2':
			score += 10;
			break;
		case '3':
			score += 30;
			break;
		case '4':
			while (true) {
				if (numbers[counter] != null && numbers[counter].x == x && numbers[counter].y == y) {
					numbers[counter] = null;
					break;
				}
				counter++;
			}
			score += 100;
			break;
		case '5':
			while (true) {
				if (numbers[counter] != null && numbers[counter].x == x && numbers[counter].y == y) {
					numbers[counter] = null;
					break;
				}
				counter++;
			}
			score += 300;
			break;
		case '=':
			score += 300;
			break;
		case '*':
			score += 300;
			break;
		case 'P':
			randomPlayerLocation(map);
			break;
		}
	}

	void move(char[][] map, ClassNumber[] numbers, Trap[] traps) {
		boolean flag = true;
		Random rnd = new Random();
		int counter = 0;
		while (freeze == 0 && flag) {
			int random = -1;

			if (map[x][y + 1] != '#' && map[x][y + 1] != 'C' && map[x][y + 1] != ' ') {
				map[x][y] = ' ';
				this.y++;
				setScore(map[x][y], x, y, numbers, traps, map);

				if (map[x][y] == 'P') {
					Player.life--;
					if (Player.life == 0) {
						Player.GameEnd = true;
					}
				}
				map[x][y] = 'C';

				flag = false;

			} else if (map[x - 1][y] != '#' && map[x - 1][y] != 'C' && map[x - 1][y] != ' ') {
				map[x][y] = ' ';
				this.x--;
				setScore(map[x][y], x, y, numbers, traps, map);

				if (map[x][y] == 'P') {
					Player.life--;
					if (Player.life == 0) {
						Player.GameEnd = true;
					}
				}
				map[x][y] = 'C';
				flag = false;

			} else if (map[x + 1][y] != '#' && map[x + 1][y] != 'C' && map[x + 1][y] != ' ') {
				map[x][y] = ' ';
				this.x++;
				setScore(map[x][y], x, y, numbers, traps, map);

				if (map[x][y] == 'P') {
					Player.life--;
					if (Player.life == 0) {
						Player.GameEnd = true;
					}
				}
				map[x][y] = 'C';
				flag = false;

			} else if (map[x][y - 1] != '#' && map[x][y - 1] != 'C' && map[x][y - 1] != ' ') {
				map[x][y] = ' ';
				this.y--;
				setScore(map[x][y], x, y, numbers, traps, map);

				if (map[x][y] == 'P') {
					Player.life--;
					if (Player.life == 0) {
						Player.GameEnd = true;
					}
				}
				map[x][y] = 'C';
				flag = false;
			}
			if (flag && random == -1) {
				random = rnd.nextInt(4);
			}
			if (counter == 10)
				break;
			switch (random) {
			case 0:
				if (map[x + 1][y] == ' ') {
					map[x][y] = ' ';
					this.x++;
					map[x][y] = 'C';
					flag = false;
				}
				break;
			case 1:
				if (map[x - 1][y] == ' ') {
					map[x][y] = ' ';
					this.x--;
					map[x][y] = 'C';
					flag = false;
				}
				break;
			case 2:
				if (map[x][y + 1] == ' ') {
					map[x][y] = ' ';
					this.y++;
					map[x][y] = 'C';
					flag = false;
				}
				break;
			case 3:
				if (map[x][y - 1] == ' ') {
					map[x][y] = ' ';
					this.y--;
					map[x][y] = 'C';
					flag = false;
				}
				break;
			}

		}
		if (freeze > 0)
			freeze--;

	}

	public static void randomPlayerLocation(char[][] map) {
		Random rnd = new Random();
		while (true) {
			int rndx = rnd.nextInt((52) + 1);
			int rndy = rnd.nextInt((20) + 1);
			if (map[rndx][rndy] == ' ') {
				map[rndx][rndy] = 'P';
				Player.cursorX = rndx;
				Player.cursorY = rndy;
				cn.getTextWindow().setCursorPosition(rndx, rndy);
				System.out.print("P");
				break;
			}
		}
	}
}