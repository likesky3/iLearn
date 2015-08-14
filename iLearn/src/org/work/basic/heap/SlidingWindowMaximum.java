package org.work.basic.heap;

import java.util.Arrays;
import java.util.Comparator;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Integer[] array = {1,2,3};
        Arrays.sort(array, new LargerComparator());
        for (int a : array) {
            System.out.println(a);
        }
    }
    
    
}
class LargerComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}
