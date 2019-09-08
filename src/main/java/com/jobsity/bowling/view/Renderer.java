package com.jobsity.bowling.view;

import com.jobsity.bowling.controller.ScoreObtainer;
import com.jobsity.bowling.model.Pinfalls;
import com.jobsity.bowling.model.Player;

import java.util.List;
import java.util.Map;

public class Renderer {

    public void render(Map<String, Player> players) {

        System.out.println("Frame"+"\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        players.forEach((playerName, player) -> {

            System.out.println(playerName);
            System.out.print("Pinfalls\t");
            List<Pinfalls> pinfallsList = player.getPinfallsList();
            pinfallsList.forEach((pinfall) -> {

                Integer first = pinfall.getFirst();
                display(first);
                if (first != ScoreObtainer.STRIKE)
                    display(pinfall.getSecond());
                else
                    System.out.print("\t");
            });
            System.out.println();
            List<Integer> scoreList = player.getScoreList();
            System.out.print("Score\t\t");
            scoreList.forEach((score) -> {

                System.out.print(score+"\t\t");
            });
            System.out.println();
        });
    }

    private void display(Integer pinfall) {

        if (pinfall == ScoreObtainer.STRIKE) {
            System.out.print("X\t");
        } else if (pinfall == ScoreObtainer.SPARE) {
            System.out.print("/\t");
        } else if (pinfall == ScoreObtainer.FAULT) {
            System.out.print("F\t");
        } else {
            System.out.print(pinfall+"\t");
        }
    }
}
