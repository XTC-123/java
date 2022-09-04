package com;

import cn.hutool.core.date.StopWatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 比较ArrayList和LinkedList的特性
 * ArrayList：
 *     1.内部存储结构是数组，需要连续的内存空间
 *     2.随机访问快（按数组下标查询）
 *     3.尾部插入、删除性能可以，其他部分插入、删除都会移动数据，因此性能会低
 *     4.可以利用CPU缓存，局部性原理
 * LinkedList：
 *     1.内部存储结构是双向链表，不需要连续的内存空间
 *     2.随机访问慢（需要遍历链表）
 *     3.头尾插入删除性能高
 *     4.占用内存多
 *
 * 对于CPU缓存，局部性原理
 *     CPU在将需要的数据加载到CPU缓存中时，会将连续内存的数据一并加载到缓存中
 *     因为ArrayList是连续的内存空间，所以再使用后面的元素时，不用再从内存加载，直接使用即可
 *     然而LinkedList内存并不是连续的，所以，就算加载了相连的内存，也不一定是需要用到的，可能仍然需要去内存中加载
 * @author xtc
 * @create 2022-09-04 16:03
 */
@SuppressWarnings("all")
public class CompareArrayListAndLinkedList {

    /**
     * 测试随机访问
     */
    @Test
    public void testRandomAccess(){
        for (int i = 0; i < 5; i++) {
            int[] array = randArray();
            List<Integer> arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
            LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("ArrayList");
            arrayList.get(500);
            stopWatch.stop();
            stopWatch.start("LinkedList");
            linkedList.get(500);
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());

        }
    }
    /**
     * 测试头部插入
     */
    @Test
    public void testAddFirst(){
        for (int i = 0; i < 5; i++) {
            int[] array = randArray();
            List<Integer> arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
            LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("ArrayList");
            arrayList.add(0, 1);
            stopWatch.stop();
            stopWatch.start("LinkedList");
            linkedList.addFirst(1);
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());

        }
    }

    /**
     * 测试尾部插入
     */
    @Test
    public void testAddLast(){
        for (int i = 0; i < 5; i++) {
            int[] array = randArray();
            List<Integer> arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
            LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("ArrayList");
            arrayList.add(1);
            stopWatch.stop();
            stopWatch.start("LinkedList");
            linkedList.add(1);
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());

        }
    }

    /**
     * 测试中间插入
     */
    @Test
    public void testAddMid(){
        for (int i = 0; i < 5; i++) {
            int[] array = randArray();
            List<Integer> arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
            LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("ArrayList");
            arrayList.add(arrayList.size() >>> 2,1);
            stopWatch.stop();
            stopWatch.start("LinkedList");
            linkedList.add(linkedList.size() >>> 2, 1);
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());

        }
    }

    public int[] randArray(){
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length);
        }
        return array;
    }

}
