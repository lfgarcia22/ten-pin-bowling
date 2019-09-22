package ec.com.lfgarcia22.tenpinbowling.model;

public class FileContent {

    private Integer fileLine;
    private String playerName;
    private String pinFalls;
    private Integer pinsKnockedDown;

    public Integer getFileLine() {
        return fileLine;
    }

    public void setFileLine(Integer fileLine) {
        this.fileLine = fileLine;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPinFalls() {
        return pinFalls;
    }

    public void setPinFalls(String pinFalls) {
        this.pinFalls = pinFalls;
    }

    public Integer getPinsKnockedDown() {
        return pinsKnockedDown;
    }

    public void setPinsKnockedDown(Integer pinsKnockedDown) {
        this.pinsKnockedDown = pinsKnockedDown;
    }

}
