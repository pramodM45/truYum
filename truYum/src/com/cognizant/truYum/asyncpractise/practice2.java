package com.cognizant.truYum.asyncpractise;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class practice2{
    public static int create(int n){
        System.out.println(Thread.currentThread());
        Sleep(1000);
        return n;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();

        //using threads
//        newClass obj1=new newClass(1);
//        newClass obj2=new newClass(2);
//        Thread t1=new Thread(obj1);
//        Thread t2=new Thread(obj2);
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//        int n=obj1.getN();
//        int m=obj2.getN();

        //using executor service
        ExecutorService obj= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<Integer> future1=obj.submit(()->create(1));
        Future<Integer> future2=obj.submit(()->create(2));
        int n= future1.get();
        int m= future2.get();
//        int n=create(1);
//        int m=create(2);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        obj.shutdown();

    }
    public static void Sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class newClass implements Runnable {
        private int n;
        public newClass(int n) {
            this.n=n;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            Sleep(1000);
            this.setN(this.getN()+5);
        }
    }
}
