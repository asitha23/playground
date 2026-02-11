package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.lang.IO.println;

public class LongestBalancedSub {

    /**
     * <a href="https://leetcode.com/problems/longest-balanced-subarray-i/description/?envType=daily-question&envId=2026-02-10">Link</a>
     * @param nums
     * @return
     */
    public int longestBalanced(int[] nums) {
        int maxLength = 0;
        println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> distinctEvens = new HashSet<>();
            Set<Integer> distinctOdds = new HashSet<>();

            for (int j = i; j < nums.length; j++) {
                int val = nums[j];

                // Categorize the distinct number
                if (val % 2 == 0) {
                    distinctEvens.add(val);
                } else {
                    distinctOdds.add(val);
                }

                // Check if the current subarray [i...j] is balanced
                if (distinctEvens.size() == distinctOdds.size()) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    void main() {
        int[] array = new int[]{1,3,5,6,8,10};
//        Random random = new Random();
//        for (int i = 0; i < array.length; i++) {
//            array[i] = i;//random.nextInt(10);
//        }

        println(longestBalanced(array));
    }
}
