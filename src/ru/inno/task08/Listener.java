package ru.inno.task08;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Принимает подключения от клиентов, которые хотят
 * присоединиться к чату.
 *
 * @author Alexey Balakin
 */
public class Listener extends Thread {
    private final ServerSocket serverSocket;
    /**
     * Список подключившихся участников чата
     */
    private final List<Sender> users;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.users = new CopyOnWriteArrayList<>();
    }

    /**
     * Создает сокет для каждого подключившегося клиента.
     * Клиенты добавляются в list
     */
    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Sender sender = new Sender(socket, users);
                users.add(sender);
                sender.start();
                System.out.println("Connected " + socket);
            } catch (SocketException e) {
                System.out.println("Socket closed. Listener will shutdown...");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Listener shutdown.");
    }
}
