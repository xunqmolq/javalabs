package com.example.demo.dao;

import java.nio.file.NoSuchFileException;

public interface IDAO {
    void setWritedFunction(WritedFunction writedFunction) throws NoSuchFileException;
    WritedFunction getWritedFunction(int id) throws NoSuchFileException, IllegalArgumentException;
}
