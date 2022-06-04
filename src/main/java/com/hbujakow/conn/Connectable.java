package com.hbujakow.conn;

import java.io.IOException;
import java.net.URL;

public interface Connectable {
    String getJsonString(URL url) throws IOException;
}