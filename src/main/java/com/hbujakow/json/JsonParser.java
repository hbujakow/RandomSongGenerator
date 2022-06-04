package com.hbujakow.json;

import java.net.MalformedURLException;

public interface JsonParser<T> {
    T getResult() throws MalformedURLException;
}
