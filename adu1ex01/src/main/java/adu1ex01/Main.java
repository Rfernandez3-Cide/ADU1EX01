package adu1ex01;

import java.io.*; // Import necessary classes for file input and output
import java.util.Scanner; // Import Scanner for reading user input

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Prompt user for the source file path
        System.out.println("Enter the path of the source file:");
        String originFile = scanner.nextLine(); // Read the source file path

        // Prompt user for the destination file path
        System.out.println("Enter the path of the destination file:");
        String destinationFile = scanner.nextLine(); // Read the destination file path

        // Ask user if the file is binary
        System.out.println("Is the file binary? (yes/no):");
        String answer = scanner.nextLine().trim().toLowerCase(); // Normalize user input
        boolean isBinary = answer.equals("yes"); // Determine if the file is binary based on input

        try {
            // Call appropriate method based on file type
            if (isBinary) {
                copyBinaryFile(originFile, destinationFile); // Copy binary file
                System.out.println("Binary file copied successfully.");
            } else {
                copyTextFile(originFile, destinationFile); // Copy text file
                System.out.println("Text file copied successfully.");
            }
        } catch (IOException e) {
            // Catch and display error if copying fails
            System.err.println("Error copying file: " + e.getMessage());
        }

        scanner.close(); // Close the scanner to free resources
    }

    /**
     * Copies a binary file from origin to destination using byte streams.
     * 
     * @param origin      The source file path
     * @param destination The destination file path
     * @throws IOException If an I/O error occurs
     */
    public static void copyBinaryFile(String origin, String destination) throws IOException {
        // Use try-with-resources to automatically close the streams
        try (InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024]; // Buffer for reading bytes
            int length;

            // Read from source and write to destination until EOF
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length); // Write only the bytes read
            }
        }
    }

    /**
     * Copies a text file from origin to destination using character streams.
     * 
     * @param origin      The source file path
     * @param destination The destination file path
     * @throws IOException If an I/O error occurs
     */
    public static void copyTextFile(String origin, String destination) throws IOException {
        // Use try-with-resources to automatically close the streams
        try (Reader in = new FileReader(origin);
                Writer out = new FileWriter(destination)) {

            char[] buffer = new char[1024]; // Buffer for reading characters
            int length;

            // Read from source and write to destination until EOF
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length); // Write only the characters read
            }
        }
    }
}
