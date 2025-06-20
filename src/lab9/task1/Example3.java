package lab9.task1;

public class Example3 {
    private static int step = 0;

    public static void space() {
        for (int i = 0; i < step; i++) {
            System.out.print(" ");
        }
    }

    public static void m(int x) {
        space();
        System.out.println(x + "->");
        step++;
        int next_value = 2 * x + 1;
        if (next_value < 20) {
            m(next_value);
        }
        step--;
        space();
        System.out.println(x + "<-");
    }

    public static void main(String[] args) {
        m(0);
    }
}
