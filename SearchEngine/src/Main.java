
// main file to run
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable<String> table = new HashTable<>();
        // index -> text number
        // it begins at index 1
        int[] txtWordCount = new int[101];

        new FileInteractions().fillTable(table, txtWordCount);
        System.out.print("Give words: ");
        Scanner input = new Scanner(System.in);
        System.out.println("The Most Relevant File is: " +
                new Query().searchWords(table, input.nextLine(), txtWordCount));
        input.close();
    }
}
