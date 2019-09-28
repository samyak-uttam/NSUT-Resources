package com.example.nsutallin1.Class;

public class Notice {

    private String pubDate;

    private String title;

    private String pubBy;

    public Notice(String pubDate, String title, String pubBy) {
        this.pubDate = pubDate;
        this.title = title;
        this.pubBy = pubBy;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPubBy() {
        return pubBy;
    }
}
