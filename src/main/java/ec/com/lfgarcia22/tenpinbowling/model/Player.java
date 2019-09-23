package ec.com.lfgarcia22.tenpinbowling.model;

public abstract class Player {

    protected final String name;
    protected Integer finalScore;

    public Player(String name) {
        this.name = name;
        finalScore = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public abstract void setFinalScore(Integer finalScore);

}
