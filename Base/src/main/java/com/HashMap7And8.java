package com;

import org.junit.Test;

import java.util.HashMap;

/**
 * 对于HashMap 1.7 和 1.8比较和认识
 * @author xtc
 * @create 2022-09-05 20:19
 */
public class HashMap7And8 {

    @Test
    public void test(){
        /**
         * HashMap 1.8
         * 结构：数组，链表，红黑树
         * 数组长度必须是2的倍数，默认是 static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
         * 负载因子默认是0.75 static final float DEFAULT_LOAD_FACTOR = 0.75f;
         * 扩容阈值 = 负载因子 * 数组长度
         * 链表最大长度 static final int TREEIFY_THRESHOLD = 8;
         * 数组树化前最大长度必须小于 static final int MIN_TREEIFY_CAPACITY = 64;
         * key，value可以为null
         * 计算hash的方法 hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
         * 非线程安全
         *
         */
        // 在new HashMap，并没有初始化数组
        // 若没有指定初始容量，则默认为 16 static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
        // 若指定了初始容量，则为比指定容量大的第一个2的倍数的容量
        HashMap<String, String> map = new HashMap<>();
        // 在第一次put时，会对数组初始化
        // put时，尾插法
        //   若对应存放的索引位置为空：则直接new一个node存放，若超过最大容量，则扩容
        //   若对应存放的不为空：则判断第一个key是否与当前存放的key相等，
        //      若相等：则直接替换值
        //      若不相等：则判断是否第一个节点是否是红黑树
        //          若是：调用红黑树的put方法，若超过最大容量，则扩容
        //          若不是：则往链表后面找
        //              若找到相同key的：则替换值
        //              若没找到相同key的：则新建一个节点，判断是否超出链表最大长度 static final int TREEIFY_THRESHOLD = 8;
        //                  若否：添加成功，若超过最大容量，则扩容
        //                  若是：则判断当前数组长度是否大于等于 static final int MIN_TREEIFY_CAPACITY = 64;
        //                      若否：则扩容
        //                      若是：则树化链表
        map.put("", "");

        // remove时
        //   若数组为空，或者计算到的索引位置为空，则直接结束
        //   若索引位置有值，是否是该key
        //      若是：
        //          若是红黑树，调用红黑树的remove方法
        //          非红黑树：直接删除
        //      若不是，并且有下一个节点，判断索引位置值是否是红黑树
        //          是：调用红黑树get方法，然后调用红黑树的remove方法删除节点
        //          否：在链表中找到对应key，删除
        //
        map.remove("");

        /**
         * resize 扩容
         *      1.计算扩容后的容量，最大元素
         *      2.新建数组，转移元素
         *          遍历整个数组
         *              红黑树调用split方法分割
         *              非红黑树，将链表分为两个
         *                  第一个：原位置 (e.hash & oldCap) == 0
         *                  第二个：在原位置+原数组长度 (e.hash & oldCap) != 0
         */

        /**
         * 线程安全问题
         *      在多个线程同时put时，若put的是同一个位置，并且这个位置的元素是null，此时可能导致另一个被覆盖
         */
    }

    @Test
    public void test2(){
        /**
         * HashMap jdk 1.7
         * 结构：数组 + 链表
         * 数组长度必须是2的倍数、
         * 负载因子默认是0.75 static final float DEFAULT_LOAD_FACTOR = 0.75f;
         * 扩容阈值 = 负载因子 * 数组长度
         * key，value可以为null
         * 计算hash方法
         *      if (0 != h && k instanceof String) {
         *             return sun.misc.Hashing.stringHash32((String) k);
         *         }
         *         h ^= k.hashCode();
         *         h ^= (h >>> 20) ^ (h >>> 12);
         *         return h ^ (h >>> 7) ^ (h >>> 4);
         *  非线程安全
         */
        /**
         * new HashMap时 并没有初始化数组
         * 没有指定初始容量，则默认为 16 static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
         * 若指定了初始容量，则为比指定容量大的第一个2的倍数的容量
         */
        /**
         * 在第一次put时，会对数组初始化
         * put时，头插法
         *     找到已久存在的值
         *         是：替换
         *         否：若超出容量，则先扩容，再存放新元素
         */
        /**
         * remove时
         *      找到已存在，直接删除
         */
        /**
         * 线程安全问题
         *      当前Map大小，数组长度为2，size为1， key0，负载因子0.75，容量为1
         *      Thread1：put一个新的key1，并且这个key计算后保存到原来值的后面
         *      Thread2：put一个新的key2，并且这个key计算后保存到原来值的后面
         *      1. Thead2先进入transfer方法，再Thread1进入transfer方法
         *      2. Thread1 执行到 next = key1.next ， 此时 next = key2
         *      3. THread2 执行transfer完毕， {
         *          0:key0
         *          1:key2->key1   // 尾插法
         *      }
         *      4. Thread1 执行transfer完毕, {
         *          0:key0
         *          1:key1->key2->key1.... // 尾插法导致循环指向
         *      }
         *      5. 调用map.get() 此时死循环
         *
         */

    }



}
