package com;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试ArrayList
 * @author xtc
 * @create 2022-09-04 13:32
 */
public class TestArrayList {

    public static void main(String[] args) {

    }

    /**
     * 测试扩容规则
     * 1.若容量为0，则扩容为10
     * 2.若容量不为0，则扩容为原容量的1.5   capacity + (capacity >>> 1)
     *
     * 初始化时，若没有指定容量，默认是0
     * public ArrayList() {
     *     this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
     * }
     *
     * private static final int DEFAULT_CAPACITY = 10;
     * 添加元素时，若elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA（初始化没有指定容量的）
     *           则：取DEFAULT_CAPACITY 和 minCapacity中的较大值
     *           否则取minCapacity
     * minCapacity = size + 1; 这个就是刚刚好存放所有元素的容量
     *
     * private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
     * 若扩容后的容量超出了最大容量MAX_ARRAY_SIZE，则判断minCapacity是否超出了Integer.MAX_VALUE
     *                          若超出了，则报OutOfMemoryError异常
     *                          若没有超出，则判断是否大于MAX_ARRAY_SIZE，若大于则容量为Integer.MAX_VALUE
     *                                                                若小于则容量为MAX_ARRAY_SIZE
     */
    @Test
    public void testCapacityExpansion() throws Exception{
        List<Integer> capacityList = new ArrayList<>(20);
        List<Object> list = null;
        for (int i = 0; i < 1000; i++) {
            if(list == null){
                list = new ArrayList<>();
                capacityList.add(length(list));
            }else{
                list.add(i);
                int length = length(list);
                if(length != capacityList.get(capacityList.size() - 1)){
                    capacityList.add(length);
                }
            }
        }
        System.out.println(capacityList);
    }


    public static int length(List<Object> list) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends List> listClass = list.getClass();
        Field field = listClass.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] elementData = (Object[]) field.get(list);
        return elementData.length;
    }

}
