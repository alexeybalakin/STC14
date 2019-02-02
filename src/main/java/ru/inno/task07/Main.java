package ru.inno.task07;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        RuntimeCompiler rc = new RuntimeCompiler();
        Worker magicWorker = rc.getSomeWorker(); //System.out.println("Doing magic...");
        magicWorker.doWork();
    }
}
