package ru.mirea.client_server_architecture.practice3.server;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@AllArgsConstructor
class Messager implements Runnable {
    private Socket socket;

    @Override
    public void run() {
        System.out.println("Подключение: " + socket);
        try {
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            while (in.hasNextLine()) {
                out.println(in.nextLine().toUpperCase());
            }
        } catch (Exception e) {
            System.out.println("Ошибка:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
            System.out.println("Closed: " + socket);
        }

    }
}
