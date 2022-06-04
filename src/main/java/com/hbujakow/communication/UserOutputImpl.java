package com.hbujakow.communication;

import com.hbujakow.json.jsonobjects.Music;

import java.util.*;
import java.util.stream.Collectors;

public class UserOutputImpl implements UserOutput {
    private HashSet<String> wordsSet = new HashSet<>();
    private LinkedList<Music> songsList = new LinkedList<>();

    public UserOutputImpl(HashSet<String> wordsSet, LinkedList<Music> songsList) {
        this.wordsSet = wordsSet;
        this.songsList = songsList;
    }

    @Override
    public void showInfo() {

        HashMap<String, String> wordMusicMap = new HashMap<>();
        int i = 0;
        for (var word : wordsSet) {
            if (songsList.get(i).getTitle() == null) {
                wordMusicMap.put(word, "No recording found!");
            } else {
                var musicInfo = "Artist: " + songsList.get(i).getArtist() + ", " +
                        "Album: " + songsList.get(i).getAlbum() + ", " +
                        "Title: " + songsList.get(i++).getTitle();
                wordMusicMap.put(word, musicInfo);
            }

        }

        wordMusicMap.entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList()).forEach(System.out::println);

    }
}
