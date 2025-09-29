package adu1ex01;

import java.io.*; // Import necessary classes for file input and output
import java.util.Scanner; // Import Scanner for reading user input

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Ask user for the name of the source file (without folder)
        System.out.println("Enter the name of the source file (e.g., file.jpg or file.xml):");
        String originFileName = scanner.nextLine().trim();

        // Ask user for the source folder path
        System.out.println("Enter the source folder path:");
        String originFolder = scanner.nextLine().trim();

        // Ask user for the destination folder path
        System.out.println("Enter the destination folder path:");
        String destinationFolder = scanner.nextLine().trim();

        // Build full paths
        String originFile = originFolder + File.separator + originFileName;
        String destinationFile = destinationFolder + File.separator + originFileName;

        // Decide if file is binary based on its extension
        boolean isBinary = isBinaryFile(originFileName);

        try {
            // Call appropriate method based on file type
            if (isBinary) {
                copyBinaryFile(originFile, destinationFile); // Copy binary file using streams
                System.out.println("Binary file copied successfully.");
            } else {
                copyTextFile(originFile, destinationFile); // Copy text file using Readers/Writers
                System.out.println("Text file copied successfully.");
            }
        } catch (IOException e) {
            // Catch and display error if copying fails
            System.err.println("Error copying file: " + e.getMessage());
        }

        scanner.close(); // Close the scanner to free resources
    }

    /**
     * Determines if a file is binary based on its extension.
     * Treats common image and pdf extensions as binary files.
     * 
     * @param filename The name of the file
     * @return true if file is binary, false otherwise
     */
    private static boolean isBinaryFile(String filename) {
        String lower = filename.toLowerCase();
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png") ||
               lower.endsWith(".gif") || lower.endsWith(".bmp") || lower.endsWith(".pdf");
    }

    /**
     * Copies a binary file from origin to destination using byte streams.
     * 
     * @param origin      The source file path
     * @param destination The destination file path
     * @throws IOException If an I/O error occurs
     */
    public static void copyBinaryFile(String origin, String destination) throws IOException {
        try (InputStream in = new FileInputStream(origin);
             OutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024]; // Buffer for reading bytes
            int length;

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
        try (Reader in = new FileReader(origin);
             Writer out = new FileWriter(destination)) {

            char[] buffer = new char[1024]; // Buffer for reading characters
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length); // Write only the characters read
            }
        }
    }
}
