import java.util.HashSet;
import java.util.Set;

public class IsHappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        if (n < 1)
            return false;
        set.add(n);
        while (n > 0) {
            int next = 0;
            while (n > 0) {
                int rest = n % 10;
                n /= 10;
                next += rest* rest;
            }
            n = next;
            if (!set.add(n)) {
                return false;
            }

        }
        return true;
    }

    void main() {
        System.out.println(isHappy(19));
    }
}
