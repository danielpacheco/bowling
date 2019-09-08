package com.jobsity.bowling;

import com.jobsity.bowling.model.Player;
import com.jobsity.bowling.controller.Reader;
import com.jobsity.bowling.controller.ScoreObtainer;
import com.jobsity.bowling.view.Renderer;

import java.util.List;
import java.util.Map;

public class Bowling {

    public static void main(String[] args) {

        Map<String, List<Integer>> playersMap = new Reader().read(args[0]);
        ScoreObtainer scoreObtainer = new ScoreObtainer();
        Map<String, Player> players = scoreObtainer.getPinFalls(playersMap);
        players = scoreObtainer.getScore(players);
        new Renderer().render(players);
    }
}
