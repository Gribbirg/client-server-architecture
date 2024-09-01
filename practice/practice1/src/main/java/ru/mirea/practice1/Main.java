package ru.mirea.practice1;

public class Main {
    public static void main(String[] args) {
        PingPongThread pingPong = PingPongThread.builder()
                .workTimeMillis(1_000)
                .onPing(() -> System.out.println("Ping"))
                .onPong(() -> System.out.println("Pong"))
                .build();

        pingPong.start();
    }
}