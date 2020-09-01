package sum;

/**
 * 求和
 * @author w.dehai
 */
public class SumUtil {
    public static void main(String[] args) {

    }

    int sum(int a, int b) {
        if (a <= 0 || b >= 1000000) {
            throw new IllegalArgumentException("a must greater than 0, b must less than 1000000");
        }
        return a + b;
    }
}
