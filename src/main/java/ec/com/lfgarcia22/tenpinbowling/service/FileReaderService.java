package ec.com.lfgarcia22.tenpinbowling.service;

import ec.com.lfgarcia22.tenpinbowling.model.FileContent;
import ec.com.lfgarcia22.tenpinbowling.model.FileContentBuilder;
import ec.com.lfgarcia22.tenpinbowling.utils.Constants;
import ec.com.lfgarcia22.tenpinbowling.utils.TenPinBowlingException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderService {

    public List<FileContent> readFile(String path) throws TenPinBowlingException {
        Path filePath = Paths.get(path);
        try (Stream<String> stream = Files.lines(filePath)) {
            List<String> lines = stream.collect(Collectors.toList());
            if (lines.isEmpty()) throw new TenPinBowlingException(Constants.EMPTY_MESSAGE);

            List<FileContent> fileContent = new ArrayList<>();
            int lineNumber = 1;
            for (String line : lines) {
                matchInputLine(line);

                String[] content = line.split(Constants.TAB_SPLIT);
                fileContent.add(
                        new FileContentBuilder()
                                .setFileLine(lineNumber++)
                                .setPlayerName(content[0])
                                .setPins(content[1])
                                .build()
                );
            }

            return fileContent;
        } catch (IOException | UncheckedIOException ex) {
            throw new TenPinBowlingException(Constants.NOT_FOUND_MESSAGE, ex);
        }
    }

    private void matchInputLine(String line) throws TenPinBowlingException {
        if (!line.matches(Constants.FILE_MATCH_REGEX)) {
            throw new TenPinBowlingException(Constants.BAD_INPUT_MESSAGE);
        }
    }

}
