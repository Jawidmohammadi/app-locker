package com.jawidmohammadi.applocker.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.jawidmohammadi.applocker.model.entity.App;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * This Interface retrieve all the data from the SQLite database that room created in our entity classes.
 */
@Dao
public interface AppDao {

  /**
   * This adds a new app in the database.
   * @param app take a new app object
   * @return a single app.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long>insert(App app);

  /**
   * This add a collection of app in the database
   * @param app takes a list of apps.
   * @return list of apps
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>>insert(Collection<App> app);

  /**
   * Update an app in the database
   */
  @Update
  Single<Integer>update(App app);

  /**
   * Delete a single app from the database
   */
  @Delete
  Single<Integer> delete(App... app);

  /**
   * This query returns list of apps from database in ASCENDING order.
   * @return list of app.
   */
  @Query("SELECT * FROM App ORDER BY application_id")
  Single<List<App>> select();

  /**
   * This query select a apps by their id.
   * @return a single app by its id.
   */
  @Query("SELECT * FROM App WHERE application_id = :appId")
  Single<App> select (long appId);

  /**
   * select apps by there package name.
   */
  @Query("SELECT * FROM App WHERE package = :pkg")
  Maybe<App> select(String pkg);







}
