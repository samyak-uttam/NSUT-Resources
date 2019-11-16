package com.example.nsutallin1.Class;

public class Contest {

    private String name;

    private String startingTime;

    private String endTime;

    private String contestLink;

    public Contest(String name, String startingTime, String endTime, String contestLink) {
        this.name = name;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.contestLink = contestLink;
    }

    public String getName() {
        return name;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getContestLink() {
        return contestLink;
    }
}
