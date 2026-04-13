package leetcode;

public class XorSingle {
    void main() {
        int[] array = new int[] {35, 35, 67, 774, 67};
        int ans = 0;
        for (int num :array) {
            ans ^= num;
        }
        System.out.println(ans);
    }
}
