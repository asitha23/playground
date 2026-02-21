package leetcode;

import java.util.HashMap;
import java.util.Map;

class LongestBalancedString {
    public int longestBalanced(String s) {
        if (s.length() <= 2) {
            return s.length();
        }
        int maxLength = 0;
        Map<Character, Integer> counts = new HashMap<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                char c = charArray[j];
                int count = counts.getOrDefault(c, 0) + 1;
                counts.put(c, count);
                if (counts.values().stream().allMatch(val -> val.equals(count))) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
            counts.clear();
        }
        return maxLength;
    }

    void main() {
        LongestBalancedString longestBalanced = new LongestBalancedString();
        System.out.println(longestBalanced.longestBalanced("zzabccy"));
    }
}