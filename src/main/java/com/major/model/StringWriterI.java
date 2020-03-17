package com.major.model;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
@Component
public class StringWriterI {

    public StringWriterI(){}

public void write(String input,String filename,String path) throws IOException {

    FileWriter fw = new FileWriter(path+filename);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(input);
    bw.flush();
    fw.close();
    bw.close();

}

}
