package ec.com.lfgarcia22.tenpinbowling.service;

import ec.com.lfgarcia22.tenpinbowling.model.FileContent;
import ec.com.lfgarcia22.tenpinbowling.model.FileContentBuilder;
import ec.com.lfgarcia22.tenpinbowling.utils.TenPinBowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderServiceTest {

    private FileReaderService fileReaderService;
    private ClassLoader classLoader;
    private String resourcePath;

    @BeforeEach
    void beforeEach() {
        fileReaderService = new FileReaderService();
        classLoader = getClass().getClassLoader();
    }

    private void setResource(String fileName) {
        URL resource = classLoader.getResource(fileName);
        resourcePath = resource.getPath();
    }

    @Test
    void shouldThrowExceptionWhenFileDoesNotExist() {
        assertThrows(TenPinBowlingException.class, () -> fileReaderService.readFile(""));
    }

    @Test
    void shouldThrowExceptionWhenFileIsEmpty() {
        setResource("empty-file");

        assertThrows(TenPinBowlingException.class, () -> fileReaderService.readFile(resourcePath), "Empty File!");
    }

    @Test
    void shouldNotThrowExceptionWhenFileExistsInClasspathAndNotEmpty() {
        setResource("perfect-game-file");

        assertDoesNotThrow(() -> fileReaderService.readFile(resourcePath));
    }

    @Test
    void shouldThrowExceptionWhenAnyLineIsNotValid() {
        setResource("bad-input-no-score-file");

        assertThrows(TenPinBowlingException.class, () -> fileReaderService.readFile(resourcePath), "Bad Input!");
    }

    @Test
    void shouldThrowExceptionWhenFileHasAnyRowWithNoScore() {
        setResource("bad-input-no-score-file");

        assertThrows(TenPinBowlingException.class, () -> fileReaderService.readFile(resourcePath));
    }

    @Test
    void shouldThrowExceptionWhenFileHasAnyRowWithNegativeScore() {
        setResource("bad-input-negative-score-file");

        assertThrows(TenPinBowlingException.class, () -> fileReaderService.readFile(resourcePath));
    }

    @Test
    void shouldThrowExceptionWhenFileHasAnyRowMalformed() {
        setResource("bad-input-malformed-file");

        assertThrows(TenPinBowlingException.class, () -> fileReaderService.readFile(resourcePath));
    }

    @Test
    void shouldReturnFileContentAsFileContentArrayWhenFileIsCorrect() {
        setResource("perfect-game-file");
        List<FileContent> expectedFileContent = Arrays.asList(
                new FileContentBuilder().setFileLine(1).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(2).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(3).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(4).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(5).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(6).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(7).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(8).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(9).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(10).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(11).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(12).setPlayerName("Luis").setPins("10").build()
        );

        assertDoesNotThrow(() -> {
            List<FileContent> actual = fileReaderService.readFile(resourcePath);
            FileContent expectedPlayer = expectedFileContent.get(0);
            FileContent actualPlayer = actual.get(0);

            assertEquals(expectedFileContent.size(), actual.size());
            assertEquals(expectedPlayer.getFileLine(), actualPlayer.getFileLine());
            assertEquals(expectedPlayer.getPlayerName(), actualPlayer.getPlayerName());
            assertEquals(expectedPlayer.getPinsKnockedDown(), actualPlayer.getPinsKnockedDown());
        });
    }

}