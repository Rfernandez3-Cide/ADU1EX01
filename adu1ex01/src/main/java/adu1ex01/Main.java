// Roberto Fernández del Barrio.//
// ADU1EX01....................//
// 43232819H....................//
// 14-10-2025...................//

package adu1ex01;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Allowed extensions (you can add more)
            String[] extensions = { "jpg", "xml" };

            Path originPath = askOriginFile(extensions);
            Path destinationFolder = askDestinationFolder();

            // Detect file type
            String extension = getExtension(originPath);
            boolean isBinary = isBinaryExtension(extension);

            // Copy using the appropriate method
            if (isBinary) {
                copyFileStream(originPath, destinationFolder);
            } else {
                copyFileReaderWriter(originPath, destinationFolder);
            }

            System.out.println("\nFile copied successfully.");

        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    // Ask for the origin file and validate it
    public static Path askOriginFile(String[] extensions) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the source file: ");
        String pathString = scanner.nextLine().trim();
        Path path = Paths.get(pathString);

        if (!Files.exists(path)) {
            throw new FileNotFoundException("The file does not exist.");
        }
        if (!Files.isRegularFile(path)) {
            throw new IOException("Not a valid file.");
        }

        // Validate extension
        if (extensions != null && extensions.length > 0) {
            boolean found = false;
            for (String ext : extensions) {
                if (path.toString().toLowerCase().endsWith("." + ext.toLowerCase())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IOException("Invalid extension (only allowed: jpg, xml).");
            }
        }

        return path;
    }

    // Ask for the destination folder
    public static Path askDestinationFolder() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the destination folder: ");
        String pathString = scanner.nextLine().trim();
        Path path = Paths.get(pathString);

        if (!Files.exists(path)) {
            throw new IOException("The destination folder does not exist.");
        }
        if (!Files.isDirectory(path)) {
            throw new IOException("The destination path is not a folder.");
        }

        return path;
    }

    // Copy using streams (for binary files)
    public static void copyFileStream(Path originPath, Path destinationFolder) throws IOException {
        Path finalDestination = destinationFolder.resolve(originPath.getFileName());

        try (InputStream in = new FileInputStream(originPath.toFile());
                OutputStream out = new FileOutputStream(finalDestination.toFile())) {

            byte[] buffer = new byte[4096];
            long totalBytes = Files.size(originPath);
            long copiedBytes = 0;
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                copiedBytes += bytesRead;
                double progress = (double) copiedBytes / totalBytes * 100;
                System.out.printf("Progress: %.2f%%%n", progress);
            }
        }
    }

    // Copy using Reader/Writer (for text files)
    public static void copyFileReaderWriter(Path originPath, Path destinationFolder) throws IOException {
        Path finalDestination = destinationFolder.resolve(originPath.getFileName());

        try (BufferedReader reader = new BufferedReader(new FileReader(originPath.toFile()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(finalDestination.toFile()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Extract the file extension
    public static String getExtension(Path path) {
        String name = path.getFileName().toString();
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex != -1) ? name.substring(dotIndex + 1).toLowerCase() : "";
    }

    // Determine if the extension is binary
    public static boolean isBinaryExtension(String extension) {
        String[] binaryExts = { "jpg", "jpeg", "png", "gif", "pdf", "exe" };
        for (String ext : binaryExts) {
            if (ext.equalsIgnoreCase(extension))
                return true;
        }
        return false;
    }
}
