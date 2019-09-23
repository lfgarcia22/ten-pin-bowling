package ec.com.lfgarcia22.tenpinbowling.service;

import ec.com.lfgarcia22.tenpinbowling.Main;
import ec.com.lfgarcia22.tenpinbowling.model.FileContent;
import ec.com.lfgarcia22.tenpinbowling.model.FileContentBuilder;
import ec.com.lfgarcia22.tenpinbowling.model.Frame;
import ec.com.lfgarcia22.tenpinbowling.model.PlayerBowling;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
class PlayerBowlingServiceTest {

    @Autowired
    private PlayerBowlingService playerBowlingService;

    @Autowired
    private FrameService frameService;

    private static PlayerBowlingDataModelTest dataTest;

    @BeforeAll
    static void beforeAll() {
        dataTest = new PlayerBowlingDataModelTest();
    }

    @Test
    void shouldReturnDistinctNamesFromFileContent() {
        List<String> expectedNames = Arrays.asList("Jeff", "John");
        List<FileContent> fileContent = Arrays.asList(
                new FileContentBuilder().setFileLine(1).setPlayerName("John").setPins("10").build(),
                new FileContentBuilder().setFileLine(2).setPlayerName("Jeff").setPins("10").build(),
                new FileContentBuilder().setFileLine(3).setPlayerName("John").setPins("8").build(),
                new FileContentBuilder().setFileLine(4).setPlayerName("John").setPins("F").build(),
                new FileContentBuilder().setFileLine(5).setPlayerName("Jeff").setPins("10").build()
        );

        List<String> actualNames = playerBowlingService.getNames(fileContent);

        assertArrayEquals(expectedNames.toArray(), actualNames.toArray());
    }

    @Test
    void shouldSetFramesToPlayerWithPerfectGame() {
        PlayerBowling player = new PlayerBowling("Luis");
        List<FileContent> fileContent = dataTest.getPerfectGameData();

        playerBowlingService.setFramesToPlayer(fileContent, player);

        assertEquals(1, player.getFrames().get(0).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(1).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(2).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(3).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(4).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(5).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(6).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(7).getPinsKnockedDown().size());
        assertEquals(1, player.getFrames().get(8).getPinsKnockedDown().size());
        assertEquals(3, player.getFrames().get(9).getPinsKnockedDown().size());

        player.getFrames().forEach(frame -> frame.getPinsKnockedDown().values()
                .forEach(pinFall -> assertEquals(10, pinFall)));
    }

    @Test
    void shouldPrintPinFallsWithPerfectGameInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getPerfectGameData();

            String expectedText = "\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX";

            playerBowlingService.setFramesToPlayer(fileContent, player);
            String actualPinFalls = frameService.getPinFallsFromFrames(player.getFrames());

