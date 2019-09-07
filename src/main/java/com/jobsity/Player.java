package com.jobsity;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Integer> pinsList;
    private List<Integer> scoreList;
    private List<Pinfalls> pinfallsList;

    public Player(List<Integer> pinsList) {
        this.pinsList = pinsList;
        this.scoreList = new ArrayList<>();
        this.pinfallsList = new ArrayList<>();
    }

    public List<Integer> getPinsList() {
        return pinsList;
    }

    public void setPinsList(List<Integer> pinsList) {
        this.pinsList = pinsList;
    }

    public List<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Integer> scoreList) {
        this.scoreList = scoreList;
    }

    public List<Pinfalls> getPinfallsList() {
        return pinfallsList;
    }

    public void setPinfallsList(List<Pinfalls> pinfallsList) {
        this.pinfallsList = pinfallsList;
    }
}
