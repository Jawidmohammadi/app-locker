package com.jawidmohammadi.applocker.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.jawidmohammadi.applocker.model.entity.Application;
import io.reactivex.Single;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Dao
public interface ApplicationDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Application application);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Application> application);

  @Update
  Single<Integer> update(Application application);

  @Delete
  Single<Integer> delete(Application... application);

  @Query("SELECT * FROM Application ORDER BY application_id")
  LiveData<List<Application>> select(); // Let us Know when the content is changed Live Data




}
