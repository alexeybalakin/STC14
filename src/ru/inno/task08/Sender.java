package ru.inno.task08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Считывает сообщения от одного из участников чата
 * и отправляет его всем остальным.
 *
 * @author Alexey Balakin
 */
public class Sender extends Thread {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private final List<Sender> users;

    public Sender(Socket socket, List<Sender> users) {
        this.socket = socket;
        this.users = users;
    }

    /**
     * Получает потоки ввода/вывода из сокета, к которому
     * подключился участник чата.
     * Запрашивает имя участника чата и использует его,
     * когда пересылает сообщение остальным пользователям.
     * Читает из потока ввода сообщение пользоваателя
     * и пересылает его всем другм участникам.
     * После получения от пользователя сообщеия "quit"
     * удаляет его из списка участников и завершает работу.
     */
    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String message = "Enter you name:";
            dataOutputStream.writeUTF(message);
            String name = dataInputStream.readUTF();
            while (true) {
                message = dataInputStream.readUTF();
                if (message.equals("quit")) {
                    users.remove(this);
                    return;
                }
                for (Sender sender : users) {
                    if (sender.socket != this.socket) {
                        sender.dataOutputStream.writeUTF(name + " says: " + message);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Disconnected " + socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
