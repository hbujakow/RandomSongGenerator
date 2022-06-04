package com.hbujakow;

import com.hbujakow.communication.UserInput;
import com.hbujakow.communication.UserInputImpl;
import com.hbujakow.communication.UserOutput;
import com.hbujakow.communication.UserOutputImpl;
import com.hbujakow.conn.Connection;
import com.hbujakow.exceptions.InputNotValidException;
import com.hbujakow.json.JsonParser;
import com.hbujakow.json.MusicGen;
import com.hbujakow.json.RandomWordGen;
import com.hbujakow.json.jsonobjects.Music;
import com.hbujakow.json.jsonobjects.Word;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    private static HashSet<String> wordsSet = new HashSet<>();
    private static LinkedList<Music> songsList = new LinkedList<>();

    public static void main(String[] args) {
        createRandomWords();

        System.out.println("Random words: " + wordsSet);

        generateSongs();

        UserOutput musicOutput = new UserOutputImpl(wordsSet, songsList);

        System.out.println("\n===========================================\n");

        musicOutput.showInfo();
    }

    private static void generateSongs() {
        JsonParser<LinkedList<Music>> musicGenerator = new MusicGen(new Connection(), wordsSet);
        try {
            songsList = musicGenerator.getResult();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void createRandomWords() {

        int input = 5;
        UserInput<Integer> userInput = new UserInputImpl();

        while (true) {
            try {
                input = userInput.getInput();
                break;
            } catch (InputNotValidException e) {
                e.printStackTrace();
            }
        }

        JsonParser<Word> randomWordGen = new RandomWordGen(new Connection());

        while (wordsSet.size() < input) {
            try {
                wordsSet.add(randomWordGen.getResult().getWord());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


    }
}
