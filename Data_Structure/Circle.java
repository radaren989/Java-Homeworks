public class Circle extends Point {
    private int r;

    Circle(int r) {
        super(0, 0);
        this.r = r;
    }

    Circle(int x, int y, int r) {
        super(x, y);
        this.r = r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }
}
