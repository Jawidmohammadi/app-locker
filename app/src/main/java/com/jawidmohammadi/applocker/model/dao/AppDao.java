package com.jawidmohammadi.applocker.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.jawidmohammadi.applocker.model.entity.App;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface AppDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long>insert(App app);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>>insert(Collection<App> app);

  @Update
  Single<Integer>update(App app);

  @Delete
  Single<Integer> delete(App... app);

  @Query("SELECT * FROM App ORDER BY application_id")
  LiveData<List<App>> select();




}