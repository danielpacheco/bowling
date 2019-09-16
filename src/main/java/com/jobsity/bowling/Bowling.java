package com.jobsity.bowling;

import com.jobsity.bowling.controller.Reader;
import com.jobsity.bowling.controller.ScoreObtainer;
import com.jobsity.bowling.model.Player;
import com.jobsity.bowling.controller.TextReader;
import com.jobsity.bowling.controller.MapScoreObtainer;
import com.jobsity.bowling.view.Renderer;
import com.jobsity.bowling.view.TextRenderer;

import java.util.List;
import java.util.Map;

public class Bowling {

    /**
     * From the PDF I believe I've done all the tasks, including:


     "Your program should be able to handle all possible cases of a game both including:
     - "a game where all rolls are 0,"
     - "all rolls are fouls (F) and"
     - a perfect game, where all rolls are strikes

     - Unit test: Tests should cover at least the non-trivial classes and methods
     - Integration test: At least cover the three main cases:
     - Sample input (2 players),
     - perfect score, zero score -> **Tested integration with files**

     - "Code should depend on interfaces",


     - validator on reader
     * The program should handle bad input like
     - more than ten throws -> **but as far as I understand this is valid if last one is a spare or a strike**
     - no chance will produce a negative number of knocked down pins
     - or more than 10 pins or frames -> **in case of frames, moves after 11 are ignored**
     - invalid score value
     - or incorrect format
     "
     "
     * @param args
     */
    public static void main(String[] args) {

        Reader textReader = new TextReader();
        Map<String, List<Integer>> playersMap = textReader.read(args[0]);

        ScoreObtainer scoreObtainer = new MapScoreObtainer();
        Map<String, Player> players = scoreObtainer.getPinFalls(playersMap);
        players = scoreObtainer.getScore(players);

        Renderer renderer = new TextRenderer();
        renderer.render(players);
    }
}
