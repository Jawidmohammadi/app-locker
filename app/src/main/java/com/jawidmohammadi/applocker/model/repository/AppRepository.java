package com.jawidmohammadi.applocker.model.repository;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.util.Base64;
import android.util.Log;
import com.jawidmohammadi.applocker.model.entity.App;
import com.jawidmohammadi.applocker.model.entity.Attempt;
import com.jawidmohammadi.applocker.services.AppLockerDatabase;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class AppRepository {

  private final AppLockerDatabase database;
  private final Random rng;
  private final Context context;

  public AppRepository(Context context) {
    if (context == null) {
      throw new IllegalStateException();
    }
    this.context = context;
    rng = new SecureRandom();
    database = AppLockerDatabase.getInstance();
  }

  public void logAttempt(App app, Timestamp timestamp, String password) {
    Attempt attempt = new Attempt();
    attempt.setSuccess(true);
    attempt.setTimeStamp(timestamp);
    attempt.setApplicationId(app.getId());
    database.getAttemptDao().insert(attempt);
  }
  // TODO implement in app view model to invoke this methode in the repository to lock it.

  public Flowable<App> getAll() {
    PackageManager manager = context.getPackageManager();
    Set<App> appSet = new TreeSet<>();
    database.getApplicationDao().select()
        .subscribeOn(Schedulers.io())
        .subscribe(
            (apps) -> {
              for (App app : apps) {
                app.setLabel(manager.getApplicationLabel(
                    manager.getApplicationInfo(app.getPkg(), 0)).toString());
              }
              appSet.addAll(apps);
              appSet.addAll(buildAppPackageList());
            }
        );
    return Flowable.fromIterable(appSet);
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

  private List<App> buildAppPackageList() {
    PackageManager manager = context.getPackageManager();
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
    List<ResolveInfo> intents = manager.queryIntentActivities(intent, 0);
    List<App> apps = new ArrayList<>();
    for (ResolveInfo resInfo : intents) {
      try {
        App app = new App();
        app.setPkg(resInfo.activityInfo.packageName);
        app.setLabel(manager.getApplicationLabel(
            manager.getApplicationInfo(resInfo.activityInfo.packageName, 0)).toString());
        apps.add(app);
      } catch (NameNotFoundException e) {
        Log.e(getClass().getName(), "Can't add app to list", e);
      }
    }
//    Collections.sort(apps);
    return apps;
  }

}
