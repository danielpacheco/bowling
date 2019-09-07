package com.jobsity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Reader {

    public Map<String, List<Integer>> read(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            AtomicReference<Map<String, List<Integer>>> playersReference =
                    new AtomicReference<>(new HashMap<String, List<Integer>>());

            stream.forEach((line)->{

                String[] tokens = line.split("\t");
                if (tokens.length > 1) {

                    String playerName = tokens[0];
                    String playerPins = tokens[1];
                    Map<String, List<Integer>> players = playersReference.get();
                    List<Integer> pinsList = players.get(playerName);

                    //todo add pins validator
                    Integer pins = 0;
                    if (playerPins.contains("F"))
                        pins = Scorer.FOUL;
                    else
                        pins = Integer.valueOf(playerPins);

                    if (pinsList == null) {
                        pinsList = new ArrayList<>();
                    }
                    pinsList.add(pins);
                    players.put(playerName, pinsList);
                    playersReference.set(players);

                }
            });
            return playersReference.get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
