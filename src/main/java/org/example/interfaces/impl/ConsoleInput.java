package org.example.interfaces.impl;

import org.example.interfaces.Input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
