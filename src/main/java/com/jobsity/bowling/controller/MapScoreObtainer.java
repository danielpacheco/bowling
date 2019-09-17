package com.jobsity.bowling.controller;

import com.jobsity.bowling.model.Pinfalls;
import com.jobsity.bowling.model.Player;
import com.jobsity.bowling.model.ScoreMoreThanTenException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MapScoreObtainer implements ScoreObtainer {

    private static final Logger LOGGER = Logger.getLogger(MapScoreObtainer.class.getName());

    /**
     * Get pinfalls per player
     *
     * @param playersMap
     * @return
     */
    @Override
    public Map<String, Player> getPinFalls(Map<String, List<Integer>> playersMap) {

            Map<String, Player> players = new HashMap<>();
            playersMap.forEach((playerName, pinsList) -> {

                try {

                    //initialize players
                    Player player = players.get(playerName);
                    if (player == null) {

                        player = new Player(pinsList);
                    }

                    List<Integer> playerPins = player.getPinsList();
                    List<Pinfalls> playerPinfalls = player.getPinfallsList();
                    int frame = 0;
                    for (int pinIndex = 0; pinIndex < playerPins.size() && frame++ <= 10; ) {

                        //set pinfalls
                        int pins = playerPins.get(pinIndex);
                        int nextPins = 0;
                        if ((pinIndex + 1) < playerPins.size()) {

                            nextPins = playerPins.get(pinIndex + 1);
                        }
                        Pinfalls pinfalls = new Pinfalls();

                        if (pins == 10) {

                            if (nextPins != 0) {
                                throw new ScoreMoreThanTenException(playerName, frame);
                            }

                            pinfalls.setFirst(STRIKE);
                            pinIndex += 1;

                        } else if (pins == FOUL) {

                            if (nextPins > 10) {
                                throw new ScoreMoreThanTenException(playerName, frame);
                            }

                            pinfalls.setFirst(FOUL);
                            nextPins(nextPins, pinfalls);
                            pinIndex += 2;

                        } else if (pins >= 0) {

                            pinfalls.setFirst(pins);
                            if (pins + nextPins == 10) {

                                pinfalls.setSecond(SPARE);

                            } else {

                                if (pins + nextPins < 10) {
                                    throw new ScoreMoreThanTenException(playerName, frame);
                                }

                                nextPins(nextPins, pinfalls);
                            }
                            pinIndex += 2;

                        }
                        playerPinfalls.add(pinfalls);
                    }
                    player.setPinfallsList(playerPinfalls);
                    players.put(playerName, player);

                } catch (Exception e) {

                    LOGGER.severe(e.getClass().getName() + e.getMessage());
                    System.exit(0);
                }
            });

            return players;
        }

    private void nextPins(int nextPins, Pinfalls pinfalls) {

        if (nextPins == 10) {

            pinfalls.setSecond(STRIKE);
        } else if (nextPins == FOUL) {

            pinfalls.setSecond(FOUL);
        } else {

            pinfalls.setSecond(nextPins);
        }
    }

    /**
     * Get scores per player per frame
     *
     * @param players
     * @return
     */
    @Override
    public Map<String, Player> getScore(Map<String, Player> players) {

            players.forEach((playerName, player) -> {

                List<Integer> playerPins = player.getPinsList();
                List<Pinfalls> playerPinfalls = player.getPinfallsList();
                List<Integer> scoreList = player.getScoreList();

            int pinsIndex = 0;
            int frame = 0;
            int score = 0;
            while (pinsIndex < playerPins.size() && frame <= 10) {

                Pinfalls pinfalls = playerPinfalls.get(frame++);
                if (pinfalls.getFirst().equals(STRIKE)) {

                    score += 10;
                    if ((pinsIndex + 1) < playerPins.size()) {

                        score += playerPins.get(pinsIndex + 1);
                    }
                    if ((pinsIndex + 2) < playerPins.size()) {

                        score += playerPins.get(pinsIndex + 2);
                    }
                    ++pinsIndex;

                } else if (pinfalls.getFirst().equals(FOUL)) {

                    if (((pinsIndex + 1) < playerPins.size()) && playerPins.get(pinsIndex + 1) > 0) {

                        score += playerPins.get(pinsIndex + 1);
                    }
                    pinsIndex += 2;

                } else {

                    score += playerPins.get(pinsIndex);
                    int nextPins = 0;
                    if ((pinsIndex + 1) < playerPins.size()) {

                        nextPins = playerPins.get(pinsIndex + 1);
                    }

                    if (pinfalls.getSecond().equals(SPARE)) {

                        score += nextPins;
                        if ((pinsIndex + 2) < playerPins.size()) {

                            int nextNext = playerPins.get(pinsIndex + 2);
                            if (nextNext != FOUL) {

                                score += nextNext;
                            }
                        }

                    } else {

                        score += nextPins;
                    }

                    pinsIndex += 2;

                }
                scoreList.add(score);
            }
            player.setScoreList(scoreList);
            players.put(playerName, player);

            });
            return players;
        }

}
