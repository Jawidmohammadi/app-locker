package com.jawidmohammadi.applocker.services;

import androidx.lifecycle.LiveData;
import com.jawidmohammadi.applocker.model.entity.App;
import java.util.List;

public interface AppGetterInterface {

  public LiveData<List<App>> getAppList();
}
