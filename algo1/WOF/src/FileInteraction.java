import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileInteraction {
    public static Stack readFile(String fileName) {
        try {
            File readfile = new File(fileName);
            Scanner scanLines = new Scanner(readfile);
            int numOfLines = 0;
            while (scanLines.hasNextLine()) {
                scanLines.nextLine();
                numOfLines++;
            }
            scanLines.close();

            Stack newStack = new Stack(numOfLines);
            Scanner scanCountry = new Scanner(readfile);
            while (scanCountry.hasNextLine()) {
                newStack.push(scanCountry.nextLine());
            }
            scanCountry.close();

            return newStack;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFile(Stack names, Stack scores) {
        try {
            FileWriter writer = new FileWriter("HighScoreTable.txt");
            while (!scores.isEmpty()) {
                writer.write(names.pop().toString() + " " + scores.pop().toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
