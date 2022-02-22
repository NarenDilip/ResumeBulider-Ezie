package com.naren.resumebuilder.localdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "personInfo")
public class PersonInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Name;
    private String MobileNumber;
    private String EmailAddress;
    private String ResidentalAddress;
    private String UserImage;
    private String ResumeNo;

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

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getResidentalAddress() {
        return ResidentalAddress;
    }

    public void setResidentalAddress(String residentalAddress) {
        ResidentalAddress = residentalAddress;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getResumeNo() {
        return ResumeNo;
    }

    public void setResumeNo(String resumeNo) {
        ResumeNo = resumeNo;
    }
}
