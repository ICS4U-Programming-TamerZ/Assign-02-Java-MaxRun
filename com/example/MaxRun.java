package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * MaxRun Program
 * Author: Tamer Zreg
 * @version 1.0
 * @since April 15, 2024
 *
 * Description: This program reads input from a text file, calculates the maximum and minimum runs of consecutive characters for each line individually, and then calculates them for the entire file. It then writes the results to an output file.
 */
public class MaxRun {
    public static void main(String[] args) {
        // Relative paths for input and output files
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Check if the input file exists
        File file = new File(inputFile);
        if (!file.exists()) {
            System.out.println("Input file does not exist.");
            return; // Exit the program if the input file doesn't exist
        }

        try {
            // Open input file for reading
            Scanner scanner = new Scanner(file);
            // Open output file for writing
            FileWriter writer = new FileWriter(outputFile);

            // Calculate maximum and minimum runs for each line individually
            while (scanner.hasNextLine()) {
                // Read a line from the input file
                String line = scanner.nextLine().trim(); // Trim to handle blank lines
                // Ensure line is not empty
                if (!line.isEmpty()) {
                    // Calculate maximum run for the current line and convert to lowercase for case insensitivity
                    int maxRunLine = maxRun(line.toLowerCase());
                    int minRunLine = minRun(line.toLowerCase());
                    // Print the result to the console
                    System.out.println("Max run of '" + line + "' is: " + maxRunLine);
                    System.out.println("Min run of '" + line + "' is: " + minRunLine);
                    // Write the result to the output file
                    writer.write("Max run of '" + line + "' is: " + maxRunLine + "\n");
                    writer.write("Min run of '" + line + "' is: " + minRunLine + "\n");
                }
            }

            // Reset the scanner to read from the beginning of the file
            scanner = new Scanner(file);

            // Calculate maximum and minimum runs for the entire file
            StringBuilder fileContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                // Append each line to the file content
                fileContent.append(scanner.nextLine());
            }
            // Calculate maximum run for the entire file content and convert to lowercase for case insensitivity
            int maxRunFile = maxRun(fileContent.toString().toLowerCase());
            int minRunFile = minRun(fileContent.toString().toLowerCase());
            // Print the result to the console
            System.out.println("Max run of the entire file is: " + maxRunFile);
            System.out.println("Min run of the entire file is: " + minRunFile);
            // Write the result to the output file
            writer.write("Max run of the entire file is: " + maxRunFile + "\n");
            writer.write("Min run of the entire file is: " + minRunFile + "\n");

            // Close the scanner and writer
            scanner.close();
            writer.close();

            // Print a message indicating successful completion
            System.out.println("Output has been written to " + outputFile);
        } catch (IOException e) {
            // Print any errors that occur during file handling
            e.printStackTrace();
        }
    }

    // Method to calculate the maximum run of consecutive characters in a string
    public static int maxRun(String str) {
        // Return 0 if the string is null or empty
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int maxRun = 1; // Initialize the maximum run to 1
        int currentRun = 1; // Initialize the current run to 1

        // Loop through the string starting from the second character
        for (int i = 1; i < str.length(); i++) {
            // Check if the current character is the same as the previous one (case insensitive)
            if (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(str.charAt(i - 1))) {
                // If they are the same, increment the current run
                currentRun++;
                // Update the maximum run if the current run is greater
                if (currentRun > maxRun) {
                    maxRun = currentRun;
                }
            } else {
                // If the characters are different, reset the current run to 1
                currentRun = 1;
            }
        }

        // Return the maximum run found
        return maxRun;
    }

    // Method to calculate the minimum run of consecutive characters in a string
    public static int minRun(String str) {
        // Return 0 if the string is null or empty
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int minRun = Integer.MAX_VALUE; // Initialize the minimum run to the maximum integer value
        int currentRun = 1; // Initialize the current run to 1

        // Loop through the string starting from the second character
        for (int i = 1; i < str.length(); i++) {
            // Check if the current character is the same as the previous one (case insensitive)
            if (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(str.charAt(i - 1))) {
                // If they are the same, increment the current run
                currentRun++;
            } else {
                // If the characters are different, update the minimum run if the current run is less
                if (currentRun < minRun) {
                    minRun = currentRun;
                }
                // Reset the current run to 1
                currentRun = 1;
            }
        }

        // Update the minimum run if the last run is less than the current minimum run
        if (currentRun < minRun) {
            minRun = currentRun;
        }

        // Return the minimum run found
        return minRun;
    }
}
