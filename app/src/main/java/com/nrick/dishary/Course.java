package com.nrick.dishary;

/**
 * Created by Faisal Haque Bappy on 18-Jul-19.
 */
public class Course {
    private String title, catagory, status;

    public Course() {
    }

    public Course(String title, String catagory, String status) {
        this.title = title;
        this.catagory = catagory;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
