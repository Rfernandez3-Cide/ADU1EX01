package adu1ex01;

import java.io.*; // Import classes for file input/output
import java.util.Scanner; // Import Scanner class for user input

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create Scanner to read input from the user

        // Ask the user for the source file name
        System.out.println(
                "Enter the name of the source file (e.g., image.jpg or data.xml, you can take the (descarga.jpg) for testing): ");
        String originFile = scanner.nextLine().trim(); // Read and trim the source file name

        // Ask the user for the destination file name
        System.out.println("Enter the name of the destination file (e.g., copy.jpg or copy.xml):");
        String destinationFile = scanner.nextLine().trim(); // Read and trim the destination file name

        // Determine whether the file should be treated as binary or text
        boolean isBinary = isBinaryFile(originFile);

        try {
            if (isBinary) {
                // Copy the file using binary stream if it’s a binary file
                copyBinaryFile(originFile, destinationFile);
                System.out.println("Binary file copied successfully.");
            } else {
                // Copy the file using character stream if it’s a text file
                copyTextFile(originFile, destinationFile);
                System.out.println("Text file copied successfully.");
            }
        } catch (IOException e) {
            // Catch and report any I/O error during copying
            System.err.println("Error copying file: " + e.getMessage());
        }

        scanner.close(); // Close the scanner to free system resources
    }

    /**
     * Determines whether a file is binary or text based on its extension.
     * Files ending in .jpg or .xml are considered text files.
     * All other extensions are treated as binary.
     *
     * @param filename The name of the file to check
     * @return true if binary, false if text
     */
    private static boolean isBinaryFile(String filename) {
        String lower = filename.toLowerCase(); // Convert filename to lowercase for case-insensitive comparison
        return !(lower.endsWith(".jpg") || lower.endsWith(".xml")); // Only .jpg and .xml are text files here
    }

    /**
     * Copies a binary file using byte streams.
     *
     * @param origin      The source file path
     * @param destination The destination file path
     * @throws IOException if an I/O error occurs during copying
     */
    public static void copyBinaryFile(String origin, String destination) throws IOException {
        try (InputStream in = new FileInputStream(origin); // Input stream for reading bytes
                OutputStream out = new FileOutputStream(destination)) { // Output stream for writing bytes

            byte[] buffer = new byte[1024]; // Byte buffer for efficient reading/writing
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length); // Write only the bytes that were read
            }
        }
    }

    /**
     * Copies a text file using character streams.
     *
     * @param origin      The source file path
     * @param destination The destination file path
     * @throws IOException if an I/O error occurs during copying
     */
    public static void copyTextFile(String origin, String destination) throws IOException {
        try (Reader in = new FileReader(origin); // Reader for reading characters
                Writer out = new FileWriter(destination)) { // Writer for writing characters

            char[] buffer = new char[1024]; // Character buffer for efficient reading/writing
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length); // Write only the characters that were read
            }
        }
    }
}
