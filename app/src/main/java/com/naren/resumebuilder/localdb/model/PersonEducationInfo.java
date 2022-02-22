package com.naren.resumebuilder.localdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "personEducationInfo")
public class PersonEducationInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Name;
    private String ResumeNo;
    private String Course;
    private String School;
    private String Score;
    private String PassYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getResumeNo() {
        return ResumeNo;
    }

    public void setResumeNo(String resumeNo) {
        ResumeNo = resumeNo;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getPassYear() {
        return PassYear;
    }

    public void setPassYear(String passYear) {
        PassYear = passYear;
    }
}
