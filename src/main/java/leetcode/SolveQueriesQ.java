package leetcode;

import java.util.*;

public class SolveQueriesQ {
    //https://leetcode.com/problems/closest-equal-element-queries/?envType=daily-question&envId=2026-04-16
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        // Map each number to a list of indices where it appears
        Map<Integer, List<Integer>> valToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>(queries.length);

        for (int targetIdx : queries) {
            int targetVal = nums[targetIdx];
            List<Integer> indices = valToIndices.get(targetVal);

            // If the number only appears once, there's no "other" instance
            if (indices.size() <= 1) {
                result.add(-1);
                continue;
            }

            // Find where targetIdx sits in our sorted list of indices
            int pos = Collections.binarySearch(indices, targetIdx);

            // Check immediate neighbors in the sorted index list
            int prevIdx = (pos > 0) ? indices.get(pos - 1) : indices.get(indices.size() - 1);
            int nextIdx = (pos < indices.size() - 1) ? indices.get(pos + 1) : indices.get(0);

            // Calculate circular distances
            int dist1 = getCircularDist(targetIdx, prevIdx, n);
            int dist2 = getCircularDist(targetIdx, nextIdx, n);

            result.add(Math.min(dist1, dist2));
        }

        return result;

    }

    private int getCircularDist(int i, int j, int n) {
        int absoluteDist = Math.abs(i - j);
        return Math.min(absoluteDist, n - absoluteDist);
    }

    void main() {
        int[] nums = {1,3,1,4,1,3,2};
        int[] queries = {0,3,5};
        System.out.println(solveQueries(nums, queries));
    }
}
