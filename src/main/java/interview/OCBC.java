package interview;

import java.util.List;

import static java.lang.IO.println;

public class OCBC {

    static void main() {
        Integer a = 127;
        Integer b = 127;
        println("(a == b) " + (a == b));
        Integer c = 200;
        Integer d = 200;
        int e = 127;
        int f = 200;
        println("c == d " + (c == d));
        println("e == a " + (e == a));
        println("f == d " + (f == d));
        List<String> l1 = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        String.join(",", l1);
        Float f1 = 5F;
        Float f2 = 5F;
        float f3 = 5F;
        float f4 = 5F;
        Double d1 = 5D;
        Double d2 = 5D;
        double d3 = 5D;
        double d4 = 5D;
        println("f1 == f2 " + (f1 == f2));
        println("f4 == f3 " + (f4 == f3));
        println("d1 == d2 " + (d1 == d2));
        println("d3 == d4 " + (d3 == d4));

    }

    public Long method1(int[] arr) {
        Long a  = 0L;
        for (int i : arr) {
            a +=i;
        }
        return a;
    }

    public long method2(int[] arr) {
        long a  = 0L;
        for (int i : arr) {
            a +=i;
        }
        return a;
    }
}
