package project2;

import java.awt.event.KeyListener;
import enigma.core.Enigma;
import java.awt.event.KeyEvent;

public class Player {
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	public static enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
	public KeyListener klis;
	static int cursorX = 1, cursorY = 1;
	public static char send = ' ';
	public static StarTrek st = new StarTrek();
	public static boolean GameEnd = false;

	void CursorMovement(char[][] map, ClassNumber[] numbers) {
		cn.getTextWindow().setCursorPosition(cursorX, cursorY);
		System.out.print("P");
		while (true) {
			KeyListener klis = new KeyListener() {
				public void keyTyped(KeyEvent e) {
				}

				public void keyPressed(KeyEvent e) {
					if (keypr == 0) {
						keypr = 1;
						rkey = e.getKeyCode();
					}
				}

				public void keyReleased(KeyEvent e) {
				}
			};
			cn.getTextWindow().addKeyListener(klis);
			if (keypr == 1) {
				// eğer cursoyY=1 ise yukarı çıkamasın çünkü map'in dışına gider ve
				// map[][cursorY] boş ise oraya ilerlesin
				// diğer tüm if'ler aynı mantık

				if (cursorY != 1 && rkey == KeyEvent.VK_UP && map[cursorX][cursorY - 1] != '#') { // yukarı
					// if olacak eğer şuan bulunduğu konum 1,2,3,4,5 ise alıp backpack'e verecek
					if (map[cursorX][cursorY - 1] == '1' || map[cursorX][cursorY - 1] == '2'
							|| map[cursorX][cursorY - 1] == '3' || map[cursorX][cursorY - 1] == '4'
							|| map[cursorX][cursorY - 1] == '5') {
						send = (char) map[cursorX][cursorY - 1];
						cursorY--;
						AddBackPack(send, numbers);
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX, cursorY + 1);
						System.out.print(" ");
						map[cursorX][cursorY + 1] = ' ';
						rkey = 0;
						break;
					} else if (map[cursorX][cursorY - 1] == ' ') {
						cursorY--;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX, cursorY + 1);
						System.out.print(" ");
						map[cursorX][cursorY + 1] = ' ';
						if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
								|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
								|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // alt
								|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // üst
							DeleteBackPack(S1ForBackPack);
							rkey = 0;
							break;
						}
						rkey = 0;
						break;
					} else if (map[cursorX + 1][cursorY] == '=' || map[cursorX - 1][cursorY] == '=' // sol sağ
							|| map[cursorX][cursorY - 1] == '=' || map[cursorX][cursorY + 1] == '=' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == '=' || map[cursorX - 1][cursorY + 1] == '=' // üst
							|| map[cursorX + 1][cursorY - 1] == '=' || map[cursorX - 1][cursorY - 1] == '=' // alt

							|| map[cursorX + 1][cursorY] == '*' || map[cursorX - 1][cursorY] == '*' // sol sağ
							|| map[cursorX][cursorY - 1] == '*' || map[cursorX][cursorY + 1] == '*' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == '*' || map[cursorX - 1][cursorY + 1] == '*' // üst
							|| map[cursorX + 1][cursorY - 1] == '*' || map[cursorX - 1][cursorY - 1] == '*') { // alt
						rkey = 0;
						break;
						// 25 saniye süresini aktif et
					} else if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
							|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // üst
							|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // alt
						DeleteBackPack(S1ForBackPack);
						rkey = 0;
						break;
					}
					// = ve * ise 8 kare etrafina geldiginde aktif et
					// C ise 8 kare etraf na gelirse cani gidicek

				}

				if (cursorY != 22 && rkey == KeyEvent.VK_DOWN && map[cursorX][cursorY + 1] != '#') { // aşağı
					if (map[cursorX][cursorY + 1] == '1' || map[cursorX][cursorY + 1] == '2'
							|| map[cursorX][cursorY + 1] == '3' || map[cursorX][cursorY + 1] == '4'
							|| map[cursorX][cursorY + 1] == '5') {
						send = (char) map[cursorX][cursorY + 1];
						cursorY++;
						AddBackPack(send, numbers);
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX, cursorY - 1);
						System.out.print(" ");
						map[cursorX][cursorY - 1] = ' ';
						rkey = 0;
						break;
					} else if (map[cursorX][cursorY + 1] == ' ') {
						cursorY++;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX, cursorY - 1);
						System.out.print(" ");
						map[cursorX][cursorY - 1] = ' ';
						if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
								|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
								|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // üst
								|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // alt
							DeleteBackPack(S1ForBackPack);
							rkey = 0;
							break;
						}
						rkey = 0;
						break;
					} else if (map[cursorX + 1][cursorY] == '=' || map[cursorX - 1][cursorY] == '=' // sol sağ
							|| map[cursorX][cursorY - 1] == '=' || map[cursorX][cursorY + 1] == '=' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == '=' || map[cursorX - 1][cursorY + 1] == '=' // üst
							|| map[cursorX + 1][cursorY - 1] == '=' || map[cursorX - 1][cursorY - 1] == '=' // alt

							|| map[cursorX + 1][cursorY] == '*' || map[cursorX - 1][cursorY] == '*' // sol sağ
							|| map[cursorX][cursorY - 1] == '*' || map[cursorX][cursorY + 1] == '*' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == '*' || map[cursorX - 1][cursorY + 1] == '*' // üst
							|| map[cursorX + 1][cursorY - 1] == '*' || map[cursorX - 1][cursorY - 1] == '*') { // alt
						rkey = 0;
						break;
						// 25 saniye süresini aktif et
					} else if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
							|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // üst
							|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // alt
						DeleteBackPack(S1ForBackPack);
						rkey = 0;
						break;
					}
				}

				if (cursorX != 1 && rkey == KeyEvent.VK_LEFT && map[cursorX - 1][cursorY] != '#') { // sol
					if (map[cursorX - 1][cursorY] == '1' || map[cursorX - 1][cursorY] == '2'
							|| map[cursorX - 1][cursorY] == '3' || map[cursorX - 1][cursorY] == '4'
							|| map[cursorX - 1][cursorY] == '5') {
						send = (char) map[cursorX - 1][cursorY];
						cursorX--;
						AddBackPack(send, numbers);
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX + 1, cursorY);
						System.out.print(" ");
						map[cursorX + 1][cursorY] = ' ';
						rkey = 0;
						break;
					} else if (map[cursorX - 1][cursorY] == ' ') {
						cursorX--;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX + 1, cursorY);
						System.out.print(" ");
						map[cursorX + 1][cursorY] = ' ';
						if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
								|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
								|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // alt
								|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // üst
							DeleteBackPack(S1ForBackPack);
							rkey = 0;
							break;
						}
						rkey = 0;
						break;
					} else if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
							|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // üst
							|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // alt
						DeleteBackPack(S1ForBackPack);
						rkey = 0;
						break;
					}

				}
				if (cursorX != 54 && rkey == KeyEvent.VK_RIGHT && map[cursorX + 1][cursorY] != '#') { // sağ
					if (map[cursorX + 1][cursorY] == '1' || map[cursorX + 1][cursorY] == '2'
							|| map[cursorX + 1][cursorY] == '3' || map[cursorX + 1][cursorY] == '4'
							|| map[cursorX + 1][cursorY] == '5') {
						send = (char) map[cursorX + 1][cursorY];
						cursorX++;
						AddBackPack(send, numbers);
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX - 1, cursorY);
						System.out.print(" ");
						map[cursorX - 1][cursorY] = ' ';
						rkey = 0;
						break;
					} else if (map[cursorX + 1][cursorY] == ' ') {
						cursorX++;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						System.out.print("P");
						map[cursorX][cursorY] = 'P';
						cn.getTextWindow().setCursorPosition(cursorX - 1, cursorY);
						System.out.print(" ");
						map[cursorX - 1][cursorY] = ' ';
						if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
								|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
								|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // alt
								|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // üst
							DeleteBackPack(S1ForBackPack);
							rkey = 0;
							break;
						}
						rkey = 0;
						break;
					} else if (map[cursorX + 1][cursorY] == '=' || map[cursorX - 1][cursorY] == '=' // sol sağ
							|| map[cursorX][cursorY - 1] == '=' || map[cursorX][cursorY + 1] == '=' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == '=' || map[cursorX - 1][cursorY + 1] == '=' // üst
							|| map[cursorX + 1][cursorY - 1] == '=' || map[cursorX - 1][cursorY - 1] == '=' // alt

							|| map[cursorX + 1][cursorY] == '*' || map[cursorX - 1][cursorY] == '*' // sol sağ
							|| map[cursorX][cursorY - 1] == '*' || map[cursorX][cursorY + 1] == '*' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == '*' || map[cursorX - 1][cursorY + 1] == '*' // üst
							|| map[cursorX + 1][cursorY - 1] == '*' || map[cursorX - 1][cursorY - 1] == '*') { // alt
						rkey = 0;
						break;
						// 25 saniye süresini aktif et
					} else if (map[cursorX + 1][cursorY] == 'C' || map[cursorX - 1][cursorY] == 'C' // sol sağ
							|| map[cursorX][cursorY - 1] == 'C' || map[cursorX][cursorY + 1] == 'C' // aşağı yukarı
							|| map[cursorX + 1][cursorY + 1] == 'C' || map[cursorX - 1][cursorY + 1] == 'C' // alt
							|| map[cursorX + 1][cursorY - 1] == 'C' || map[cursorX - 1][cursorY - 1] == 'C') { // üst
						DeleteBackPack(S1ForBackPack);
						rkey = 0;
						break;
					}

				}

				if (cursorY != 1 && rkey == KeyEvent.VK_W) {// yukarı
					rkey = 0;
					WASDforBackPack(S1ForBackPack, map, 1);
					break;
				}
				if (cursorX != 1 && rkey == KeyEvent.VK_A) {// sol
					rkey = 0;
					WASDforBackPack(S1ForBackPack, map, 2);
					break;
				}
				if (cursorY != 22 && rkey == KeyEvent.VK_S) { // aşağı
					rkey = 0;
					WASDforBackPack(S1ForBackPack, map, 3);
					break;
				}
				if (cursorX != 54 && rkey == KeyEvent.VK_D) { // sağ
					rkey = 0;
					WASDforBackPack(S1ForBackPack, map, 4);
					break;
				}
				keypr = 0;
				rkey = 0;
			}
			rkey = 0;
			keypr = 0;
			break;
		}

	}

	public static void AddBackPack(char gelen, ClassNumber[] numbers) {
		// 1 ise puan artıyor değilse gerekli işlemleri yapıyor
		int counter = 0;

		if (gelen == '1') {
			score = score + 1;
		}

		else {
			switch (gelen) {
				case '2': {
					if (!S1ForBackPack.isFull() || S1ForBackPack.peek().equals('2')) {
						score = score + 5;
						if (S1ForBackPack.isEmpty())
							S1ForBackPack.push(gelen);
						else if (S1ForBackPack.peek().equals(gelen)) {
							S1ForBackPack.pop();
							energy = energy + 30;
						} else {
							if (S1ForBackPack.peek().equals('*') || S1ForBackPack.peek().equals('=')) {
								S1ForBackPack.push(gelen);
							} else {
								S1ForBackPack.pop();
							}
						}
					} else {
						score = score + 5;

					}

				}
					break;

				case '3': {
					if (!S1ForBackPack.isFull() || S1ForBackPack.peek().equals('3')) {
						score = score + 15;
						if (S1ForBackPack.isEmpty())
							S1ForBackPack.push(gelen);
						else if (S1ForBackPack.peek().equals(gelen)) {
							S1ForBackPack.pop();
							S1ForBackPack.push('=');
						} else {
							if (S1ForBackPack.peek().equals('*') || S1ForBackPack.peek().equals('=')) {
								S1ForBackPack.push(gelen);
							} else {
								S1ForBackPack.pop();
							}
						}
					} else {
						score = score + 15;
					}
				}
					break;
				case '4': {
					while (true) {
						if (numbers[counter] != null && numbers[counter].x == cursorX
								&& numbers[counter].y == cursorY) {
							numbers[counter] = null;
							break;
						}
						counter++;
					}

					if (!S1ForBackPack.isFull() || S1ForBackPack.peek().equals('4')) {
						score = score + 50;
						if (S1ForBackPack.isEmpty())
							S1ForBackPack.push(gelen);
						else if (S1ForBackPack.peek().equals(gelen)) {
							S1ForBackPack.pop();
							energy = energy + 240;
						}

						else {
							if (S1ForBackPack.peek().equals('*') || S1ForBackPack.peek().equals('=')) {
								S1ForBackPack.push(gelen);
							}

							else {
								S1ForBackPack.pop();
							}
						}
					}

					else {
						score = score + 50;

					}

				}
					break;

				case '5': {
					while (true) {
						if (numbers[counter] != null && numbers[counter].x == cursorX
								&& numbers[counter].y == cursorY) {
							numbers[counter] = null;
							break;
						}
						counter++;
					}
					if (S1ForBackPack.isEmpty() || S1ForBackPack.peek().equals('5')) {
						score = score + 150;
						if (!S1ForBackPack.isEmpty())
							S1ForBackPack.push(gelen);

						else if (S1ForBackPack.peek().equals(gelen)) {
							S1ForBackPack.pop();
							S1ForBackPack.push('*');
						}

						else {
							if (S1ForBackPack.peek().equals('*') || S1ForBackPack.peek().equals('=')) {
								S1ForBackPack.push(gelen);
							} else {
								S1ForBackPack.pop();
							}
						}
					} else {
						score = score + 150;

					}

				}
					break;
			}
		}

	}

	public static void DeleteBackPack(Stack S1ForBackPack) {
		for (int i = 0; i < 2; i++) {
			if (S1ForBackPack.isEmpty()) {
				break;
			} else {
				S1ForBackPack.pop();
				if (S1ForBackPack.isEmpty()) {
					break;
				}
			}
		}
	}

	public static void WASDforBackPack(Stack S1ForBackPack, char[][] map, int key) {
		char peek = ' ';
		if (!S1ForBackPack.isEmpty() && ((char) S1ForBackPack.peek() == '=' || (char) S1ForBackPack.peek() == '*')) {
			switch (key) {
				case 1: // W yukarı
				{
					if (map[cursorX][cursorY - 1] == ' ') {
						peek = (char) S1ForBackPack.pop();
						map[cursorX][cursorY - 1] = peek;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY - 1);
						System.out.print(peek);
					} else {
						break;
					}
				}
					break;
				case 2: // A sağı
				{
					if (map[cursorX - 1][cursorY] == ' ') {
						peek = (char) S1ForBackPack.pop();
						map[cursorX - 1][cursorY] = peek;
						cn.getTextWindow().setCursorPosition(cursorX - 1, cursorY);
						System.out.print(peek);
					} else {
						break;
					}
				}
					break;
				case 3: // S aşşağısı
				{
					if (map[cursorX][cursorY + 1] == ' ') {
						peek = (char) S1ForBackPack.pop();
						map[cursorX][cursorY + 1] = peek;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY + 1);
						System.out.print(peek);
					} else {
						break;
					}
				}
					break;
				case 4: // D sağı
				{
					if (map[cursorX + 1][cursorY] == ' ') {
						peek = (char) S1ForBackPack.pop();
						map[cursorX + 1][cursorY] = peek;
						cn.getTextWindow().setCursorPosition(cursorX + 1, cursorY);
						System.out.print(peek);
					} else {
						break;
					}
				}
					break;

				default:
					break;
			}
		} else if (!S1ForBackPack.isEmpty() && ((char) S1ForBackPack.peek() == '2' || (char) S1ForBackPack.peek() == '3'
				|| (char) S1ForBackPack.peek() == '4' || (char) S1ForBackPack.peek() == '5')) {
			S1ForBackPack.pop();
		}
	}

	static Stack S1ForBackPack = new Stack(8);
	public static int score = 0;
	static int energy = 50;
	static int life = 5;

}