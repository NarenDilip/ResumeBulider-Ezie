package com.naren.resumebuilder.localdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "personSkillInfo")
public class PersonSkillInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Name;
    private String ResumeNo;
    private String SkillSet;
    private String Skillrate;

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

    public String getSkillSet() {
        return SkillSet;
    }

    public void setSkillSet(String skillSet) {
        SkillSet = skillSet;
    }

    public String getSkillrate() {
        return Skillrate;
    }

    public void setSkillrate(String skillrate) {
        Skillrate = skillrate;
    }
}
