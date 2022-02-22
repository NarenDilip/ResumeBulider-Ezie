package com.naren.resumebuilder.localdb.model;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface PersonInfoDAO {

    @Insert
    public void insert(PersonInfo... personInfo);

    @Update
    public void update(PersonInfo... personInfo);

    @Delete
    public void delete(PersonInfo personInfo);

    @Query("SELECT * FROM personInfo")
    public List<PersonInfo> getDetails();

    @Query("SELECT * FROM personInfo WHERE ResumeNo = :number")
    public List<PersonInfo> getbyName(String number);

    @Query("SELECT COUNT (*) FROM personInfo WHERE Name = :name")
    public String getCountbyname(String name);

    @Query("UPDATE personInfo SET Name = :name, MobileNumber= :number,EmailAddress= :email, ResidentalAddress= :address, UserImage= :image  WHERE ResumeNo = :resumeno")
    void updatepersonInfo(String name, String number, String email, String address, String image, String resumeno);

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

    @Query("DELETE FROM personInfo WHERE resumeno = :number")
    void DeletepersonInfo(String number);

    @Query("DELETE FROM personInfo")
    void DeletePersonUserDetails();

}
