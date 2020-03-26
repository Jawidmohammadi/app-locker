package com.jawidmohammadi.applocker.controller.ui.privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jawidmohammadi.applocker.R;

public class PrivacyFragment extends Fragment implements OnClickListener {

  private Button lockFrequency;
  private CheckBox hidePatternTrail;
  private CheckBox patternVibration;
  private CheckBox emailSecurity;


  private PrivacyViewModel privacyViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_privacy, container, false);
    view.findViewById(R.id.lock_frequency).setOnClickListener(this);
    view.findViewById(R.id.hide_pattern_trail).setOnClickListener(this);
    view.findViewById(R.id.pattern_vibration).setOnClickListener(this);
    view.findViewById(R.id.security_email).setOnClickListener(this);
    return view;
  }

  @Override
  public void onClick(View view) {
    boolean checked = ((CheckBox) view).isChecked();
    switch (view.getId()) {
      case R.id.hide_pattern_trail:
        if (checked) {

        } else {

        }
        break;
      case R.id.pattern_vibration:
        if (checked) {

        } else {

        }
        break;
      case R.id.security_email:
        if (checked){

        } else {

        } break;
      default:
    }
  }


}