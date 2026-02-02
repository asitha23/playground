package leetcode;

import java.util.Arrays;

import static java.lang.IO.println;

public class DuplicateNumbersInOrderedArray {
    public int[] removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        if (arr.length == 1) {
            return arr;
        }
        int i=0;
        for(int j=1;j<arr.length;j++){
            if(arr[i]!=arr[j]){
                i++;
                arr[i]=arr[j];
            }
        }
        return arr;
    }

    void main() {
        DuplicateNumbersInOrderedArray dn = new DuplicateNumbersInOrderedArray();
        println(Arrays.toString(dn.removeDuplicates(new int[]{1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9})));
    }
}
