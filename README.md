This program copies a file from a source path to a destination folder. It validates that the file exists and has an allowed extension (.jpg or .xml). Binary files are copied using byte streams, while text files are copied line by line using readers and writers. If successful, the program displays a confirmation message.

Information about the development environment

Operating System: Windows 11

IDE: Visual Studio Code

Java Version: Picked up JAVA_TOOL_OPTIONS: -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8
openjdk version "25" 2025-09-16 LTS
OpenJDK Runtime Environment Temurin-25+36 (build 25+36-LTS)
OpenJDK 64-Bit Server VM Temurin-25+36 (build 25+36-LTS, mixed mode, sharing)

Libraries used: java.io, java.nio.file, java.util.Scanner

Load notebook (execution instructions)

Open the project in your IDE.

Compile the file Main.java.

Run the program.

When prompted, enter the source file path and the destination folder path.

If both paths are valid, the file will be copied successfully.