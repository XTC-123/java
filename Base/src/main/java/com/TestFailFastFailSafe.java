package com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试在迭代集合时，修改了集合中的元素时，应对方案
 *
 * fail-fast ： 若集合被修改了，则立即抛出异常 如：ArrayList
 * fail-safe ： 若集合被修改了，不会抛出异常，但是遍历的还是原集合，修改后的集合不会被遍历到 如：CopyOnWriteArrayList
 * @author xtc
 * @create 2022-09-04 14:30
 */
public class TestFailFastFailSafe {

    /**
     * fail-fast
     * 修改list的值后，抛出ConcurrentModificationException异常
     *
     * foreach 实际是调用了迭代器 Iterator
     * 在new Iterator时，会将 expectedModCount = modCount
     *     modeCount是每次ArrayList修改后，会修改modeCount
     *     expectedModCount则是在生成迭代器对象时 expectedModCount = modCount
     * 在ArrayList实现的Iterator中，每次获取下一个元素时，会调用checkForComodification方法，来检查是否发生了修改，判断expectedModCount == modCount
     *     若数组发生修改，则modCount发生改变，则会抛出异常
     *
     */
    @Test
    public void testFailFast() throws InterruptedException {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        new Thread(()->{
            try {
                Thread.sleep(100);
                list.add(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        for (Object o : list) {
            System.out.println(o);
            if(o.equals(3)){
                Thread.sleep(200);
            }
        }
        System.out.println(list);
    }

    /**
     * fail-safe
     * 修改后，遍历完还是原来的值
     * 在获取Iterator时，会将数组给到迭代器中
     * CopyOnWriteArrayList在修改的时候，会用新的数组覆盖原来的数组
     * 此时，Iterator中的数组与CopyOnWriteArrayList中的数组不是对应的同一个了
     *
     */
    @Test
    public void testFailSafe() throws InterruptedException {
        List<Object> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        new Thread(()->{
            try {
                Thread.sleep(100);
                list.add(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        for (Object o : list) {
            System.out.println(o);
            if(o.equals(3)){
                Thread.sleep(200);
            }
        }
        System.out.println(list);
    }

}
