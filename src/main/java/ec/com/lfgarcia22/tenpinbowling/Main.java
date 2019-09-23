package ec.com.lfgarcia22.tenpinbowling;

import ec.com.lfgarcia22.tenpinbowling.model.FileContent;
import ec.com.lfgarcia22.tenpinbowling.model.Player;
import ec.com.lfgarcia22.tenpinbowling.model.PlayerBowling;
import ec.com.lfgarcia22.tenpinbowling.service.FileReaderService;
import ec.com.lfgarcia22.tenpinbowling.service.FrameService;
import ec.com.lfgarcia22.tenpinbowling.service.PlayerBowlingService;
import ec.com.lfgarcia22.tenpinbowling.utils.TenPinBowlingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private PlayerBowlingService playerBowlingService;

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    private FrameService frameService;

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StringBuilder result = new StringBuilder("\n\n\n\n")
                .append("Application Started...\n")
                .append("Ten-Pin Bowling\n")
                .append("By:\tLuis García Castro\n")
                .append("\n");

        for (String filePath : args) {
            result.append(printGameResultForFile(filePath))
                    .append("\n\n");
        }

        LOG.info(result.toString());
    }

    public StringBuilder printGameResultForFile(String filePath) throws TenPinBowlingException {
        StringBuilder playerResult = new StringBuilder()
                .append("File read: ")
                .append(filePath)
                .append("\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");

        List<FileContent> fileContent = fileReaderService.readFile(filePath);
        List<PlayerBowling> players = playerBowlingService.feedFromFileContent(fileContent);
        players.sort(Comparator.comparing(Player::getName));

        players.forEach(player -> {
            String pinFalls = frameService.getPinFallsFromFrames(player.getFrames());
            String frameScore = frameService.getFrameScoreFromFrames(player.getFrames());
            playerResult.append("\n")
                    .append(player.getName())
                    .append("\nPinfalls")
                    .append(pinFalls)
                    .append("\nScore\t\t")
                    .append(frameScore);
        });
        return playerResult;
    }

}
