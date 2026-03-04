package leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.IO.println;

public class SpecialPositions {

    public int specialPositions(int[][] nums) {
        int rowCount = nums.length;
        int colCount = nums[0].length;
        int[] rowSum = Arrays.stream(nums).mapToInt(row -> Arrays.stream(row).sum()).toArray();
        int[] colSum = IntStream.range(0, colCount).map(col -> Arrays.stream(nums).mapToInt(num -> num[col]).sum()).toArray();
        int count = 0;
        for (int  i = 0; i < rowCount; i++) {
            for  (int  j = 0; j < colCount; j++) {
                if (rowSum[i] == 1 && colSum[j] == 1 && nums[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    static void main() {
        SpecialPositions sp = new SpecialPositions();
        println(sp.specialPositions(new int[][]{{1,0,0,0},{0,1,1,1},{0,1,1,1}}));
    }
}
