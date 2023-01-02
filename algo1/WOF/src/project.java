import java.util.Random;

public class project {
    public static void main(String[] args) throws InterruptedException {
        int userScore = 0, step = 0;
        Stack uCountriesStack = FileInteraction.readFile("countries.txt");
        Stack countriesStack = sort(uCountriesStack);

        Stack alphaStack = alphaStack();

        Stack nameAndScores = FileInteraction.readFile("HighScoreTable.txt");
        Stack uNames = new Stack(nameAndScores.size() + 1), uScores = new Stack(nameAndScores.size() + 1),
                names = new Stack(nameAndScores.size() + 1), scores = new Stack(nameAndScores.size() + 1);
        while (!nameAndScores.isEmpty()) {
            String temp = (String) nameAndScores.pop();
            String[] tempArr = temp.split(" ");
            uNames.push(tempArr[0]);
            uScores.push(Integer.parseInt(tempArr[1]));
        }

        Random rnd = new Random();
        int randomNum = rnd.nextInt(1, 197);
        Queue wordQueue = new Queue(retriveWord(countriesStack, randomNum - 1).length());
        Queue blankWordQueue = new Queue(retriveWord(countriesStack, randomNum - 1).length());
        putWord(wordQueue, blankWordQueue, retriveWord(countriesStack, randomNum - 1));
        System.out.println("Randomly Generated Number: " + randomNum);
        while (!finished(wordQueue, blankWordQueue)) {
            System.out.printf("word: %-35s step: %-10d score: %-20d %s\n", makeString(blankWordQueue), step,
                    userScore, alphaString(alphaStack));
            int wheel = turnWheel();
            String rndLetter = randomLetter(alphaStack);
            int multiplier = setBlankQueue(wordQueue, blankWordQueue, rndLetter);
            if (wheel > 0) {
                System.out.println("Wheel: " + wheel);
                userScore += wheel * multiplier;
            } else if (wheel == 0) {
                System.out.println("Wheel: Bankrupt");
                userScore = 0;
            } else if (wheel == -1) {
                System.out.println("Wheel: Double Money");
                userScore *= 2;
            }

            System.out.println("Guess: " + rndLetter);
            step++;
            Thread.sleep(2000);
        }
        System.out.printf("word: %-35s step: %-10d score: %-20d %s\n", makeString(blankWordQueue), step,
                userScore, alphaString(alphaStack));
        System.out.println("\nYou win TRY" + userScore + "\n");
        uNames.push("You");
        uScores.push(userScore);

        // sorts scores and sorts names respect to scores
        while (!uScores.isEmpty()) {
            int temp;
            String tempStr;
            while (!uScores.isEmpty()) {
                temp = Integer.parseInt(uScores.pop().toString());
                tempStr = uNames.pop().toString();
                while (!scores.isEmpty() && Integer.parseInt(scores.peek().toString()) > temp) {
                    uScores.push(scores.pop());
                    uNames.push(names.pop());
                }
                scores.push(temp);
                names.push(tempStr);
            }
        }
        printTable(names, scores);

        FileInteraction.writeFile(names, scores);
    }

    static void printTable(Stack names, Stack scores) {
        Stack temp = new Stack(10), temp2 = new Stack(10);
        String tempName = "";
        int tempScore = 0;
        System.out.println("High Score Table");
        for (int i = 0; i < 10; i++) {
            tempName = names.pop().toString();
            tempScore = Integer.parseInt(scores.pop().toString());
            System.out.printf("%-6s %-10d\n", tempName, tempScore);
            temp.push(tempName);
            temp2.push(tempScore);
        }
        while (!temp.isEmpty()) {
            names.push(temp.pop());
            scores.push(temp2.pop());
        }
    }

    static boolean finished(Queue wordQueue, Queue blankWordQueue) {
        boolean flag = true;
        String temp1 = "", temp2 = "";
        for (int i = 0; i < wordQueue.size(); i++) {
            temp1 = wordQueue.dequeue().toString();
            wordQueue.enqueue(temp1);
            temp2 = blankWordQueue.dequeue().toString();
            blankWordQueue.enqueue(temp2);
            if (!temp1.equalsIgnoreCase(temp2)) {
                flag = false;
            }
        }

        return flag;

    }

