package com.hbujakow;

import java.io.IOException;

public interface Connectable<T> {
    String getJsonString() throws IOException;

    T getResult();
}