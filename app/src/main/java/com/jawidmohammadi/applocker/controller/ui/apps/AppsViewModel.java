package com.jawidmohammadi.applocker.controller.ui.apps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppsViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public AppsViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is Apps fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}