    static String randomLetter(Stack alphaStack) {
        Stack temp = new Stack(alphaStack.size());
        String letter = "";
        int rndNum = 0;
        Random rnd = new Random();
        while (letter.equals("")) {
            rndNum = rnd.nextInt(0, 26);
            for (int i = 0; i < rndNum; i++) {
                Object temp2 = alphaStack.pop();
                if (temp2 != null)
                    temp.push(temp2.toString());
                else
                    temp.push(null);
            }
            Object c = alphaStack.pop();
            if (c != null) {
                letter = c.toString();
                alphaStack.push(null);
            } else
                alphaStack.push(c);

            while (!temp.isEmpty()) {
                alphaStack.push(temp.pop());
            }
        }

        return letter;
    }

    static String makeString(Queue blankQueue) {
        String word = "";
        for (int i = 0; i < blankQueue.size(); i++) {
            String c = blankQueue.dequeue().toString();
            word += c;
            blankQueue.enqueue(c);
        }

        return word;
    }

    static int setBlankQueue(Queue wordQueue, Queue blankQueue, String letter) {
        int counter = 0;
        for (int i = 0; i < wordQueue.size(); i++) {
            String wordLetter = wordQueue.dequeue().toString();
            if (wordLetter.equalsIgnoreCase(letter)) {
                blankQueue.dequeue();
                blankQueue.enqueue(wordLetter);
                wordQueue.enqueue(wordLetter);
                counter++;
            } else {
                blankQueue.enqueue(blankQueue.dequeue());
                wordQueue.enqueue(wordLetter);
            }
        }
        if (counter == 0)
            return 1;
        else
            return counter;
    }

    static void putWord(Queue wordQueue, Queue blankQueue, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            wordQueue.enqueue(chars[i]);
            if (chars[i] == ' ')
                blankQueue.enqueue(' ');
            else
                blankQueue.enqueue('-');
        }
    }

    static String retriveWord(Stack countriesStack, int nth) {
        Stack temp = new Stack(countriesStack.size());
        String word = "";
        for (int i = 0; i < nth; i++) {
            temp.push(countriesStack.pop());
        }
        word = countriesStack.peek().toString();
        while (!temp.isEmpty()) {
            countriesStack.push(temp.pop());
        }
        return word;
    }

    static String alphaString(Stack alphaStack) {
        Stack temp = new Stack(alphaStack.size());
        String str = "", tempStr = "";
        while (!alphaStack.isEmpty()) {
            Object c = alphaStack.pop();
            if (c != null) {
                tempStr = c.toString();
                str += tempStr;
                temp.push(tempStr);
            } else
                temp.push(null);

        }
        while (!temp.isEmpty()) {
            alphaStack.push(temp.pop());
        }
        return str;
    }

    static int turnWheel() {
        Random rnd = new Random();
        int bonus = -2;
        int stopped = rnd.nextInt(1, 9);
        switch (stopped) {
            case 1:
                bonus = 10;
                break;
            case 2:
                bonus = 50;
                break;
            case 3:
                bonus = 100;
                break;
            case 4:
                bonus = 250;
                break;
            case 5:
                bonus = 500;
                break;
            case 6:
                bonus = 1000;
                break;
            case 7:
                bonus = -1;
                break;
            case 8:
                bonus = 0;
                break;
        }

        return bonus;
    }

    static Stack alphaStack() {
        Stack s = new Stack(26);
        int i = 0;
        while (!s.isFull()) {
            s.push((char) (90 - i));
            i++;
        }
        return s;
    }

    static Stack sort(Stack inputStack) {
        Stack temp = new Stack(200);
        Stack sorted = new Stack(200);
        String tempStr;
        while (!inputStack.isEmpty()) {
            tempStr = inputStack.pop().toString();

            while (!temp.isEmpty() && temp.peek().toString().compareTo(tempStr) > 0) {
                inputStack.push(temp.pop());
            }
            temp.push(tempStr);
        }

        while (!temp.isEmpty()) {
            sorted.push(temp.pop());
        }
        return sorted;
    }
}
