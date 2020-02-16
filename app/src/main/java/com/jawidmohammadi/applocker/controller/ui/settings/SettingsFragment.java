package com.jawidmohammadi.applocker.controller.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.controller.ui.privacy.PrivacyViewModel;

public class SettingsFragment extends Fragment {

  private PrivacyViewModel settingsViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    settingsViewModel =
        ViewModelProviders.of(this).get(PrivacyViewModel.class);
    View root = inflater.inflate(R.layout.fragment_privacy, container, false);
    final TextView textView = root.findViewById(R.id.text_notifications);
    settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }

}
