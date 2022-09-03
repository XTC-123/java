package com;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * 折半查找
 * @author xtc
 * @create 2022-08-31 19:48
 */
public class BinarySearch {

    @Test
    public void testBinarySearch(){
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,123,1234};

        int target = 1234;

        int index = binarySearch(a, target);
        System.out.println(index);

    }

    public int binarySearch(int[] array , int target){
        int left = 0,right = array.length - 1, mid;
        while(left <= right){
//            mid = (left + right) / 2;// × 若是数组比较长，可能会导致相加之和超出 int最大值
            mid = (left + right) >>> 1;// √ 因为这个是正数，不带符号位的右移一位
//            mid = (left + right) >> 1;// × 带符号位的右移一位，因为这个是正数，所以不行，超出最大值后移动，变为负数
//            mid = left + (right - left) / 2;// √  left / 2 + right / 2 -> left - left / 2 + right / 2 -> left + (right - left) / 2
            if(array[mid] == target){
                return mid;
            }else if(array[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }


}
