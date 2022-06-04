package com.hbujakow.jsonobjects;

public class Word {
    String word;
    String definition;
    String pronunciation;

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                '}';
    }
}