package com;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 * @author xtc
 * @create 2022-08-31 22:06
 */
public class InsertSort {

    @Test
    public void test(){
        int[] array = {1,2,556,2,3,514,651,231,645,4531,31,3216,51,321};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void insertSort(int[] array) {
        int maxSortIndex = 0;// 排序序的最后一个位置
        int currentIndex = 1;// 要插入的数的索引位置
        while(currentIndex < array.length){
            int insertValue = array[currentIndex];
            while(currentIndex > 0 && insertValue < array[currentIndex - 1]){
                array[currentIndex] = array[currentIndex - 1];
                currentIndex--;
            }
            array[currentIndex] = insertValue;
            maxSortIndex++;
            currentIndex = maxSortIndex + 1;
            System.out.println(Arrays.toString(array));
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
