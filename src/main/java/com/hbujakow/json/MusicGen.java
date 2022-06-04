package com.hbujakow.json;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hbujakow.conn.Connectable;
import com.hbujakow.exceptions.NoDataException;
import com.hbujakow.json.jsonobjects.Music;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;


public class MusicGen implements JsonParser<LinkedList<Music>> {
    private final String MUSICURL = "https://musicbrainz.org/ws/2/recording?fmt=json&limit=1&query=recording:";
    private Connectable conn;
    private HashSet<String> wordsSet;

    public MusicGen(Connectable conn, HashSet<String> wordsSet) {
        this.conn = conn;
        this.wordsSet = wordsSet;
    }


    @Override
    public LinkedList<Music> getResult() {
        LinkedList<Music> result = new LinkedList<>();

        for (var word : wordsSet) {
            Gson gson = new Gson();
            JsonObject jsonObject = null;

            try {
                jsonObject = gson.fromJson(conn.getJsonString(new URL(MUSICURL + word)), JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert jsonObject != null;
            Music music = new Music();

            //CHECK COUNT > 0
            if (jsonObject.get("count").getAsInt() == 0){
                try {
                    throw new NoDataException("No recording found!");
                } catch (NoDataException e) {
                    result.add(music);
                    continue;
                }
            }

            //song title
            jsonObject.get("recordings").getAsJsonArray().forEach(jsonElement -> {
                music.setTitle(jsonElement.getAsJsonObject().get("title").getAsString());
            });

            //song artist
            jsonObject.get("recordings").getAsJsonArray().forEach(jsonElement -> {
                music.setArtist(jsonElement.getAsJsonObject().get("artist-credit").getAsJsonArray().get(0)
                        .getAsJsonObject().get("name").getAsString());
            });

            //song album title
            jsonObject.get("recordings").getAsJsonArray().forEach(jsonElement -> {
                music.setAlbum(jsonElement.getAsJsonObject().get("releases").getAsJsonArray().get(0)
                        .getAsJsonObject().get("title").getAsString());
            });

            result.add(music);
        }

        return result;
    }
}
