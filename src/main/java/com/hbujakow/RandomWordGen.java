package com.hbujakow;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.hbujakow.jsonobjects.Word;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class RandomWordGen implements Connectable<Word> {
    private final String RANDOMWORDSURL = "https://random-words-api.vercel.app/word";
    private final int MINLENGTH = 5;
    private final int MAXLENGTH = 20;

    @Override
    public String getJsonString() throws IOException {
        URL url = new URL(RANDOMWORDSURL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HTTP Response: " + responseCode);
        }

        StringBuilder stringBuilder = new StringBuilder();
        Scanner sc = new Scanner(url.openStream());

        while (sc.hasNext()) {
            stringBuilder.append(sc.nextLine());
        }
        sc.close();

        return stringBuilder.toString();
    }

    @Override
    public Word getResult() {
        Gson gson = new Gson();
        Word[] words = null;

        try {
            do {
                words = gson.fromJson(getJsonString(), Word[].class);
            } while (words[0].getWord().length() > MAXLENGTH || words[0].getWord().length() < MINLENGTH);

        } catch (IOException e) {
            e.printStackTrace();
        }

        assert words != null;
        return words[0];
    }


}