package org.example.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriteUtil {

    public static void writeToFile(String fileName, String multiLineContent) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.print(multiLineContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
