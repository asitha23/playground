package experiments.algo;

import java.util.Arrays;
import java.util.Random;

import static java.lang.IO.println;

public class InsertionSort {

    void main() {
      int[] array = new int[10];
      Random rand = new Random();
      for (int i = 0; i < array.length; i++) {
          array[i] = rand.nextInt(100);
      }

      println("before sort" + Arrays.toString(array));
      insertSort(array);
      println("after sort" + Arrays.toString(array));
    }

    private void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
