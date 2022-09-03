package com;

import java.io.*;
import java.time.Instant;
import java.util.Arrays;

/**
 * 快速排序
 * 1.单边，以最右边为基准值
 * 2.双边，以最左边为基准值
 * @author xtc
 * @create 2022-09-03 12:51
 */
public class QuickSort {

    public static void main(String[] args) throws IOException {
        int[] array = ArrayUtil.readArray();
//        System.out.println(Arrays.toString(array));
        long start = Instant.now().toEpochMilli();
        quickSort(array);
        long end = Instant.now().toEpochMilli();
        System.out.println(end - start);
//        System.out.println(Arrays.toString(array));
        System.out.println(count);
    }

    /**
     * 快排启动方法
     */
    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快排递归方法
     */
    private static void quickSort(int[] array, int l, int r) {
        if(l >= r){
            return;
        }
        int p = partition3(array, l, r);
        quickSort(array, l, p-1);// 左边再快排
        quickSort(array, p + 1, r);// 右边再快排
    }

    /**
     * 1.以右边为基准值，单边
     * 将比基准值大的放在左边，比基准值小的放在右边，返回基准值索引
     * 796911
     */
    public static int partition(int[] array, int l, int r){
        int p = r - 1;
        int pV = array[r];
        while(l <= p){
            if(array[l] > pV){
                if(l != p){
                    swap(array, l, p);
                }
                p--;
            }else{
                l++;
            }
        }
        if(l != r){
            swap(array, l, r);
        }
//        System.out.println(Arrays.toString(array));
        return l;
    }

    /**
     * 1.以右边为基准值，单边
     * 将比基准值大的放在左边，比基准值小的放在右边，返回基准值索引
     * 811837
     * 881210
     */
    public static int partition2(int[] array, int l, int r){
        int p = l;// 设置最后基准值在开始位置
        int pV = array[r];
        for (int i = l; i < r; i++) {
            if(array[i] < pV){// 如果当前值小于基准值
                if(i != p){// 等于就不用交换了
                    swap(array, i ,p);
                }
                p++;// 基准值不在这里，前移一位
            }
        }

        // 最后将基准位置的值和选定的基准值做交换
        if(p != r){
            swap(array, p, r);
        }

        return p;
    }

    /**
     * 2.以左边为基准，双边
     * 将比基准值大的放在左边，比基准值小的放在右边，返回基准值索引
     */
    public static int partition3(int[] array, int l, int r){
        int pV = array[l];
        int i = l;
        int j = r;
        while(i < j){
            while(i < j && array[j] > pV){// 找到小于等于的
                j--;
            }
            while(i < j && array[i] <= pV){// 找到大于的
                i++;
            }
            if(i != j){
                swap(array, i, j);
            }
        }
        if(i != l){
            swap(array, i, l);
        }
        return i;
    }
    static int count = 0;

    private static void swap(int[] array, int i, int j) {
        count++;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
