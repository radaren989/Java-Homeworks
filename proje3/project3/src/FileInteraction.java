import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInteraction {
    static DoubleLinkedList readScoreFile() {
        try {
            DoubleLinkedList scoreTable = new DoubleLinkedList();
            File scoreFile = new File("HighScoreTable.txt");
            Scanner scan = new Scanner(scoreFile);
            while (scan.hasNextLine()) {
                String str[] = scan.nextLine().split(" ");
                // str[0] - name / str[1] - surname /
                Player temp = new Player();
                temp.name = str[0];
                temp.surname = str[1];
                temp.score = Float.parseFloat(str[2]);
                scoreTable.addSorted(temp);
            }
            scan.close();
            return scoreTable;
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}