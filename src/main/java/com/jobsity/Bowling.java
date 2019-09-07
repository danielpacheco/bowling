package com.jobsity;

import java.util.List;
import java.util.Map;

public class Bowling {

    public static void main(String[] args) {

        Reader reader = new Reader();
        Map<String, List<Integer>> players = reader.read(args[0]);

    }
}
