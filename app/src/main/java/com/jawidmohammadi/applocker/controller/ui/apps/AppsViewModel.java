package com.jawidmohammadi.applocker.controller.ui.apps;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.jawidmohammadi.applocker.model.entity.App;
import com.jawidmohammadi.applocker.model.repository.AppRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.LinkedList;
import java.util.List;

// added this to view list of apps in recycler view

public class AppsViewModel extends AndroidViewModel implements LifecycleObserver {

  private final AppRepository repository;
  private final MutableLiveData<List<App>> apps;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public AppsViewModel(@NonNull Application application) {
    super(application);
    repository = new AppRepository(application);
    apps = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<List<App>> getApps() {
    return apps;
  }

  public void refreshApps() {
    List<App> apps = new LinkedList<>();
    pending.add(
        repository.getAll()
            .doFinally(() -> this.apps.postValue(apps))
            .forEach(apps::add)
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }

}
