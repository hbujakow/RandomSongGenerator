package com.hbujakow;

import com.hbujakow.jsonobjects.Word;

public class Main {
    public static void main(String[] args) {

        Connectable<Word> randomWordGen = new RandomWordGen();

        System.out.println(randomWordGen.getResult());

    }
}