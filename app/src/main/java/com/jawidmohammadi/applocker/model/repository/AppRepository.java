package com.jawidmohammadi.applocker.model.repository;

import android.app.Application;
import android.provider.Settings.Secure;
import android.util.Base64;
import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.jawidmohammadi.applocker.model.entity.App;
import com.jawidmohammadi.applocker.model.entity.Attempt;
import com.jawidmohammadi.applocker.services.AppGetterInterface;
import com.jawidmohammadi.applocker.services.AppLockerDatabase;
import com.jawidmohammadi.applocker.services.GetAppService;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class AppRepository {

  private final AppLockerDatabase database;
//  private final AppGetterInterface appService;

  private Random rng;
  private static Application context;



  private AppRepository() {
    if (context == null) {
      throw new IllegalStateException();
    }
    rng = new SecureRandom();
    database = AppLockerDatabase.getInstance();
  }
// change to a login function instead of a login attempt.

  public void logAttempt(App app, Timestamp timestamp, String password) {
    Attempt attempt = new Attempt();
    attempt.setSuccess(true);
    attempt.setTimeStamp(timestamp);
    attempt.setApplicationId(app.getId());
    database.getAttemptDao().insert(attempt);
  }

  public LiveData<List<App>> getAll() {

    return database.getApplicationDao().select();
  }

  public Completable lock(String pkg, String password) throws NoSuchAlgorithmException {
    return Completable.fromSingle(
        database.getApplicationDao().select(pkg)
            .subscribeOn(Schedulers.io())
            .map((app) -> {
              throw new IllegalArgumentException();
            })
            .switchIfEmpty(Single.fromCallable(() -> {
              App app = new App();
              app.setPkg(pkg);
              MessageDigest digest = MessageDigest.getInstance("SHA-256");
              digest.update(password.getBytes());
              byte[] saltBytes = new byte[16];
              rng.nextBytes(saltBytes);
              digest.update(saltBytes);
              String hashedPassword = Base64
                  .encodeToString(digest.digest(), Base64.NO_PADDING | Base64.NO_WRAP);
              String salt = Base64.encodeToString(saltBytes, Base64.NO_PADDING | Base64.NO_WRAP);
              app.setHashedPassword(hashedPassword);
              app.setSalt(salt);
              database.getApplicationDao().insert(app)
                  .doOnSuccess(app::setId)
                  .subscribe();
              return app;
            }))
    );
  }
  // TODO implement in app view model to invoke this methode in the repository to lock it.

  public static void setContext(Application context) {
    AppRepository.context = context;
  }


  public static AppRepository getInstance() {
    return InstanceHolder.INSTANCE();
  }

  private static class InstanceHolder {

    private static final AppRepository INSTANCE() {
      return new AppRepository();
    }
  }

}
