package com.hbujakow.communication;

import com.hbujakow.exceptions.InputNotValidException;

import java.util.Scanner;

public class UserInputImpl implements UserInput<Integer> {
    private final int MINLENGTH = 5;
    private final int MAXLENGTH = 20;

    @Override
    public Integer getInput() throws InputNotValidException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a number (5-20): ");
        var input = sc.nextLine();

        if (!input.matches("-?\\d+")) {
            throw new InputNotValidException("Invalid input: not an integer");
        }
        int result = Integer.parseInt(input);
        if (result < MINLENGTH || result > MAXLENGTH) {
            throw new InputNotValidException("Invalid input: out of bounds");
        }

        return result;
    }
}
