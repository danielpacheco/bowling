package com.jobsity.bowling;

import com.jobsity.bowling.model.Player;
import com.jobsity.bowling.controller.TextReader;
import com.jobsity.bowling.controller.MapScoreObtainer;
import com.jobsity.bowling.view.TextRenderer;

import java.util.List;
import java.util.Map;

public class Bowling {

    /*
    v/
        Your program should be able to handle all possible cases of a game both including:
        v/ "a game where all rolls are 0,"
        v/ "all rolls are fouls (F) and"
        v/ a perfect game, where all rolls are strikes

    Unit test: Tests should cover at least the non-trivial classes and methods
    Integration test: At least cover the three main cases:
        v/ Sample input (2 players),
        v/ perfect score, zero score -> Tested integration with files

        v/ "Code should depend on interfaces",

    todo
        validator on reader
             * The program should handle bad input like
             more than ten throws -> but as far as I understand this is valid if last one is a spare or a strike
             todo no chance will produce a negative number of knocked down pins
             todo or more than 10
             todo invalid score value
             todo or incorrect format

    */

    public static void main(String[] args) {

        Map<String, List<Integer>> playersMap = new TextReader().read(args[0]);
        MapScoreObtainer scoreObtainer = new MapScoreObtainer();
        Map<String, Player> players = scoreObtainer.getPinFalls(playersMap);
        players = scoreObtainer.getScore(players);
        new TextRenderer().render(players);
    }
}
