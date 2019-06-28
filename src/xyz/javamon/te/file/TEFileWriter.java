package xyz.javamon.te.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TEFileWriter {

    private Path path;

    public TEFileWriter(Path path) {
        this.path = path;
    }

    public boolean write(String text) throws IOException {
        return write(text, "UTF-8");
    }

    public boolean write(String text, String charset) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(path, Charset.forName(charset), StandardOpenOption.CREATE)) {
            bw.write(text);
        }
        return true;
    }

}
