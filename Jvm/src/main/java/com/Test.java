package com;


import java.lang.reflect.Proxy;

/**
 * @author xtc
 * @create 2022-09-10 10:43
 */
public class Test {

    static final int a;

    static{
        a = 1;
    }

    public static void main(String[] args) {
//        new TestFinal();
        int a = TestFinal.a;
//        int[] ints = new int[10];
    }
}
class TestFinal {
    static final int a = 1;
    static final int b;
    static {
        b = 2;
        System.out.println("TestFinal.class init");
    }

    int c;
    {
        System.out.println("方法块");
    }
    public TestFinal(){
        super();
        System.out.println("Object init");
    }

}
