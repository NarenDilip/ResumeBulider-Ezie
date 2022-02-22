package com.naren.resumebuilder.localdb.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonEducationInfoDAO {
    @Insert
    public void insert(PersonEducationInfo... personEducationInfo);

    @Update
    public void update(PersonEducationInfo... personEducationInfo);

    @Delete
    public void delete(PersonEducationInfo personEducationInfo);

    @Query("SELECT * FROM personEducationInfo")
    public List<PersonEducationInfo> getDetails();

    @Query("SELECT * FROM personEducationInfo WHERE ResumeNo = :number")
    public List<PersonEducationInfo> getworkbyName(String number);

    @Query("SELECT COUNT (*) FROM personEducationInfo WHERE Name = :name")
    public String getworkcountbyname(String name);

    @Query("UPDATE personEducationInfo SET Name = :name, Course= :course, School= :school, Score= :score, PassYear= :year  WHERE ResumeNo = :resumeno")
    void updatepersonEducationInfo(String name, String course, String school, String score, String year, String resumeno);

//    @Query("SELECT COUNT (*) FROM devices WHERE Name = :type AND createdDate = :date")
//    public String getDevicedetailsbyname(String type,String date);
//
//    //    @Query("SELECT * FROM devices")
//    @Query("SELECT * FROM devices ORDER BY id DESC")
//    public LiveData<List<Devices>> getAllDevices();
//
//    @Query("SELECT * FROM devices WHERE Name = :type ORDER BY id DESC")
//    public List<Devices> NoDevices(String type);
//
//    @Query("SELECT * FROM devices ORDER BY id DESC")
//    public List<Devices> getSuperDevices();

    @Query("DELETE FROM personEducationInfo WHERE resumeno = :number")
    void DeletepersonEducationInfo(String number);

    @Query("DELETE FROM personEducationInfo")
    void DeleteCarrierDetails();

}
