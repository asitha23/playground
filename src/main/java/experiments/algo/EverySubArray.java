package experiments.algo;

import static java.lang.IO.print;
import static java.lang.IO.println;

public class EverySubArray {

    public void printEverySubArray(int[] nums) {
        int n = nums.length;

        // Outer loop to pick the starting index 'i'
        for (int i = 0; i < n; i++) {
            // Middle loop to pick the ending index 'j' (starting from 'i')
            for (int j = i; j < n; j++) {
                // Innermost loop to print/process elements from 'i' to 'j'
                System.out.print("[");
                for (int k = i; k <= j; k++) {
                    System.out.print(nums[k]);
                    if (k < j) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        }

    }

    void main() {
        printEverySubArray(new int[] {3, 2, 2, 5, 4});
    }
}
