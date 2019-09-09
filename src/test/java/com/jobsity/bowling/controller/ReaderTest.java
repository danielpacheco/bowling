package com.jobsity.bowling.controller;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ReaderTest {

    @Test
    public void processLinesTest() {

        TextReader reader = new TextReader();
        List<String> lines = Arrays.asList(
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

        Map<String, List<Integer>> players = reader.processLines(lines.stream());
        List<Integer> jeff = players.get("Jeff");
        assertEquals((Integer)10, jeff.get(0));
        assertEquals((Integer) 7, jeff.get(1));
        assertEquals((Integer) 3, jeff.get(2));
        assertEquals((Integer) 9, jeff.get(3));
        assertEquals((Integer) 0, jeff.get(4));
        assertEquals((Integer)10, jeff.get(5));
        assertEquals((Integer) 0, jeff.get(6));
        assertEquals((Integer) 8, jeff.get(7));

        List<Integer> john = players.get("John");
        assertEquals((Integer) 3, john.get(0));
        assertEquals((Integer) 7, john.get(1));
        assertEquals((Integer) 6, john.get(2));
        assertEquals((Integer) 3, john.get(3));
        assertEquals((Integer)10, john.get(4));
        assertEquals((Integer) 8, john.get(5));
        assertEquals((Integer) 1, john.get(6));
        assertEquals((Integer)10, john.get(7));
    }
}