            assertEquals(expectedText, actualPinFalls);
        });
    }

    @Test
    void shouldPrintPinFallsWithSpareGameInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getSpareGameData();
            String expectedText = "\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5\t/\t5";

            playerBowlingService.setFramesToPlayer(fileContent, player);
            String actualPinFalls = frameService.getPinFallsFromFrames(player.getFrames());

            assertEquals(expectedText, actualPinFalls);
        });
    }

    @Test
    void shouldPrintPinFallsWithRollsAsZeroInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getAllZeroData();
            String expectedText = "\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\tF\tF\tF";

            playerBowlingService.setFramesToPlayer(fileContent, player);
            String actualPinFalls = frameService.getPinFallsFromFrames(player.getFrames());

            assertEquals(expectedText, actualPinFalls);
        });
    }

    @Test
    void shouldPrintPinFallsWithSampleDataInputByPlayer() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Jeff");
            List<FileContent> fileContent = dataTest.getSampleInputData();
            List<FileContent> fileContentForJeff = fileContent.stream()
                    .filter(content -> content.getPlayerName().equalsIgnoreCase("Jeff"))
                    .collect(Collectors.toList());
            String expectedPinFallForJeff = "\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1";

            playerBowlingService.setFramesToPlayer(fileContentForJeff, player);
            String actualPinFalls = frameService.getPinFallsFromFrames(player.getFrames());

            assertEquals(expectedPinFallForJeff, actualPinFalls);
        });
    }

    @Test
    void shouldSetScoreToFrameWithPerfectGameInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getPerfectGameData();
            List<Integer> expectedScore = Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);

            playerBowlingService.setFramesToPlayer(fileContent, player);
            playerBowlingService.setScoreToFrame(player);
            List<Integer> actualScore = player.getFrames().stream()
                    .map(Frame::getScore)
                    .collect(Collectors.toList());

            assertEquals(10, actualScore.size());
            assertArrayEquals(expectedScore.toArray(), actualScore.toArray());
        });
    }

    @Test
    void shouldSetScoreToFrameWithSpareGameInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getSpareGameData();
            List<Integer> expectedScore = Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150);

            playerBowlingService.setFramesToPlayer(fileContent, player);
            playerBowlingService.setScoreToFrame(player);
            List<Integer> actualScore = player.getFrames().stream()
                    .map(Frame::getScore)
                    .collect(Collectors.toList());

            assertEquals(10, actualScore.size());
            assertArrayEquals(expectedScore.toArray(), actualScore.toArray());
        });
    }

    @Test
    void shouldSetScoreToFrameWithRollsAsZeroInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getAllZeroData();
            List<Integer> expectedScore = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            playerBowlingService.setFramesToPlayer(fileContent, player);
            playerBowlingService.setScoreToFrame(player);
            List<Integer> actualScore = player.getFrames().stream()
                    .map(Frame::getScore)
                    .collect(Collectors.toList());

            assertEquals(10, actualScore.size());
            assertArrayEquals(expectedScore.toArray(), actualScore.toArray());
        });
    }

    @Test
    void shouldSetScoreToFrameWithSampleDataInputByPlayer() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Jeff");
            List<FileContent> fileContent = dataTest.getSampleInputData();
            List<FileContent> fileContentForJeff = fileContent.stream()
                    .filter(content -> content.getPlayerName().equalsIgnoreCase("Jeff"))
                    .collect(Collectors.toList());
            List<Integer> expectedScore = Arrays.asList(20, 39, 48, 66, 74, 84, 90, 120, 148, 167);

            playerBowlingService.setFramesToPlayer(fileContentForJeff, player);
            playerBowlingService.setScoreToFrame(player);
            List<Integer> actualScore = player.getFrames().stream()
                    .map(Frame::getScore)
                    .collect(Collectors.toList());

            assertEquals(10, actualScore.size());
            assertArrayEquals(expectedScore.toArray(), actualScore.toArray());
        });
    }

    @Test
    void shouldPrintScoreWithPerfectGameInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getPerfectGameData();
            String expectedText = "30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300";

            playerBowlingService.setFramesToPlayer(fileContent, player);
            playerBowlingService.setScoreToFrame(player);
            String actualFrameScore = frameService.getFrameScoreFromFrames(player.getFrames());

            assertEquals(expectedText, actualFrameScore);
        });
    }

    @Test
    void shouldPrintScoreWithSpareGameInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getSpareGameData();
            String expectedText = "15\t\t30\t\t45\t\t60\t\t75\t\t90\t\t105\t\t120\t\t135\t\t150";

            playerBowlingService.setFramesToPlayer(fileContent, player);
            playerBowlingService.setScoreToFrame(player);
            String actualFrameScore = frameService.getFrameScoreFromFrames(player.getFrames());

            assertEquals(expectedText, actualFrameScore);
        });
    }

    @Test
    void shouldPrintScoreWithRollsAsZeroInput() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Luis");
            List<FileContent> fileContent = dataTest.getAllZeroData();
            String expectedText = "0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0";

            playerBowlingService.setFramesToPlayer(fileContent, player);
            playerBowlingService.setScoreToFrame(player);
            String actualFrameScore = frameService.getFrameScoreFromFrames(player.getFrames());

            assertEquals(expectedText, actualFrameScore);
        });
    }

    @Test
    void shouldPrintScoreWithSampleDataInputByPlayer() {
        assertDoesNotThrow(() -> {
            PlayerBowling player = new PlayerBowling("Jeff");
            List<FileContent> fileContent = dataTest.getSampleInputData();
            List<FileContent> fileContentForJeff = fileContent.stream()
                    .filter(content -> content.getPlayerName().equalsIgnoreCase("Jeff"))
                    .collect(Collectors.toList());
            String expectedText = "20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167";

            playerBowlingService.setFramesToPlayer(fileContentForJeff, player);
            playerBowlingService.setScoreToFrame(player);
            String actualFrameScore = frameService.getFrameScoreFromFrames(player.getFrames());

            assertEquals(expectedText, actualFrameScore);
        });
    }

}