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

@Dao
public interface AttemptDao {


  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Attempt attempt);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Attempt> attempt);


  @Update
  Single<Integer> update(Attempt attempt);

  @Delete
  Single<Integer> delete(Attempt... attempt);

  @Query("SELECT * FROM Attempt ORDER BY attempt_id")
  LiveData<List<Attempt>> select();

}
