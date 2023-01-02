import java.awt.Color;
import java.util.Random;
import enigma.console.TextAttributes;

public class SingleLinkedList {
	private Node head;

	public void add(Object data) {

		Node newNode = new Node(data);
		if (head == null)
			head = newNode;
		else {
			Node temp = head;
			while (temp.getLink() != null) {
				temp = temp.getLink();
			}
			temp.setLink(newNode);
		}
	}

	public int size() {
		int counter = 0;
		if (head == null)
			return 0;
		else {
			Node temp = head;

			while (temp != null) {
				temp = temp.getLink();
				counter++;
			}
		}
		return counter;
	}

	public void display() {
		if (head == null)
			System.out.println("List is empty");
		else {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
		}

	}

	public void displayColumn(int columnNumber) {
		if (head == null)
			System.out.println("List is empty");
		else {
			Node temp = head;
			int y = 4;
			while (temp != null) {
				Test.cn.getTextWindow().setCursorPosition(3 * columnNumber + columnNumber - 1, y);
				if (3 * columnNumber + columnNumber - 1 == Test.cursorX && y == Test.cursorY)
					Test.color = new TextAttributes(Color.GREEN, Color.BLACK);
				else if (3 * columnNumber + columnNumber - 1 == Test.selectedX && y == Test.selectedY)
					Test.color = new TextAttributes(Color.RED, Color.BLACK);
				else
					Test.color = new TextAttributes(Test.white, Color.BLACK);
				Test.cn.getTextWindow().output(temp.getData().toString(), Test.color);
				y++;
				temp = temp.getLink();
			}
		}

	}

	public void delete(Object data) {
		if (head == null)
			System.out.println("Listis empty!");

		else {
			if (head.getData().equals(data)) {
				head = head.getLink();
				return;
			}

			Node temp = head;
			Node prev = null;
			while (temp != null) {

				if (temp.getData().equals(data)) {
					prev.setLink(temp.getLink());
					temp = prev;
					return;
				}

				prev = temp;
				temp = temp.getLink();
			}

		}
	}

	public int findMax() {
		int maxVal = Integer.MIN_VALUE;
		if (head == null)
			System.out.println("List is empty");
		else {
			Node temp = head;
			while (temp != null) {
				if ((Integer) temp.getData() > maxVal)
					maxVal = (Integer) temp.getData();
				temp = temp.getLink();
			}
		}
		return maxVal;
	}

	public boolean search(Object data) {
		if (head == null)
			System.out.println("List is empty");
		else {
			Node temp = head;
			while (temp != null) {
				if (temp.getData() == data)
					return true;
				temp = temp.getLink();
			}
		}
		return false;
	}

	void fillingBox() {
		for (int i = 0; i < 5; i++) {
			for (int j = 1; j <= 10; j++) {
				add(j); // 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3.....
			}
		}
	}

	int choosingRandomNumber() {
		int rnd = (int) (Math.random() * size() + 1);
		int counter = 1;
		Node temp = head;

		while (temp != null) {
			if (counter == rnd)
				break;
			counter++;
			temp = temp.getLink();
		}

		if (temp != null) {
			Object data = temp.getData();
			delete(data);
			return (int) data;
		} else {
			return 0;
		}
	}

	Object drawNumber() {
		Random rand = new Random();
		int number = rand.nextInt(size());
		int counter = 0;
		Object data = null;
		Node temp = head;
		while (temp.getLink() != null) {
			if (counter == number) {
				data = temp.getData();
				break;
			} else {
				temp = temp.getLink();
				counter++;
			}
		}
		delete(data);
		return data;

	}

	void displayBox(String number) {
		Test.cn.getTextWindow().setCursorPosition(27, 8);
		if (27 == Test.cursorX && 8 == Test.cursorY)
			Test.color = new TextAttributes(Color.GREEN, Color.BLACK);
		else if (27 == Test.selectedX && 8 == Test.selectedY)
			Test.color = new TextAttributes(Color.RED, Color.BLACK);
		else
			Test.color = new TextAttributes(Test.white, Color.BLACK);
		Test.cn.getTextWindow().output(number, Test.color);

	}

}
