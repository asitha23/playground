package leetcode;

import java.util.Arrays;

import static java.lang.IO.println;

public class BalancedArray {
    /**
     * a array is said to be blanced when its minimum values times k is grater than or equal to max value
     * find minimum number of removes should be doing to make nums array balanced
     * <a href="https://leetcode.com/problems/minimum-removals-to-balance-array/description/?envType=daily-question&envId=2026-02-06">link</a>
     * @param nums array
     * @param k multiplier
     * @return int
     */
    public int minRemoval(int[] nums, int k) {
        if (nums.length == 1)
            return 0;
        Arrays.sort(nums);
        if ((long)nums[0] * k >= (long)nums[nums.length - 1]){
            return 0;
        }
        int left = 0;
        int maxWindow = 0;

        for  (int right = 0; right < nums.length; right++) {
            while(nums[right] > (long)nums[left] * k) {
                left++;
            }
            maxWindow = Math.max(maxWindow, right - left + 1);
        }
        return nums.length - maxWindow;
    }

    void main() {
        BalancedArray balancedArray = new BalancedArray();
        println(balancedArray.minRemoval(new int[]{8,99,65,16,39}, 3));
        //          26   34
        //[ 8, 16, 39, 65, 99 ]
        //   24 , 69 ,
    }
}
