package com.naren.resumebuilder.localdb.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.naren.resumebuilder.localdb.model.typeconverters.DateTypeConverter;

/**
 * Created by Naren on 20,February,2022
 */

@Database(entities = {PersonInfo.class, PersonCarrierInfo.class, PersonEducationInfo.class, PersonSkillInfo.class, PersonWorkInfo.class}, version = 1)
@TypeConverters({DateTypeConverter.class})

public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonInfoDAO personInfoDAO();

    public abstract PersonCarrierInfoDAO personCarrierInfoDAO();

    public abstract PersonEducationInfoDAO personEducationInfoDAO();

    public abstract PersonSkillInfoDAO personSkillInfoDAO();

    public abstract PersonWorkInfoDAO personWorkInfoDAO();

}
