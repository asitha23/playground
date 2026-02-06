package leetcode;

import static java.lang.IO.println;

public class TransformedCyclicArray {
    /**
     * <a href="https://leetcode.com/problems/transformed-array/description/?envType=daily-question&envId=2026-02-05">Link</a>
     * @param nums
     * @return
     */
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[((i + nums[i]) % n + n) % n];
        }
        return ans;
    }

    void main() {
        println(((6 % 5) + 5) % 5);
    }
}
