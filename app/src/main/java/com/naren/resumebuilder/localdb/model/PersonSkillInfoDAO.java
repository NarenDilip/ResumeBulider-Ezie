package com.naren.resumebuilder.localdb.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonSkillInfoDAO {
    @Insert
    public void insert(PersonSkillInfo... personSkillInfo);

    @Update
    public void update(PersonSkillInfo... personSkillInfo);

    @Delete
    public void delete(PersonSkillInfo personSkillInfo);

    @Query("SELECT * FROM personSkillInfo")
    public List<PersonSkillInfo> getDetails();

    @Query("SELECT * FROM personSkillInfo WHERE ResumeNo = :number")
    public List<PersonSkillInfo> getskillbyName(String number);

    @Query("SELECT COUNT (*) FROM personSkillInfo WHERE Name = :name")
    public String getskillcountbyname(String name);

    @Query("UPDATE personSkillInfo SET Name = :name, SkillSet= :set, Skillrate= :rate  WHERE ResumeNo = :resumeno")
    void updatepersonSkillInfo(String name, String set, String rate, String resumeno);

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

    @Query("DELETE FROM personSkillInfo WHERE resumeno = :number")
    void DeletepersonSkillInfo(String number);

    @Query("DELETE FROM personSkillInfo")
    void DeleteskillDetails();

}
