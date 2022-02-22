package com.naren.resumebuilder.basepojo;

/**
 * Created by Naren on 21,February,2022
 */
public class PersonResumeData {

    String name;
    String designation;
    String image;
    String resumeId;

    public PersonResumeData(String name, String designation, String image, String resumeId) {
        this.name = name;
        this.designation = designation;
        this.image = image;
        this.resumeId=resumeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
}
