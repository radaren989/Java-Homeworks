import enigma.console.TextAttributes;
import java.awt.Color;

public class MLL {

	public class ColumnNode {

		private String data;
		private ColumnNode down;
		private NumberNode right;

		public ColumnNode(String data) {

			this.data = data;
			this.down = null;
			this.right = null;

		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public ColumnNode getDown() {
			return down;
		}

		public void setDown(ColumnNode down) {
			this.down = down;
		}

		public NumberNode getRight() {
			return right;
		}

		public void setRight(NumberNode right) {
			this.right = right;
		}
	}

	public class NumberNode {
		private int number;
		private NumberNode next;

		public NumberNode(int data) {
			number = data;
			next = null;

		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public NumberNode getNext() {
			return next;
		}

		public void setNext(NumberNode next) {
			this.next = next;
		}

	}

	public void addNumber(String columnName, int number) {
		if (head == null) {
			System.out.println("there is no team");
		} else {
			NumberNode newNNode = new NumberNode(number);
			ColumnNode temp = head;

			while (temp != null && !(temp.getData()).equalsIgnoreCase(columnName)) {
				temp = temp.getDown();
			}

			if (temp != null) {
				if (temp.getRight() == null) {
					temp.setRight(newNNode);

				} else {
					NumberNode tempN = temp.getRight();

					while (tempN.getNext() != null) {
						tempN = tempN.getNext();
					}
					tempN.setNext(newNNode);

				}

			} else {
				System.out.println("there is no team named: " + columnName);

			}

		}

	}

	private ColumnNode head;

	public MLL() {
		this.head = null;
	}

	int columnCount() {
		int counter = 0;
		if (head != null) {
			ColumnNode temp = head;
			while (temp != null) {
				temp = temp.getDown();
				counter++;
			}
		}
		return counter;
	}

	public void addColumn(String columnName) {
		ColumnNode newNode = new ColumnNode(columnName);
		if (head == null) {
			head = newNode;
		} else {
			ColumnNode temp = head;
			while (temp.getDown() != null) { // en son elemanda duruyor
				temp = temp.getDown();
			}
			temp.setDown(newNode);
		}

	}

	public int getLastNumber(String columnName) {

		// Gets the last number of selected column, returns 0 if the column is empty.

		ColumnNode tempColumn = head;
		NumberNode tempNumber = null;

		while (tempColumn != null) {
			if (tempColumn.getData().equals(columnName)) {
				tempNumber = tempColumn.getRight();
			}
			tempColumn = tempColumn.getDown();
		}

		if (tempNumber != null) {
			while (tempNumber.getNext() != null) {
				tempNumber = tempNumber.getNext();
			}
			return tempNumber.getNumber();
		} else {
			return 0;
		}
	}

	public void changeSet(String from, String target, int selectedNumberCount) {
		ColumnNode tempColumn = head, tempColumn2 = head;
		NumberNode prev = null, tempNumberSelected = null, tempTargetNumber = null;

		while (tempColumn != null) {
			if (tempColumn.getData().equals(from)) {
				tempNumberSelected = tempColumn.getRight();
				tempColumn2 = tempColumn;
			}
			tempColumn = tempColumn.getDown();
		}

		tempColumn = head;

		while (tempColumn != null) {
			if (tempColumn.getData().equals(target)) {
				tempTargetNumber = tempColumn.getRight();
				break;
			}
			tempColumn = tempColumn.getDown();
		}

		for (int i = 0; i < selectedNumberCount; i++) {
			prev = tempNumberSelected;
			tempNumberSelected = tempNumberSelected.getNext();

		}

		if (tempTargetNumber != null)
			while (tempTargetNumber.getNext() != null) {
				tempTargetNumber = tempTargetNumber.getNext();
			}

		if (tempTargetNumber == null && (tempNumberSelected.getNumber() == 1 || tempNumberSelected.getNumber() == 10)) {
			Test.transferCounter++;
			tempColumn.setRight(tempNumberSelected);
			if (prev == null) {
				tempColumn2.setRight(null);
			} else
				prev.setNext(null);
		}
		if (tempTargetNumber != null && Math.abs(tempNumberSelected.getNumber() - tempTargetNumber.getNumber()) < 2) {
			Test.transferCounter++;
			tempTargetNumber.setNext(tempNumberSelected);
			if (prev == null) {
				tempColumn2.setRight(null);
			} else
				prev.setNext(null);
		}

	}

