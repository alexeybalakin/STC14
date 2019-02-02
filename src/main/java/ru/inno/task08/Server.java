package ru.inno.task08;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 * Серверная часть многопользовательского чата.
 * Создает серверный сокет для подключения клиентских приложений.
 * Запускает отдельный поток, который будет принимать все подключения.
 * При вводе в консоль "quit" приложение заверщает работу.
 *
 * @author Alexey Balakin
 */
public class Server {
    public static final int PORT = 7777;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting on port = " + PORT);
        Thread listener = new Listener(serverSocket);
        listener.setDaemon(true);
        listener.start();
        Scanner scanner = new Scanner(System.in);
        while (!"quit".equals(scanner.nextLine())) {
        }
        System.out.println("Stopping server...");
        serverSocket.close();
        System.out.println("Server shutdown.");
    }
}
