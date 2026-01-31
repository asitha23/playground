package codility;// you can also use imports, for example:
 import java.util.*;
 import java.util.stream.IntStream;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");


public class MissingPositive {
    public static int findSmallestMissing(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int n = nums.length;

        // 1. Cyclic Sort: Place each number in its correct "slot"
        // nums[i] should be at index nums[i] - 1
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 2. Find the first index where the value doesn't match the slot
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 3. If all slots are filled correctly, the answer is n + 1
        return n + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void main(String[] args) {
        int[] test1 = {3, 4, -1, 1};
        System.out.println("Missing in {3, 4, -1, 1}: " + findSmallestMissing(test1)); // Output: 2

        int[] test2 = {1, 2, 0};
        System.out.println("Missing in {1, 2, 0}: " + findSmallestMissing(test2));    // Output: 3
    }

    public int solution(int[] A) {
        List<Integer> list = IntStream.of(A).boxed().toList();
        Collections.sort(list);

        int last = list.getLast();
        if (last <= 0)
            return 1;
        for (int i = 2; i <= last; i++) {
            if (!list.contains(i)){
                return i;
            }
        }
        return last;
    }
}