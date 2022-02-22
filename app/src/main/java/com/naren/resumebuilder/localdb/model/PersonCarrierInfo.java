package com.naren.resumebuilder.localdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "PersonCarrierInfo")
public class PersonCarrierInfo  {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Name;
    private String ResumeNo;
    private String carrierdesc;

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

    public String getCarrierdesc() {
        return carrierdesc;
    }

    public void setCarrierdesc(String carrierdesc) {
        this.carrierdesc = carrierdesc;
    }
}
