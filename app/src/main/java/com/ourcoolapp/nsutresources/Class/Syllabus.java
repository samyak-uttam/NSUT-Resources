package com.ourcoolapp.nsutresources.Class;

public class Syllabus {

    private String branch;

    private int imageResID;

    private String fileID;

    public Syllabus(String branch, int imageResID, String fileID) {
        this.branch = branch;
        this.imageResID = imageResID;
        this.fileID = fileID;
    }

    public String getBranch() {
        return branch;
    }

    public int getImageResID() {
        return imageResID;
    }

    public String getFileID() {
        return fileID;
    }
}
