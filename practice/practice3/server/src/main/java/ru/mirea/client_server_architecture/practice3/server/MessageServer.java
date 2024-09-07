package ru.mirea.client_server_architecture.practice3.server;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageServer {
    private static final int SERVER_PORT = 50001;
    private static final List<Socket> clients = new ArrayList<>();
    private static final List<String> messages = new ArrayList<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server launched on port:" + SERVER_PORT);

            scheduler.scheduleAtFixedRate(() -> {
                if (!messages.isEmpty()) {
                    broadcastMessages();
                }
            }, 5, 5, TimeUnit.SECONDS);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                (new Messager(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcastMessages() {
        List<String> broadcastMessages = new ArrayList<>(messages);
        messages.clear();
        for (Socket client : clients) {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                for (String message : broadcastMessages) {
                    out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AllArgsConstructor
    private static class Messager extends Thread {
        private final Socket clientSocket;

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    synchronized (messages) {
                        messages.add("Client " + clientSocket.getInetAddress() + ": " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
