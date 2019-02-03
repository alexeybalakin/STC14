package ru.inno.task08;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

/**
 * Читает сообщения из потока ввода
 * и печатает их на консоль.
 *
 * @author Alexey Balakin
 */
public class Receiver extends Thread {
    private DataInputStream inputStream;

    public Receiver(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(inputStream.readUTF());
            } catch (SocketException e) {
                System.out.println("Socket closed. Receiver will shutdown...");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Receiver shutdown.");
    }
}
