import java.io.*;
import java.util.*;

public class FileHandlingToolbox {

    static Scanner sc = new Scanner(System.in);
    static String fileName = "input.txt"; // change your file path if needed

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n---- File Handling Toolbox ----");
            System.out.println("1. Count Total Words");
            System.out.println("2. Count Specific Word");
            System.out.println("3. Search Word in File");
            System.out.println("4. Display Word Frequencies");
            System.out.println("5. Copy File");
            System.out.println("6. Append to File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1: countTotalWords(); break;
                case 2: countSpecificWord(); break;
                case 3: searchWordInFile(); break;
                case 4: displayWordFrequencies(); break;
                case 5: copyFile(); break;
                case 6: appendToFile(); break;
                case 7: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }

    // 1. Count Total Words
    public static void countTotalWords() {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int count = 0;
            while (fileScanner.hasNext()) {
                fileScanner.next();
                count++;
            }
            System.out.println("Total words: " + count);
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    // 2. Count Specific Word
    public static void countSpecificWord() {
        try {
            System.out.print("Enter the word to count: ");
            String wordToCount = sc.next();
            int count = 0;

            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNext()) {
                String word = fileScanner.next();
                if (word.equalsIgnoreCase(wordToCount)) {
                    count++;
                }
            }
            System.out.println("The word '" + wordToCount + "' occurs " + count + " times.");
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    // 3. Search Word in File
    public static void searchWordInFile() {
        try {
            System.out.print("Enter the word to search: ");
            String searchWord = sc.next();

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNo = 0;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                lineNo++;
                if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                    System.out.println("Found in line " + lineNo + ": " + line);
                    found = true;
                }
            }

            if (!found) System.out.println("Word not found in the file.");
            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // 4. Display Word Frequencies
    public static void displayWordFrequencies() {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            Map<String, Integer> freq = new HashMap<>();

            while (fileScanner.hasNext()) {
                String word = fileScanner.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
                if (!word.isEmpty()) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
            }

            System.out.println("Word Frequencies:");
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    // 5. Copy File
    public static void copyFile() {
        try {
            System.out.print("Enter destination file name: ");
            String destFileName = sc.next();

            FileReader fr = new FileReader(fileName);
            FileWriter fw = new FileWriter(destFileName);

            int c;
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }

            System.out.println("File copied to " + destFileName + " successfully.");
            fr.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // 6. Append to File
    public static void appendToFile() {
        try {
            System.out.println("Enter content to append (single line): ");
            String content = sc.nextLine();

            FileWriter fw = new FileWriter(fileName, true);
            fw.write("\n" + content);
            fw.close();

            System.out.println("Content appended successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
