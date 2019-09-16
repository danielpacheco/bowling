package com.jobsity.bowling.controller;

import java.util.List;
import java.util.Map;

public interface Reader {

    Map<String, List<Integer>> read(String fileName);
}
