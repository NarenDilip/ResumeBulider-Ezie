package com.naren.resumebuilder.localdb.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonCarrierInfoDAO {
    @Insert
    public void insert(PersonCarrierInfo... personCarrierInfos);

    @Update
    public void update(PersonCarrierInfo... personCarrierInfos);

    @Delete
    public void delete(PersonCarrierInfo personCarrierInfos);

    @Query("SELECT * FROM PersonCarrierInfo")
    public List<PersonCarrierInfo> getDetails();

    @Query("SELECT * FROM PersonCarrierInfo WHERE ResumeNo = :number")
    public List<PersonCarrierInfo> getCarrierbyName(String number);

    @Query("SELECT COUNT (*) FROM PersonCarrierInfo WHERE Name = :name")
    public String getCCarriercountbyname(String name);

    @Query("UPDATE PersonCarrierInfo SET Name = :name, carrierdesc= :desc  WHERE ResumeNo = :resumeno")
    void updatePersonCarrierInfo(String name, String desc, String resumeno);

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

    @Query("DELETE FROM PersonCarrierInfo WHERE resumeno = :number")
    void DeletePersonCarrierInfo(String number);

    @Query("DELETE FROM personCarrierInfo")
    void DeleteCarrierDetails();

}
