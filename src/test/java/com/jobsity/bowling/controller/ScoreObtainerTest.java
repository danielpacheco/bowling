package com.jobsity.bowling.controller;

import com.jobsity.bowling.model.Pinfalls;
import com.jobsity.bowling.model.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

import static org.junit.Assert.assertEquals;

public class ScoreObtainerTest {

    /**
     * Most common cases for pin falls
     */
    @Test
    public void getPinFallsTest() {

        ScoreObtainer scoreObtainer = new MapScoreObtainer();
        List<String> lines = getTestData();
        TextReader reader = new TextReader();

        Map<String, List<Integer>> players = reader.processLines(lines.stream());
        Map<String, Player> pinFalls = scoreObtainer.getPinFalls(players);

        Player player = pinFalls.get("Jeff");
        List<Pinfalls> pinfallsList = player.getPinfallsList();
        assertEquals(MapScoreObtainer.STRIKE, pinfallsList.get(0).getFirst());
        assertEquals((Integer)7, pinfallsList.get(1).getFirst());
        assertEquals(MapScoreObtainer.SPARE, pinfallsList.get(1).getSecond());
        assertEquals((Integer)9, pinfallsList.get(2).getFirst());
        assertEquals((Integer)0, pinfallsList.get(2).getSecond());
        assertEquals(MapScoreObtainer.STRIKE, pinfallsList.get(3).getFirst());
        assertEquals((Integer)0, pinfallsList.get(4).getFirst());
        assertEquals((Integer)8, pinfallsList.get(4).getSecond());

        Player john = pinFalls.get("John");
        pinfallsList = john.getPinfallsList();
        assertEquals((Integer)3, pinfallsList.get(0).getFirst());
        assertEquals(MapScoreObtainer.SPARE, pinfallsList.get(0).getSecond());
        assertEquals((Integer)6, pinfallsList.get(1).getFirst());
        assertEquals((Integer)3, pinfallsList.get(1).getSecond());
        assertEquals(MapScoreObtainer.STRIKE, pinfallsList.get(2).getFirst());
        assertEquals((Integer)8, pinfallsList.get(3).getFirst());
        assertEquals((Integer)1, pinfallsList.get(3).getSecond());
        assertEquals(MapScoreObtainer.STRIKE,pinfallsList.get(4).getFirst());
    }

    @Test
    public void getPinFallsMoreThanTenScoreTest() {

        ScoreObtainer scoreObtainer = new MapScoreObtainer();
        List<String> lines = getTestDataMoreThanTenScore();
        TextReader reader = new TextReader();

        Map<String, List<Integer>> players = reader.processLines(lines.stream());
        List<String> msgs = new ArrayList<>();
        Filter filter = record -> {

            msgs.add(record.getMessage());
            return true;

        };
        MapScoreObtainer.LOGGER.setFilter(filter);
        try {

            scoreObtainer.getPinFalls(players);

        } catch (RuntimeException e) { }

        assertEquals("com.jobsity.bowling.model.ScoreMoreThanTenException for player: Jeff, on frame: 2", msgs.get(0));
    }

    @Test
    public void getScoreTest() {

        ScoreObtainer scoreObtainer = new MapScoreObtainer();
        List<String> lines = getTestData();
        TextReader reader = new TextReader();

        Map<String, List<Integer>> players = reader.processLines(lines.stream());
        Map<String, Player> pinFalls = scoreObtainer.getPinFalls(players);
        Map<String, Player> score = scoreObtainer.getScore(pinFalls);

        Player jeff = score.get("Jeff");
        List<Integer> scoreList = jeff.getScoreList();
        assertEquals((Integer)20, scoreList.get(0));
        assertEquals((Integer)39, scoreList.get(1));
        assertEquals((Integer)48, scoreList.get(2));
        assertEquals((Integer)66, scoreList.get(3));
        assertEquals((Integer)74, scoreList.get(4));

        Player john = score.get("John");
        scoreList = john.getScoreList();
        assertEquals((Integer)16, scoreList.get(0));
        assertEquals((Integer)25, scoreList.get(1));
        assertEquals((Integer)44, scoreList.get(2));
        assertEquals((Integer)53, scoreList.get(3));
        //cause we don't have more moves for this test
        assertEquals((Integer)63, scoreList.get(4));

    }

    private List<String> getTestData() {
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

    private List<String> getTestDataMoreThanTenScore() {
        return Arrays.asList(
                "Jeff\t10",
                "John\t3",
                "John\t7",
                "Jeff\t7",
                "Jeff\t5"
        );
    }

}
