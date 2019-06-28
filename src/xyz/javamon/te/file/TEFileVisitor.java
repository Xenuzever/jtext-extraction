package xyz.javamon.te.file;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class TEFileVisitor<T extends JTextArea> implements FileVisitor<Path> {

    private String[] regexes;

    public TEFileVisitor(String[] regexes) {
        this.regexes = regexes;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        // read
        TEFileReader reader = new TEFileReader(file);
        String text = reader.readMatchedText(regexes);
        System.out.println(text);
        // wirte
        String fileName = String.format("%s_%s", "extract", file.toFile().getName());
        Path writeFile = file.getParent().resolve(fileName);
        TEFileWriter writer = new TEFileWriter(writeFile);
        writer.write(text);
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }


}
