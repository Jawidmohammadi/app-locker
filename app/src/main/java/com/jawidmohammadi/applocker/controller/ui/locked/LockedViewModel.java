package com.jawidmohammadi.applocker.controller.ui.locked;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LockedViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public LockedViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is dashboard fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}