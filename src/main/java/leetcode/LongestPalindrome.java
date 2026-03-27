package leetcode;

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int length = 0;
        int max = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int l1 = longestPalindrome(s, i, i);
            int l2 = longestPalindrome(s, i, i + 1);
            int currentMax = Math.max(l1, l2);
            if (max < currentMax) {
                max = currentMax;
                start = i - ((max - 1)/2);
                end = i + (max/2);
            }
        }
        return s.substring(start, end + 1);
    }

    private int longestPalindrome(String s, int min, int max) {
            while (max < s.length() && min > 0 && s.charAt(min) == s.charAt(max)) {
                max++;
                min--;
            }
            return max - min - 1;
    }
}
