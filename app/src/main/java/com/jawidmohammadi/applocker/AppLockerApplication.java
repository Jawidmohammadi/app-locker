package com.jawidmohammadi.applocker;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.jawidmohammadi.applocker.model.repository.AppRepository;
import com.jawidmohammadi.applocker.services.AppLockerDatabase;
import com.jawidmohammadi.applocker.services.GoogleSignInService;
import io.reactivex.schedulers.Schedulers;

//this should extend Application
public class AppLockerApplication  extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    GoogleSignInService.setContext(this);
    AppLockerDatabase.setContext(this);
    AppLockerDatabase.getInstance().getApplicationDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }
}
