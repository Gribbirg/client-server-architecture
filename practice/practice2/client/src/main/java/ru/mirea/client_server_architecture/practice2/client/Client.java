package ru.mirea.client_server_architecture.practice2.client;

import lombok.NoArgsConstructor;
import ru.mirea.client_server_architecture.practice2.server.QuadraticEquationRemote;
import ru.mirea.client_server_architecture.practice2.server.dto.QuadraticEquation;
import ru.mirea.client_server_architecture.practice2.server.dto.QuadraticEquationResult;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@NoArgsConstructor
public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(8888);
            QuadraticEquationRemote equationRemote = (QuadraticEquationRemote) registry.lookup("QuadraticEquationRemote");

            QuadraticEquationResult res = equationRemote.solveEquation(new QuadraticEquation(1, 4, 3));
            System.out.println(resToString(res));
        } catch (Exception e) {
            System.err.println("Client Exception: " + e);
        }
    }

    private static String resToString(QuadraticEquationResult equation) {
        if (equation instanceof QuadraticEquationResult.TwoRoot twoRoot) {
            return "x1 = " + twoRoot.getFirstRoot() + ", x2 = " + twoRoot.getSecondRoot();
        } else if (equation instanceof QuadraticEquationResult.OneRoot oneRoot) {
            return "x1 = " + oneRoot.getRoot();
        } else if (equation instanceof QuadraticEquationResult.NoRoot) {
            return "No root";
        } else if (equation instanceof QuadraticEquationResult.AnyRoot) {
            return "Any root";
        }
        return null;
    }
}
