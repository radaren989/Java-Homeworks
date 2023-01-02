import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Test {
	static enigma.console.Console cn = Enigma.getConsole("Column", 50, 30, 20, 7);
	static KeyListener klis;
	static int keypr;
	static int rkey;
	static MLL columns = new MLL();
	static SingleLinkedList Box = new SingleLinkedList();
	static DoubleLinkedList scoreTable = new DoubleLinkedList();
	static Color white = new Color(224, 224, 224);
	static TextAttributes color = new TextAttributes(white, Color.BLACK);
	static boolean initialNumberDrawn = false;
	static boolean drawnFromBox = false;
	static boolean isBoxEmpty = false;
	static String prevNumber = "";

	static int cursorX = 3, cursorY = 4, selectedX = -1, selectedY = -1, cursorYprev = 4, score = 0,
			transferCounter = 0;

	public static void main(String[] args) throws InterruptedException, IOException {

		Box.fillingBox();
		for (int i = 1; i <= 5; i++)
			columns.addColumn("c" + i);
		// Filling Columns
		for (int i = 0; i < 6; i++) {
			columns.addNumber("c1", Box.choosingRandomNumber());
			columns.addNumber("c2", Box.choosingRandomNumber());
			columns.addNumber("c3", Box.choosingRandomNumber());
			columns.addNumber("c4", Box.choosingRandomNumber());
			columns.addNumber("c5", Box.choosingRandomNumber());
		}

		keypr = 0;
		klis = new KeyListener() {
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

		// Reading HighscoreTable

		scoreTable = FileInteraction.readScoreFile();

		while (true) {// Menu
			consoleClear();
			System.out.println("\n\n\t\tMenu");
			System.out.println("\t\t1. Play Game");
			System.out.println("\t\t2. High Score");
			System.out.println("\t\t3. Exit");

			while (true) {// Game
				if (keypr == 1) {
					if (rkey == KeyEvent.VK_1) {
						startGame();
					} else if (rkey == KeyEvent.VK_2) {
						// High score
						DisplayHighScoreTable();
						keypr = 0;

						while (true) {
							Thread.sleep(10);
							if (keypr == 1)
								break;
						}

					} else if (rkey == KeyEvent.VK_3)
						System.exit(0);
					keypr = 0;
					break;
				}
				System.out.print("");
			}
		}
	}

	static void startGame() {
		consoleClear();
		while (true) {
			if (keypr == 1) {
				cursorEvents();
				if (rkey == KeyEvent.VK_ESCAPE) {
					Player player = new Player();
					player.name = "Your";
					player.surname = "Score";
					player.score = (score / 10) + (score / transferCounter);
					scoreTable.addSorted(player);
					break;
				}
				cn.getTextWindow().setCursorPosition(25, 15);
				System.out.println("Box size: " + "  ");
				cn.getTextWindow().setCursorPosition(25, 15);
				System.out.println("Box size: " + Box.size());
				if (Box.size() == 0 && score == 5000)
					break;
				keypr = 0;
			}
			displayScreen();
		}
	}

	static void consoleClear() {
		cn.getTextWindow().setCursorPosition(0, 0);
		System.out.println(
				"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
		cn.getTextWindow().setCursorPosition(0, 0);
	}

	static void DisplayHighScoreTable() throws IOException {
		consoleClear();
		cn.getTextWindow().setCursorPosition(0, 0);
		scoreTable.displayHighScores();
	}

	static void cursorEvents() {
		switch (rkey) {
			case KeyEvent.VK_RIGHT: {
				if (columnFinder() == 5)
					cursorX = 3;
				else
					cursorX += 4;
				if (cursorY < 4)
					cursorY += 2;
				else if (columns.columnSize("c" + columnFinder()) + 3 < cursorY)
					cursorY = columns.columnSize("c" + columnFinder()) + 3;
				if (columns.columnSize("c" + columnFinder()) == 0)
					cursorY = 3;
				break;
			}
			case KeyEvent.VK_DOWN: {
				if (cursorY != 3) {
					if (cursorY >= ourColumnSize() + 3)
						cursorY = 4;
					else
						cursorY++;
				}
				break;
			}
			case KeyEvent.VK_LEFT: {
				if (columnFinder() == 1)
					cursorX = 19;
				else
					cursorX -= 4;
				if (cursorY < 4)
					cursorY += 2;
				else if (columns.columnSize("c" + columnFinder()) + 3 < cursorY)
					cursorY = columns.columnSize("c" + columnFinder()) + 3;
				if (columns.columnSize("c" + columnFinder()) == 0)
					cursorY = 3;
				break;
			}
			case KeyEvent.VK_UP: {
				if (cursorY != 3) {
					if (cursorY <= 4)
						cursorY = ourColumnSize() + 3;
					else
						cursorY--;
				}
				break;
			}
			case KeyEvent.VK_Z: {
				drawnFromBox = false;
				if (cursorY > 3) {
					selectedX = cursorX;
					selectedY = cursorY;
					if (cursorY >= ourColumnSize() + 2)
						cursorY = 4;
					else
						cursorY++;
				}
				break;
			}
			case KeyEvent.VK_X: {

				if (!drawnFromBox) {
					if (selectedX != -1 && selectedX != cursorX) {
						columns.changeSet("c" + (selectedX + 1) / 4, "c" + columnFinder(), selectedY - 4);
						for (int i = selectedY; i < 29; i++) {
							cn.getTextWindow().setCursorPosition(selectedX, i);
							System.out.println("  ");
						}
						columns.isFullSet("c" + columnFinder());
						columns.isFullSet("c" + (selectedX + 1) / 4);
						cursorY++;
						selectedX = -1;
					}
					break;
				} else {
					// if the box is not empty
					if (!isBoxEmpty) {
						// transfer from box
						drawnFromBox = false;
						cn.getTextWindow().setCursorPosition(27, 8);
						System.out.println("  ");

						// if the column is empty
						if ((columns.getLastNumber("c" + columnFinder()) == 0)
								&& (Integer.valueOf(prevNumber) == 10 || Integer.valueOf(prevNumber) == 1)) {

							columns.addNumber("c" + columnFinder(), Integer.valueOf(prevNumber));
							transferCounter++;

							// to draw a new number next time

							initialNumberDrawn = false;
							prevNumber = "";

						} else if (Math
								.abs(columns.getLastNumber("c" + columnFinder()) - Integer.valueOf(prevNumber)) < 2) {

							columns.addNumber("c" + columnFinder(), Integer.valueOf(prevNumber));
							transferCounter++;

							// to draw a new number next time

							initialNumberDrawn = false;
							prevNumber = "";
						}
						selectedX = -1;

						if (Box.size() == 0) {
							isBoxEmpty = true;
						}

						break;
					}
				}
			}

			case KeyEvent.VK_B: {
				drawnFromBox = true;
				selectedX = 27;
				selectedY = 8;
				if (!initialNumberDrawn) {// draw a number
					if (Box.size() != 0) {
						prevNumber = Integer.toString(Box.choosingRandomNumber());
					} else {
						prevNumber = "";
					}

					initialNumberDrawn = true;
					Box.displayBox(prevNumber);

				}
				break;
			}
			default:

		}
	}

	static int columnFinder() {
		return (cursorX + 1) / 4;
	}

	static int ourColumnSize() {
		return columns.columnSize("c" + columnFinder());
	}

	static void displayScreen() {
		cn.getTextWindow().setCursorPosition(25, 2);
		System.out.println("Transfer: " + transferCounter);
		cn.getTextWindow().setCursorPosition(25, 3);
		System.out.println("Score   : " + score);
		cn.getTextWindow().setCursorPosition(26, 6);
		System.out.println("Box");
		cn.getTextWindow().setCursorPosition(26, 7);
		System.out.println("+--+");
		cn.getTextWindow().setCursorPosition(26, 8);
		System.out.println("|");
		cn.getTextWindow().setCursorPosition(29, 8);
		System.out.println("|");
		cn.getTextWindow().setCursorPosition(26, 9);
		System.out.println("+--+");

		// display columns
		for (int i = 1; i <= columns.columnCount(); i++)
			columns.displayColumn("c" + i);

		// display box
		Box.displayBox(prevNumber);
	}

}
