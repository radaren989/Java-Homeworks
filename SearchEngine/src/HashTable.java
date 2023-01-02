
public class HashTable<T> {
    private Entry<T, LinkedList<Integer, Integer>>[] table;
    private static final int DEFAULT_CAPACITY = 2477;
    private static final float LOAD_FACTOR = 0.5f;
    private int currentCapacity;
    private int numberOfEntries;
    private int collisionCount;

    HashTable() {
        @SuppressWarnings("unchecked")
        Entry<T, LinkedList<Integer, Integer>>[] temp = (Entry<T, LinkedList<Integer, Integer>>[]) new Entry[DEFAULT_CAPACITY];
        table = temp;
        numberOfEntries = 0;
        currentCapacity = DEFAULT_CAPACITY;
        collisionCount = 0;
    }

    public void add(Entry<T, LinkedList<Integer, Integer>> e, int txtNumber) {
        int index = _PAF(e.getKey());
        int availableIndex = _LP(index, table, e.getKey());
        if (table[availableIndex] == null) {
            LinkedList<Integer, Integer> tempList = new LinkedList<>();
            tempList.add(new Node<>(txtNumber, 1));
            e.setValue(tempList);
            table[availableIndex] = e;
            numberOfEntries++;
            _checkCapacity();
        } else
            _UpdateList(table[availableIndex].getValue(), txtNumber);

    }

    public Entry<T, LinkedList<Integer, Integer>> getEntry(T key) {
        int index = _PAF(key);
        int availableIndex = _LP(index, table, key);
        if (table[availableIndex] != null)
            return table[availableIndex];

        return null;
    }

    private void _IncreaseCapacity() {
        currentCapacity *= 2;
        currentCapacity = _getNextPrime(currentCapacity);

        @SuppressWarnings("unchecked")
        Entry<T, LinkedList<Integer, Integer>>[] temp = (Entry<T, LinkedList<Integer, Integer>>[]) new Entry[currentCapacity];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int index = _PAF(table[i].getKey());
                int availableIndex = _LP(index, temp, table[i].getKey());
                temp[availableIndex] = table[i];
            }
        }

        table = temp;
    }

    private void _checkCapacity() {
        if ((float) numberOfEntries / (float) currentCapacity >= LOAD_FACTOR)
            _IncreaseCapacity();

    }

    // Simple Summation Function
    @SuppressWarnings("unused")
    private int _SSF(T key) {
        int sum = 0;
        String keyStr = key.toString();
        for (int i = 0; i < keyStr.length(); i++) {
            sum += (int) keyStr.charAt(i);
        }
        return sum % currentCapacity;
    }

    // Linear Probing
    // I don't need to remove an enrty that is why entries don't have flag
    private int _LP(int index, Entry<T, LinkedList<Integer, Integer>>[] temp, T key) {
        while (temp[index] != null && !key.equals(temp[index].getKey())) {
            if (index == currentCapacity - 1)
                index = 0;

            collisionCount++;
            index++;
        }
        return index;
    }

    // Polynomial Accumulation Function
    private int _PAF(T key) {
        int sum = 0;
        String keyStr = key.toString();
        for (int i = 0; i < keyStr.length(); i++) {
            sum += (keyStr.charAt(i) - 96) * Math.pow(7, keyStr.length() - i - 1);
        }
        return sum % currentCapacity;
    }

    // Double Hashing
    @SuppressWarnings("unused")
    private int _DH(int index, Entry<T, LinkedList<Integer, Integer>>[] temp, T key) {
        int counter = 1;

        while (temp[index] != null && !key.equals(temp[index].getKey())) {
            index = (index + (counter * (31 - index % 31))) % currentCapacity;
            counter++;
            collisionCount++;
        }

        return index;
    }

    private void _UpdateList(LinkedList<Integer, Integer> list, int txtNumber) {
        ListIterator<Integer, Integer> li = list.iterator();
        while (li.hasNext()) {
            if ((int) li.getNext().getKey() == txtNumber) {
                Node<Integer, Integer> temp = li.next();
                temp.setValue(temp.getValue() + 1);
                return;
            }
            li.next();
        }

        list.add(new Node<>(txtNumber, 1));

    }

    public int getCollisionCount() {
        return collisionCount;
    }

    private int _getNextPrime(int num) {
        num++;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                num++;
                i = 2;
            } else {
                continue;
            }
        }
        return num;

    }
}