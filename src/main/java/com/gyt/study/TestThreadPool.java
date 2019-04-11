package com.gyt.study;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: yuantao.guo
 * @Date: 2019/4/11 21:29
 * @Description:
 */
public class TestThreadPool {
    public static void main(String[] args) throws Exception {
        HaveTimeOutThreadPool a = new HaveTimeOutThreadPool(10);
        a.submit(() -> {
            System.out.println(111);
            try {
                int i = 1;
                while (1 == i) {
                    Boolean h = Thread.currentThread().isInterrupted();
                    System.out.println( h);
                    if(h){
                        System.out.println("必须是truea" + h);
                        Thread.sleep(4000);
                    }
                }
            } catch (InterruptedException e) {
                Thread.sleep(1000);
                System.out.println("lalallal");
                System.out.println(Thread.currentThread().isInterrupted());
               throw e;
            }
            System.out.println(Thread.currentThread().isInterrupted());
            return "hhh";
        },1000);

/*        a.submit(() -> {
            System.out.println(111);
            int i = 1;
            while(i == 1){
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(111);
            }
            return "hhh";
        },1000);*/
        System.out.println(111222);

    }


}
