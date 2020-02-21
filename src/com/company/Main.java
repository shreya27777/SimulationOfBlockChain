package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static int minerId = 1;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3, r -> new Thread(r, "" + minerId++));
        for (int i = 0; i < 10; i++) {
            executor.submit(new Worker("someMessage" + i));
        }
        executor.shutdown();
        executor.awaitTermination(500, TimeUnit.SECONDS);

        print();
    }

    private static void print() {
        for (Block block : SharedData.getBlockList()) {
            System.out.println(block);
            System.out.println();
        }
    }
}
