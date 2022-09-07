package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 测试线程池的情况下，取ThreadLocal的值，会取到上一次使用此线程set值的问题
 * @author xtc
 * @create 2022-09-07 21:25
 */
public class TestThreadLocal {

    public static void main(String[] args) throws InterruptedException {

        final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(()-> threadLocal.set("123"));
        System.out.println(threadLocal.get());
        Thread.sleep(100);
        executorService.execute(()-> System.out.println(threadLocal.get()));


    }

}
