package com.ourcoolapp.nsutresources.Class;

public class Contest {

    private int imgResId;

    private String name;

    private String startingTime;

    private String endTime;

    private String contestLink;

    public Contest(int imgResId, String name, String startingTime, String endTime, String contestLink) {
        this.imgResId = imgResId;
        this.name = name;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.contestLink = contestLink;
    }

    public int getImgResId() {
        return imgResId;
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
