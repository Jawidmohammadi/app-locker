package com.jawidmohammadi.applocker.controller.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * the setting view model extends viewModel and it provide live data to the ui fragments.
 */
public class SettingsViewModel extends ViewModel {


  private MutableLiveData<String> mText;

  public SettingsViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is Settings fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}

