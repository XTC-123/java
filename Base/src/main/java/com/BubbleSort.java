package com;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author xtc
 * @create 2022-08-31 21:03
 */
public class BubbleSort {

    @Test
    public void test(){
        int[] array = {1,1,51,6,32,15,68,12,2,14,5,651,321,3865,85234,554565};

        bubbleSort2(array);

        System.out.println(Arrays.toString(array));


    }

    public void bubbleSort(int[] array){
        int count = 0;
        int maxIndex = array.length - 1;
        while(maxIndex > 0){
            for (int i = 0; i < maxIndex; i++){
                count++;
                if(array[i] > array[i + 1]){
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            maxIndex--;
        }
        System.out.println(count);
    }

    public void bubbleSort1(int[] array){
        int count = 0;
        int maxIndex = array.length - 1;
        while(maxIndex > 0){
            boolean flag = true;// 优化
            for (int i = 0; i < maxIndex; i++){
                count++;
                if(array[i] > array[i + 1]){
                    flag = false;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            if(flag) break;
            maxIndex--;
        }
        System.out.println(count);
    }

    public void bubbleSort2(int[] array){
        int count = 0;
        int maxIndex = array.length - 1;
        while(maxIndex > 0){
            int swapIndex = 0;// 优化
            for (int i = 0; i < maxIndex; i++){
                count++;
                if(array[i] > array[i + 1]){
                    swapIndex = i;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            maxIndex = swapIndex;
        }
        System.out.println(count);
    }

}
