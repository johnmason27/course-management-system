package com.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Interface between IO operations and the application.
 */
public class IOManager {
    /**
     * Read the string contents of a file.
     * @param filename The filepath of the file to read
     * @return The string contents of the file
     */
    public String readFile(String filename) {
        String contents = "";

        try {
            contents = Files.readString(Path.of(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }

    /**
     * Write the given string contents to a specified filepath.
     * @param filename The filepath to write to
     * @param contents The string contents of the file
     */
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
