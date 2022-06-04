package com.hbujakow.json;

import com.google.gson.Gson;
import com.hbujakow.conn.Connectable;
import com.hbujakow.json.jsonobjects.Word;

import java.io.IOException;
import java.net.URL;

public class RandomWordGen implements JsonParser<Word> {
    private final String RANDOMWORDSURL = "https://random-words-api.vercel.app/word";
    private Connectable conn;

    public RandomWordGen(Connectable conn) {
        this.conn = conn;
    }

    @Override
    public Word getResult() {
        Gson gson = new Gson();
        Word[] words = null;

        try {
            words = gson.fromJson(conn.getJsonString(new URL(RANDOMWORDSURL)), Word[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert words != null;
        return words[0];
    }


}