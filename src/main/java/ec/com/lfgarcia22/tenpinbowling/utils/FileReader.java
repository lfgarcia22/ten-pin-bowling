package ec.com.lfgarcia22.tenpinbowling.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public List<String> readFile(String path) throws TenPinBowlingException {
        Path filePath = Paths.get(path);
        try (Stream<String> stream = Files.lines(filePath)) {
            List<String> lines = stream.collect(Collectors.toList());
            if (lines.isEmpty()) throw new TenPinBowlingException("Empty File!");

            List<String> fileContent = new ArrayList<>();
            for (String line : lines) {
                verifyInformation(line);
                fileContent.add(line);
            }
            return fileContent;
        } catch (IOException | UncheckedIOException ex) {
            throw new TenPinBowlingException("File not found!", ex);
        }
    }

    private void verifyInformation(String line) throws TenPinBowlingException {
        if (!line.matches("[a-zA-Z ]+[\t](10|F|[0-9])")) {
            throw new TenPinBowlingException("Bad Input!");
        }
    }

}
