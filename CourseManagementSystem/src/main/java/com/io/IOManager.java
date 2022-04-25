package com.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IOManager {
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.length() != 0) {
                    fileContents.add(line);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(filename + " couldn't be found.");
            System.exit(0);
        }

        return fileContents;
    }

    public void writeFile(ArrayList<String> lines, String filename) throws IOException {
        try {
            FileWriter csvWriter = new FileWriter(filename, false);
            for (String line :
                    lines) {
                csvWriter.append(String.join(",", line));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
