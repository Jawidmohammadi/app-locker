package com.jawidmohammadi.applocker.model.dao;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.net.ConnectivityManager.OnNetworkActiveListener;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.jawidmohammadi.applocker.model.entity.Attempt;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;
/**
 * This Interface retrieve all the data from the SQLite database that room created in our entity classes.
 */
@Dao
public interface AttemptDao {

  /**
   * This function add an attempt to the database.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Attempt attempt);

  /**
   * This function add a collections of attempts in the database.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Attempt> attempt);

  /**
   * this function update an attempt in the database.
   * @return a single attempt.
   */
  @Update
  Single<Integer> update(Attempt attempt);

  /**
   * this function delete an attempt from  the database/
   * @param attempt
   * @return
   */
  @Delete
  Single<Integer> delete(Attempt... attempt);

  /**
   * This query Select all the attempts.
   * @return attempts by there id order.
   */
  @Query("SELECT * FROM Attempt ORDER BY attempt_id")
  LiveData<List<Attempt>> select();

}
