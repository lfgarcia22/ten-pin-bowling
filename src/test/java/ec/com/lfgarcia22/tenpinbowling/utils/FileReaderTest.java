package ec.com.lfgarcia22.tenpinbowling.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {

    private FileReader fileReader;
    private ClassLoader classLoader;
    private String resourcePath;

    @BeforeEach
    public void beforeEach() {
        fileReader = new FileReader();
        classLoader = getClass().getClassLoader();
    }

    private void setResource(String fileName) {
        URL resource = classLoader.getResource(fileName);
        resourcePath = resource.getPath();
    }

    @Test
    public void shouldThrowExceptionWhenFileDoesNotExist() {
        assertThrows(TenPinBowlingException.class, () -> fileReader.readFile(""));
    }

    @Test
    public void shouldThrowExceptionWhenFileIsEmpty() {
        setResource("empty-file");

        assertThrows(TenPinBowlingException.class, () -> fileReader.readFile(resourcePath), "Empty File!");
    }

    @Test
    public void shouldNotThrowExceptionWhenFileExistsInClasspathAndNotEmpty() {
        setResource("perfect-game-file");

        assertDoesNotThrow(() -> fileReader.readFile(resourcePath));
    }

    @Test
    public void shouldThrowExceptionWhenAnyLineIsNotValid() {
        setResource("bad-input-no-score-file");

        assertThrows(TenPinBowlingException.class, () -> fileReader.readFile(resourcePath), "Bad Input!");
    }

    @Test
    public void shouldThrowExceptionWhenFileHasAnyRowWithNoScore() {
        setResource("bad-input-no-score-file");

        assertThrows(TenPinBowlingException.class, () -> fileReader.readFile(resourcePath));
    }

    @Test
    public void shouldThrowExceptionWhenFileHasAnyRowWithNegativeScore() {
        setResource("bad-input-negative-score-file");

        assertThrows(TenPinBowlingException.class, () -> fileReader.readFile(resourcePath));
    }

    @Test
    public void shouldThrowExceptionWhenFileHasAnyRowMalformed() {
        setResource("bad-input-malformed-file");

        assertThrows(TenPinBowlingException.class, () -> fileReader.readFile(resourcePath));
    }

    @Test
    public void shouldReturnFileContentAsStringArrayWhenFileIsCorrect() {
        setResource("perfect-game-file");
        List<String> expectedFileContent = Arrays.asList(
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10",
                "Luis\t10"
        );

        assertDoesNotThrow(() -> {
            List<String> actual = fileReader.readFile(resourcePath);

            assertArrayEquals(expectedFileContent.toArray(), actual.toArray());
        });
    }

}