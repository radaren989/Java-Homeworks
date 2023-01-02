package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    static String[] readFile(String fileName) {
        try {
            File mapFile = new File(fileName);
            Scanner scan = new Scanner(mapFile);
            int counter = 0;
            while (scan.hasNextLine()) {
                counter++;
                scan.nextLine();
            }
            scan.close();
            String[] map = new String[counter];
            Scanner scan2 = new Scanner(mapFile);
            int counter2 = 0;
            while (scan2.hasNextLine()) {
                map[counter2] = scan2.nextLine();
                counter2++;
            }
            scan2.close();

            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
