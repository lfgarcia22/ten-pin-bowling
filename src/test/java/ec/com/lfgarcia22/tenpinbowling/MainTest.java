package ec.com.lfgarcia22.tenpinbowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
class MainTest {

    @Autowired
    private Main main;

    private String getResourcePath(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return resource.getPath();
    }

    @Test
    void shouldPrintResultSuccessfullyWhenPerfectGameFileRead() {
        assertDoesNotThrow(() -> {
            String resourcePath = getResourcePath("perfect-game-file");
            StringBuilder expectedResult = new StringBuilder()
                    .append("File read: ")
                    .append(resourcePath)
                    .append("\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n")
                    .append("Luis\n")
                    .append("Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n")
                    .append("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300");

            StringBuilder actualResult = main.printGameResultForFile(resourcePath);

            assertEquals(expectedResult.toString(), actualResult.toString());
        });
    }

    @Test
    void shouldPrintResultSuccessfullyWhenSpareGameFileRead() {
        assertDoesNotThrow(() -> {
            String resourcePath = getResourcePath("spare-game-file");
            StringBuilder expectedResult = new StringBuilder()
                    .append("File read: ")
                    .append(resourcePath)
                    .append("\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n")
                    .append("Luis\n")
                    .append("Pinfalls\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\n")
                    .append("Score\t\t15\t\t30\t\t45\t\t60\t\t75\t\t90\t\t105\t\t120\t\t135\t\t150");

            StringBuilder actualResult = main.printGameResultForFile(resourcePath);

            assertEquals(expectedResult.toString(), actualResult.toString());
        });
    }

    @Test
    void shouldPrintResultSuccessfullyWhenAllZeroFileRead() {
        assertDoesNotThrow(() -> {
            String resourcePath = getResourcePath("all-zero-file");
            StringBuilder expectedResult = new StringBuilder()
                    .append("File read: ")
                    .append(resourcePath)
                    .append("\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n")
                    .append("Luis\n")
                    .append("Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\tF\tF\tF\n")
                    .append("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0");

            StringBuilder actualResult = main.printGameResultForFile(resourcePath);

            assertEquals(expectedResult.toString(), actualResult.toString());
        });
    }

    @Test
    void shouldPrintResultSuccessfullyWhenSampleInputFileRead() {
        assertDoesNotThrow(() -> {
            String resourcePath = getResourcePath("sample-input-file");
            StringBuilder expectedResult = new StringBuilder()
                    .append("File read: ")
                    .append(resourcePath)
                    .append("\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n")
                    .append("Jeff\n")
                    .append("Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n")
                    .append("Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n")
                    .append("John\n")
                    .append("Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n")
                    .append("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151");

            StringBuilder actualResult = main.printGameResultForFile(resourcePath);

            assertEquals(expectedResult.toString(), actualResult.toString());
        });
    }

    @Test
    void shouldExecuteSuccessfullyMainProgram() {
        assertDoesNotThrow(() -> {
            String resourcePath1 = getResourcePath("sample-input-file");
            String resourcePath2 = getResourcePath("perfect-game-file");

            main.run(resourcePath1, resourcePath2);
        });
    }

}