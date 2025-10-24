// Roberto Fernández del Barrio.//
// 43232819H....................//
// Finalizado el 24-10-2025.....//
package adu1ex01;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Allowed extensions (only these file types can be copied)
            String[] extensions = { "jpg", "xml" };

            // Ask the user for the source file and destination folder
            Path originPath = askOriginFile(extensions);
            Path destinationFolder = askDestinationFolder();

            // Get the file extension to decide the copy method
            String extension = getExtension(originPath);

            // Determine if the file is binary or text
            if (isBinaryExtension(extension)) {
                // Copy binary files (images, executables, etc.)
                copyFileStream(originPath, destinationFolder);
            } else {
                // Copy text files
                copyFileReaderWriter(originPath, destinationFolder);
            }

            System.out.println("\nFile copied successfully.");

        } catch (IOException ex) {
            // Catch and display any I/O error
            System.err.println("Error: " + ex.getMessage());
        }
    }

    // Asks the user for the source file path and validates that it exists and has
    // an allowed extension.
    public static Path askOriginFile(String[] extensions) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the source file: ");
        Path path = Paths.get(scanner.nextLine().trim());

        // Check if the file exists and is a valid file
        if (!Files.exists(path))
            throw new FileNotFoundException("The file does not exist.");
        if (!Files.isRegularFile(path))
            throw new IOException("Not a valid file.");

        // Check if the file extension is valid
        boolean valid = false;
        for (String ext : extensions) {
            if (path.toString().toLowerCase().endsWith("." + ext.toLowerCase())) {
                valid = true;
                break;
            }
        }
        if (!valid)
            throw new IOException("Invalid extension (only allowed: jpg, xml).");

        return path;
    }

    // Asks the user for the destination folder and validates that it exists.
    public static Path askDestinationFolder() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the destination folder: ");
        Path path = Paths.get(scanner.nextLine().trim());

        // Check if the folder exists and is a directory
        if (!Files.exists(path))
            throw new IOException("The destination folder does not exist.");
        if (!Files.isDirectory(path))
            throw new IOException("The destination path is not a folder.");

        return path;
    }

    // Copies binary files (such as images or executables) using byte streams.
    public static void copyFileStream(Path originPath, Path destinationFolder) throws IOException {
        Path finalDestination = destinationFolder.resolve(originPath.getFileName());

        // Use InputStream and OutputStream to copy bytes
        try (InputStream in = new FileInputStream(originPath.toFile());
                OutputStream out = new FileOutputStream(finalDestination.toFile())) {

            byte[] buffer = new byte[8192]; // Buffer size (8 KB)
            int bytesRead;

            // Read and write in blocks until no bytes remain
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    // Copies text files line by line using Reader/Writer.
    public static void copyFileReaderWriter(Path originPath, Path destinationFolder) throws IOException {
        Path finalDestination = destinationFolder.resolve(originPath.getFileName());

        // Use BufferedReader and BufferedWriter for text files
        try (BufferedReader reader = new BufferedReader(new FileReader(originPath.toFile()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(finalDestination.toFile()))) {

            String line;
            // Copy line by line to preserve text format
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Returns the file extension (without the dot)
    public static String getExtension(Path path) {
        String name = path.getFileName().toString();
        int dotIndex = name.lastIndexOf('.');
        // If a dot exists, return what follows; otherwise return an empty string
        return (dotIndex != -1) ? name.substring(dotIndex + 1).toLowerCase() : "";
    }

    // Checks if an extension corresponds to a binary file type.
    public static boolean isBinaryExtension(String extension) {
        String[] binaryExts = { "jpg", "jpeg", "png", "gif", "pdf", "exe" }; // Binary file types
        for (String ext : binaryExts) {
            if (ext.equalsIgnoreCase(extension))
                return true;
        }
        return false;
    }
}
