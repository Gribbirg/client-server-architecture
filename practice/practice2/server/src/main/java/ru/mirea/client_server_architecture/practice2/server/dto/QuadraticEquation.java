package ru.mirea.client_server_architecture.practice2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class QuadraticEquation implements Serializable {
    private double a;
    private double b;
    private double c;
}