	public void display() {
		if (head == null) {
			System.out.println("there is no team");
		} else {
			ColumnNode temp = head;

			while (temp != null) {
				System.out.print(temp.getData() + ": ");
				NumberNode tempP = temp.getRight();

				while (tempP != null) {
					System.out.print(tempP.getNumber() + " ");
					tempP = tempP.getNext();
				}
				System.out.print("\n");

				temp = temp.getDown();
			}

		}

	}

	public void displayColumn(String columnName) {
		int columnNumber = 0;
		switch (columnName) {
			case "c1": {
				columnNumber = 1;
				break;
			}
			case "c2": {
				columnNumber = 2;
				break;
			}
			case "c3": {
				columnNumber = 3;
				break;
			}
			case "c4": {
				columnNumber = 4;
				break;
			}
			case "c5": {
				columnNumber = 5;
				break;
			}
			default:
		}
		ColumnNode tempColumn = head;
		while (tempColumn != null) {
			if (tempColumn.getData().equals(columnName))
				break;
			tempColumn = tempColumn.getDown();
		}

		if (head == null)
			System.out.println("List is empty");
		else {
			Test.cn.getTextWindow().setCursorPosition(4 * columnNumber - 1, 2);
			if (4 * columnNumber - 1 == Test.cursorX && 3 >= Test.cursorY) {
				Test.cn.getTextWindow().output(String.valueOf(tempColumn.getData()), new TextAttributes(Color.GREEN));
				Test.cn.getTextWindow().setCursorPosition(4 * columnNumber - 1, 3);
				Test.cn.getTextWindow().output(String.valueOf("--"), new TextAttributes(Color.GREEN));
			} else {

				Test.cn.getTextWindow().output(String.valueOf(tempColumn.getData()), new TextAttributes(Test.white));
				Test.cn.getTextWindow().setCursorPosition(4 * columnNumber - 1, 3);
				Test.cn.getTextWindow().output(String.valueOf("--"), new TextAttributes(Test.white));
			}

			NumberNode temp = tempColumn.getRight();
			int y = 4;
			while (temp != null) {
				Test.cn.getTextWindow().setCursorPosition(4 * columnNumber - 1, y);
				if (4 * columnNumber - 1 == Test.cursorX && y == Test.cursorY)
					Test.color = new TextAttributes(Color.GREEN, Color.BLACK);
				else if (4 * columnNumber - 1 == Test.selectedX && y == Test.selectedY)
					Test.color = new TextAttributes(Color.RED, Color.BLACK);
				else
					Test.color = new TextAttributes(Test.white, Color.BLACK);
				Test.cn.getTextWindow().output(String.valueOf(temp.getNumber()), Test.color);
				y++;
				temp = temp.getNext();
			}
		}

	}

	public void isFullSet(String columnName) {
		ColumnNode tempColumn = head;
		NumberNode tempNumber = null;
		boolean flag = false;
		if (columnSize(columnName) == 10) {
			while (tempColumn != null) {
				if (tempColumn.getData().equals(columnName)) {
					tempNumber = tempColumn.getRight();
					break;
				}
				tempColumn = tempColumn.getDown();
			}

			if (tempNumber.getNumber() == 1) {
				tempNumber = tempNumber.getNext();

				flag = true;
				for (int i = 2; i < 11; i++) {
					if (tempNumber.getNumber() != i)
						flag = false;

					tempNumber = tempNumber.getNext();
				}
			}

			else if (tempNumber.getNumber() == 10) {
				tempNumber = tempNumber.getNext();

				flag = true;
				for (int i = 9; i > 0; i--) {
					if (tempNumber.getNumber() != i)
						flag = false;

					tempNumber = tempNumber.getNext();
				}
			}
		}
		if (flag) {
			tempColumn.setRight(null);
			for (int i = 4; i < 29; i++) {
				Test.cn.getTextWindow().setCursorPosition(Integer.parseInt(columnName.substring(1)) * 4 - 1, i);
				System.out.println("  ");
			}
			Test.score += 1000;
		}

	}

	public int columnSize(String columnName) {
		ColumnNode tempColumn = head;
		while (tempColumn != null) {
			if (tempColumn.getData().equals(columnName))
				break;
			tempColumn = tempColumn.getDown();
		}
		int counter = 0;
		if (tempColumn.getRight() != null) {
			NumberNode temp = tempColumn.getRight();
			counter = 1;
			while (temp.getNext() != null) {
				temp = temp.getNext();
				counter++;
			}
		}
		return counter;
	}
}
