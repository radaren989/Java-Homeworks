public interface Pairable<T extends Number> {
    public T getFirst();

    public T getSecond();

    public void changeOrder();
}
