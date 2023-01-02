package project2;

import java.awt.Color;
import java.util.Random;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class StarTrek {
	public static enigma.console.Console cn = Enigma.getConsole("Star Trek Warp Wars", 80, 23, 20);
	public static Computer[] computers = new Computer[1000];
	public static int computersIndex = 0;
	public static ClassNumber[] numbers = new ClassNumber[1000];
	public static int numbersIndex = 0;
	public static Trap[] traps = new Trap[1000];
	public static int trapIndex = 0;
	public static Warp[] warps = new Warp[1000];
	public static int warpIndex = 0;

	public static void main(String[] args) throws InterruptedException {
		Player ppp = new Player();
		String[] mapString = FileReader.readFile("map.txt");
		char[][] map = new char[55][23];

		for (int i = 0; i < 55; i++) {
			for (int j = 0; j < 23; j++) {
				map[i][j] = mapString[j].charAt(i);
			}
		}
		for (int i = 0; i < mapString.length; i++) {
			System.out.print(mapString[i]);
			if (i + 1 != mapString.length)
				System.out.println();
		}
		Queue inputQueue = new Queue(15);
		InputQueue.fillQueue(inputQueue);

		displayInputQueue(inputQueue);
		for (int i = 0; i < 20; i++) {
			addValue(map, inputQueue);
		}
		int counter = 0;
		int speed, time = 0;
		while (Player.life != 0) {
			// player move but in speed
			long time1 = System.currentTimeMillis();
			if (Player.energy == 0)
				speed = 10;
			else
				speed = 5;

			if (counter % speed == 0) {
				green();
				ppp.CursorMovement(map, numbers);
				displayBackPack(Player.S1ForBackPack);
				white();
			}

			// 1sec
			if (counter % 40 == 0) {
				for (int i = 0; i < trapIndex; i++) {
					if (traps[i] != null) {
						if (traps[i].delete) {
							cn.getTextWindow().setCursorPosition(traps[i].x, traps[i].y);
							System.out.print(' ');
							map[traps[i].x][traps[i].y] = ' ';
							traps[i] = null;

						} else {
							traps[i].checkSquare(map, computers, numbers);
							if (traps[i].time > 0)
								traps[i].time--;
						}
					}
				}
				for (int i = 0; i < warpIndex; i++) {
					if (warps[i] != null) {
						if (warps[i].delete) {
							cn.getTextWindow().setCursorPosition(warps[i].x, warps[i].y);
							System.out.print(' ');
							map[warps[i].x][warps[i].y] = ' ';
							warps[i] = null;
						} else {
							int[] position = warps[i].checkSquare(map, computers, numbers);
							if (position != null) {
								cn.getTextWindow().setCursorPosition(position[0], position[1]);
								System.out.print(' ');
							}
							if (warps[i].time > 0)
								warps[i].time--;
						}
					}
				}
			}

			// halfsec
			if (counter % 20 == 0) {
				// all other moves

				for (int i = 0; i < computersIndex; i++) {
					if (computers[i] != null) {
						cn.getTextWindow().setCursorPosition(computers[i].x, computers[i].y);
						System.out.print(' ');
						computers[i].move(map, numbers, traps);
						cn.getTextWindow().setCursorPosition(computers[i].x, computers[i].y);
						pink();
						System.out.print('C');
						white();
					}
				}
				for (int i = 0; i < numbersIndex; i++) {
					if (numbers[i] != null) {
						cn.getTextWindow().setCursorPosition(numbers[i].x, numbers[i].y);
						System.out.print(' ');
						numbers[i].move(map);
						cn.getTextWindow().setCursorPosition(numbers[i].x, numbers[i].y);
						blue();
						System.out.print(numbers[i].c);
						white();
					}
				}
				cn.getTextWindow().setCursorPosition(56, 16);
				System.out.print("P.Energy: " + Player.energy);
				cn.getTextWindow().setCursorPosition(56, 17);
				System.out.print("P.Score: " + Player.score);
				cn.getTextWindow().setCursorPosition(56, 18);
				System.out.print("P.Life: " + Player.life);
				cn.getTextWindow().setCursorPosition(56, 20);
				System.out.print("C.Score: " + Computer.score);
				cn.getTextWindow().setCursorPosition(56, 22);
				System.out.print("Time: " + time);
			}

			// 3 sec
			if (counter % 120 == 0) {
				addValue(map, inputQueue);
			}

			counter++;
			long time2 = System.currentTimeMillis();
			Thread.sleep(25 - (time2 - time1));
			if (counter % 40 == 0) {
				time = (counter / 40);
				if (Player.energy > 0)
					Player.energy--;
			}
		}
		for (int i = 0; i < 30; i++) {
			System.out.println(" ");
		}
		cn.getTextWindow().setCursorPosition(25, 10);
		red();
		System.out.println("------------");
		cn.getTextWindow().setCursorPosition(25, 11);
		System.out.println("|Game Over!|");
		cn.getTextWindow().setCursorPosition(25, 12);
		System.out.println("------------");

	}

	static void orange() {
		TextAttributes write = new TextAttributes(Color.orange);
		cn.setTextAttributes(write);
	}

	static void magenta() {
		TextAttributes write = new TextAttributes(Color.MAGENTA);
		cn.setTextAttributes(write);
	}

	static void blue() {
		TextAttributes write = new TextAttributes(Color.blue);
		cn.setTextAttributes(write);
	}

	static void green() {
		TextAttributes write = new TextAttributes(Color.green);
		cn.setTextAttributes(write);
	}

	static void red() {
		TextAttributes write = new TextAttributes(Color.red);
		cn.setTextAttributes(write);
	}

	static void white() {
		TextAttributes write = new TextAttributes(Color.white);
		cn.setTextAttributes(write);
	}

	static void pink() {
		TextAttributes write = new TextAttributes(Color.pink);
		cn.setTextAttributes(write);
	}

	static void addValue(char[][] map, Queue inputQueue) {
		Random rnd = new Random();
		boolean flag = true;
		while (flag) {
			int x = rnd.nextInt(1, 54);
			int y = rnd.nextInt(1, 22);
			if (map[x][y] == ' ') {
				cn.getTextWindow().setCursorPosition(x, y);
				red();
				char element = (char) inputQueue.dequeue();
				map[x][y] = element;
				if (element == '4' || element == '5') {
					numbers[numbersIndex] = new ClassNumber(x, y, element);
					numbersIndex++;
				}
				if (element == 'C') {
					computers[computersIndex] = new Computer(x, y);
					computersIndex++;
				}
				if (element == '=') {
					traps[trapIndex] = new Trap(x, y);
					trapIndex++;
				}
				if (element == '*') {
					warps[warpIndex] = new Warp(x, y);
					warpIndex++;
				}
				System.out.print(element);
				cn.getTextWindow().setCursorPosition(56, 3);
				green();
				InputQueue.putOne(inputQueue);
				for (int k = 0; k < inputQueue.size(); k++) {
					element = (char) inputQueue.dequeue();
					System.out.print(element);
					inputQueue.enqueue(element);
				}
				flag = false;
			}
		}
		white();
	}

	public static void displayBackPack(Stack S1ForBackPack) {

		char peek = ' ';

		if (S1ForBackPack.isEmpty()) {
			peek = ' ';
			magenta();
			cn.getTextWindow().setCursorPosition(56, 5);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 6);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 7);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 8);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 9);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 10);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 11);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 12);
			System.out.print("| " + peek + " |");
			cn.getTextWindow().setCursorPosition(56, 13);
			System.out.print("+---+");
			cn.getTextWindow().setCursorPosition(56, 14);
			white();
			System.out.println("P.Backpack");
		}

		else {
			Stack tempStack = new Stack(S1ForBackPack.size());
			int size = S1ForBackPack.size();
			for (int i = 0; i < size; i++) {
				tempStack.push(S1ForBackPack.pop());
			}
			for (int i = 0; i < 8; i++) {
				cn.getTextWindow().setCursorPosition(58, 12 - i);
				System.out.println(' ');
			}
			size = tempStack.size();
			for (int i = 0; i < size; i++) {
				S1ForBackPack.push(tempStack.pop());
				peek = (char) S1ForBackPack.peek();
				cn.getTextWindow().setCursorPosition(58, 12 - i);
				orange();
				System.out.println(peek);

			}
		}

	}

	static void displayInputQueue(Queue inputQueue) {
		cn.getTextWindow().setCursorPosition(56, 1);
		System.out.println("Input");
		cn.getTextWindow().setCursorPosition(56, 2);
		System.out.println("<<<<<<<<<<<<<<<");
		cn.getTextWindow().setCursorPosition(56, 3);
		for (int k = 0; k < inputQueue.size(); k++) {
			char c = (char) inputQueue.dequeue();
			System.out.print(c);
			inputQueue.enqueue(c);
		}
		inputQueue.dequeue();
		InputQueue.putOne(inputQueue);
		cn.getTextWindow().setCursorPosition(56, 4);
		System.out.println(">>>>>>>>>>>>>>>");
	}
}
