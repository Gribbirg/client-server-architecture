package ru.mirea.client_server_architecture.practice2.server;

import ru.mirea.client_server_architecture.practice2.server.dto.QuadraticEquation;
import ru.mirea.client_server_architecture.practice2.server.dto.QuadraticEquationResult;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuadraticEquationRemote extends Remote {
    QuadraticEquationResult solveEquation(QuadraticEquation equation) throws RemoteException;
}
