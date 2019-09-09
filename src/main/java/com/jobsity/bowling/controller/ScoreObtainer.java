package com.jobsity.bowling.controller;

import com.jobsity.bowling.model.Player;

import java.util.List;
import java.util.Map;

public interface ScoreObtainer {

    Integer STRIKE = -1;
    Integer SPARE = -2;
    Integer FOUL = -3;

    Map<String, Player> getPinFalls(Map<String, List<Integer>> playersMap);

    Map<String, Player> getScore(Map<String, Player> players);
}
