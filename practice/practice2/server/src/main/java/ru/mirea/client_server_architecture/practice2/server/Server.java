package ru.mirea.client_server_architecture.practice2.server;

import lombok.NoArgsConstructor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@NoArgsConstructor
public class Server extends QuadraticEquationRemoteImpl {
    public static void main(String[] args) {
        try {
            QuadraticEquationRemoteImpl equationRemoteImpl = new QuadraticEquationRemoteImpl();
            QuadraticEquationRemote equationRemote = (QuadraticEquationRemote) UnicastRemoteObject.exportObject(equationRemoteImpl, 0);

            Registry registry = LocateRegistry.createRegistry(8888);
            registry.bind("QuadraticEquationRemote", equationRemote);
            System.out.println("Server started");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
        }
    }
}
