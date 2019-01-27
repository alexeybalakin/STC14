package ru.inno.task08;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Клиентская часть многопользовательского чата.
 * Подключается к сокету сервера, запускает в
 * отдельном потоке обработчик входящих сообщений.
 * Сообщения введеные в консоли отправляет ч/з
 * поток вывода на серверный сокет.
 * После ввода сообщения "quit" заканчивает работу.
 *
 * @author Alexey Balakin
 */
public class Client {
    public static void main(String[] args) {
        String message = "";
        try (Socket socket = new Socket("localhost", Server.PORT)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Thread threadReceiver = new Receiver(dataInputStream);
            threadReceiver.setDaemon(true);
            threadReceiver.start();
            Scanner scanner = new Scanner(System.in);
            while (!message.equals("quit")) {
                message = scanner.next();
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            }
        } catch (SocketException e) {
            System.out.println("Socket closed. Client will shutdown...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client shutdown.");
    }
}



