package streamapi;

import java.util.List;

public class Reduce {

    List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    void main() {
        reduce();
        reduceWithIdentity();
        reduceWithCombined();
    }
    
    private void reduce() {
        System.out.println(list.stream().reduce((subtotal, x) -> subtotal + x).orElse(0));
    }

    private void reduceWithIdentity() {
        System.out.println(list.stream().reduce(0, (subtotal, x) -> subtotal + x).intValue() + " with identity");
    }

    private void reduceWithCombined() {
        System.out.println(list.parallelStream().reduce(0, (x, y) -> x + y * y, Integer::sum));
        // Third parameter combiner is used to combine parallelStream out put
        System.out.println(list.stream().map(x -> x * x).reduce(0, (subtotal, x) -> subtotal + x).intValue() + "ye");
    }
}
