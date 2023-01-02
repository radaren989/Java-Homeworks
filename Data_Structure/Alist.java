
public class Alist<T> {
    private T[] list;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    Alist() {
        this(DEFAULT_CAPACITY);
    }

    Alist(int capacity) {
        if (capacity > MAX_CAPACITY)
            capacity = MAX_CAPACITY;
        else if (capacity < DEFAULT_CAPACITY)
            capacity = DEFAULT_CAPACITY;

        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity + 1];
        list = temp;
        numberOfEntries = 0;
    }

    public void add(T newEntry) {
        list[numberOfEntries] = newEntry;
        numberOfEntries++;
    }

    public void add(T newEntry, int index) {
        for (int i = numberOfEntries + 1; index < numberOfEntries - 1; i--) {
            list[i] = list[i - 1];
        }
        list[index] = newEntry;
        numberOfEntries++;
    }

    public T remove(int index) {
        T element = list[index];
        for (int i = index; index < numberOfEntries - 1; i++) {
            list[i] = list[i + 1];
        }

        numberOfEntries--;
        return element;
    }
}
