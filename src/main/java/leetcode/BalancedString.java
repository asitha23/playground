package leetcode;

import static java.lang.IO.println;

/**
 *  <a href="https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/?envType=daily-question&envId=2026-02-07">link</a>
 */
public class BalancedString {

    public int minimumDeletions(String s) {
        if (s.length() == 1)
            return 0;
        if (s.indexOf('a') == -1 || s.indexOf('b') == -1)
        {
            return 0;
        }

        //b's which are appearing before first a needs to remove and tailing a's need to remove
        int start = s.indexOf('a');
        int end = s.lastIndexOf('b');
        int beforeNAfterCount = start + (s.length() - 1 - end);

        return beforeNAfterCount + minRemovals(s, start, end);
    }

    int minRemovals(String s, int start, int end) {
        int count = Integer.MAX_VALUE;
        int midIndex = s.indexOf("ab", start);

        while (midIndex >= 0 && midIndex < end) {
            int currCount = 0;
            for (int i = start; i < end; i++) {
                char c = s.charAt(i);
                if ((i < midIndex && c == 'b') || (i > midIndex && c == 'a')) {
                    currCount++;
                }
            }
            count = Math.min(currCount, count);
            midIndex = s.indexOf("ab", midIndex + 1);
        }
        return count;
    }

    /**
     * This the correct solution
     * @param s
     * @return
     */
    public int minDeletions(String s) {
        int deletions = 0;
        int bCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == 'b') {
                // If we see a 'b', we just increment our 'b' counter.
                // It doesn't break balance yet, but it might later.
                bCount++;
            } else {
                // We found an 'a'. If there are 'b's before it, we have a conflict.
                // We have two choices:
                // 1. Delete this 'a' (cost: previous deletions + 1)
                // 2. Keep this 'a' and delete one of the previous 'b's (cost: bCount)
                deletions = Math.min(deletions + 1, bCount);
            }
        }

        return deletions;
    }

    void main() {
        BalancedString bs = new BalancedString();
        println(bs.minimumDeletions("aabbbbaabababbbbaaaaaabbababaaabaabaabbbabbbbabbabbababaabaababbbbaaaaabbabbabaaaabbbabaaaabbaaabbbaabbaaaaabaa"));
        println(bs.minDeletions("aabbbbaabababbbbaaaaaabbababaaabaabaabbbabbbbabbabbababaabaababbbbaaaaabbabbabaaaabbbabaaaabbaaabbbaabbaaaaabaa"));
    }
}
