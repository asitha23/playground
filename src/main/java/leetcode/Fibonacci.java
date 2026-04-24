package leetcode;

public class Fibonacci {

    void main() {
        printFibonacci(13);
    }

    void printFibonacci(int n) {
        int first = 0;
        int second = 1;
        for  (int i = 0; i <= n; ++i) {
            System.out.println(first);
            int next = first + second;
            first = second;
            second = next;
        }
    }
}
