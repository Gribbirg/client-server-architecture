package ru.mirea.client_server_architecture.practice2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

public class QuadraticEquationResult implements Serializable {
    @AllArgsConstructor
    @Getter
    public static class TwoRoot extends QuadraticEquationResult {
        private double firstRoot;
        private double secondRoot;
    }

    @AllArgsConstructor
    @Getter
    public static class OneRoot extends QuadraticEquationResult {
        private double root;
    }

    public static class NoRoot extends QuadraticEquationResult {
    }

    public static class AnyRoot extends QuadraticEquationResult {
    }
}
