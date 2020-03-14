package com.jawidmohammadi.applocker.controller.ui.entry;

import android.content.Intent;
import android.content.SharedPreferences;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.controller.MainActivity;

public class EntryActivity extends AppCompatActivity {
private static final String HASHED_PIN = "pin";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    setContentView(R.layout.activity_entry);
    TextView actionMessage = findViewById(R.id.action_message);
    if (preferences.contains(HASHED_PIN)) {
      //TODO Change text of action message if there is already a pin setup.
    }

    findViewById(R.id.enter).setOnClickListener((view) -> {
      if (preferences.contains(HASHED_PIN)) {
        //TODO Store a new pin or  Check pin against stored hashed pin in shared preferences
        switchToMain();
      } else{
        //TODO Store the initial hashed pin.
        switchToMain();
      }
    });
  }

  private void switchToMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
