package com.example.atishay.event_lister;


public class Events_db {

    private String Student_chapter_name;
    private String Event_name;
    private String Event_Category ;
    private String Event_Description;
    private String Event_Date;
    private String Event_Time;
    private int Max_Registration;
    private String End_Date;
    private String End_Time;
    private String picturePath;
    private String id;

    public Events_db(String student_chapter_name, String event_name, String event_Category, String event_Description, String event_Date, String event_Time, int max_Registration, String end_Date, String end_Time, String picturePath, String id) {
        Student_chapter_name = student_chapter_name;
        Event_name = event_name;
        Event_Category = event_Category;
        Event_Description = event_Description;
        Event_Date = event_Date;
        Event_Time = event_Time;
        Max_Registration = max_Registration;
        End_Date = end_Date;
        End_Time = end_Time;
        this.picturePath = picturePath;
        this.id = id;
    }

    public Events_db(){

    }
    public String getStudent_chapter_name() {
        return Student_chapter_name;
    }

    public void setStudent_chapter_name(String student_chapter_name) {
        Student_chapter_name = student_chapter_name;
    }

    public String getEvent_name() {
        return Event_name;
    }

    public void setEvent_name(String event_name) {
        Event_name = event_name;
    }

    public String getEvent_Category() {
        return Event_Category;
    }

    public void setEvent_Category(String event_Category) {
        Event_Category = event_Category;
    }

    public String getEvent_Description() {
        return Event_Description;
    }

    public void setEvent_Description(String event_Description) {
        Event_Description = event_Description;
    }

    public String getEvent_Date() {
        return Event_Date;
    }

    public void setEvent_Date(String event_Date) {
        Event_Date = event_Date;
    }

    public String getEvent_Time() {
        return Event_Time;
    }

    public void setEvent_Time(String event_Time) {
        Event_Time = event_Time;
    }

    public int getMax_Registration() {
        return Max_Registration;
    }

    public void setMax_Registration(int max_Registration) {
        Max_Registration = max_Registration;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        End_Time = end_Time;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
