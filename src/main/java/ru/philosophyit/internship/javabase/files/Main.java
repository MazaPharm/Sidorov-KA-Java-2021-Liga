package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    private static StringBuilder fileWays;

    public static void main(String ...args) throws IOException {
        System.out.println(Files.readString(Path.of("src/main/resources/hello.txt")));

        System.out.println("1-Й СПОСОБ");
        Files.walk(Paths.get("src"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(System.out::println);


        System.out.println("\n");
        System.out.println("2-Й СПОСОБ");
        File file = new File("C:\\Users\\Tsar\\Desktop\\Java Liga\\javabase\\src");
        fileWays = new StringBuilder();
        allWays(file);

        // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        // например 
        // src/main/java/ru/philosophyit/internship/javabase/files/Main.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/DeadLock.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/LiveLock.java
        // src/main/java/ru/philosophyit/internship/javabase/threads/Completable.java
        // и т.д.
        /// Более удачные оформления вывода в консоль приветствуются
    }

    private static void allWays(File file){
        for (File f:file.listFiles()) {
            if(f.isDirectory()){
                allWays(f);
            } else {
                fileWays.append(f);
                System.out.println(fileWays.toString());
                fileWays.setLength(0);

            }

        }
    }
}
