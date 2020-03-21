package com.jawidmohammadi.applocker.services;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
//import com.jawidmohammadi.applocker.BackgroundServices;
import com.jawidmohammadi.applocker.model.dao.AppDao;
import com.jawidmohammadi.applocker.model.dao.AttemptDao;
import com.jawidmohammadi.applocker.model.entity.App;
import com.jawidmohammadi.applocker.model.entity.Attempt;
import com.jawidmohammadi.applocker.services.AppLockerDatabase.Converters;
import java.util.Date;

@Database (
    entities = {App.class , Attempt.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class AppLockerDatabase extends RoomDatabase {

  private static final String DB_NAME = "applocker_database";
  private static Application context;

  // background services constructor
//  public AppLockerDatabase(BackgroundServices backgroundServices) {

//  }

  public static void setContext(Application context){
    AppLockerDatabase.context = context;

  }
  public static AppLockerDatabase getInstance(){
    return InstanceHolder.INSTANCE;
  }

  public abstract AppDao getApplicationDao();
  public abstract AttemptDao getAttemptDao();
  private static class InstanceHolder {

    private static final AppLockerDatabase INSTANCE = Room.databaseBuilder(
        context, AppLockerDatabase.class, DB_NAME)
        .build();
  }

  public static class Converters {

    @TypeConverter
    public static Long formDate(Date date){
      return (date != null) ? date.getTime() : null;
    }

    @TypeConverter
    public static Date formLong(Long value){
      return (value != null) ? new Date(value) : null;
    }

  }


}

