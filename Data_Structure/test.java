public interface MaxHeapInterface<T extends Comparable<? super T>> {

}

public class test {

    public static void main(String[] args) {
        Parent a = new subclass1();
        Parent b = new subclass2();
        a.Print();
        b.Print();
    }

}