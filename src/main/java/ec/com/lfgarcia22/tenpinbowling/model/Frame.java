package ec.com.lfgarcia22.tenpinbowling.model;

import java.util.HashMap;
import java.util.Map;

public class Frame {

    private final Integer id;
    private final Map<Integer, Integer> pinsKnockedDown;
    private final Map<Integer, String> pinFalls;
    private Integer score;

    public Frame(int id) {
        this.id = id;
        this.pinFalls = new HashMap<>();
        this.pinsKnockedDown = new HashMap<>();
        this.score = 0;
    }

    public Integer getId() {
        return id;
    }

    public Map<Integer, Integer> getPinsKnockedDown() {
        return pinsKnockedDown;
    }

    public Map<Integer, String> getPinFalls() {
        return pinFalls;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
