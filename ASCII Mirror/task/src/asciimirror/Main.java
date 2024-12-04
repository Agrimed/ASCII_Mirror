package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        MyFile myFile = new MyFile();
        myFile.run();

    }
}

class MyFile {

    List<String> list = new ArrayList<>();

    public void run() {
        try {
            readFromFile();
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        int max_size = maxSizeLine();
        addSpacesToStr(max_size);
        printList();
    }

     private String readFilePath() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private boolean checkFilePathExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private File getFile(String filePath) {
        return new File(filePath);
    }

    private void printLine(String line) {
        System.out.println(line);
    }

    private void readFromFile() throws FileNotFoundException {

         printLine("Input the file path:");
         String filePath = readFilePath();
         File file = getFile(filePath);
         try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
         } catch (FileNotFoundException e) {
             System.out.println("MyFile not found: " + filePath);
         }
    }

    private int maxSizeLine() {
         return list.stream()
                 .mapToInt(String::length)
                 .max()
                 .orElse(0);
    }

    private void addSpacesToStr(int maxSize) {
         list = list.stream()
                 .map(str -> str + " ".repeat(maxSize - str.length()))
                 .toList();
    }

    public static String reverseString(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = switch (str.charAt(i)) {
                case '<' -> '>';
                case '>' -> '<';
                case '[' -> ']';
                case ']' -> '[';
                case '{' -> '}';
                case '}' -> '{';
                case '(' -> ')';
                case ')' -> '(';
                case '/' -> '\\';
                case '\\' -> '/';
                default -> str.charAt(i);
            };
            result = c + result;
        }
        return result;
    }

    private void printList() {
         list.forEach(str -> System.out.println(str + " | " + reverseString(str)));
    }
}

