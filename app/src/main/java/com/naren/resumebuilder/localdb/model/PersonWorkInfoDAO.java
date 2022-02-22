package com.naren.resumebuilder.localdb.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonWorkInfoDAO {
    @Insert
    public void insert(PersonWorkInfo... personWorkInfos);

    @Update
    public void update(PersonWorkInfo... personWorkInfos);

    @Delete
    public void delete(PersonWorkInfo personWorkInfo);

    @Query("SELECT * FROM personWorkInfo")
    public List<PersonWorkInfo> getDetails();

    @Query("SELECT * FROM personWorkInfo WHERE ResumeNo = :number")
    public List<PersonWorkInfo> getworkbyName(String number);

    @Query("SELECT COUNT (*) FROM personWorkInfo WHERE Name = :name")
    public String getworkcountbyname(String name);

    @Query("UPDATE personWorkInfo SET Name = :name, CompanyInfo= :company, JobTitle= :job, StartDate= :start, EndDate= :end, JobDescription= :jobdesc  WHERE ResumeNo = :resumeno")
    void updatepersonWorkInfo(String name, String company, String job, String start, String end, String jobdesc, String resumeno);

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

    @Query("DELETE FROM personWorkInfo WHERE resumeno = :number")
    void DeletepersonWorkInfo(String number);

    @Query("DELETE FROM personWorkInfo")
    void DeleteCarrierDetails();

}
