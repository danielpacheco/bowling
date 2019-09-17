package com.jobsity.bowling.controller;

import com.jobsity.bowling.model.PlaysFileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.jobsity.bowling.controller.FileValidator.areTokensValid;

public class TextReader implements Reader {

    private static final Logger LOGGER = Logger.getLogger(TextReader.class.getName());

    @Override
    public Map<String, List<Integer>> read(String fileName) {

        try {

            Stream<String> lines = Files.lines(Paths.get(fileName));
            Map<String, List<Integer>> players = processLines(lines);
            return players;

        } catch (IOException e) {

            LOGGER.severe(new PlaysFileNotFoundException().getClass().getName());
            System.exit(0);
        }
        return null;
    }

    Map<String, List<Integer>> processLines(Stream<String> lines) {

        Map<String, List<Integer>> players = new HashMap<>();
        AtomicInteger count = new AtomicInteger(0);
        lines.forEach((line) -> {


            String[] tokens = line.split("\\t");
            if (areTokensValid(tokens, count.incrementAndGet())) {

                String playerName = tokens[0];
                String playerPins = tokens[1];
                List<Integer> pinsList = players.get(playerName);

                Integer pins = MapScoreObtainer.FOUL;
                if (!playerPins.contains("F")) {

                    pins = Integer.valueOf(playerPins);
                }

                if (pinsList == null) {

                    pinsList = new ArrayList<>();
                }
                pinsList.add(pins);
                players.put(playerName, pinsList);
            }
        });
        return players;
    }
}
