package com.company;
/*
Создать массив потоков . Для вычисления факториала N . каждый вычисляет факториал 10(Если компьютер не тянет - возьмите меньше).
Сделать с помощью потоков и без помощи потоков. Замерить время выполнения(найти в интернете измерить время работы программы).
*/

import java.util.Random;

public class Main {
    public static int getFactorial() {
        Random random = new Random();
        int result = 1;
        for (int i = 1; i <= 10; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {


        long startNoFlow = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            getFactorial();
        }
        long stopNoFlow = System.nanoTime();
        System.out.println("Time no flow = " + (stopNoFlow - startNoFlow) / 1000000 + " milliseconds");


        Runnable[] task = new Runnable[4];
        for (int i = 0; i < task.length; i++) {
            task[i] = new Runnable() {
                @Override
                public void run() {
                    getFactorial();
                }
            };
        }

        long startWithFlow = System.nanoTime();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < threads.length; j++) {
                threads[j] = new Thread(task[j]);
                threads[j].start();
            }
        }
        long stopWithFlow = System.nanoTime();

        System.out.println("Time with flow = " + (stopWithFlow - startWithFlow) / 1000000 + " milliseconds");

    }
}
