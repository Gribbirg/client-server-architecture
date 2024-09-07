package ru.mirea.client_server_architecture.practice3.server;

import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class MessageServer {
    public static void main(String[] args) {
        try {
            var listener = new ServerSocket(59898);
            System.out.println("Сервер запущен...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Messager(listener.accept()));
            }
        } catch (Exception e) {

        }
    }
}
