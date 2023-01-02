public class OrderedPair<T extends Number> implements Pairable<T> {
    private T first, second;

    OrderedPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public void changeOrder() {
        T temp = this.first;
        this.first = this.second;
        this.second = temp;
    }
}
