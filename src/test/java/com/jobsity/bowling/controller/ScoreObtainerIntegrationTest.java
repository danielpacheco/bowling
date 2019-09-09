package com.jobsity.bowling.controller;

import com.jobsity.bowling.model.Pinfalls;
import com.jobsity.bowling.model.Player;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ScoreObtainerIntegrationTest {

    @Test
    public void testGetPlayers() {

        ScoreObtainer scoreObtainer = new MapScoreObtainer();
        List<String> lines = getLines();
        TextReader reader = new TextReader();

        Map<String, List<Integer>> players = reader.processLines(lines.stream());
        Map<String, Player> pinfallsMap = scoreObtainer.getPinFalls(players);

        Player jeff = pinfallsMap.get("Jeff");
        List<Pinfalls> pinfallsList = jeff.getPinfallsList();
        assertEquals(MapScoreObtainer.STRIKE, pinfallsList.get(0).getFirst());
        assertEquals((Integer)7, pinfallsList.get(1).getFirst());
        assertEquals(MapScoreObtainer.SPARE, pinfallsList.get(1).getSecond());
        assertEquals((Integer)9, pinfallsList.get(2).getFirst());
        assertEquals((Integer)0, pinfallsList.get(2).getSecond());
        assertEquals(MapScoreObtainer.STRIKE, pinfallsList.get(3).getFirst());
        assertEquals((Integer)0, pinfallsList.get(4).getFirst());
        assertEquals((Integer)8, pinfallsList.get(4).getSecond());

        Player john = pinfallsMap.get("John");
        pinfallsList = john.getPinfallsList();
        assertEquals((Integer)3, pinfallsList.get(0).getFirst());
        assertEquals(MapScoreObtainer.SPARE, pinfallsList.get(0).getSecond());
        assertEquals((Integer)6, pinfallsList.get(1).getFirst());
        assertEquals((Integer)3, pinfallsList.get(1).getSecond());
        assertEquals(MapScoreObtainer.STRIKE, pinfallsList.get(2).getFirst());
        assertEquals((Integer)8, pinfallsList.get(3).getFirst());
        assertEquals((Integer)1, pinfallsList.get(3).getSecond());
        assertEquals(MapScoreObtainer.STRIKE,pinfallsList.get(4).getFirst());

        Map<String, Player> scoreMap = scoreObtainer.getScore(pinfallsMap);
        jeff = scoreMap.get("Jeff");
        List<Integer> scoreList = jeff.getScoreList();
        assertEquals((Integer)20, scoreList.get(0));
        assertEquals((Integer)39, scoreList.get(1));
        assertEquals((Integer)48, scoreList.get(2));
        assertEquals((Integer)66, scoreList.get(3));
        assertEquals((Integer)74, scoreList.get(4));

        john = scoreMap.get("John");
        scoreList = john.getScoreList();
        assertEquals((Integer)16, scoreList.get(0));
        assertEquals((Integer)25, scoreList.get(1));
        assertEquals((Integer)44, scoreList.get(2));
        assertEquals((Integer)53, scoreList.get(3));
        //cause we don't have more moves for this test
        assertEquals((Integer)63, scoreList.get(4));
    }

    private List<String> getLines() {
        return Arrays.asList(
                    "Jeff\t10",
                    "John\t3",
                    "John\t7",
                    "Jeff\t7",
                    "Jeff\t3",
                    "John\t6",
                    "John\t3",
                    "Jeff\t9",
                    "Jeff\t0",
                    "John\t10",
                    "Jeff\t10",
                    "John\t8",
                    "John\t1",
                    "Jeff\t0",
                    "Jeff\t8",
                    "John\t10"
            );
    }
}
