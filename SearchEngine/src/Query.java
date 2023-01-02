
public class Query {

    public String searchWords(HashTable<String> table, String words, int[] txtWordCount) {
        String[] wordArr = words.toLowerCase().split(" ");
        if (!_checkValidity(wordArr))
            return "Invalid Input";

        LinkedList<Integer, Integer> filesContainWords = new LinkedList<>();
        for (String word : wordArr) {
            _getFilesContainsWords(filesContainWords, word, table);
        }

        return _getMostRelevant(filesContainWords, txtWordCount);
    }

    private String _getMostRelevant(LinkedList<Integer, Integer> list, int[] wordCount) {
        float highest = 0f;
        int index = -1;
        ListIterator<Integer, Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            Node<Integer, Integer> temp = iterator.next();
            float ratio = (float) temp.getValue() / (float) wordCount[temp.getKey()];
            if (highest < ratio) {
                highest = ratio;
                index = (int) temp.getKey();
            }
        }
        if (index == -1)
            return "Files Does Not contain words";

        return String.format("%03d", index) + ".txt";
    }

    private boolean _checkValidity(String[] words) {
        if (words.length < 1 || words[0] == "")
            return false;

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (!Character.isLetter(word.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    private void _getFilesContainsWords(LinkedList<Integer, Integer> list, String word, HashTable<String> table) {
        if (table.getEntry(word) != null) {
            ListIterator<Integer, Integer> entryListIterator = table.getEntry(word).getValue().iterator();
            while (entryListIterator.hasNext()) {
                _checkAndAdd(entryListIterator.next(), list);
            }
        }
    }

    // checks if the list already contains the file
    private void _checkAndAdd(Node<Integer, Integer> node, LinkedList<Integer, Integer> list) {
        ListIterator<Integer, Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.getNext().getKey() == node.getValue()) {
                Node<Integer, Integer> temp = iterator.next();
                temp.setValue(temp.getValue() + node.getValue());
                return;
            }
            iterator.next();
        }

        list.add(new Node<>(node.getKey(), node.getValue()));

    }
}
