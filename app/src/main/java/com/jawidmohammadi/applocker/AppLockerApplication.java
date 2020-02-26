package com.jawidmohammadi.applocker;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.jawidmohammadi.applocker.model.repository.AppRepository;
import com.jawidmohammadi.applocker.services.AppLockerDatabase;
import io.reactivex.schedulers.Schedulers;

//this should extend Application
public class AppLockerApplication  extends Application {
//Overriding onCreate
// whenever we override we should look at the documentation "it says in the documentaition If you override this method, be sure to call super.onCreate()., so we will keep it"
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    AppRepository.setContext(this);
    AppLockerDatabase.setContext(this);
    AppLockerDatabase.getInstance().getApplicationDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }
}
