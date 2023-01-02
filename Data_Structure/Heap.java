public final class Heap<T extends Comparable<? super T>> {
    private T[] heap;
    private int lastIndex;
    private boolean isInitialized = false;
    private static final int DEFAULT_CAPACİTY = 25;
    private static final int MAX_CAPACİTY = 10000;

    Heap(int initialCapacity) {
        if (initialCapacity < DEFAULT_CAPACİTY) {
            initialCapacity = DEFAULT_CAPACİTY;
        }

        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        isInitialized = true;

    }

    public void add(T newEntry) {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while (parentIndex > 0 & newEntry.compareTo(heap[parentIndex]) > 0) {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }

        heap[newIndex] = newEntry;
        lastIndex++;
    }

    public T remove() {
        T root = null;

        if (!isEmpty()) {
            root = heap[0];
            heap[0] = heap[lastIndex];
            reheap(0);
        }

        return root;

    }

    public void reheap(int rootIndex) {
        T orphan = heap[rootIndex];
        int leftChildIndex = rootIndex * 2 + 1;
        boolean done = false;

        while (!done && (lastIndex > rootIndex)) {

            int largChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if (largChildIndex <= lastIndex && heap[largChildIndex].compareTo(heap[rightChildIndex]) < 0) {
                largChildIndex = rightChildIndex;
            }
            if (orphan.compareTo(heap[largChildIndex]) < 0) {
                heap[rootIndex] = heap[largChildIndex];
                rootIndex = largChildIndex;
                leftChildIndex = rootIndex * 2 + 1;
            } else
                done = true;
        }

        heap[rootIndex] = orphan;
    }

}
