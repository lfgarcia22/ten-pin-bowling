package ec.com.lfgarcia22.tenpinbowling.model;

public class FileContentBuilder {

    private final FileContent obj;

    public FileContentBuilder() {
        this.obj = new FileContent();
    }

    public FileContentBuilder setFileLine(Integer fileLine) {
        obj.setFileLine(fileLine);
        return this;
    }

    public FileContentBuilder setPlayerName(String playerName) {
        obj.setPlayerName(playerName);
        return this;
    }

    public FileContentBuilder setPins(String pinFalls) {
        obj.setPinFalls(pinFalls.toUpperCase());
        int value = pinFalls.equalsIgnoreCase("f")
                ? 0
                : Integer.parseInt(pinFalls);
        obj.setPinsKnockedDown(value);
        return this;
    }

    public FileContent build() {
        return obj;
    }

}
