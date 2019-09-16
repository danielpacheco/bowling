package com.jobsity.bowling.view;

import com.jobsity.bowling.model.Player;

import java.util.Map;

public interface Renderer {

    void render(Map<String, Player> players);
}
