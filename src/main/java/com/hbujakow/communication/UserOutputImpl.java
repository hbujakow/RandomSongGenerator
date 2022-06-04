package com.hbujakow.communication;

import com.hbujakow.json.jsonobjects.Music;

import java.util.HashSet;
import java.util.LinkedList;

public class UserOutputImpl implements UserOutput {
    private HashSet<String> wordsSet = new HashSet<>();
    private LinkedList<Music> songsList = new LinkedList<>();

    public UserOutputImpl(HashSet<String> wordsSet, LinkedList<Music> songsList) {
        this.wordsSet = wordsSet;
        this.songsList = songsList;
    }

    @Override
    public void showInfo() {
        int i = 0;
        for (var word : wordsSet) {
            System.out.println(word + " -- " + songsList.get(i++));
        }
    }
}
