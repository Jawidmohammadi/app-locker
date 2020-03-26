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
import io.reactivex.functions.Action;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * store and process the data  for the user interface and communicate with the repository
 */
public class AppsViewModel extends AndroidViewModel implements LifecycleObserver {

  private final AppRepository repository;
  private final MutableLiveData<Set<App>> apps;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;


  public AppsViewModel(@NonNull Application application) {
    super(application);
    repository = new AppRepository(application);
    apps = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  /**
   * this method return list of all our apps.
   */
  public LiveData<Set<App>> getApps() {
    return apps;
  }

  /**
   * this method refresh the status of our app
   */
  public void refreshApps() {
    pending.add(
        repository.getAll()
            .subscribe(
                apps::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * this method lock the apps
   * @param app it takes an app object
   * @param password it takes a string password
   */
  public void lock(App app, String password) {
    pending.add(
        repository.lock(app.getPkg(), password)
            .subscribe(
                this::refreshApps,
                throwable::postValue
            )
    );
  }

  /**
   * this method unlock the app
   * @param app takes an app object
   */
  public void unlock(App app) {
    pending.add(
        repository.remove(app)
            .subscribe(
                this::refreshApps,
                throwable::postValue
            )
    );
  }

  /**
   * this method unlock the applocker application it self for the first time when a user want to enter it.
   * @param app it takes an app object
   * @param password it takes a password type string.
   * @param action this is to unlock the app.
   */
  public void unlockOnce(App app, String password, Action action) {
    pending.add(
        repository.unlock(app, password)
            .subscribe(
                action::run,
                throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }

}
