# ADU1EX01
ADU1EX01 Data Acces
Java File Copier

This is a simple Java application that allows users to copy files (either binary or character/text files) from one folder to another. The program prompts the user to input the source and destination file names, and determines whether to use stream-based or non-stream-based copying based on the file type.

Features

Supports copying binary files (e.g., images, executables).

Supports copying text/character files.

Automatically chooses between stream and non-stream methods.

Uses both approaches (stream and non-stream) as required by the exercise.

Requirements

Java JDK 8 or higher

A terminal or IDE to run the program

How It Works

The program asks the user for:

Source file name (including path if needed)

Destination file name (including path)

File type: binary or text

Based on the user’s input, it:

Uses streams (InputStream/OutputStream) for binary files.

Uses character-based readers/writers (FileReader/FileWriter) for text files.

It performs the copy operation and notifies the user of success or failure.

Code Structure

The main logic is inside a single Java class. The core methods include:

copyBinaryFileWithStreams(String source, String destination)

copyTextFileWithReaders(String source, String destination)

Both stream and non-stream methods are demonstrated as required.

Example Usage (MUST CHANGE)
Enter source file path: data/image.png
Enter destination file path: backup/image_copy.png
Copying file using streams...
File copied successfully!

Notes

Make sure the source file exists before running the program.

The destination folder must have write permissions.

Binary files should be copied using streams to avoid corruption.

Learning Objectives

This exercise helps practice:

Working with file I/O in Java

Understanding stream-based vs non-stream-based file operations

Building console applications that interact with the user
