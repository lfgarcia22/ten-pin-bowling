package ec.com.lfgarcia22.tenpinbowling.service;

import ec.com.lfgarcia22.tenpinbowling.model.FileContent;
import ec.com.lfgarcia22.tenpinbowling.model.Frame;
import ec.com.lfgarcia22.tenpinbowling.model.PlayerBowling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerBowlingService {

    @Autowired
    private FrameService frameService;

    public List<String> getNames(final List<FileContent> fileContent) {
        return fileContent.parallelStream()
                .map(FileContent::getPlayerName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public void setFramesToPlayer(List<FileContent> fileContent, PlayerBowling player) {
        int frameId = 0;
        for (FileContent content : fileContent) {
            Frame playerFrame = player.getFrames().get(frameId);
            if (frameId < 9 && content.getPinsKnockedDown() == 10) {
                frameService.addPinsKnockedDownToFrame(10, playerFrame);
                frameService.addPinFallsToFrame("", playerFrame);
                frameService.addPinFallsToFrame("X", playerFrame);
                frameId++;
            } else if (frameId < 9 && content.getPinsKnockedDown() < 10) {
                frameService.addPinsKnockedDownToFrame(content.getPinsKnockedDown(), playerFrame);
                frameService.addPinFallsToFrame(content.getPinFalls(), playerFrame);
                int pinFallsSize = player.getFrames().get(frameId).getPinFalls().size();
                if (pinFallsSize == 2) {
                    frameId++;
                }
            } else if (frameId == 9) {
                String pinFalls = content.getPinsKnockedDown() == 10
                        ? "X"
                        : content.getPinFalls();
                frameService.addPinsKnockedDownToFrame(content.getPinsKnockedDown(), playerFrame);
                frameService.addPinFallsToFrame(pinFalls, playerFrame);
            }
        }
    }

    public void setScoreToFrame(PlayerBowling player) {
        List<Frame> frames = player.getFrames();
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            Integer pinsKnockedDownTotal = streamReduceFramePinsKnockedDown(frame.getPinsKnockedDown().values());
            if (frame.getId() < 10 && frame.getPinsKnockedDown().size() == 1) {
                int toIdx = i + (frame.getId() == frames.size() - 1 ? 2 : 3);
                List<Integer> pinsKnockedDown = streamPinsKnockedDownExtraScore(player.getFrames(), i, toIdx);
                pinsKnockedDownTotal += streamReduceFramePinsKnockedDown(pinsKnockedDown.subList(0, 2));
            } else if (frame.getId() < 10 && frame.getPinsKnockedDown().size() == 2 && pinsKnockedDownTotal == 10) {
                int toIdx = i + (frame.getId() == frames.size() - 1 ? 1 : 2);
                List<Integer> pinsKnockedDown = streamPinsKnockedDownExtraScore(player.getFrames(), i, i + 2);
                pinsKnockedDownTotal += streamReduceFramePinsKnockedDown(pinsKnockedDown.subList(0, 1));
            }

            frame.setScore(player.getFinalScore() + pinsKnockedDownTotal);
            player.setFinalScore(frame.getScore());
        }
    }

    public List<PlayerBowling> feedFromFileContent(final List<FileContent> fileContent) {
        List<String> names = getNames(fileContent);
        List<PlayerBowling> players = new ArrayList<>();
        names.parallelStream().forEach(name -> {
            PlayerBowling player = new PlayerBowling(name);

            List<FileContent> fileContentByPlayer = fileContent.parallelStream()
                    .filter(content -> name.toUpperCase().equals(content.getPlayerName().toUpperCase()))
                    .sorted(Comparator.comparingInt(FileContent::getFileLine))
                    .collect(Collectors.toList());

            setFramesToPlayer(fileContentByPlayer, player);
            setScoreToFrame(player);

            players.add(player);
        });
        return players;
    }

    private Integer streamReduceFramePinsKnockedDown(Collection<Integer> pinsKnockedDown) {
        return pinsKnockedDown.stream()
                .mapToInt(pins -> pins)
                .sum();
    }

    private List<Integer> streamPinsKnockedDownExtraScore(List<Frame> frames, int fromIdx, int toIdx) {
        List<Frame> nextTwoFrames = frames.subList(fromIdx + 1, toIdx);
        List<Integer> pinsKnockedDown = new ArrayList<>();
        nextTwoFrames.forEach(_frame ->
                pinsKnockedDown.addAll(_frame.getPinsKnockedDown().values()));
        return pinsKnockedDown;
    }

}
