package xyz.javamon.te.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEFileReader {

    private Path path;

    public TEFileReader(Path path) {
        this.path = path;
    }

    public String readMatchedText(String[] regexes) throws IOException {
        return Files.lines(path)
                .map(x -> extractMatchedString(regexes, x))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce((x1, x2) -> joinedStrings(x1, x2))
                .orElse("");
    }

    private String joinedStrings(String s1, String s2) {
        return new StringJoiner(System.lineSeparator()).add(s1).add(s2).toString();
    }

    private Optional<String> extractMatchedString(String[] regexs, String str) {
        return Arrays.stream(regexs)
                .map(r -> extractMatchedString(r, str))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce((x1, x2) -> joinedStrings(x1, x2));
    }

    private Optional<String> extractMatchedString(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Optional.of(matcher.group(1)) : Optional.empty();
    }

}
