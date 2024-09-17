package ru.mirea.client_server_architecture.practice1;

import lombok.Builder;

@Builder
class PingPongThread extends Thread {
    private long workTimeMillis;
    private Runnable onPing;
    private Runnable onPong;

    private final Thread pingThread = new Thread(this::ping);
    private final Thread pongThread = new Thread(this::pong);
    private final Object lock = new Object();
    private static boolean isPingTurn = true;

    @Override
    public void run() {
        try {
            pingThread.start();
            pongThread.start();

            Thread.sleep(workTimeMillis);

            pingThread.interrupt();
            pongThread.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pong() {
        try {
            while (true) {
                synchronized (lock) {
                    while (!isPingTurn) {
                        lock.wait();
                    }
                    onPing.run();
                    isPingTurn = false;
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void ping() {
        try {
            while (true) {
                synchronized (lock) {
                    while (isPingTurn) {
                        lock.wait();
                    }
                    onPong.run();
                    isPingTurn = true;
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
