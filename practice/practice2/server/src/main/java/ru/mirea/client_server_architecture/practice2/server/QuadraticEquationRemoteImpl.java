package ru.mirea.client_server_architecture.practice2.server;

import ru.mirea.client_server_architecture.practice2.server.dto.QuadraticEquation;
import ru.mirea.client_server_architecture.practice2.server.dto.QuadraticEquationResult;

import java.rmi.RemoteException;

class QuadraticEquationRemoteImpl implements QuadraticEquationRemote {
    @Override
    public QuadraticEquationResult solveEquation(QuadraticEquation equation) throws RemoteException {
        if (equation.getA() == 0 && equation.getB() == 0 && equation.getC() == 0) {
            return solveAllZeroEquation();
        } else if (equation.getA() == 0 && equation.getB() == 0) {
            return solveConstEquation(equation.getC());
        } else if (equation.getA() == 0) {
            return solveLinearEquation(equation.getB(), equation.getC());
        } else {
            return solveQuadraticEquation(equation.getA(), equation.getB(), equation.getC());
        }
    }

    private QuadraticEquationResult solveAllZeroEquation() {
        return new QuadraticEquationResult.AnyRoot();
    }

    private QuadraticEquationResult solveConstEquation(double c) {
        return new QuadraticEquationResult.NoRoot();
    }

    private QuadraticEquationResult solveLinearEquation(double b, double c) throws RemoteException {
        double root = -b / c;
        return new QuadraticEquationResult.OneRoot(root);
    }

    private QuadraticEquationResult solveQuadraticEquation(double a, double b, double c) throws RemoteException {
        double d = b * b - 4 * a * c;
        if (d > 0) {
            double root1 = (-b - Math.sqrt(d)) / (2 * a);
            double root2 = (-b + Math.sqrt(d)) / (2 * a);
            return new QuadraticEquationResult.TwoRoot(root1, root2);
        } else if (d == 0) {
            double root = -b / (2 * a);
            return new QuadraticEquationResult.OneRoot(root);
        } else {
            return new QuadraticEquationResult.NoRoot();
        }
    }
}
