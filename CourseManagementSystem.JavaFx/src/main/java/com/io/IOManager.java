package com.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOManager {
    public String readFile(String filename) {
        String contents = "";

        try {
            contents = Files.readString(Path.of(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }

    public void writeFile(String filename, String contents) {
        try {
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.write(contents);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
