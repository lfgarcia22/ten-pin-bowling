package ec.com.lfgarcia22.tenpinbowling.service;

import ec.com.lfgarcia22.tenpinbowling.model.FileContent;
import ec.com.lfgarcia22.tenpinbowling.model.FileContentBuilder;

import java.util.Arrays;
import java.util.List;

class PlayerBowlingDataModelTest {

    private final List<FileContent> perfectGameData;
    private final List<FileContent> spareGameData;
    private final List<FileContent> allZeroData;
    private final List<FileContent> sampleInputData;

    PlayerBowlingDataModelTest() {
        perfectGameData = feedPerfectGameData();
        spareGameData = feedSpareGameData();
        allZeroData = feedAllZeroData();
        sampleInputData = feedSampleInputData();
    }

    private List<FileContent> feedPerfectGameData() {
        return Arrays.asList(
                new FileContentBuilder().setFileLine(1).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(2).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(3).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(4).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(5).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(6).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(7).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(8).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(9).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(10).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(11).setPlayerName("Luis").setPins("10").build(),
                new FileContentBuilder().setFileLine(12).setPlayerName("Luis").setPins("10").build()
        );
    }

    List<FileContent> getPerfectGameData() {
        return perfectGameData;
    }

    private List<FileContent> feedSpareGameData() {
        return Arrays.asList(
                new FileContentBuilder().setFileLine(1).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(2).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(3).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(4).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(5).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(6).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(7).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(8).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(9).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(10).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(11).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(12).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(13).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(14).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(15).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(16).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(17).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(18).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(19).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(20).setPlayerName("Luis").setPins("5").build(),
                new FileContentBuilder().setFileLine(21).setPlayerName("Luis").setPins("5").build()
        );
    }

    List<FileContent> getSpareGameData() {
        return spareGameData;
    }

    private List<FileContent> feedAllZeroData() {
        return Arrays.asList(
                new FileContentBuilder().setFileLine(1).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(2).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(3).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(4).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(5).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(6).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(7).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(8).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(9).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(10).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(11).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(12).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(13).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(14).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(15).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(16).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(17).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(18).setPlayerName("Luis").setPins("0").build(),
                new FileContentBuilder().setFileLine(19).setPlayerName("Luis").setPins("F").build(),
                new FileContentBuilder().setFileLine(20).setPlayerName("Luis").setPins("F").build(),
                new FileContentBuilder().setFileLine(21).setPlayerName("Luis").setPins("f").build()
        );
    }

    List<FileContent> getAllZeroData() {
        return allZeroData;
    }

    private List<FileContent> feedSampleInputData() {
        return Arrays.asList(
                new FileContentBuilder().setFileLine(1).setPlayerName("Jeff").setPins("10").build(),
                new FileContentBuilder().setFileLine(2).setPlayerName("John").setPins("3").build(),
                new FileContentBuilder().setFileLine(3).setPlayerName("John").setPins("7").build(),
                new FileContentBuilder().setFileLine(4).setPlayerName("Jeff").setPins("7").build(),
                new FileContentBuilder().setFileLine(5).setPlayerName("Jeff").setPins("3").build(),
                new FileContentBuilder().setFileLine(6).setPlayerName("John").setPins("6").build(),
                new FileContentBuilder().setFileLine(7).setPlayerName("John").setPins("3").build(),
                new FileContentBuilder().setFileLine(8).setPlayerName("Jeff").setPins("9").build(),
                new FileContentBuilder().setFileLine(9).setPlayerName("Jeff").setPins("0").build(),
                new FileContentBuilder().setFileLine(10).setPlayerName("John").setPins("10").build(),
                new FileContentBuilder().setFileLine(11).setPlayerName("Jeff").setPins("10").build(),
                new FileContentBuilder().setFileLine(12).setPlayerName("John").setPins("8").build(),
                new FileContentBuilder().setFileLine(13).setPlayerName("John").setPins("1").build(),
                new FileContentBuilder().setFileLine(14).setPlayerName("Jeff").setPins("0").build(),
                new FileContentBuilder().setFileLine(15).setPlayerName("Jeff").setPins("8").build(),
                new FileContentBuilder().setFileLine(16).setPlayerName("John").setPins("10").build(),
                new FileContentBuilder().setFileLine(17).setPlayerName("Jeff").setPins("8").build(),
                new FileContentBuilder().setFileLine(18).setPlayerName("Jeff").setPins("2").build(),
                new FileContentBuilder().setFileLine(19).setPlayerName("John").setPins("10").build(),
                new FileContentBuilder().setFileLine(20).setPlayerName("Jeff").setPins("F").build(),
                new FileContentBuilder().setFileLine(21).setPlayerName("Jeff").setPins("6").build(),
                new FileContentBuilder().setFileLine(22).setPlayerName("John").setPins("9").build(),
                new FileContentBuilder().setFileLine(23).setPlayerName("John").setPins("0").build(),
                new FileContentBuilder().setFileLine(24).setPlayerName("Jeff").setPins("10").build(),
                new FileContentBuilder().setFileLine(25).setPlayerName("John").setPins("7").build(),
                new FileContentBuilder().setFileLine(26).setPlayerName("John").setPins("3").build(),
                new FileContentBuilder().setFileLine(27).setPlayerName("Jeff").setPins("10").build(),
                new FileContentBuilder().setFileLine(28).setPlayerName("John").setPins("4").build(),
                new FileContentBuilder().setFileLine(29).setPlayerName("John").setPins("4").build(),
                new FileContentBuilder().setFileLine(30).setPlayerName("Jeff").setPins("10").build(),
                new FileContentBuilder().setFileLine(31).setPlayerName("Jeff").setPins("8").build(),
                new FileContentBuilder().setFileLine(32).setPlayerName("Jeff").setPins("1").build(),
                new FileContentBuilder().setFileLine(33).setPlayerName("John").setPins("10").build(),
                new FileContentBuilder().setFileLine(34).setPlayerName("John").setPins("9").build(),
                new FileContentBuilder().setFileLine(35).setPlayerName("John").setPins("0").build()
        );
    }

    List<FileContent> getSampleInputData() {
        return sampleInputData;
    }

}
