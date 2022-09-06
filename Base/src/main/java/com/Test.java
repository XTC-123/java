package com;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xtc
 * @create 2022-09-06 19:39
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(Unsafe.class);
//        Unsafe unsafe = Unsafe.getUnsafe();
        Object o = unsafe.allocateInstance(Test.class);
        System.out.println(o);
    }

}
