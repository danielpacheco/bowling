package com.jobsity.bowling.model;

public class ScoreMoreThanTenException extends Exception {

    private String player;
    private int frame = 0;

    public ScoreMoreThanTenException(String playerName, int frame) {
        this.frame = frame;
        this.player = playerName;
    }

    @Override
    public String getMessage() {

        return " for player: "+player+", on frame: "+frame;
    }
}
