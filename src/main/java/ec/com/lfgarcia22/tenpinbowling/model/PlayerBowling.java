package ec.com.lfgarcia22.tenpinbowling.model;

import java.util.Arrays;
import java.util.List;

public class PlayerBowling extends Player {

    private final List<Frame> frames = Arrays.asList(
            new Frame(1),
            new Frame(2),
            new Frame(3),
            new Frame(4),
            new Frame(5),
            new Frame(6),
            new Frame(7),
            new Frame(8),
            new Frame(9),
            new Frame(10)
    );

    public PlayerBowling(String name) {
        super(name);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    @Override
    public void setFinalScore(Integer finalScore) {
        super.finalScore = finalScore;
    }

}
