import static org.junit.Assert.*;

/**
 * An Integer tester created by Flik Enterprises.
 */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }
    // the problem lies in Integer 对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
}