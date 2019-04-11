package com.gyt.study;

import java.util.concurrent.*;

/**
 * @Auther: yuantao.guo
 * @Date: 2019/4/11 22:02
 * @Description:
 */
public class HaveTimeOutThreadPool {
    private ThreadPoolExecutor pool;

    private ThreadPoolExecutor OutTimePool;

    public HaveTimeOutThreadPool(int size) {
        pool = new ThreadPoolExecutor(size, size,
                200L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        OutTimePool = new ThreadPoolExecutor(size, size,
                200L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    public void submit(Callable<String> callable,long timeOut) throws InterruptedException {
        Future<String> future = pool.submit(callable);
        Thread.sleep(10000);
        future.cancel(true);
        OutTimePool.execute(() -> {
                    try {
                        System.out.println(System.currentTimeMillis());
                        future.get(timeOut,TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        System.out.println(333);
                    } catch (ExecutionException e) {
                        System.out.println(111);
                    } catch (TimeoutException e) {
                        System.out.println("timeout");
                        boolean a = future.cancel(true);
                        System.out.println(future.isCancelled());
                        System.out.println(a);
                    } catch (CancellationException e){
                        System.out.println("wudio");
                    }
                }
        );
    }
}
