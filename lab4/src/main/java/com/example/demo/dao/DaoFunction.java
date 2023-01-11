package com.example.demo.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;


public class DaoFunction implements IDAO{
    public static void main(String[] args) {
        DaoFunction d = new DaoFunction();
        WritedFunction f = new WritedFunction("namdsae", 2d,14d,67d,2d,2);
        try {
            d.setWritedFunction(f);
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setWritedFunction(WritedFunction writedFunction) throws NoSuchFileException {
        ObjectMapper mapper = new ObjectMapper();
        List<WritedFunction> functions;
        try {
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            if (new File("data.json").length() != 0) {
                functions = mapper.readValue(new File("data.json"), new TypeReference<List<WritedFunction>>() {});
                for (WritedFunction f: functions){
                    if(f.getId() == writedFunction.getId()){
                        throw new IllegalArgumentException("Same ID was found.");
                    }
                }
                functions.add(writedFunction);
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), functions);
            }else{
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), writedFunction);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WritedFunction getWritedFunction(int id) throws NoSuchFileException, IllegalArgumentException {
        List<WritedFunction> functions;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            functions = mapper.readValue(new File("data.json"), new TypeReference<List<WritedFunction>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (WritedFunction f: functions){
            if(f.getId() == id){
                return f;
            }
        }
        throw new IllegalArgumentException();
    }
}
