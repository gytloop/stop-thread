package com.gyt.study;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: yuantao.guo
 * @Date: 2019/4/11 22:55
 * @Description:
 */
public class TestEnd {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(40);//创建一个可容纳40个线程的线程池
        final List<Future> threadList = new ArrayList<Future>();
        for(int i=0;i<40;i++){
            System.out.println(i+"开始时间："+System.currentTimeMillis());
            Future future = pool.submit(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        System.out.println("11111");
                    }
                    System.out.println("结束时间："+System.currentTimeMillis());
                }

            });
            threadList.add(future);
        }


        for(Future future:threadList){
            final Future futureTemp = future;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        futureTemp.get(200, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        futureTemp.cancel(true);
                    } catch (ExecutionException e) {
                        futureTemp.cancel(true);
                    } catch (TimeoutException e) {
                        futureTemp.cancel(true);
                    }
                }
            });
            t.start();
        }
    }
}
