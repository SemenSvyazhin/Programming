package lab9.task1;

public class Example2 {
    public static void m(int x) {
        int next_value = 2 * x + 1;
        if (next_value < 20) {
            m(next_value);
        }
        System.out.println("x=" + x);
    }

    public static void main(String[] args) {
        m(0);
    }
}
