package com.hbujakow.conn;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Connection implements Connectable{

    @Override
    public String getJsonString(URL url) throws IOException {
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
}

