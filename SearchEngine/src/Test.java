// for testing diffient hashing algorithms
public class Test {
    public static void main(String[] args) {
        HashTable<String> table = new HashTable<>();
        // index -> text number
        // inside -> number of words
        // it begins at index 1
        int[] txtWordCount = new int[101];
        String[] searchWords = new String[1000];

        long tableFillStart = System.currentTimeMillis();
        new FileInteractions().fillTable(table, txtWordCount);
        Long tableFillEnd = System.currentTimeMillis();

        new FileInteractions().getSearchWords(searchWords);

        Query q = new Query();
        Long searchStart = System.currentTimeMillis();
        for (int i = 0; i < searchWords.length; i++) {
            q.searchWords(table, searchWords[i], txtWordCount);
        }
        Long searchEnd = System.currentTimeMillis();

        System.out.println("Fill Time:" + (tableFillEnd - tableFillStart) +
                "\nSearch Time:" + (searchEnd - searchStart) +
                "\nCollisions: " + table.getCollisionCount());

    }
}
