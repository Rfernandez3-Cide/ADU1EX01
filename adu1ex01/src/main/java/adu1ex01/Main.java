package adu1ex01;

import java.io.*; // Import necessary classes for file input and output
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner; // Import Scanner for reading user input
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            String[] extensions = { "jpg", "xml" };

            Path originPath = askOriginFile(extensions);

            Path destinationPath = askDestinationFile(extensions);

            copyFile(askOriginFile, askDestinationFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Path askOriginFile(String[] extensions) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.println("Prompt the path of the origin file");
        String pathString = scanner.next();
        Path path = Paths.get(pathString);
    }
}