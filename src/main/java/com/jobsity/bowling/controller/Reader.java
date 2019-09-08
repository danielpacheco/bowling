package com.jobsity.bowling.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Reader {

    public Map<String, List<Integer>> read(String fileName) {


        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));
            Map<String, List<Integer>> players = new HashMap<>();

            lines.forEach((line) -> {

                String[] tokens = line.split("\\t");
                if (tokens.length > 1) {

                    String playerName = tokens[0];
                    String playerPins = tokens[1];
                    List<Integer> pinsList = players.get(playerName);

                    //todo add pins validator
                    Integer pins = ScoreObtainer.FAULT;
                    if (!playerPins.contains("F"))
                        pins = Integer.valueOf(playerPins);

                    if (pinsList == null) {
                        pinsList = new ArrayList<>();
                    }
                    pinsList.add(pins);
                    players.put(playerName, pinsList);
                }
            });
            return players;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
