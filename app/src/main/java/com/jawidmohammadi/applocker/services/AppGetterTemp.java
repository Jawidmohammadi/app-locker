package com.jawidmohammadi.applocker.services;

import androidx.lifecycle.LiveData;
import com.jawidmohammadi.applocker.model.entity.App;
import java.util.List;

public class AppGetterTemp implements  AppGetterInterface {

  private LiveData<List<App>> appList;

  @Override
  public LiveData<List<App>> getAppList() {
    return appList;
  }

}
