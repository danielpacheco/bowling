package com.jobsity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Scorer {

    public static final Integer STRIKE = -1;
    public static final Integer SPARE = -2;
    public static final Integer FOUL = -3;

    public AtomicReference<Map<String, Player>> getPinFalls(Map<String, List<Integer>> playersMap) {

            AtomicReference<Map<String, Player>> playersReference = new AtomicReference<>(new HashMap<String, Player>());
            playersMap.forEach((playerName, pinsList) -> {

                //initialize player
                Map<String, Player> players = playersReference.get();
                Player player = players.get(playerName);
                if (player == null) {
                    player = new Player(pinsList);
                }

                List<Integer> playerPins = player.getPinsList();
                List<Pinfalls> playerPinfalls = player.getPinfallsList();
                int pinIndex = 0;
                for (int frame = 0; frame < 10; frame++) {

                    //set pinfalls
                    Integer pins = playerPins.get(pinIndex);
                    Integer nextPins = playerPins.get(pinIndex + 1);
                    Pinfalls pinfalls = new Pinfalls();
                    if (pins == 10) {
                        pinfalls.setFirst(STRIKE);
                    } else if (pins < 0) {
                        pinfalls.setFirst(FOUL);
                        if (nextPins == 10)
                            pinfalls.setSecond(STRIKE);
                        else
                            pinfalls.setSecond(nextPins);
                    } else if (pins > 0) {
                        pinfalls.setFirst(pins);
                        if (pins+nextPins == 10) {
                            pinfalls.setSecond(SPARE);
                        } else {
                            pinfalls.setSecond(nextPins);
                        }
                    }

                    playerPinfalls.add(pinfalls);
                }
                player.setPinfallsList(playerPinfalls);
                players.put(playerName, player);
                playersReference.set(players);

            });

            return playersReference;
        }

        public void getScore(AtomicReference<Map<String, Player>> players) {

        }

}
