package com.jawidmohammadi.applocker.controller.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.controller.ui.privacy.PrivacyViewModel;

public class SettingsFragment extends Fragment implements View.OnClickListener {

  private PrivacyViewModel settingsViewModel;
  private Button createPin;
  private Button createPattern;
  private Button createPassword;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
//    settingsViewModel =
//        ViewModelProviders.of(this).get(PrivacyViewModel.class);
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    view.findViewById(R.id.create_pin).setOnClickListener(this);
    view.findViewById(R.id.create_password).setOnClickListener(this);
    view.findViewById(R.id.create_pattern).setOnClickListener(this);
//    settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
////      @Override
////      public void onChanged(@Nullable String s) {
////        textView.setText(s);
////      }
    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.create_pin:
        break;
      case R.id.create_password:
        break;
      case R.id.create_pattern:
        break;
      default:
    }
    System.out.println("Button"); // TODO Create Functionality for each Button press.
  }
}
