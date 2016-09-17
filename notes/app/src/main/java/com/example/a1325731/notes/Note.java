package com.example.a1325731.notes;

import java.util.Date;

/**
 * Created by 1325731 on 2016-09-16.
 */
public class Note {
    private String title;
    private String body;
    private int category;
    private boolean hasReminder;
    private Date reminder;

    public Note() {
        this.title = "";
        this.body = "";
        this.category = 0;
        this.hasReminder = false;
        this.reminder = new Date();
    }

    public Note(String title, String body, int category, boolean hasReminder, Date reminder) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.hasReminder = hasReminder;
        this.reminder = reminder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    @Override
    public String toString() {
        String noteString;

        if (isHasReminder()) {
           noteString = "T:" + getTitle() + ", B:" + getBody() + ", C:" +  getCategory() + ", R:" +  getReminder();
        }
        else {
            noteString = "T:" + getTitle() + ", B:" + getBody() + ", C:" +  getCategory();
        }

        return noteString;
    }

}
