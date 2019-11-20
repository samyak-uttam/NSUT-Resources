package com.example.nsutallin1.Class;

public class Course {

    private int imgResId;

    private String courseName;

    private String courseDescription;

    public Course(int imgResId, String courseName, String courseDescription) {
        this.imgResId = imgResId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public int getImgResId() {
        return imgResId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
