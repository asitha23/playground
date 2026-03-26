package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindrome {

    /***
     * <link href="https://leetcode.com/problems/longest-palindrome/description/"> link </a>
     * @param s input
     * @return int
     */
    private int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int length = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                length += 2;
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        if (!set.isEmpty())
            length++;
        return length;
    }

    void main() {
        String s = "abcdefg";
        System.out.println(longestPalindrome(s));
    }
}
