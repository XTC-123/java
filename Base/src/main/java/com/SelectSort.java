package com;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序
 * @author xtc
 * @create 2022-08-31 21:37
 */
public class SelectSort {

    @Test
    public void test(){
        int[] array = {1,23,456,13,156,4684,8956,43,1216,54,6};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void selectSort(int[] array) {
        int minIndex = 0, minValueIndex = 0;
        while(minIndex < array.length - 1){
            for (int i = minIndex + 1; i < array.length; i++) {
                if(array[i] < array[minValueIndex]){
                    minValueIndex = i;
                }
            }
            if(minIndex != minValueIndex){
                int temp = array[minIndex];
                array[minIndex] = array[minValueIndex];
                array[minValueIndex] = temp;
            }
            minIndex++;
            minValueIndex = minIndex;
        }

    }

}
