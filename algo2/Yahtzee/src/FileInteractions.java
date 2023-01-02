import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileInteractions {

    static SingleLinkedList readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            SingleLinkedList scoreTable = new SingleLinkedList();
            while (scan.hasNextLine()) {
                String str = scan.nextLine() + " " + scan.nextLine();
                scoreTable.sortedInsert(str);
            }
            scan.close();

            return scoreTable;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeFile(String fileName, SingleLinkedList list) {
        try {

            FileWriter writer = new FileWriter(fileName);

            String[] str = list.returnList().split(" ");
            for (int i = 0; i < 20; i += 2) {
                String stra = str[i] + " " + str[i + 1] + "\n";
                writer.write(stra);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
