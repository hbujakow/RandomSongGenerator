package com.hbujakow.communication;

import com.hbujakow.exceptions.InputNotValidException;

public interface UserInput<T> {
    T getInput() throws InputNotValidException;
}
