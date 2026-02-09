package experiments.algo;

import java.util.Arrays;
import java.util.Random;

import static java.lang.IO.println;

public class MergeS {

    void main(String[] args) {
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        println("before merge sort" + Arrays.toString(args));
        doMergeSort(array);
        println("after merge sort" + Arrays.toString(args));


    }

    private void doMergeSort(int[] array) {
        int len = array.length;
        if (len < 2) {
            return;
        }
        int mid = len / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, len);
        doMergeSort(left);
        doMergeSort(right);

        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[index] = left[leftIndex];
                leftIndex++;
            } else {
                array[index] = right[rightIndex];
                rightIndex++;
            }
            index++;
        }
        while (leftIndex < left.length) {
            array[index] = left[leftIndex];
            leftIndex++;
            index++;
        }
        while (rightIndex < right.length) {
            array[index] = right[rightIndex];
            rightIndex++;
            index++;
        }
    }
}
