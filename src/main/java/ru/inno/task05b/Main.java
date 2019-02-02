package ru.inno.task05b;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(5);
        for (int i = 0; i < 15; i++) {
            int number = i;
            myThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task - " + number);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        while (!myThreadPool.isFinished()) {
            Thread.sleep(1);
        }
        myThreadPool.shutdown();
    }
}
