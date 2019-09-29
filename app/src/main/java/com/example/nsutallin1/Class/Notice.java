package com.example.nsutallin1.Class;

public class Notice {

    private String pubDate;

    private String title;

    private String pubBy;

    private String downloadLink;

    public Notice(String pubDate, String title, String pubBy, String downloadLink) {
        this.pubDate = pubDate;
        this.title = title;
        this.pubBy = pubBy;
        this.downloadLink = downloadLink;
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

    public String getDownloadLink() {
        return downloadLink;
    }
}
